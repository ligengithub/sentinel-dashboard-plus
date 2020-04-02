package com.demo.senthinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ligen
 * @title: Main
 * @projectName sentinel
 * @description:
 * @date 2020/3/1911:00
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        // 配置规则.
        initFlowRules();

        while (true) {
            // 1.5.0 版本开始可以直接利用 try-with-resources 特性，自动 exit entry
            try (Entry entry = SphU.entry("HelloWorld")) {
                // 被保护的逻辑
//                System.out.println("hello world");
                say();

            } catch (BlockException ex) {
                // 处理被流控的逻辑
                Thread.sleep(10000);
                System.out.println("blocked!");
            }

            // 逻辑  2
            try (Entry entry = SphU.entry("HelloWorld2")) {
                // 被保护的逻辑
//                System.out.println("hello world");
                say2();
            } catch (BlockException ex) {
                // 处理被流控的逻辑
                Thread.sleep(10000);
                System.out.println("blocked!");
            }
        }
    }


    private static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<FlowRule>();
        // 规则1
        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 20.
        rule.setCount(20);
        rules.add(rule);
        // 规则2，3，.....

        FlowRuleManager.loadRules(rules);
    }


    @SentinelResource("HelloWorld")
    public static void say() {
        System.out.println("hello world");
    }

    @SentinelResource("HelloWorld")
    public static void say2() {
        System.out.println("hello world----2");
    }


}
