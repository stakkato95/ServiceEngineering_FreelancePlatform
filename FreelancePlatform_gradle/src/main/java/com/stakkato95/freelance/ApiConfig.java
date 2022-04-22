package com.stakkato95.freelance;

import java.net.URI;

public class ApiConfig {

    public static final String OPEN_API_TAG = "API";
    public static final String BASE_URL = "http://localhost:8080";

    public static URI createdLocation(String endpoint, Long id) {
        return URI.create(BASE_URL + endpoint + "/" + id);
    }
}
