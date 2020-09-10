package com.example.demo.configs.hessian;

import com.example.demo.service.abstraction.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.remoting.support.RemoteExporter;

@Configuration
public class HessianConfig {

    @Autowired
    private BookService bookService;

    @Bean(name = "/interconnect/bookService")
    public RemoteExporter remoteExporter() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(bookService);
        exporter.setServiceInterface(BookService.class);
        return exporter;
    }
}
