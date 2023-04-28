package com.sis.test.tcamp.controller;

import com.sis.test.tcamp.common.CommonImportDto;
import com.sis.test.tcamp.service.TongsuoService;
import com.sis.test.tcamp.utils.ShellUtils;
import com.sis.test.tcamp.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/sm2Sign")
    public Result sm2Sign(@RequestBody CommonImportDto body) throws IOException {
        return tongsuoService.sm2Sign(body);
    }

}
