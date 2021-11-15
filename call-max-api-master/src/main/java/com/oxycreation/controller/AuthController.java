package com.oxycreation.controller;

import com.oxycreation.dto.*;
import com.oxycreation.model.User;
import com.oxycreation.security.JwtUtil;
import com.oxycreation.service.CustomUserDetailService;
import com.oxycreation.service.UserDetailImpl;
import com.oxycreation.service.UserService;
import com.oxycreation.util.CommonResponse;
import com.oxycreation.util.DateFormats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@RequestMapping(value = "/api/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    public UserService userService;
    @Autowired
    CustomUserDetailService customUserDetailService;
    @Autowired
    private JwtUtil jwtUtil;

    SimpleDateFormat sm = new DateFormats().DATE_TIME_FORMAT;

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public ResponseEntity<?> postUser(@Valid @RequestBody SignUp signUpDto) {

        User user = userService.getByEmail(signUpDto.getEmail());

        if (user != null) {
            return new ResponseEntity(new CommonResponse(sm.format(new Date()), HttpServletResponse.SC_CONFLICT, "Conflict", "this email already exists!", "/api/auth/sign-up"), new HttpHeaders(), HttpStatus.CONFLICT);
        }
        if (!signUpDto.getPassword().equals(signUpDto.getConfirmPassword())) {
            return new ResponseEntity(new CommonResponse(sm.format(new Date()), HttpServletResponse.SC_BAD_REQUEST, "Password", "The password and confirmation password do not match.", "/api/auth/sign-up"), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        userService.addUser(signUpDto);
        return new ResponseEntity(new CommonResponse(sm.format(new Date()), HttpServletResponse.SC_OK, "Success", "You have signed up successfully", "/api/auth/sign-up"), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/sign-in", method = RequestMethod.POST)
    public ResponseEntity<?> signIn(@Valid @RequestBody SignIn signIn) throws BadCredentialsException, Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signIn.getEmail(), signIn.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtUtil.generateJwtToken(authentication);

            UserDetailImpl userDetails = (UserDetailImpl) authentication.getPrincipal();


            JwtResponse result = new JwtResponse(jwt, "Bearer", new UserResponse(userDetails.getId(), userDetails.getEmail(), userDetails.getProfileImage(), userDetails.getRole().getId(), userDetails.getRole().getName()));

            return new ResponseEntity(result, new HttpHeaders(), HttpStatus.OK);

        } catch (BadCredentialsException be) {
            return new ResponseEntity(new CommonResponse(sm.format(new Date()), HttpServletResponse.SC_NOT_FOUND, "BadCredentialsException", "Invalid username and password", "/api/auth/sign-in"), new HttpHeaders(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new Exception(e);
        }

    }

}

