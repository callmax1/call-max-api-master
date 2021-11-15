package com.oxycreation.service;

import com.oxycreation.dto.SignUp;
import com.oxycreation.dto.UserDto;
import com.oxycreation.model.User;
import com.oxycreation.util.Page;
import com.oxycreation.util.Pagination;

import java.util.List;


public interface UserService {

    Long addUser(SignUp signUpDto);
    void update(UserDto userDto, Long id, Long userId);
    User getByEmail(String email);
    UserDto getByUserId(Long id);
     List<UserDto> findByAgentName(String name);
    Pagination users(Page page,Long id, String email, String propertyName, String sortOrder);
    List<UserDto> findByEmail(String email);

}
