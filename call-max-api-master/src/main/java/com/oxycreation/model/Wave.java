package com.oxycreation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "waves")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wave extends SharedModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lob_id")
    private Long lobId;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "lob_id",nullable = false,insertable = false, updatable = false)
    private Lob lob;

    private String name;

    @Column(name = "new_hire_orientation",columnDefinition= "DATE")
    @Temporal(TemporalType.DATE)
    private Date newHireOrientation;

    @Column(name = "on_boarding",columnDefinition= "DATE")
    @Temporal(TemporalType.DATE)
    private Date onBoarding;

    @Column(name = "product_specific_training",columnDefinition= "DATE")
    @Temporal(TemporalType.DATE)
    private Date productSpecificTraining;

    @Column(name="number_of_candidate")
    private Integer numberOfCandidate;

    private String comment;


}