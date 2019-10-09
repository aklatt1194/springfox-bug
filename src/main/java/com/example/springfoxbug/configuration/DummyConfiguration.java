package com.example.springfoxbug.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.view.RedirectView;

@Configuration
public class DummyConfiguration {

    @Bean
    Jackson2JsonEncoder jackson2JsonEncoder(ObjectMapper mapper) {
        mapper.registerModule(new Jdk8Module());
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return new Jackson2JsonEncoder(mapper);
    }

    @Bean
    Jackson2JsonDecoder jackson2JsonDecoder(ObjectMapper mapper) {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        return new Jackson2JsonDecoder(mapper);
    }

    @Bean
    WebFluxConfigurer webFluxConfigurer(Jackson2JsonEncoder encoder, Jackson2JsonDecoder decoder) {
        return new WebFluxConfigurer() {

            @Override
            public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
                configurer.defaultCodecs().jackson2JsonEncoder(encoder);
                configurer.defaultCodecs().jackson2JsonDecoder(decoder);
            }

            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {

                registry.addResourceHandler("/swagger-ui.html**")
                        .addResourceLocations("classpath:/META-INF/resources/");

                registry.addResourceHandler("/webjars/**")
                        .addResourceLocations("classpath:/META-INF/resources/webjars/");
            }
        };
    }

    /*@Bean
    DocumentationPluginsBootstrapper swaggerDocumentationStarter(DocumentationPluginsManager documentationPluginsManager,
                                                                 List<RequestHandlerProvider> handlerProviders,
                                                                 DocumentationCache scanned,
                                                                 ApiDocumentationScanner resourceListing,
                                                                 TypeResolver typeResolver,
                                                                 Defaults defaults,
                                                                 PathProvider pathProvider,
                                                                 Environment environment) {
        return new DocumentationPluginsBootstrapper(documentationPluginsManager, handlerProviders, scanned, resourceListing, typeResolver, defaults, pathProvider, environment);
    }*/

    @Controller
    public class SwaggerRootController {
        /**
         * Reroute the main page to swagger-ui.
         *
         * @return
         */
        @RequestMapping(value = "/", method = RequestMethod.GET)
        public RedirectView rootPage() {
            return new RedirectView("swagger-ui.html");
        }

    }

}
