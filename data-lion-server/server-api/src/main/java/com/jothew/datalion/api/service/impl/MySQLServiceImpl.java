package com.jothew.datalion.api.service.impl;

import com.jothew.datalion.api.service.MySQLService;
import com.jothew.datalion.api.service.support.IBackup;
import com.jothew.datalion.api.service.support.ITestConnected;
import com.jothew.datalion.common.entity.model.JDBCModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MySQLServiceImpl implements MySQLService {

    @Resource(name = "mySQLBackup")
    private IBackup backup;

    @Autowired
    private ITestConnected iTestConnected;

    @Override
    public Object testConnected(JDBCModel jdbcModel) {
        return iTestConnected.testConnected(jdbcModel);
    }

    @Override
    public Object backup(JDBCModel jdbcModel) {
        return backup.backup(jdbcModel);
    }
}
