package com.oxycreation.service;

import com.oxycreation.dto.RoleDto;
import com.oxycreation.model.Role;
import com.oxycreation.util.Page;
import com.oxycreation.util.Pagination;

import java.util.List;

public interface RoleService {
    Long addRole(RoleDto roleDto);
    void updateRole(RoleDto roleDto, Long id, Long userId);
    Role getById(Long id);
    Role getByName(String name);
    List<Role> findByRoleName(String name);
    Pagination roles(Page page, String name);
}
