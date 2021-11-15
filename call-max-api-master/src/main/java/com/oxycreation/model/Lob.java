package com.oxycreation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "lobs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lob extends SharedModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_id")
    private Long companyId;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "company_id",nullable = false,insertable = false, updatable = false)
    private Company company;

    @Column(name = "vertical_id")
    private Long verticalId;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "vertical_id",nullable = false,insertable = false, updatable = false)
    private Vertical vertical;

    private String name;

    private String pitch;

    @Column(name = "client_observed_holiday")
    private String clientObservedHoliday;

    @Column(name = "hours_of_operation")
    private String hoursOfOperation;

    @Column(name = "training_duration")
    private String trainingDuration;

    @Column(name = "missed_in_training_termination")
    private Double missedInTrainingTermination;

    @Column(name="pay_rate_during_training")
    private Double payRateDuringTraining;

    @Column(name="pay_rate_during_production")
    private Double payRateDuringProduction;


    @Column(name = "background_check")
    private Boolean backgroundCheck;

    @Column(name = "drug_screening")
    private Boolean drugScreening;

    @Column(name = "missed_in_training")
    private Double missedInTraining;


}