package com.entando.apiman.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {
    @Bean
    public WebClient webclient() {
      
        WebClient webClient = WebClient
                .builder()
                .baseUrl("http://localhost:8080")
                .defaultCookie("cookieKey", "cookieValue")
              //  .defaultHeaders(header -> header.setBasicAuth(userName, password))
                .defaultHeaders(header -> header.setBearerAuth("eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJGSjg2R2NGM2pUYk5MT2NvNE52WmtVQ0lVbWZZQ3FvcXRPUWVNZmJoTmxFIn0.eyJleHAiOjE2NjMyODk3MzAsImlhdCI6MTY2MzI4OTQ5MCwiYXV0aF90aW1lIjoxNjYzMjg5MjQ5LCJqdGkiOiIzNGJhMDBkOS05YjUzLTQzYzAtYjkwMC01YzczMDhhM2MyOWEiLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAvYXV0aC9yZWFsbXMvYXBpbWFuIiwiYXVkIjoiYWNjb3VudCIsInN1YiI6ImU0NTNiZjExLWVlMjktNDg3Ny1iYTZiLWM1OTQ4NjZhNzZhNSIsInR5cCI6IkJlYXJlciIsImF6cCI6ImFwaW1hbnVpIiwic2Vzc2lvbl9zdGF0ZSI6ImJlYjAxZWFiLTJlYmEtNDEzNy05ZWE4LTQ1YmM3Njk1OGNjYSIsImFjciI6IjEiLCJhbGxvd2VkLW9yaWdpbnMiOlsiaHR0cDovL2xvY2FsaG9zdDo4MDgwIiwiaHR0cHM6Ly9sb2NhbGhvc3Q6ODQ0MyJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiIsImFwaXVzZXIiLCJkZWZhdWx0LXJvbGVzLWFwaW1hbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7ImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoib3BlbmlkIHByb2ZpbGUgZW1haWwiLCJzaWQiOiJiZWIwMWVhYi0yZWJhLTQxMzctOWVhOC00NWJjNzY5NThjY2EiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsIm5hbWUiOiJHb3ZpbmQgVGVzdCIsInByZWZlcnJlZF91c2VybmFtZSI6ImdvdmluZCIsImdpdmVuX25hbWUiOiJHb3ZpbmQiLCJmYW1pbHlfbmFtZSI6IlRlc3QiLCJlbWFpbCI6ImFkbWluQHRlc3QuY29tIn0.mVNSmv05ZMz5FapfllcaPhnGn2p2rLrPvuw4TlxI3097BY_-VlPfX0PomvpZChrad7Oc-yv-1WhI9ZoZrBzSLlPe1Cm8OU-imVIydKKceTFhIXcaCPX1-WIJdrORAZnqP6pOMW0-L3kqk-G2I1ZvKRbsmWq_UAbszF-t8esRh6U"))
                .build();
        return webClient;
    }

}