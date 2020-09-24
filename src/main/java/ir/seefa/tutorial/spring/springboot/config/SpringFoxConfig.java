package ir.seefa.tutorial.spring.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 10 Sep 2020 T 18:43:38
 */
// 1. define configuration bean for Swagger2 API
@Configuration
// 2. Enabling Swagger2 API
@EnableSwagger2
public class SpringFoxConfig {

    // 3. define bean about application information in Swagger UI
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ir.seefa.tutorial.spring.springboot.controller"))    // Inform API base package
                .paths(PathSelectors.regex("/.*"))
                .build()
                .apiInfo(apiEndpointsInfo())
                .host("seefa.ir:9090");
    }

    // 4. define general summary application information
    private ApiInfo apiEndpointsInfo() {
        return new ApiInfoBuilder()
                .description("Seefa Swagger2 UI with Spring Boot Rest JPA CRUD tutorial")
                .contact(new Contact("Seefa", "http://seefa.ir", "info@seefa.ir"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0")
                .title("spring-boot-rest-jpa-swagger2-tutorial")
                .build();
    }
}
