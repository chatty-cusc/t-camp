package com.sis.test.tcamp.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chatty
 * @create 2023-04-28 15:53
 */
@Slf4j
public class ShellUtils {

    public static List<String> executeNewFlow(List<String> commands) {
        List<String> rspList = new ArrayList<String>();
        Runtime run = Runtime.getRuntime();
        try {
            Process proc = run.exec("/bin/bash", null, null);
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(proc.getOutputStream())), true);
            for (String line : commands) {
                log.info("执行命令:" + line);
                out.println(line);
            }
            // 这个命令必须执行，否则in流不结束。
            out.println("exit");
            String rspLine = "";
//            log.info("返回结果:BufferedReader" + in.readLine());
            while ((rspLine = in.readLine()) != null) {
                System.out.println(rspLine);
                log.info("返回结果:" + rspLine);
                rspList.add(rspLine);
            }
            proc.waitFor();
            in.close();
            out.close();
            proc.destroy();
        } catch (IOException | InterruptedException e1) {
            e1.printStackTrace();
        }
        return rspList;
    }
}
