package com.oxycreation.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class StateDto {

    @NotBlank(message = "name is mandatory")
    private String name;

    @NotBlank(message = "code is mandatory")
    private String code;

    private String description;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
