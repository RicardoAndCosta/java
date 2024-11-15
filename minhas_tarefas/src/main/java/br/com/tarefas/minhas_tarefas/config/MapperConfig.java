package br.com.tarefas.minhas_tarefas.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
   
   @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
