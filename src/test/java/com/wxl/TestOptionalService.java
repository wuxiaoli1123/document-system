package com.wxl;


import com.wxl.system.System01Application;
import com.wxl.system.entity.Optional;
import com.wxl.system.entity.Sc;
import com.wxl.system.service.NoticeService;
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


    @Autowired
    private NoticeService noticeService;

    @Test
    public void testFindByCno(){
        Optional optional = optionalService.findByCno("08");
        System.out.println(optional);
    }

//  测试选课
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
    public void testdelNotice(){
        List<Integer> snum = new ArrayList<Integer>();
        snum.add(1);
        snum.add(2);
        noticeService.delNotice(snum);

    }

    @Test
    public void testaddOptional(){
        List<Optional> list = new ArrayList<>();
        Optional optional = new Optional();
        optional.setCno("21");
        optional.setCname("软件工程");
        optional.setCredit(4);
        optional.setTname("美华");
        optional.setPlace("A403");
        optional.setNumber(0);
        optional.setMax(10);
        optional.setGrade("2017");
        optional.setTerm("xx");
        optional.setTc_id(1);

        Optional o2 = new Optional();
        o2.setCno("20");
        o2.setCname("软件工程");
        o2.setCredit(4);
        o2.setTname("美华");
        o2.setPlace("A403");
        o2.setNumber(0);
        o2.setMax(10);
        o2.setGrade("2017");
        o2.setTerm("xx");
        o2.setTc_id(1);

        list.add(optional);
        list.add(o2);

        optionalService.addOptional(list);
    }
}
