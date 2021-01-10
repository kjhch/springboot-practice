package com.hch.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;

/**
 * springboot配置类：
 * <p></p>
 * {@link EnableConfigurationProperties @EnableConfigurationProperties}的作用
 * 是使标注了{@link org.springframework.boot.context.properties.ConfigurationProperties @ConfigurationProperties}的类生效，
 * 例如下面的CustomProperties类。因为spring只会掌管受ioc容器控制的类，而ConfigurationProperties不具备component的效果
 * <p></p>
 * {@link ImportResource @ImportResource}的作用是是导入一个xml配置文件等价于{@code <import resource="classpath:application-context.xml"/>}
 * <p></p>
 *
 * @author hch
 */
@Configuration
@EnableKafka
@EnableSwagger2
@EnableConfigurationProperties(CustomProperties.class)
@ImportResource(locations = "classpath:application-context.xml")
@ServletComponentScan("com.hch.controller")  // 扫描标注了WebServlet注解的类
public class AppConfig {

    @Bean
    public LocalSessionFactoryBean localSessionFactoryBean(DataSource dataSource) {
        LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean;
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hch.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("springboot利用swagger构建api文档")
                .description("简单优雅的restfun风格，https://gitee.com/ataide2/WarReport")
                .termsOfServiceUrl("https://gitee.com/ataide2/WarReport")
                .version("1.0")
                .build();
    }

}
