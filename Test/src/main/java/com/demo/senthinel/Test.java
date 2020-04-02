package com.demo.senthinel;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.ListenableFuture;
import com.ning.http.client.Response;

import java.util.Random;

/**
 * @author ligen
 * @title: Test
 * @projectName sentinel
 * @description:
 * @date 2020/3/2411:48
 */
public class Test {

    public static void main(String[] args) {
        // 测试限流
        test(25, "/test2");
        // 测试降级
        test(5, "/test3");

    }

    /**
     * @ desc : 测试限流
     * @ params
     * @ return
     * @ date 2020/3/31
     * @ author ligen
     */
    public static void test(int threadCnt, String resource) {
        for (int i1 = 0; i1 < threadCnt; i1++) {
            AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
            Random random = new Random();
            Thread thread = new Thread(
                    () -> {
                        while (true) {
                            ListenableFuture<Response> execute = asyncHttpClient.prepareGet("http://localhost:19966" + resource).execute();
                            System.out.println("resources："+resource+"-----"+Thread.currentThread().getName());
                            try {
                                Thread.sleep(random.nextInt(1000));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
            );
            thread.start();
        }
    }

}
