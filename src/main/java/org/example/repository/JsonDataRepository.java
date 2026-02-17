package org.example.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.*;

import java.io.InputStream;
import java.util.List;

public class JsonDataRepository {

    private final ObjectMapper mapper = new ObjectMapper();

    public List<Astronaut> loadAstronauts() throws Exception {
        InputStream is = getClass().getClassLoader().getResourceAsStream("astronauts.json");
        return mapper.readValue(is, new TypeReference<List<Astronaut>>() {});
    }

    public List<MissionEvent> loadEvents() throws Exception {
        InputStream is = getClass().getClassLoader().getResourceAsStream("events.json");
        return mapper.readValue(is, new TypeReference<List<MissionEvent>>() {});
    }

    public List<Supply> loadSupplies() throws Exception {
        InputStream is = getClass().getClassLoader().getResourceAsStream("supplies.json");
        return mapper.readValue(is, new TypeReference<List<Supply>>() {});
    }
}


