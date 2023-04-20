package com.jothew.datalion.api.controller;

import com.jothew.datalion.api.service.MySQLService;
import com.jothew.datalion.common.entity.model.JDBCModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("mysql")
public class MySQLController {

    @Autowired
    private MySQLService mySQLService;


    @PostMapping("testConnected")
    @ResponseBody
    public Object testConnected(@RequestBody JDBCModel jdbcModel){
        return mySQLService.testConnected(jdbcModel);
    }

    @PostMapping("backup")
    @ResponseBody
    public Object backup(@RequestBody JDBCModel jdbcModel){
        return mySQLService.backup(jdbcModel);
    }

}
