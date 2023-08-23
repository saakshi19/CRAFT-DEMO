package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Clients {

    private ObjectMapper objectMapper;

//    @Value("${dms-service.url}")
//    private String dmsServiceEndpoint;
//
//    @Value("${dms-service.connection-timeout}")
//    private int dmsConnectionTimeout;
//
//    @Value("${dms-service.read-timeout}")
//    private int dmsReadTimeout;
//
//    Request.Options options = new Request.Options(dmsConnectionTimeout, dmsReadTimeout);
//
//    @Bean
//    public HttpDegradationServiceClient dmsDegradationsClient() {
//        return Feign.builder()
//                .encoder(new JacksonEncoder(objectMapper))
//                .decoder(new JacksonDecoder(objectMapper))
//                .options(options)
//                .contract(new Contract.Default())
//                .target(HttpDegradationServiceClient.class, dmsServiceEndpoint);
//    }
}
