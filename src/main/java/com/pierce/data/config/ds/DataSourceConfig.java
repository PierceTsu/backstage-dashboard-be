package com.pierce.data.config.ds;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @Project : data
 * @Package Name : com.pierce.data.config
 * @Description: 多数据源配置
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-09-04
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = "dDashboard")
    @ConfigurationProperties(prefix = "spring.datasource.dDashboard")
    public DataSource dDashboard() {
        return new DruidDataSource();
    }

    @Bean(name = "dStatConf")
    @ConfigurationProperties(prefix = "spring.datasource.dStatConf")
    public DataSource dStatConf() {
        return new DruidDataSource();
    }
}
