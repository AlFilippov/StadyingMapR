

package com.alphilippov.studyingmap.network.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "application_name",
        "professionDataLists"
})
public class GetProfessionDTO {

    @JsonProperty("id")
    private long id;
    @JsonProperty("application_name")
    private String applicationName;
    @JsonProperty("professionDataLists")
    private List<ProfessionDataListDTO> professionDataLists = null;

    @JsonProperty("id")
    public long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty("application_name")
    public String getApplicationName() {
        return applicationName;
    }


    @JsonProperty("professionDataLists")
    public List<ProfessionDataListDTO> getProfessionDataLists() {
        return professionDataLists;
    }


}


