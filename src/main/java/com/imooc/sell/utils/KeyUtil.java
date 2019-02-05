package com.imooc.sell.utils;

import java.util.Random;

/**
 * @author linxr
 * @version 1.0
 * @since <pre>2019/1/26</pre>
 */
public class KeyUtil {

    /**
     * 生成唯一主键
     * 格式：时间+随机数
     *
     * */
    public static String genUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(900000)+100000;

        return System.currentTimeMillis()+String.valueOf(number);
    }

}



