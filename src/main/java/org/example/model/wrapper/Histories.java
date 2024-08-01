package org.example.model.wrapper;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.History;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JacksonXmlRootElement(localName = "histories")
public class Histories {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "history")
    ArrayList<History> histories = new ArrayList<>();
}
