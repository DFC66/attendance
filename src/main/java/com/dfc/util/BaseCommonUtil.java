package com.dfc.util;

import java.util.UUID;

public class BaseCommonUtil {
    public static String uuid(){
        return UUID.randomUUID().toString();
    }


    /**
     * Title:String
     * @Description: TODO
     * @Param:
     * @Return:replace - uuid
     * @throws:
     * @author:YuMing
     * @Date:2017-12-28上午9:43:51
     * @return
     */
    public static String uuidReplace(){
        return UUID.randomUUID().toString().replace("-", "");
    }


}
