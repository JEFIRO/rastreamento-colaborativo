package com.jefiro.rastreamento.android.Model.GeolocateApi;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class GoogleGeolocationService {

    private final WebClient webClient;

    private final String API_KEY = "AIzaSyDEEuT2RDu7rW0cov1rHGNRf74cxigslGU";

    public GoogleGeolocationService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://www.googleapis.com").build();
    }

    public Mono<WrapperResponse> getLocation(WifiListDTO wifiList) {
        return webClient.post()
                .uri("/geolocation/v1/geolocate?key=" + API_KEY)
                .bodyValue(wifiList)
                .retrieve()
                .bodyToMono(WrapperResponse.class);
    }
}
