package com.jothew.datalion.api.service.support.imp;

import com.jothew.datalion.api.service.support.IBackup;
import com.jothew.datalion.common.entity.model.JDBCModel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.*;

@Component
public class MySQLBackup implements IBackup {

    @Override
    public Object backup(JDBCModel model) {
        String savePath = model.getPath();
        File saveFile = new File(savePath);
        if (!saveFile.exists()) {// 如果目录不存在
            saveFile.mkdirs();// 创建文件夹
        }
        if(!savePath.endsWith(File.separator)){
            savePath = savePath + File.separator;
        }

        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        try {
            printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(savePath + model.getFileName()), "utf8"));
            Process process = Runtime.getRuntime().exec(" mysqldump -h" + model.getJdbcUrl() + " -P" + model.getPort() + " -u" + model.getUsername() + " -p" + model.getPassword() + " --default-character-set=utf8 " + model.getDatabase());
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(), "utf8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while((line = bufferedReader.readLine())!= null){
                printWriter.println(line);
            }
            printWriter.flush();

            if(process.waitFor() == 0){
                return true;
            }
        } catch (Exception e){
            return false;
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
