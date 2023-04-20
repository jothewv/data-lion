package com.jothew.datalion.common.entity.model;

import lombok.Data;

@Data
public class JDBCModel {

    private String type;
    private String jdbcUrl;
    private String port;
    private String driver;
    private String username;
    private String password;
    private String database;
    private String table;
    private String path;
    private String fileName;
}
