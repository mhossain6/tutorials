package com.example.restclient.Client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class HTTPRestClient {

    @Value("${SERVICE_URL}")
    String url;

    Logger logger = LoggerFactory.getLogger(HTTPRestClient.class);

    public void callRestService() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();

        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        logger.info("Response from server is {}", response.body());

    }


    public void callRestServiceAsync() throws Exception {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();

        HttpClient httpClient = HttpClient.newBuilder().build();

        CompletableFuture<HttpResponse<String>> response = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        response.thenApply( (resp)  -> {
                    logger.info("Response from server is {}", resp.body());
                    return resp.body();
                });

    }

    public void callRestServiceAsyncViaThreadPool() throws Exception {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> response1 = HttpClient.newBuilder()
                .executor(executorService)
                .build()
                .sendAsync(request, HttpResponse.BodyHandlers.ofString());

        response1.thenApply((resp)  -> {
            logger.info("Response from server is {}", resp.body());
            return resp.body();
        });

    }

}
