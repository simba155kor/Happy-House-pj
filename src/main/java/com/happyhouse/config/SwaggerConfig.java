package com.happyhouse.config;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//http://localhost:9999/swagger-ui.html
	
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	private String version = "Bug 없는 HappyHouse";
	private String title = "Final project" + version;

	@Bean
	public Docket api() {
		List<ResponseMessage> responseMessages = new ArrayList<ResponseMessage>();
		responseMessages.add(new ResponseMessageBuilder().code(200).message("OK !!!").build());
		responseMessages.add(new ResponseMessageBuilder().code(500).message("서버 문제 발생 !!!").responseModel(new ModelRef("Error")).build());
		responseMessages.add(new ResponseMessageBuilder().code(404).message("페이지를 찾을 수 없습니다 !!!").build());
		return new Docket(DocumentationType.SWAGGER_2).consumes(getConsumeContentTypes()).produces(getProduceContentTypes())
					.apiInfo(apiInfo()).groupName(version).select()
					.apis(RequestHandlerSelectors.basePackage("com.happyhouse.controller"))
					.paths(PathSelectors.ant("/**")).build()
					.useDefaultResponseMessages(false)
					.globalResponseMessage(RequestMethod.GET,responseMessages);
	}
	
	private Set<String> getConsumeContentTypes() {
        Set<String> consumes = new HashSet<String>();
        consumes.add("application/json;charset=UTF-8");
        consumes.add("application/xml;charset=UTF-8");
        consumes.add("application/x-www-form-urlencoded");
        return consumes;
    }

    private Set<String> getProduceContentTypes() {
        Set<String> produces = new HashSet<String>();
        produces.add("application/json;charset=UTF-8");
        return produces;
    }
	
//	private Predicate<String> postPaths() {
////		return PathSelectors.any();
////		return or(regex("/admin/.*"), regex("/user/.*"));
////		return regex("/book/**");
//		return regex("/book/**");
//	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(title)
				.description("<h3>SSAFY API Reference for Developers</h3>Swagger를 이용한 tBook API<br><img src=\"img/ssafy_logo.png\" width=\"150\">") 
				.contact(new Contact("SSAFY", "https://edu.ssafy.com", "ssafy@ssafy.com"))
				.license("SSAFY License")
				.licenseUrl("https://www.ssafy.com/ksp/jsp/swp/etc/swpPrivacy.jsp")
				.version("1.0").build();

	}

}


