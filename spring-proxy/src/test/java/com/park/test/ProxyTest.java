package com.park.test;


import com.park.model.Student;
import com.park.model.Teacher;
import com.park.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-tx.xml")
public class ProxyTest {
   @Resource
    private Teacher teacher;
    @Test
    public void demo(){

       // studentService.toCollege();
        teacher.teach();
    }

}
