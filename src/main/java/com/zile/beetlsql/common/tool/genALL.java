package com.zile.beetlsql.common.tool;


import org.beetl.sql.core.*;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.core.mapper.BaseMapper;
import org.beetl.sql.ext.DebugInterceptor;
import org.beetl.sql.ext.gen.GenConfig;
import org.beetl.sql.ext.gen.GenFilter;
import org.beetl.sql.ext.gen.MapperCodeGen;

import java.io.File;

/**
 * Created by zileShi on 2019/7/1 0001.
 **/
public class genALL {
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
        if (path.contains("springboot-BeetlSQL/")) {
            path = path.substring(0, path.indexOf("springboot-BeetlSQL/")) + "springboot-BeetlSQL/";
        }
        path = path.replace("/C:/", "C:/").replace("/D:/", "D:/").replace("/E:/", "E:/").replace("/F:/", "F:/").replace("/G:/", "G:/").replace("%20", " ").replace("file:", "");

        path = path + "resources/sql";

        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        SQLLoader loader = new ClasspathLoader("sql");
        // 数据库命名跟java命名一样，所以采用DefaultNameConversion，还有一个是UnderlinedNameConversion，下划线风格的，
        UnderlinedNameConversion unc = new  UnderlinedNameConversion();
        // 最后，创建一个SQLManager,DebugInterceptor 不是必须的，但可以通过它查看sql执行情况

        SQLManager sqlManager = new SQLManager(mysql,loader,source,unc, new Interceptor[]{new DebugInterceptor()});
//        sqlManager.genPojoCodeToConsole("bg_admin"); //快速生成，显示到控制台
        // 或者直接生成java文件
        //pojo代码生成配置
        GenConfig config = new GenConfig();
        //忽略表前缀bg_admin默认为BgAdmin，忽略bg为Admin
        // config.setIgnorePrefix("bg");
        //dao代码生成配置,无参构造会有默认的模板

        //有参构造出传入的是dao生成所在的包，但是没有模板，生成后是空白
        MapperCodeGen mapper = new MapperCodeGen("com.zile.beetlsql.mapper");
        mapper.setMapperTemplate(new GenConfig().getTemplate("/org/beetl/sql/ext/gen/mapper.btl"));
        //添加Dao代码生成
        config.codeGens.add(mapper);
        sqlManager.setBaseMapper(BaseMapper.class);
        //生成所有，慎用，会覆盖
        //sqlManager.genALL("szsti.build.data.model", config, null);

        //又选择的生成，指定需要生成代码的数据库表名
        sqlManager.genALL("com.zile.beetlsql.model", config, new GenFilter(){
            @Override
            public boolean accept(String tableName){
                if(tableName.equalsIgnoreCase("user")){
                    return true;
                }else {
                    return false;
                }
            }
        });

    }
}
