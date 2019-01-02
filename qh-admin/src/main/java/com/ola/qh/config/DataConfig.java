package com.ola.qh.config;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.MultipartConfigElement;
import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.base.Predicate;
import com.ola.qh.util.SpringContextUtils;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@PropertySource("classpath:application.properties")
public class DataConfig
{

    private String get(String key) throws IOException
    {
	Properties prop = new Properties();
	prop.load(DataConfig.class.getClassLoader().getResourceAsStream("application.properties"));
	return prop.getProperty(key);
    }
    
    
    @Bean
	public SqlSessionFactoryBean mybatisSqlSession() throws Exception{
		SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
		ssfb.setDataSource(dataSource());
		ssfb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:com/ola/qh/**/mapper/*Mapper.xml"));
		return ssfb;
	}
    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大KB,MB
        factory.setMaxFileSize("1024MB");
        //设置总上传数据总大小
        factory.setMaxRequestSize("1024MB");
        return factory.createMultipartConfig();
    }

    
   /* @Bean
	public MapperScannerConfigurer mapperScanner(){
		MapperScannerConfigurer msc = new MapperScannerConfigurer();
		msc.setBasePackage("com.ola.qh.**.dao");
		return msc;
	}*/

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public DataSource dataSource() throws IOException
    {
	DruidDataSource ds = new DruidDataSource();
	System.out.println(get("spring.datasource.url"));
	ds.setUrl(get("spring.datasource.url"));
	ds.setUsername(get("spring.datasource.username"));
	ds.setPassword(get("spring.datasource.password"));
	ds.setDriverClassName(get("spring.datasource.driver-class-name"));
	ds.setMaxActive(Integer.parseInt(get("druid.max.active")));
	return ds;
    }

    /*
     * @Bean public PlatformTransactionManager transactionManager() throws IOException{ return new DataSourceTransactionManager(dataSource()); }
     */
    // 通过beanid获取实例
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public SpringContextUtils getSpringContextUtils()
    {
	return new SpringContextUtils();
    }

    @Bean
    public Docket api()
    {
	return new Docket(DocumentationType.SWAGGER_2).select() // 选择那些路径和api会生成document
		.apis(RequestHandlerSelectors.any()) // 对所有api进行监控
		.paths(paths()) // 对所有路径进行监控
		.build();
    }

    private Predicate<String> paths()
    {
	return or(regex("/api/goods.*"), regex("/api/cart.*"), regex("/api/logistic.*"), regex("/api/sms.*"), regex("/api/email.*"), regex("/api/message.*"), regex("/api/order.*"), regex("/api/user.*"));
    }
}
