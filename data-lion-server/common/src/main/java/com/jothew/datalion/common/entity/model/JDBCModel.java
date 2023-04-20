package com.jothew.datalion.common.entity.model;

import lombok.Data;

@Data
public class JDBCModel extends BaseModel{

    private String type;
    private String port;
    private String driver;
    private String database;
    private String table;
    private String path;
    private String fileName;
}
