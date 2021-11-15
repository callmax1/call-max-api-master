package com.oxycreation.service;


import com.oxycreation.dao.RoleDao;
import com.oxycreation.dto.RoleDto;
import com.oxycreation.model.Role;
import com.oxycreation.util.Page;
import com.oxycreation.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    public RoleDao roleDao;

    @Override
    @Transactional
    public Long addRole(RoleDto roleDto) {
            Role role = new Role();
            role.setName(roleDto.getName());
            role.setDescription(roleDto.getDescription());
            role.setCreatedBy(1L);
            role.setUpdatedBy(1L);
            role.setCreatedAt(new Date());
            role.setUpdatedAt(new Date());
            role.setStatus(1);
         return  roleDao.addRole(role);
    }

    @Override
    @Transactional
    public void updateRole(RoleDto roleDto, Long id, Long userId) {
        try {
            Role role = roleDao.getById(id);
            role.setName(roleDto.getName());
            role.setDescription(roleDto.getDescription());
            role.setUpdatedBy(userId);
            role.setUpdatedAt(new Date());
            roleDao.updateRole(role);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public Role getById(Long id) {
        return roleDao.getById(id);
    }

    @Override
    @Transactional
    public Role getByName(String name) {
        return roleDao.getByName(name).orElse(null);
    }

    @Override
    @Transactional
    public List<Role> findByRoleName(String name) {
        return roleDao.findByRoleName(name);
    }



    @Transactional
    public Pagination roles(Page page, String name) {
        List<Role> result = roleDao.roles(page, name);
        int count = roleDao.roleCount(name);
        return new Pagination(result,count);
    }
}
