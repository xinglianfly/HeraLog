package com.example.xiner.background.entity;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by seald on 2017/12/7.
 */

public class User implements Serializable{
   private String _id;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String[] getTokens() {
        return tokens;
    }

    public void setTokens(String[] tokens) {
        this.tokens = tokens;
    }

    public String get__v() {
        return __v;
    }

    public void set__v(String __v) {
        this.__v = __v;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String[] getProjects() {
        return projects;
    }

    public void setProjects(String[] projects) {
        this.projects = projects;
    }

    public String[] getPerms() {
        return perms;
    }

    @Override
    public String toString() {
        return "User{" +
                "_id='" + _id + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", type=" + type +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", tokens=" + Arrays.toString(tokens) +
                ", __v='" + __v + '\'' +
                ", role='" + role + '\'' +
                ", profile=" + profile +
                ", projects=" + Arrays.toString(projects) +
                ", perms=" + Arrays.toString(perms) +
                ", managed=" + Arrays.toString(managed) +
                '}';
    }

    public void setPerms(String[] perms) {
        this.perms = perms;
    }

    public String[] getManaged() {
        return managed;
    }

    public void setManaged(String[] managed) {
        this.managed = managed;
    }

    private String updatedAt;
   private String createdAt;
   private int type;
   private String password;
   private String username;
   private String []tokens;
   private String __v;
   private String role;
   private Profile profile;
   private  String []projects;
   private String []perms;
   private String []managed;


}
