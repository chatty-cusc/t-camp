package com.sis.test.tcamp.service.impl;


import com.sis.test.tcamp.common.CommonImportDto;
import com.sis.test.tcamp.common.Result;
import com.sis.test.tcamp.common.ResultEnum;
import com.sis.test.tcamp.service.TongsuoService;
import com.sis.test.tcamp.utils.ShellUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;


@Slf4j
@Service
public class TongsuoServiceImpl implements TongsuoService {

    //sm4加密
//    echo  "chatty" | /opt/tongsuo/bin/tongsuo enc -K  "3f342e9d67d6ce7be701756af7bac8f2" -e -sm4-cbc -iv 1fb2d42fb36e2e88a220b04f2e49aa13 -nosalt -base64
    @Override
    public Result sm4CbcEncrypt(CommonImportDto body) {
        String command ="echo " + body.getPlain() + " | /opt/tongsuo/bin/tongsuo enc -K " + body.getKey() + " -e -sm4-cbc -iv 1fb2d42fb36e2e88a220b04f2e49aa13 -nosalt -base64";
        List<String> list =  ShellUtils.executeNewFlow(Collections.singletonList(command));
        return Result.getResult(ResultEnum.SUCCESS, list.get(0));
    }


    //sm4解密
//    echo vET9rubTC75AMM9tNQyoiw== | /opt/tongsuo/bin/tongsuo enc -K  "3f342e9d67d6ce7be701756af7bac8f2" -d -sm4-cbc -iv 1fb2d42fb36e2e88a220b04f2e49aa13 -nosalt -base64
    @Override
    public Result sm4CbcDecode(CommonImportDto body) {
        String command = "echo " + body.getPlain() + " | /opt/tongsuo/bin/tongsuo enc -K "+body.getKey()+ " -d -sm4-cbc -iv 1fb2d42fb36e2e88a220b04f2e49aa13 -nosalt -base64";
        List<String> list =  ShellUtils.executeNewFlow(Collections.singletonList(command));
        return Result.getResult(ResultEnum.SUCCESS,list.get(0));
    }

    //sm3算法
    @Override
//    echo -n "hello tongsuo" | /opt/tongsuo/bin/tongsuo dgst -sm3
    public Result sm3Dgst(CommonImportDto body) {
        String command ="echo -n " + body.getPlain() + " | /opt/tongsuo/bin/tongsuo dgst -sm3";
        List<String> list =  ShellUtils.executeNewFlow(Collections.singletonList(command));
        return Result.getResult(ResultEnum.SUCCESS,list.get(0));
    }


//    sm2签名验证
    @Override
//    /opt/tongsuo/bin/tongsuo dgst -sm3 -verify Tongsuo/test/certs/sm2pub.key -signature sigfile msg.bin
    public Result sm2Verify(CommonImportDto body) throws IOException {
        String command = "/opt/tongsuo/bin/tongsuo dgst -sm3 -verify Tongsuo/test/certs/sm2pub.key -signature /opt/3/sigfile /opt/2/msg.bin";
        List<String> list =  ShellUtils.executeNewFlow(Collections.singletonList(command));
        if(list.size() > 0 && list.get(0).contains("OK")){
            return Result.getResult(ResultEnum.SUCCESS,list.get(0));
        }else{
            return Result.getResult(ResultEnum.FAILED);
        }
    }

//  sm2签名下载
//   /opt/tongsuo/bin/tongsuo dgst -sm3 -sign Tongsuo/test/certs/sm2.key -out sigfile msg.bin
    @Override
    public void sm2Sign(CommonImportDto body, HttpServletResponse response) throws IOException {
        String command =  "/opt/tongsuo/bin/tongsuo dgst -sm3 -sign Tongsuo/test/certs/sm2.key -out sigfile /opt/1/msg.bin";
        ShellUtils.executeNewFlow(Collections.singletonList(command));
        try {
            File file = new File("/opt/sigfile");
//            File file = new File("C:\\Users\\chend\\Desktop\\123.txt");
            log.info(file.getPath());

            String filename = file.getName();

            String ext = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
            log.info("文件后缀名：" + ext);


            FileInputStream fileInputStream = new FileInputStream(file);
            InputStream fis = new BufferedInputStream(fileInputStream);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();

            response.reset();
            response.setCharacterEncoding("UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));

            response.addHeader("Content-Length", "" + file.length());
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            outputStream.write(buffer);
            outputStream.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

//        return Result.getResult(ResultEnum.SUCCESS);
    }
}
