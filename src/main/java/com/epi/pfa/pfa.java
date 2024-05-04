package com.epi.pfa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;

@SpringBootApplication

public class pfa extends SpringBootServletInitializer {

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(pfa.class, args);
        openTabs();
    }

    private static void openTabs() throws IOException, InterruptedException {
        Runtime rt = Runtime.getRuntime();
        rt.exec("rundll32 url.dll,FileProtocolHandler " + "http://localhost:8080/swagger-ui/index.html?continue");
    }


}