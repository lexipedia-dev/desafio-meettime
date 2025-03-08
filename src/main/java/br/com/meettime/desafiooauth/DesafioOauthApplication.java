package br.com.meettime.desafiooauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DesafioOauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(DesafioOauthApplication.class, args);
    }

}
