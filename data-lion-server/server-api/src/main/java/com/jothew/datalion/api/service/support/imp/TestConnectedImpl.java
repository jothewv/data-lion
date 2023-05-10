package com.jothew.datalion.api.service.support.imp;

import com.jothew.datalion.api.service.support.ITestConnected;
import com.jothew.datalion.common.entity.model.BaseModel;
import com.jothew.datalion.common.entity.model.JDBCModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.sql.*;

@Component
public class TestConnectedImpl implements ITestConnected {
    @Override
    public boolean testConnected(JDBCModel model) {
        switch (model.getType()){
            case "MySQL":
                model.setDriver("com.mysql.cj.jdbc.Driver");
                return mySQLTestConnected(model);
            case "hive":
                model.setDriver("org.apache.hive.jdbc.HiveDriver");
                return hiveTestConnected(model);
            case "redis":
                return redisTestConnected(model);
            default:
                return false;
        }
    }

    private Boolean mySQLTestConnected(BaseModel model) {
        Connection conn=null;
        Statement stmt=null;

        try {
            //注册驱动
            Class.forName(model.getDriver());
            //打开连接
            conn= DriverManager.getConnection(model.getUrl(),model.getUsername(),model.getPassword());
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


    private static Boolean hiveTestConnected(BaseModel model) {
        Connection conn;
        Statement stmt;

        model.setUrl("jdbc:hive2://10.120.68.229:10001/default;auth=noSasl");
        model.setDriver("org.apache.hive.jdbc.HiveDriver");
        StringUtils.isEmpty(model.toString());
        try {
            //注册驱动
            Class.forName(model.getDriver());
            //打开连接
            conn= DriverManager.getConnection(model.getUrl());
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

    private static Boolean redisTestConnected(BaseModel model){
        boolean flag;
        Jedis jedis=new Jedis(model.getUrl(), 6379);
        try {

            jedis.set("testConnected", "redisTestConnected");
            String value=jedis.get("testConnected");
            if ("redisTestConnected".equals(value)){
                flag = true;
            }else {
                flag = false;
            }
        }catch (Exception e){
            flag = false;
        }finally {
            jedis.close();
        }

        return flag;

    }

}
