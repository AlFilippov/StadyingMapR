package com.alphilippov.studyingmap.network.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "indexDefenition",
        "idDefinition",
        "profession"
})
public class ProfessionOnePartDTO {

    @JsonProperty("indexDefenition")
    private int indexDefenition;
    @JsonProperty("idDefinition")
    private int idDefinition;
    @JsonProperty("profession")
    private String profession;

    @JsonProperty("indexDefenition")
    public int getIndexDefenition() {
        return indexDefenition;
    }


    @JsonProperty("idDefinition")
    public int getIdDefinition() {
        return idDefinition;
    }


    @JsonProperty("profession")
    public String getProfession() {
        return profession;
    }


}
