package com.sis.test.tcamp.controller;

import com.sis.test.tcamp.common.CommonImportDto;
import com.sis.test.tcamp.common.ResultEnum;
import com.sis.test.tcamp.service.TongsuoService;
import com.sis.test.tcamp.utils.ShellUtils;
import com.sis.test.tcamp.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;


/**
 * @author chatty
 * @create 2023-04-28 15:53
 */

@RestController
@RequestMapping("/tongsuo")
public class TestController {

    @Autowired
    private TongsuoService tongsuoService;

    @GetMapping(value = "/test")
    public List<String> test(){
        return ShellUtils.executeNewFlow(Collections.singletonList("echo -n \"hello tongsuo\" | /opt/tongsuo/bin/tongsuo dgst -sm3"));
    }

    @PostMapping(value = "/sm4CbcEncrypt")
    public Result sm4CbcEncrypt(@RequestBody CommonImportDto body) {
        return tongsuoService.sm4CbcEncrypt(body);
    }


    @PostMapping(value = "/sm4CbcDecode")
    public Result sm4CbcDecode(@RequestBody CommonImportDto body) {
        return tongsuoService.sm4CbcDecode(body);
    }


    @PostMapping(value = "/sm3Dgst")
    public Result sm3Dgst(@RequestBody CommonImportDto body) {
        return tongsuoService.sm3Dgst(body);
    }

    @PostMapping(value = "/sm2Verify")
    public Result sm2Verify(@RequestBody CommonImportDto body) throws IOException {
        return tongsuoService.sm2Verify(body);
    }

//    @PostMapping(value = "/sm2Sign")
//    public Result sm2Sign(@RequestBody CommonImportDto body) throws IOException {
//        return tongsuoService.sm2Sign(body);
//    }

    @PostMapping(value = "/upload")
    public Result upload(String type , MultipartFile file) throws IOException {
        //得到文件"upload"的服务器路径
        String sysPath="/opt/"+type+"/";
        String fileName;
        if(type.equals("3")){
             fileName="sigfile";    //得到文件名
        }else{
            fileName="msg.bin";    //得到文件名
        }

        try{
            File filePath=new File(sysPath,fileName);    //得到文件路径
            if(!filePath.getParentFile().exists()){    //判断服务器当前路径文件夹是否存在
                filePath.getParentFile().mkdirs();    //不存在则创建文件夹
            }
            BufferedOutputStream out=new BufferedOutputStream(new FileOutputStream(filePath));
            out.write(file.getBytes());
            out.flush();
            out.close();
        }catch(Exception e){
            return Result.getResult(ResultEnum.FAILED);
        }
        return Result.getResult(ResultEnum.SUCCESS);
    }

    @PostMapping(value = "/download")
    public void download(@RequestBody CommonImportDto body, HttpServletResponse response) throws IOException {
        tongsuoService.sm2Sign(body,response);
    }
}
