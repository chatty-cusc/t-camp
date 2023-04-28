package com.sis.test.tcamp.controller;

import com.sis.test.tcamp.api.ShellUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping(value = "/test")
    public List<String> test(){
        return ShellUtils.executeNewFlow(Collections.singletonList("echo -n \"hello tongsuo\" | /lib/bin/tongsuo dgst -sm3"));
    }
}
