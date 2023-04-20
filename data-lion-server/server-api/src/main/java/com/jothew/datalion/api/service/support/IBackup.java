package com.jothew.datalion.api.service.support;

import com.jothew.datalion.common.entity.model.JDBCModel;

public interface IBackup {
    Object backup(JDBCModel model);
}
