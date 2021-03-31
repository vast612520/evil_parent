package com.bewind.evil;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class JobData {
    public static void main(String[] args) throws IOException {
        new ClassPathXmlApplicationContext("classpath:spring-jobs2Data.xml");
        System.in.read();
    }
}
