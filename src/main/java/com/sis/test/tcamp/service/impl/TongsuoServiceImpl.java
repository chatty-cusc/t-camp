package com.sis.test.tcamp.service.impl;


import com.sis.test.tcamp.common.CommonImportDto;
import com.sis.test.tcamp.common.Result;
import com.sis.test.tcamp.common.ResultEnum;
import com.sis.test.tcamp.service.TongsuoService;
import com.sis.test.tcamp.utils.ShellUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;


@Slf4j
public class TongsuoServiceImpl implements TongsuoService {

    //sm4加密
    @Override
    public Result sm4CbcEncrypt(CommonImportDto body) {
        String command ="echo " + body.getPlain() + " | /opt/tongsuo/bin/tongsuo enc -K " + body.getKey() + " -e -sm4-cbc -iv 1fb2d42fb36e2e88a220b04f2e49aa13 -nosalt -base64 ";
        List<String> list =  ShellUtils.executeNewFlow(Collections.singletonList(command));
        return Result.getResult(ResultEnum.SUCCESS,list.toString());
    }


    //sm4解密
    @Override
    public Result sm4CbcDecode(CommonImportDto body) {
        String command = "echo" + body.getPlain() + "| /opt/tongsuo/bin/tongsuo enc -K "+body.getKey()+ "-d -sm4-cbc -iv 1fb2d42fb36e2e88a220b04f2e49aa13 -nosalt -base64";
        List<String> list =  ShellUtils.executeNewFlow(Collections.singletonList(command));
        return Result.getResult(ResultEnum.SUCCESS,list.toString());
    }

    //sm3算法
    @Override
    public Result sm3Dgst(CommonImportDto body) {
        String command ="echo -n " + body.getPlain() + " | /opt/tongsuo/bin/tongsuo dgst -sm3 ";
        List<String> list =  ShellUtils.executeNewFlow(Collections.singletonList(command));
        return Result.getResult(ResultEnum.SUCCESS,list.toString());
    }


//    sm2签名验证
    @Override
    public Result sm2Verify(CommonImportDto body) {
        String command = "/opt/tongsuo/bin/tongsuo dgst -sm3 -verify ./sm2pub.key -signature "+ body.getSigFile()+body.getFilePath();
        List<String> list =  ShellUtils.executeNewFlow(Collections.singletonList(command));
        return Result.getResult(ResultEnum.SUCCESS,list.toString());
    }

//  sm2签名
    @Override
    public Result sm2Sign(CommonImportDto body) {
        String command =  "/opt/tongsuo/bin/tongsuo dgst -sm3 -sign ./sm2.key -out "+ body.getSigFile()+body.getFilePath();
        List<String> list =  ShellUtils.executeNewFlow(Collections.singletonList(command));
        return Result.getResult(ResultEnum.SUCCESS,list.toString());
    }
}
