package com.pierce.data.config.ds;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Project : data
 * @Package Name : com.pierce.data.config.ds
 * @Description: d_dashboard库配置
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-09-04
 */
@Configuration
@MapperScan(basePackages = "com.pierce.data.dao.dashboard", sqlSessionFactoryRef = "ssfDashboard")
public class DashboardConfig {

    @Autowired
    @Qualifier("dDashboard")
    private DataSource dDashboard;

    @Bean
    public SqlSessionFactory ssfDashboard() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dDashboard); // 使用dDashboard数据源, 连接d_dashboard库
        factoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:mappers/dashboard/*.xml")
        );
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sstDashboard() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(ssfDashboard()); // 使用上面配置的Factory
        return template;
    }

    @Bean
    public DataSourceTransactionManager testTransactionManager() {
        return new DataSourceTransactionManager(dDashboard);
    }
}
