package com.oxycreation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "candidates")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candidate extends SharedModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name="phone_number")
    private String phoneNumber;

    private String gender;

    private String email;

    @Column(name = "state_id")
    private Long stateId;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "state_id", insertable = false, updatable = false)
    private State state;

    private String city;

    private String address;

    @Column(name="working_experience")
    private Float workingExperience;

    @Column(name="company_name")
    private String companyName;

    private String qualification;

    private String university;

    private String resume;

    @Column(name = "resume_url")
    private String resumeUrl;

    @Column(name="speed_test")
    private String speedTest;

    @Column(name="speed_test_url")
    private String speedTestUrl;

    @Column(name="skill_test")
    private String skillTest;

    @Column(name="skill_test_url")
    private String skillTestUrl;

    @Column(name="speed_test_upload")
    private Float speedTestUpload;

    @Column(name="speed_test_download")
    private Float speedTestDownload;

    @Column(name="skill_test_speed")
    private Float skillTestSpeed;

    @Column(name="skill_test_accuracy")
    private Float skillTestAccuracy;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name="assign_date" ,columnDefinition= "DATE")
    @Temporal(TemporalType.DATE)
    private Date assignDate;

    @Column(name = "wave_id")
    private Long waveId;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "wave_id",insertable = false, updatable = false)
    private Wave wave;

    private String description;

    @Column(name="candidate_status")
    private Integer candidateStatus;


}