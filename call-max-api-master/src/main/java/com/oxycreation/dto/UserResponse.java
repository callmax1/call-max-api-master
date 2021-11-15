package com.oxycreation.dto;

import com.oxycreation.model.Role;

import java.util.List;

public class UserResponse {
    private Long userId;
    private String email;
    private String profileImage;
    private Long roleId;
    private String roleName;


    public UserResponse(Long userId, String email, String profileImage, Long roleId, String roleName) {
        this.userId = userId;
        this.email = email;
        this.profileImage = profileImage;
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
