package com.gcm.model;

import com.thirdparty.model.JSONWrapper;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.web.multipart.MultipartRequest;

import java.util.List;

public class NotificationDTO implements JSONWrapper{
    private List<String> regsitrationIds;
    private String title;
    private String message;
    private String image;

    public List<String> getRegsitrationIds() {
        return regsitrationIds;
    }

    public void setRegsitrationIds(List<String> regsitrationIds) {
        this.regsitrationIds = regsitrationIds;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        JSONArray ids = new JSONArray();
        JSONObject data = new JSONObject();
        for(String l : getRegsitrationIds()){
            ids.put(l);
        }
        try {
            json.put("registration_ids", ids);
            data.put("title",getTitle());
            data.put("message",getMessage());
//            data.put("image",getImage());
            json.put("data",data);
            return json;
        }catch(JSONException e){
            return null;
        }
    }
}
