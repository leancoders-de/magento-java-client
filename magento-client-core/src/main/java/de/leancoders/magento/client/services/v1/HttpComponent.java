package de.leancoders.magento.client.services.v1;


import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;


/**
 *
 */
public interface HttpComponent {
    String post(String url, String body, Map<String, String> headers);

    String put(String url, String body, Map<String, String> headers);

    String delete(String url, Map<String, String> headers);

    String get(String uri, Map<String, String> headers);

    String jsonPost(String uri, Map<String, String> data) throws JsonProcessingException;
}
