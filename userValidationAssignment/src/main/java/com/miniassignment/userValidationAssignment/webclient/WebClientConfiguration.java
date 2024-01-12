package com.miniassignment.userValidationAssignment.webclient;

import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

//Web client configurations
@Configuration
public class WebClientConfiguration {


// Random user Webclient
    @Bean
    public WebClient randomUserWebClient() {
        HttpClient httpClient = HttpClient.create()
                .responseTimeout(java.time.Duration.ofMillis(2000))
                .doOnConnected(connection -> connection
                        .addHandlerLast(new ReadTimeoutHandler(2000, TimeUnit.MILLISECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(2000, TimeUnit.MILLISECONDS)));

        return WebClient.builder()
                .baseUrl("https://randomuser.me/api/")
                .clientConnector(new ReactorClientHttpConnector(httpClient)).build();

    }

// Nationality Webclient
    @Bean
    public WebClient userNationalityWebClient() {
        HttpClient httpClient = HttpClient.create()
                .responseTimeout(java.time.Duration.ofMillis(1000))
                .doOnConnected(connection -> connection
                        .addHandlerLast(new ReadTimeoutHandler(1000, TimeUnit.MILLISECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(1000, TimeUnit.MILLISECONDS)));
        return WebClient.builder()
                .baseUrl("https://api.nationalize.io/")
                .clientConnector(new ReactorClientHttpConnector(httpClient)).build();
    }

//  Gender webclient
    @Bean
    public WebClient userGenderWebClient() {

        HttpClient httpClient = HttpClient.create()
                .responseTimeout(java.time.Duration.ofMillis(1000))
                .doOnConnected(connection -> connection
                        .addHandlerLast(new ReadTimeoutHandler(1000, TimeUnit.MILLISECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(1000, TimeUnit.MILLISECONDS)));

        return WebClient.builder()
                .baseUrl("https://api.genderize.io/")
                .clientConnector(new ReactorClientHttpConnector(httpClient)).build();
    }
}
