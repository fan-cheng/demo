package com.demof.service.impl;

import com.demof.model.DemoModel;
import com.demof.service.DemoService;
import org.springframework.stereotype.Service;

@Service("demoService")
public class DemoServiceImpl implements DemoService {

    @Override
    public DemoModel findDemo() {
        DemoModel model = new DemoModel();
        model.setName("dubbo");
        model.setAge(23);
        model.setPhone("138888888888");
        return model;
    }
}
