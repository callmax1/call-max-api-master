package com.oxycreation.service;

import com.oxycreation.dao.UserDao;
import com.oxycreation.dto.SignUp;
import com.oxycreation.dto.UserDto;
import com.oxycreation.enums.Status;
import com.oxycreation.model.User;
import com.oxycreation.util.Page;
import com.oxycreation.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public Long addUser(SignUp signUp) {
        User user = new User();
        try {
            user.setRoleId(signUp.getRoleId());
            user.setFirstName(signUp.getFirstName());
            user.setLastName(signUp.getLastName());
            user.setEmail(signUp.getEmail());
            user.setGender(signUp.getGender());
            user.setPassword(new BCryptPasswordEncoder().encode(signUp.getPassword()));
            user.setMobile(signUp.getMobile());
            user.setQualification(signUp.getQualification());
            user.setResume(signUp.getResume());
            user.setResumeUrl(signUp.getResumeUrl());
            user.setCreatedBy(1L);
            user.setUpdatedBy(1L);
            user.setCreatedAt(new Date());
            user.setUpdatedAt(new Date());
            user.setStatus(1);
            userDao.addUser(user);
            return userDao.addUser(user);
        } catch (Exception e) {
            return 0L;
        }
    }


    @Override
    @Transactional
    public void update(UserDto userDto, Long id, Long userId) {
        try {
            User user = userDao.getById(id);
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setMobile(userDto.getMobile());
            user.setEmail(userDto.getEmail());
            user.setGender(userDto.getGender());
            user.setQualification(userDto.getQualification());
            user.setProfileImage(userDto.getProfileImage());
           user.setResume(userDto.getResume());
           user.setResumeUrl(userDto.getResumeUrl());
            userDao.updateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public User getByEmail(String email) {
        return userDao.getByEmail(email).orElse(null);
    }

    @Override
    @Transactional
    public UserDto getByUserId(Long id) {
        User user = userDao.getById(id);
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setRoleId(user.getRoleId());
        userDto.setRole(user.getRole());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setGender(user.getGender());
        userDto.setMobile(user.getMobile());
        userDto.setQualification(user.getQualification());
        userDto.setResume(user.getResume());
        userDto.setResumeUrl(user.getResumeUrl());
        userDto.setStatus(user.getStatus());
        userDto.setStatusName(Status.Active.findOne(user.getStatus()).toString());
        return  userDto;
    }

    @Override
    @Transactional
    public List<UserDto> findByAgentName(String name) {
        List<UserDto>  listDto = new ArrayList<>();
        List<User> result = userDao.findByAgentName(name);
        UserDto userDto;
        for (User user: result) {
            userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setRoleId(user.getRoleId());
            userDto.setRole(user.getRole());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setEmail(user.getEmail());
            userDto.setGender(user.getGender());
            userDto.setMobile(user.getMobile());
            userDto.setQualification(user.getQualification());
            userDto.setResume(user.getResume());
            userDto.setResumeUrl(user.getResumeUrl());
            userDto.setCreatedAt(user.getCreatedAt());
            userDto.setStatus(user.getStatus());
            userDto.setStatusName(Status.Active.findOne(user.getStatus()).toString());
            listDto.add(userDto);
        }

        return listDto;
    }

    @Override
    @Transactional
    public Pagination users(Page page,Long id, String email, String propertyName, String sortOrder) {
        List<UserDto>  listDto = new ArrayList<>();
        List<User> result = userDao.users(page,id, email,propertyName,sortOrder);
        UserDto userDto;
        for (User user: result) {
            userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setRoleId(user.getRoleId());
            userDto.setRole(user.getRole());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setEmail(user.getEmail());
            userDto.setGender(user.getGender());
            userDto.setMobile(user.getMobile());
            userDto.setQualification(user.getQualification());
            userDto.setResume(user.getResume());
            userDto.setResumeUrl(user.getResumeUrl());
            userDto.setCreatedAt(user.getCreatedAt());
            userDto.setStatus(user.getStatus());
            userDto.setStatusName(Status.Active.findOne(user.getStatus()).toString());
            listDto.add(userDto);
        }

        int count = userDao.userCount(email);
        return new Pagination(listDto,count);
    }

    @Override
    @Transactional
    public List<UserDto> findByEmail(String email) {
        List<UserDto>  listDto = new ArrayList<>();
        List<User> result = userDao.findByEmail(email);
        UserDto userDto;
        for (User user: result) {
            userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setRoleId(user.getRoleId());
            userDto.setRole(user.getRole());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setEmail(user.getEmail());
            userDto.setGender(user.getGender());
            userDto.setMobile(user.getMobile());
            userDto.setQualification(user.getQualification());
            userDto.setResume(user.getResume());
            userDto.setResumeUrl(user.getResumeUrl());
            userDto.setCreatedAt(user.getCreatedAt());
            userDto.setStatus(user.getStatus());
            userDto.setStatusName(Status.Active.findOne(user.getStatus()).toString());
            listDto.add(userDto);
        }
        return  listDto;
    }

}
