//package com.soft1851.springbootjpaadvance.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
//import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
//import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import javax.sql.DataSource;
//import java.util.Map;
//
///**
// * @Description TODO
// * @Author 涛涛
// * @Date 2020/5/14 22:00
// * @Version 1.0
// **/
//@Configuration
//public class DataSourceConfig {
//    @Autowired
//    private JpaProperties jpaProperties;
//    @Autowired
//    private HibernateProperties hibernateProperties;
//
//    @Bean(name = "vendorProperties")
//    public Map<String,Object> getVendorProperties(){
//        return hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
//    }
//
//    @Bean(name = "primaryDataSource")
//    @Primary
//    @ConfigurationProperties("spring.datasource.primary")
//    public DataSource firstDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "secondaryDataSource")
//    @ConfigurationProperties("spring.datasource.secondary")
//    public DataSource secondDataSource (){
//        return DataSourceBuilder.create().build();
//    }
//}
