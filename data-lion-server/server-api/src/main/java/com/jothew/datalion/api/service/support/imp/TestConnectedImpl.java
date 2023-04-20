package com.jothew.datalion.api.service.support.imp;

import com.jothew.datalion.api.service.support.ITestConnected;
import com.jothew.datalion.common.entity.model.JDBCModel;

import java.sql.*;

public class TestConnectedImpl implements ITestConnected {
    @Override
    public boolean testConnected(JDBCModel model) {
        switch (model.getType()){
            case "MySQL":
                return mySQLTestConnected(model);
            case "hive":
                return true;
            default:
                return false;
        }
    }

    private Boolean mySQLTestConnected(JDBCModel model) {
        Connection conn=null;
        Statement stmt=null;

        try {
            //注册驱动
            Class.forName(model.getDriver());
            //打开连接
            conn= DriverManager.getConnection(model.getJdbcUrl(),model.getUsername(),model.getPassword());
            //执行查询user表数据
            stmt=conn.createStatement();

            //完成并关闭数据库
            stmt.close();
            conn.close();

            return true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
