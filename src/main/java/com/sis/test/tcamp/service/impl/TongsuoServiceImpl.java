package com.sis.test.tcamp.service.impl;


import com.sis.test.tcamp.common.CommonImportDto;
import com.sis.test.tcamp.common.Result;
import com.sis.test.tcamp.common.ResultEnum;
import com.sis.test.tcamp.service.TongsuoService;
import com.sis.test.tcamp.utils.ShellUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Collections;
import java.util.List;


@Slf4j
public class TongsuoServiceImpl implements TongsuoService {

    //sm4加密
//    echo  "chatty" | /opt/tongsuo/bin/tongsuo enc -K  "3f342e9d67d6ce7be701756af7bac8f2" -e -sm4-cbc -iv 1fb2d42fb36e2e88a220b04f2e49aa13 -nosalt -base64
    @Override
    public Result sm4CbcEncrypt(CommonImportDto body) {
        String command ="echo " + body.getPlain() + " | /opt/tongsuo/bin/tongsuo enc -K " + body.getKey() + " -e -sm4-cbc -iv 1fb2d42fb36e2e88a220b04f2e49aa13 -nosalt -base64";
        List<String> list =  ShellUtils.executeNewFlow(Collections.singletonList(command));
        return Result.getResult(ResultEnum.SUCCESS,list.toString());
    }


    //sm4解密
//    echo vET9rubTC75AMM9tNQyoiw== | /opt/tongsuo/bin/tongsuo enc -K  "3f342e9d67d6ce7be701756af7bac8f2" -d -sm4-cbc -iv 1fb2d42fb36e2e88a220b04f2e49aa13 -nosalt -base64
    @Override
    public Result sm4CbcDecode(CommonImportDto body) {
        String command = "echo" + body.getPlain() + "| /opt/tongsuo/bin/tongsuo enc -K "+body.getKey()+ "-d -sm4-cbc -iv 1fb2d42fb36e2e88a220b04f2e49aa13 -nosalt -base64";
        List<String> list =  ShellUtils.executeNewFlow(Collections.singletonList(command));
        return Result.getResult(ResultEnum.SUCCESS,list.toString());
    }

    //sm3算法
    @Override
//    echo -n "hello tongsuo" | /opt/tongsuo/bin/tongsuo dgst -sm3
    public Result sm3Dgst(CommonImportDto body) {
        String command ="echo -n " + body.getPlain() + " | /opt/tongsuo/bin/tongsuo dgst -sm3 ";
        List<String> list =  ShellUtils.executeNewFlow(Collections.singletonList(command));
        return Result.getResult(ResultEnum.SUCCESS,list.toString());
    }


//    sm2签名验证
    @Override
//    /opt/tongsuo/bin/tongsuo dgst -sm3 -verify ./sm2pub.key -signature  signature msg.bin
    public Result sm2Verify(CommonImportDto body) throws IOException {
        String filePath = body.getFilePath();
        FileInputStream fin = new FileInputStream(filePath);
        InputStreamReader reader = new InputStreamReader(fin);
        BufferedReader buffReader = new BufferedReader(reader);
        StringBuilder xx = new StringBuilder();
        String strTmp;
        while((strTmp = buffReader.readLine())!=null){
          log.info(strTmp);
          xx.append(strTmp);
        }
        buffReader.close();
        String command1 = "echo "+ xx +" >" +body.getSigFile()+".bin";
        ShellUtils.executeNewFlow(Collections.singletonList(command1));

        String command = "/opt/tongsuo/bin/tongsuo dgst -sm3 -verify Tongsuo/test/certs/sm2pub.key -signature "+ body.getSigFile()+" "+body.getSigFile()+".bin";
        List<String> list =  ShellUtils.executeNewFlow(Collections.singletonList(command));
        return Result.getResult(ResultEnum.SUCCESS,list.toString());
    }

//  sm2签名
//    /opt/tongsuo/bin/tongsuo dgst -sm3 -sign ./sm2.key -out signature msg.bin
    @Override
    public Result sm2Sign(CommonImportDto body) throws IOException {
        String filePath = body.getFilePath();
        FileInputStream fin = new FileInputStream(filePath);
        InputStreamReader reader = new InputStreamReader(fin);
        BufferedReader buffReader = new BufferedReader(reader);
        StringBuilder xx = new StringBuilder();
        String strTmp;
        while((strTmp = buffReader.readLine())!=null){
            log.info(strTmp);
            xx.append(strTmp);
        }
        buffReader.close();
        String command1 = "echo "+ xx +" >" +body.getSigFile()+".bin";
        ShellUtils.executeNewFlow(Collections.singletonList(command1));

        String command =  "/opt/tongsuo/bin/tongsuo dgst -sm3 -sign Tongsuo/test/certs/sm2pub.key -out "+ body.getSigFile()+" "+body.getSigFile()+".bin";
        List<String> list =  ShellUtils.executeNewFlow(Collections.singletonList(command));
        return Result.getResult(ResultEnum.SUCCESS,list.toString());
    }
}
