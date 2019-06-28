package com.alphilippov.studyingmap.network.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "professionOnePart",
        "professionTwoPart"
})
public class ProfessionDataListDTO {

    @JsonProperty("id")
    private long id;
    @JsonProperty("professionOnePart")
    private List<ProfessionOnePartDTO> professionOnePart = null;
    @JsonProperty("professionTwoPart")
    private List<ProfessionTwoPartDTO> professionTwoPart = null;

    @JsonProperty("id")
    public long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty("professionOnePart")
    public List<ProfessionOnePartDTO> getProfessionOnePart() {
        return professionOnePart;
    }


    @JsonProperty("professionTwoPart")
    public List<ProfessionTwoPartDTO> getProfessionTwoPart() {
        return professionTwoPart;
    }


}
