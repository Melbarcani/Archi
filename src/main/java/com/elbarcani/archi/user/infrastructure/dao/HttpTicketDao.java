package com.elbarcani.archi.user.infrastructure.dao;

import com.elbarcani.archi.user.domain.Ticket;
import com.elbarcani.archi.user.domain.TicketDao;
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

public class HttpTicketDao implements TicketDao {
    private static final String ORDER = "order";
    private static final String USER = "user/";
    private static final String URI_PREFIX = "http://demo2009247.mockable.io/";
    private static final String BACKSLASH = "/";

    private static Logger logger = Logger.getLogger(HttpTicketDao.class);
    private final int userId;

    private HttpClient client;

    public HttpTicketDao(int userId) {
        client = HttpClient.newHttpClient();
        this.userId = userId;
    }

    public List<Ticket> getOrderDtoByUser() {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(URI_PREFIX + USER + userId + BACKSLASH + ORDER))
                .build();
        List<Ticket> order = new ArrayList<>();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            order = mapper.readValue(response.body(), new TypeReference<>() {
            });
        } catch (IOException | InterruptedException e) {
            logger.error(e.getMessage());
        }

        return order;
    }

}
