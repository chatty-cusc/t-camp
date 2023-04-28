package com.sis.test.tcamp.api;

import com.sun.jna.Library;
import com.sun.jna.Native;
import lombok.extern.slf4j.Slf4j;

/**
 * @author chatty
 * @create 2023-04-28 15:53
 */
@Slf4j
public class SoUtil {

    /**
     * so库映射的接口
     */
    private interface NativeCpp extends Library {

        /**
         * 映射的方法
         *
         * @param param
         * @return
         */
        String sendHashToMsgQueue(String param);
    }

    /**
     * 加载so库
     */
    private static class SoLibImpl implements NativeCpp {
        static NativeCpp INSTANCE;

        static {
            try {
                // 也可以采用其他方法加载so库文件,或将so文件放在项目中
                INSTANCE = (NativeCpp) Native.synchronizedLibrary(Native.loadLibrary("/usr/local/libjava_c.so", NativeCpp.class));
                System.out.println("Load SoLib success");
            } catch (Exception e) {
                // 异常处理
                INSTANCE = null;
                System.out.println("so库[/usr/local/libjava_c.so]加载失败!");
            }
        }

        @Override
        public native String sendHashToMsgQueue(String param);
    }

    /**
     * 对外暴露的工具方法,在此方法中调用so库中的native方法
     *
     * @param param
     * @return
     */
    public static String invoke(String param) {
        if (null != SoLibImpl.INSTANCE) {
            return SoLibImpl.INSTANCE.sendHashToMsgQueue(param);
        } else {
            throw new RuntimeException("无法执行native方法,请检查so库文件是否存在!");
        }
    }

    public static void main(String[] args) {

    }
}
