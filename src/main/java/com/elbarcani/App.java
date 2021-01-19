package com.elbarcani;

import com.elbarcani.archi.gui.swt.common.Launcher;
import com.elbarcani.archi.user.infrastructure.controller.TicketDto;
import com.elbarcani.archi.user.infrastructure.controller.UserDto;
import com.elbarcani.archi.user.infrastructure.http.CallHttp;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class App {
    public static void main( String[] args ) throws IOException, InterruptedException {
        CallHttp callHttp = new CallHttp();
        Launcher launcher = new Launcher();
        //test httpClient
        /*HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create("http://demo2009247.mockable.io/user/123/order"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

        List<TicketDto> tickets = mapper.readValue(response.body(), new TypeReference<>() {
        });
        System.out.println(tickets.get(0).getId());*/

    }
}
