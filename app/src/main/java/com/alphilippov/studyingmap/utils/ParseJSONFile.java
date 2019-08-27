package com.alphilippov.studyingmap.utils;

import android.content.Context;

import com.alphilippov.studyingmap.helperclasses.ProfessionalDefinition;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ParseJSONFile {
    private Context mContext;

    public ParseJSONFile(Context mContext) {
        this.mContext = mContext;
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream inputStream = mContext.getAssets().open("partOne.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public List<ProfessionalDefinition> parseJSONFile() {
        List<ProfessionalDefinition> mOnePart = new ArrayList<>();
        try {


            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray jsonArray = obj.getJSONArray("professionOnePart");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int mIdDefinition = jsonObject.getInt("idDefinition");
                int indexDefinition = jsonObject.getInt("indexDefinition");
                String profession = jsonObject.getString("profession");
                mOnePart.add(new ProfessionalDefinition(mIdDefinition, indexDefinition, profession));
            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return mOnePart;
    }
}

