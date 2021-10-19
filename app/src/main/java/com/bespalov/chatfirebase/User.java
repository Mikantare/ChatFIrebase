package com.bespalov.chatfirebase;

public class User {

    private String name;
    private String email;
    private String id;
    private int avatarMoskUpResource;

    public User() {
    }

    public User(String name, String email, String id, int avatarMoskUpResource) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.avatarMoskUpResource = avatarMoskUpResource;
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
