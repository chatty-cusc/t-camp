package com.sis.test.tcamp.api;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chaird
 * @create 2020-10-11 15:53
 */
@Slf4j
public class ShellUtils {




    /**
     * @param pathOrCommand 脚本路径或者命令
     * @return
     */
    public static List<String> exceShell(String pathOrCommand) {
        List<String> result = new ArrayList<>();

        try {
            // 执行脚本
            Process ps = Runtime.getRuntime().exec(pathOrCommand);
            int exitValue = ps.waitFor();
            if (0 != exitValue) {
                System.out.println("call shell failed. error code is :" + exitValue);
            }

            // 只能接收脚本echo打印的数据，并且是echo打印的最后一次数据
            BufferedInputStream in = new BufferedInputStream(ps.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("脚本返回的数据如下： " + line);
                result.add(line);
            }
            in.close();
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    public static String updateApprovalSh(String tempName) {
        try {
            // 执行脚本文件,主要在这步写入后调用命令
            Process process = Runtime.getRuntime().exec(tempName);
            try (PrintWriter ignored = new PrintWriter( new BufferedWriter(new OutputStreamWriter(process.getOutputStream())), true);
                 BufferedReader read = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                StringBuilder xx = new StringBuilder();
                String line;
                while ((line = read.readLine()) != null) {
                    xx.append(line);
                    log.info(line);
                }

                return String.valueOf(xx);
            }
            // Java父线程等待Shell子进程执行完毕
        } catch (Exception e) {
            log.error("错误原因:{}", e.getMessage());
        }
        return null;
    }


}
