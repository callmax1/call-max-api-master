package com.oxycreation.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "verticals")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vertical extends SharedModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="vertical_id")
    private List<Question> questions;


}
