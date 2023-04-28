package com.sis.test.tcamp.controller;

import com.sis.test.tcamp.api.ShellUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping(value = "/test")
    public String test(){
        return ShellUtils.updateApprovalSh("echo -n \"hello tongsuo\" | /opt/tongsuo/bin/tongsuo dgst -sm3");
    }


    @GetMapping(value = "/test1")
    public String test1(){
        return ShellUtils.updateApprovalSh("echo -n \"hello tongsuo\" | /lib/bin/tongsuo dgst -sm3");
    }


    @GetMapping(value = "/test2")
    public List<String> test2(){
        List<String>  list = ShellUtils.exceShell("echo -n \"hello tongsuo\" | /lib/bin/tongsuo dgst -sm3");
        return list;
    }


}
