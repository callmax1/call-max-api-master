package com.oxycreation.service;

public interface UserRoleService {
    void addUserRole(Long userId, Long roleId);
    void deleteUserRole(Long userId, Long roleId);
}
