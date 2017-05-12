package de.maeddes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication

public class ServiceNumberIncrementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceNumberIncrementApplication.class, args);
	}
}

@RestController
class NumberIncrement{

    private int counter = 0;

    @RequestMapping("/")
    public String getNumber(){

        return "New Number: "+counter++;

    }

}