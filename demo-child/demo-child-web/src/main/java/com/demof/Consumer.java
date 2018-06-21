package com.demof;

import com.demof.model.DemoModel;
import com.demof.service.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Consumer {
    public static void main(String[] args) {
        //Prevent to get IPV6 address,this way only work in debug mode
        //But you can pass use -Djava.net.preferIPv4Stack=true,then it work well whether in debug mode or not
        System.setProperty("java.net.preferIPv4Stack", "true");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring/spring.xml"});
        context.start();
        DemoService demoService = (DemoService) context.getBean("demoService"); // get remote service proxy
//        while (true) {
        try {
//                Thread.sleep(1000);
            DemoModel model = demoService.findDemo();

            System.out.print("名称："+model.getName()+",年龄："+model.getAge()+",手机号码："+model.getPhone()+"\n");


        } catch (Throwable throwable) {
            System.out.print(throwable);
            throwable.printStackTrace();
        }
    }

//    }
}
