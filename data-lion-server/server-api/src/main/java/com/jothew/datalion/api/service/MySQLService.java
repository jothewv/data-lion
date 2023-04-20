package com.jothew.datalion.api.service;

import com.jothew.datalion.common.entity.model.JDBCModel;

public interface MySQLService {

    /**
     * MySQL测试连接
     * @param jdbcModel
     * @return
     */
    Object testConnected(JDBCModel jdbcModel);

    /**
     * MySQL数据库备份
     * @param jdbcModel
     * @return
     */
    Object backup(JDBCModel jdbcModel);
}
