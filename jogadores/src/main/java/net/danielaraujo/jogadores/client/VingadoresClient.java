package net.danielaraujo.jogadores.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class VingadoresClient {
    public List<String> getCodinome() throws IOException {
        List<String> codinomes = new ArrayList<>();
        URL url = new URL("https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/vingadores.json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(url);

        for (JsonNode codinome : rootNode.get("vingadores")) {
            codinomes.add(codinome.get("codinome").asText());
        }

        return codinomes;
    }
}

