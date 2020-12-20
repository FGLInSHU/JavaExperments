package com.fuguoliang.druiddemo.demo.Configs;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
/**
 * @author FGL_S
 */
@Configuration
public class DatasourceConfig {
    @Bean(name= "userDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.userdb")
    DataSource userDatasource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "userSqlSessionFactory")
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("userDatasource")
                                                              DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        final Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*Mapper.xml");
        bean.setMapperLocations(resources);
        return bean.getObject();
    }

}
