package com.oxycreation.dto;

public class SignInResponse {
    private String token;
    private  UserDto user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDto getUserDto() {
        return user;
    }

    public void setUserDto(UserDto user) {
        this.user = user;
    }
}
