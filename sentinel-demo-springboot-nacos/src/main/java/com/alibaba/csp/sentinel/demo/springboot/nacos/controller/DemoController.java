/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.csp.sentinel.demo.springboot.nacos.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Eric Zhao
 */
@RestController
public class DemoController {

    Logger logger = LoggerFactory.getLogger(DemoController.class);


    @Order(1)
    @SentinelResource(value = "test1", blockHandler = "myBlockHandler", fallbackClass = FallBack.class, fallback = "test1FallBack")
    @GetMapping("/test1")
    public String test() {
        System.out.println("test1 zi源被访问");
        return String.valueOf(System.currentTimeMillis());
    }

    @GetMapping("/test2")
    public String test2() {
        System.out.println("test2 zi源被访问");
        return String.valueOf(System.currentTimeMillis());
    }

    // 测试degrade
    @GetMapping("/test3")
    public String test3() throws InterruptedException {
        Thread.sleep(5000);
        return String.valueOf(System.currentTimeMillis());
    }

    @GetMapping("/test4")
    public String test4(@PathVariable String data) throws InterruptedException {
        if (StringUtil.equals(data, "1")) {
            throw new RuntimeException("degrade by exception");
        }
        return String.valueOf(System.currentTimeMillis());
    }


    public String myBlockHandler(Throwable e) {
        e.printStackTrace();
        System.out.println("--------------block--------------");
        return "block";
    }

    class FallBack {
        public String test1FallBack(Throwable throwable) {
            System.out.println("发生异常");
            return "my fallback";
        }

    }

}
