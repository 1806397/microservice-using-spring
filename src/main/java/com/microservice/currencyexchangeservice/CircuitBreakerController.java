package com.microservice.currencyexchangeservice;

import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {
    private Logger logger= LoggerFactory.getLogger(CircuitBreakerController.class);
    @GetMapping("/sample-api")
    @Retry(name = "sample-api",fallbackMethod = "hardcodedResponse")
    public String SampleAPI(){
        logger.info("Sample API call received");
        ResponseEntity<String> responseEntity=new  RestTemplate().getForEntity("http://localhost:8080/some-dummy",String.class);
        return responseEntity.getBody();
    }
    public String hardCodedResponse(Exception ex){
        return "fallbackResponse";
    }
}
