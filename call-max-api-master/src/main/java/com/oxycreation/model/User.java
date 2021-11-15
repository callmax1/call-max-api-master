package com.oxycreation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends SharedModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_id")
    private Long roleId;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "role_id",nullable = false,insertable = false, updatable = false)
    private Role role;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "profile_image")
    private String profileImage;

    private String gender;

    private String email;

    private String password;

    private String mobile;

    private String qualification;

    private String resume;

    @Column(name = "resume_url")
    private String resumeUrl;


}