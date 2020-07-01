package com.aran.tech.managementArea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.aran.tech.managementArea.property.ApplicationProperties;
import com.aran.tech.managementArea.property.FileStorageProperties;
import com.aran.tech.managementArea.property.ImageProperties;

@SpringBootApplication
@EnableConfigurationProperties({
	FileStorageProperties.class ,
	ApplicationProperties.class ,
	ImageProperties.class
})
public class ManagementAreaApplication {

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public static void  main(String[] args) {
        SpringApplication.run(ManagementAreaApplication.class, args);
    }
}
