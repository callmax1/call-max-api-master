package com.oxycreation.dao;

import com.oxycreation.model.Role;
import com.oxycreation.util.Page;

import java.util.List;
import java.util.Optional;

public interface RoleDao {
    public Long addRole(Role role);
    public void updateRole(Role role);
    public  Role getById(Long id);
    public Optional<Role> getByName(String name);
    public List<Role> findByRoleName(String name);
    public List<Role> roles(Page page, String name);
    public int roleCount(String name);
}
