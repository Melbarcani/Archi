package com.elbarcani.archi.user.infrastructure.dto.http;

import com.elbarcani.archi.user.domain.User;
import com.elbarcani.archi.user.infrastructure.dto.UserDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class HttpUserDto implements UserDto {
    private static final String USER = "user/";
    private static final String URI_PREFIX = "http://demo2009247.mockable.io/";

    private static final Logger logger = Logger.getLogger(HttpUserDto.class);

    private final HttpClient client;
    private final HttpRequest request;
    private List<User> users;


    public HttpUserDto(int userId) {
        client = HttpClient.newHttpClient();
        request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(URI_PREFIX + USER + userId))
                .build();
        users = new ArrayList<>();
    }

    @Override
    public User getUserDto() {
        return users.get(0);
    }

    @Override
    public boolean isUserExist() {
        try {
            getHttpResponse();
        } catch (IOException | InterruptedException e) {
            return false;
        }
        return !users.isEmpty();
    }

    private void getHttpResponse() throws IOException, InterruptedException {
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        users = mapper.readValue(response.body(), new TypeReference<>() {
        });
    }
}
