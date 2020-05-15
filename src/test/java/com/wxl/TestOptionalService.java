package com.wxl;


import com.wxl.system.System01Application;
import com.wxl.system.entity.Optional;
import com.wxl.system.entity.Sc;
import com.wxl.system.service.OptionalService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = System01Application.class)
@RunWith(SpringRunner.class)
public class TestOptionalService {

    @Autowired
    private OptionalService optionalService;

//  更新选课人数
    @Test
    public void testadd(){
        Sc sc = new Sc();
        sc.setCno("08");
        sc.setClassno("4");
        sc.setCredit(4);
        sc.setGrade(0.00);
        sc.setSno("2017110435");
        sc.setType("公共课");
        optionalService.addSc(sc);
    }


    @Test
    public void testaddOptional(){
        List<Optional> list = new ArrayList<>();
        Optional optional = new Optional();
        optional.setCno("08");
        optional.setCname("软件工程");
        optional.setCredit(4);
        optional.setTname("美华");
        optional.setPlace("A403");
        optional.setNumber(0);
        optional.setMax(10);

        Optional o2 = new Optional();
        o2.setCno("09");
        o2.setCname("软件工程");
        o2.setCredit(4);
        o2.setTname("美华");
        o2.setPlace("A403");
        o2.setNumber(0);
        o2.setMax(10);

        list.add(optional);
        list.add(o2);

        optionalService.addOptional(list);
    }
}
