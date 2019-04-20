package com.libiyi.exa.common.util;

import java.util.UUID;

/**
 * Created by bibi on 2019/4/19.
 */
public class IdUtil {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

}
