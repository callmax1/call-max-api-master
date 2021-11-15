package com.oxycreation.dao;


import com.oxycreation.model.User;
import com.oxycreation.util.Page;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    public Long addUser(User user);
    public void updateUser(User user);
    public Optional<User> getByEmail(String email);
    public List<User> findByAgentName(String name);
    public User getById(Long id);
    public List<User> users(Page page,Long id, String email, String propertyName, String sortOrder);
    public int userCount(String email);
    public List<User> findByEmail(String email);
}
