package com.bespalov.chatfirebase;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class User {

    private String name;
    private String email;
    private String id;
    private int avatarMoskUpResource;
    private String userPhotoUri;

    public User() {
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("email", email);
        result.put("id", id);
        result.put("avatarMoskUpResource", avatarMoskUpResource);
        result.put("userPhotoUri", userPhotoUri);

        return result;
    }

    public User(String name, String email, String id, int avatarMoskUpResource, String userPhotoUri) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.avatarMoskUpResource = avatarMoskUpResource;
        this.userPhotoUri = userPhotoUri;
    }

    public String getUserPhotoUri() {
        return userPhotoUri;
    }

    public void setUserPhotoUri(String userPhotoUri) {
        this.userPhotoUri = userPhotoUri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAvatarMoskUpResource() {
        return avatarMoskUpResource;
    }

    public void setAvatarMoskUpResource(int avatarMoskUpResource) {
        this.avatarMoskUpResource = avatarMoskUpResource;
    }
}
