package com.jothew.datalion.api.service.support.imp;

import com.jothew.datalion.api.service.support.IBackup;
import com.jothew.datalion.common.entity.model.JDBCModel;
import org.springframework.stereotype.Component;

@Component
public class HiveBackUp implements IBackup {
    @Override
    public Object backup(JDBCModel model) {
        return "hive";
    }
}
