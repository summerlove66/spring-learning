package com.park.Test;


import com.park.Service.AcctService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring-tx.xml")
public class AcctTest {
    @Resource(name = "acctServiceTransaction")
    private AcctService acctService;
    @Test
    public void demo1() {
        System.out.println(acctService);
        acctService.transfer("aa", "bb", 100);
    }

}
