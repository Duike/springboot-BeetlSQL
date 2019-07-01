package com.zile.beetlsql.common.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

/**
 * Created by zileShi on 2019/7/1 0001.
 **/
@Configuration
public class BeetlSQLDataSourceConfig {

    /**
     * 引入application的数据库设置
     * @param env
     * @return
     */
    @Bean(name = "datasource")
    public DataSource dataSource(Environment env){
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(env.getProperty("spring.datasource.url"));
        ds.setUsername(env.getProperty("spring.datasource.username"));
        ds.setPassword(env.getProperty("spring.datasource.password"));
        ds.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        return ds;
    }

}
