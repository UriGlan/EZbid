package com.ezbid.dto;

public class UserDto {
    private String username;
    private Long user_id;

    public UserDto(String username, Long user_id) {
        this.username = username;
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
