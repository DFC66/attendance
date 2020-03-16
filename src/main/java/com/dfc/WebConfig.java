package com.dfc;

import com.dfc.util.BizException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;

import static com.dfc.constant.DataConstant.PATH_SPLIT;

@Component
@ConfigurationProperties(prefix = "file-service")
public class WebConfig {

    /**
     * 上传路径
     */
    private static String profile;

    public static String getProfile() {
        File file = new File(profile);
        if (!file.exists()){
            file.mkdir();
        }
        return profile;
    }

    public void setProfile(String profile) {
        WebConfig.profile = profile;
    }

    // 获取上传头像路径
    public static String getAvatarPath() {
        return profile + "avatar/";
    }

    // 获取下载路径
    public static String getDownloadPath() {
        return profile + "download/";
    }

    // 获取上传路径
    public static String getUploadPath() {
        return profile + "upload/";
    }

    public static String getFilePath(Integer courseCode,String objectName) {
        if (objectName.startsWith(PATH_SPLIT)) {
            throw new BizException("objectName 不能以 / 开头");
        }


        return profile + PATH_SPLIT +getModulePath(courseCode,objectName);

    }

    public static String getModulePath(Integer courseCode,String objectName) {
        if (objectName.startsWith(PATH_SPLIT)) {
            throw new BizException("objectName 不能以 / 开头");
        }
        String courseDictory = "courseCode"+courseCode;
        if (objectName==null){
            return courseDictory;
        }else{
            return courseDictory +PATH_SPLIT+ objectName;
        }
    }



}
