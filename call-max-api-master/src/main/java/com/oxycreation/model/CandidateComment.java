package com.oxycreation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "candidate_comments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateComment extends SharedModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "candidate_id")
    private Long candidateId;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "candidate_id", insertable = false, updatable = false)
    private Candidate candidate;

    @Column(name="comment_date" ,columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private Date commentDate;

    private String comment;


}
