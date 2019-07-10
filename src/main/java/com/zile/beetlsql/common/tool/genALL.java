package com.zile.beetlsql.common.tool;


import freemarker.template.DefaultObjectWrapper;
import org.beetl.sql.core.*;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.core.mapper.BaseMapper;
import org.beetl.sql.ext.DebugInterceptor;
import org.beetl.sql.ext.gen.GenConfig;
import org.beetl.sql.ext.gen.GenFilter;
import org.beetl.sql.ext.gen.MapperCodeGen;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by zileShi on 2019/7/1 0001.
 **/
public class genALL {

    //项目名---必填
    private static final String ProjectName = "springboot-BeetlSQL";

    //包路径---必填
    private static final String BasePackageName = "com.zile.beetlsql";

    //对应的数据库的表名---必填
    private static final String TableName = "department";


    public static void main(String[] args) throws Exception {

        //MySql数据库
        ConnectionSource source = ConnectionSourceHelper.getSimple("com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://127.0.0.1:3306/test_demo?serverTimezone=UTC&characterEncoding=utf8&useUnicode=true&useSSL=false",
                "root",
                "root");
        //ConnectionSource source = ConnectionSourceHelper.getSimple("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@172.17.8.33:1521:ORCL", "build", "build93$org");
        DBStyle mysql = new MySqlStyle();

        //获取当前路径
        String path = genALL.class.getClassLoader().getResource("").getPath();
        if (path.contains(ProjectName + "/")) {
            path = path.substring(0, path.indexOf(ProjectName + "/")) + ProjectName + "/";
        }
        path = path.replace("/C:/", "C:/").replace("/D:/", "D:/").replace("/E:/", "E:/").replace("/F:/", "F:/").replace("/G:/", "G:/").replace("%20", " ").replace("file:", "");

        String path2 = path;

        path = path + "resources/sql";

        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        SQLLoader loader = new ClasspathLoader("sql");
        // 数据库命名跟java命名一样，所以采用DefaultNameConversion，还有一个是UnderlinedNameConversion，下划线风格的，
        UnderlinedNameConversion unc = new UnderlinedNameConversion();
        // 最后，创建一个SQLManager,DebugInterceptor 不是必须的，但可以通过它查看sql执行情况

        SQLManager sqlManager = new SQLManager(mysql, loader, source, unc, new Interceptor[]{new DebugInterceptor()});
//        sqlManager.genPojoCodeToConsole("bg_admin"); //快速生成，显示到控制台
        // 或者直接生成java文件
        //pojo代码生成配置
        GenConfig config = new GenConfig();
        //忽略表前缀bg_admin默认为BgAdmin，忽略bg为Admin
        // config.setIgnorePrefix("bg");
        //dao代码生成配置,无参构造会有默认的模板

        //有参构造出传入的是dao生成所在的包，但是没有模板，生成后是空白
        MapperCodeGen mapper = new MapperCodeGen(BasePackageName + ".mapper");
        mapper.setMapperTemplate(new GenConfig().getTemplate("/org/beetl/sql/ext/gen/mapper.btl"));
        //添加Dao代码生成
        config.codeGens.add(mapper);
        sqlManager.setBaseMapper(BaseMapper.class);
        //生成所有，慎用，会覆盖
        //sqlManager.genALL("szsti.build.data.model", config, null);

        //又选择的生成，指定需要生成代码的数据库表名
        sqlManager.genALL(BasePackageName + ".model", config, new GenFilter() {
            @Override
            public boolean accept(String tableName) {
                if (tableName.equalsIgnoreCase(TableName)) {
                    return true;
                } else {
                    return false;
                }
            }
        });

        //生成对应的service和controller
        Date date = new Date(); //获取当前的系统时间。
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss"); //使用了默认的格式创建了一个日期格式化对象。
        String time = dateFormat.format(date);

        try {
            //设置模板参数
            Map root = new HashMap();
            root.put("BasePackageName", BasePackageName);
            root.put("ClassName", upperCase(TableName));
            root.put("Date", time);

            //freemarker配置
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
            //设置模板对应的文件夹路径
            cfg.setDirectoryForTemplateLoading(new File(path2 + "\\src\\main\\resources\\ftls"));
            cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_28));
            Template serviceTemp = cfg.getTemplate("ServiceImpl.ftl");
            Template controllerTemp = cfg.getTemplate("Controller.ftl");
            //生成service的文件名
            String serviceName = upperCase(TableName) + "Service.java";
            //生成service的文件名
            String controllerName = upperCase(TableName) + "Controller.java";

            //生成文件
            File javaServiceFile = new File(path2 + "src/main/java/" + replacePath(BasePackageName) + "/service/" + serviceName);
            File javaControllerFile = new File(path2 + "src/main/java/" + replacePath(BasePackageName) + "/controller/" + controllerName);


            FileWriter fwS = new FileWriter(javaServiceFile);
            FileWriter fwC = new FileWriter(javaControllerFile);

            BufferedWriter bwS = new BufferedWriter(fwS);
            BufferedWriter bwC = new BufferedWriter(fwC);

            //写入对应的模板
            serviceTemp.process(root, bwS);
            controllerTemp.process(root, bwC);

            bwS.flush();
            bwC.flush();
            bwS.close();
            bwC.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    /**
     * 把 .替换成/
     *
     * @param path
     * @return
     */
    public static String replacePath(String path) {
        return path.replace(".", "/");
    }

    /**
     * 首字母大写
     *
     * @param str
     * @return
     */
    public static String upperCase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }


}
