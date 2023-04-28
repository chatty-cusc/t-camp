package com.sis.test.tcamp.controller;

import com.sis.test.tcamp.api.ShellUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping(value = "/test")
    public List<String> test() throws IOException {
        File directory = new File("src/main/resources");
        String reportPath = directory.getCanonicalPath();
        String resource =reportPath + "/tongsuo/bin/tongsuo";
        System.out.println(resource);
        return ShellUtils.executeNewFlow(Collections.singletonList("echo -n \"hello tongsuo\" | "+resource+" dgst -sm3"));
    }


    @GetMapping(value = "/test2")
    public List<String> test2(){
        return ShellUtils.executeNewFlow(Collections.singletonList("echo -n \"hello tongsuo\" | /opt/tongsuo/bin/tongsuo dgst -sm3"));
    }


    @GetMapping(value = "/test3")
    public List<String> test3() throws IOException {
        File directory = new File("src/main/resources");
        String reportPath = directory.getCanonicalPath();
        String resource =reportPath + "/tongsuo/bin/tongsuo";
        System.out.println(resource);
        return ShellUtils.executeNewFlow(Collections.singletonList("echo -n \"hello tongsuo\" | /src/main/resources/lib/bin/tongsuo dgst -sm3"));
    }
}
