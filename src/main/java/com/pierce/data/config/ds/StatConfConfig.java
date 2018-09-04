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
 * @Description: d_stat_conf库配置
 * @Author : piercetsu@gmail.com
 * @Create Date: 2018-09-04
 */
@Configuration
@MapperScan(basePackages = "com.pierce.data.dao.statconf", sqlSessionFactoryRef = "ssfStatConf")
public class StatConfConfig {

    @Autowired
    @Qualifier("dStatConf")
    private DataSource dStatConf;

    @Bean
    public SqlSessionFactory ssfStatConf() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dStatConf); // 使用dStatDb数据源, 连接d_stat_conf库
        factoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:mappers/statconf/*.xml")
        );
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sstStatConf() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(ssfStatConf()); // 使用上面配置的Factory
        return template;
    }

    @Bean
    public DataSourceTransactionManager testTransactionManager() {
        return new DataSourceTransactionManager(dStatConf);
    }
}
