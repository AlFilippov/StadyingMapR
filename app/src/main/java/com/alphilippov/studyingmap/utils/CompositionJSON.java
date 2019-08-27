package com.alphilippov.studyingmap.utils;

import com.alphilippov.studyingmap.helperclasses.ProfessionalDefinition;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CompositionJSON extends JSONObject {
    public JSONObject createJSONObject(List<ProfessionalDefinition> list, String nameArray) {
        try {
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();

            for (ProfessionalDefinition professionalDefinition : list) {
                JSONObject profObject = new JSONObject();
                profObject.put("idDefinition", professionalDefinition.getIdDefiniton());
                profObject.put("indexDefinition", professionalDefinition.getIndexDefinition());
                profObject.put("profession", professionalDefinition.getProfession());
                jsonArray.put(profObject);

            }
            return jsonObject.put(nameArray, jsonArray);

        } catch (JSONException ex) {
            ex.printStackTrace();
            return null;

        }

    }
}
