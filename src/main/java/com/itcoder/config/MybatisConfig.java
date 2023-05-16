package com.itcoder.config;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@Import({JDBCConfig.class})
@ComponentScan("com.itcoder.mapper")
public class MybatisConfig  {
    @Bean
    public PageInterceptor getPageInterceptor(){
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("value","true");
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }
    @Bean
    public SqlSessionFactoryBean getSqlSessionFactoryBean(@Autowired DataSource dataSource,@Autowired PageInterceptor pageInterceptor) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        Interceptor[] plugins = {pageInterceptor};
        factoryBean.setPlugins(plugins);
        //开启驼峰命名规则
        factoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        //配置读取mapper的xml文件
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/itcoder/mapper/*.xml"));
        return factoryBean;
    }

    /**
     * 定义mybatis的映射扫描
     */
    @Bean
    public MapperScannerConfigurer getMapperScannerConfigurer(){
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("com.itcoder.mapper");
        return configurer;
    }
}
