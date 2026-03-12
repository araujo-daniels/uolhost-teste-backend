package net.danielaraujo.jogadores.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class VingadoresClient {
    public List<String> getCodinomes() throws IOException {
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
