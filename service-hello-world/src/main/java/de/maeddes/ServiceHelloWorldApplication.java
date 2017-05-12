package de.maeddes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceHelloWorldApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceHelloWorldApplication.class, args);
	}
}

@RestController
@RefreshScope
class SimpleController {

    @Value("${CF_INSTANCE_INDEX:0}")
    private int instance;

    @Value("${message:Hello default}")
    private String message;

    @Value("${spring.application.name}")
    private String appName;

    @RequestMapping("/")
    public String index() {
        return "Hello, bootiful World! Instance: "+instance;
    }

    @RequestMapping("/crash")
    public String crash() {
        System.exit(0);
        return "Hello, bootiful World!";
    }

    @RequestMapping("/message")
    String getMessage() {
        return this.message;
    }

    @RequestMapping("/appName")
    String getAppName() {
        return this.appName;
    }

}
