package net.danielaraujo.jogadores.client;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class LigaJusticaClient {
    public List<String> getCodinome() throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        URL url = new URL("https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/liga_da_justica.xml");
        LigaDaJustica liga = xmlMapper.readValue(url, LigaDaJustica.class);

        if (liga.getCodinomes() != null) {
            return new ArrayList<>(liga.getCodinomes());
        }

        return new ArrayList<>();
    }
}

@Getter
@Setter
@JacksonXmlRootElement(localName = "liga_da_justica")
class LigaDaJustica {
    @JacksonXmlElementWrapper(localName = "codinomes")
    @JacksonXmlProperty(localName = "codinome")
    private List<String> codinomes;
}
