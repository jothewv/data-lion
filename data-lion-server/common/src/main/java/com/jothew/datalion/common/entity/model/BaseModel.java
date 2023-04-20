package com.jothew.datalion.common.entity.model;

import lombok.Data;

@Data
public class BaseModel {

    private String jdbcUrl;
    public String username;
    private String password;
}
