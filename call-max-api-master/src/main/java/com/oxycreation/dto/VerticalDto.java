package com.oxycreation.dto;


import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class VerticalDto {

    @NotBlank(message = "name is mandatory")
    private String name;

    private String description;

    private List<QuestionDto> questions =  new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<QuestionDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDto> questions) {
        this.questions = questions;
    }
}
