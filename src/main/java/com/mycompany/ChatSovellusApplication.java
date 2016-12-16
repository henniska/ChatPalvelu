package com.mycompany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.mycompany","com.mycompany.config"})
public class ChatSovellusApplication {

    public static void main(String[] args) {
         SpringApplication.run(ChatSovellusApplication.class, args);
    }
}
