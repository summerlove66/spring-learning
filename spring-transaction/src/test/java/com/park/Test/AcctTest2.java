package com.park.Test;


import com.park.Service.AcctService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring-tx.xml")

public class AcctTest2 {
    @Resource(name="acctService")
    private AcctService acctService;
    @Test
    public void demo1(){
        acctService.transfer("aa","bb",200);
    }
}
