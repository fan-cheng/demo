package com.demof;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )throws Exception
    {
        System.out.print(">>>>>>>>>>>>order服务启动中<<<<<<<<<<<");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/spring/spring.xml");
        context.start();
        System.in.read();
    }
}
