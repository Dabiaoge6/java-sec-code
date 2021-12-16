package org.spring.demo.controller.sensitivedata.file;

import org.apache.commons.lang3.StringUtils;
import org.spring.demo.controller.sensitivedatatrack.entity.RestApiResponseVo;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author mapf
 * @description
 * @date 2020/12/6 22:03
 */
public class FileOutputHandle {
    public static RestApiResponseVo handle(String content){
        File file = new File("a.txt");
        try {
            FileOutputStream out = new FileOutputStream(file);
            if(StringUtils.isEmpty(content)){
                return new RestApiResponseVo(200, "");
            }
            out.write(content.getBytes());
            out.flush();
            out.close();
            if(file.exists()){
                file.deleteOnExit();
            }
        }
        catch (Exception e) {
            return new RestApiResponseVo(200, "请求出错");
        }
        return new RestApiResponseVo(200, "文件内容："+content);
    }
}
