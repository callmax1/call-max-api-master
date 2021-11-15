package com.oxycreation;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@EnableCaching(proxyTargetClass = true)
public class CallMaxApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CallMaxApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(CallMaxApplication.class, args);
    }
}

