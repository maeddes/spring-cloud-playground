package de.maeddes.SimpleEurekaClient;

import org.springframework.beans.factory.annotation.Autowired;
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
@RestController
@EnableDiscoveryClient
public class SimpleEurekaClientApplication {

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



	public static void main(String[] args) {
		SpringApplication.run(SimpleEurekaClientApplication.class, args);
	}
}
