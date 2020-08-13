package com.rodrigopeleias.bookstoremanager.endpoints;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Endpoint(id = "notes")
public class NotesEndpoint {

    Map<String, String> features = new ConcurrentHashMap<>();

    public NotesEndpoint() {
        features.put("firstRelease", "Project Creation");
        features.put("secondRelease", "Project Customization");
    }

    @ReadOperation
    public Map<String, String> getNotes() {
        return features;
    }

    @ReadOperation
    public String getNote(@Selector String name) {
        return features.get(name);
    }
}
