package de.maeddes.cloudyclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudyClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudyClientApplication.class, args);
	}
}

@RestController
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

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/serviceList")
    public String serviceList(){

        return this.discoveryClient.getServices().toString();
    }

    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }


}
