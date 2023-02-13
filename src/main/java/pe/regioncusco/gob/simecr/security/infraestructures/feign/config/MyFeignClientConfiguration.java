package pe.regioncusco.gob.simecr.security.infraestructures.feign.config;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class MyFeignClientConfiguration {
    @Bean
    public ErrorDecoder errorDecoder(){
        return new CustomErrorDecoder();
    }
}
