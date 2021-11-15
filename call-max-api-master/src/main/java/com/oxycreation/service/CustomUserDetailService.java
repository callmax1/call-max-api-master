package com.oxycreation.service;

import com.oxycreation.dao.UserDao;
import com.oxycreation.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CustomUserDetailService  implements UserDetailsService{

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public UserDetailImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username or email: " + username));

        return UserDetailImpl.build(user);
    }

}
