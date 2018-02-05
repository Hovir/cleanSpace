package com.godfkc.center;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CenterApplicationTests {

    @Test
    public void contextLoads() {
        for (int i = 0; i < 100000; i++) {
            String time = String.valueOf(Calendar.getInstance().getTime().getTime());
            StringBuilder data = new StringBuilder(time.substring(time.length() - 5, time.length()));
            StringBuilder intFlag = new StringBuilder(String.valueOf((int) ((Math.random() * 9 + 1) * 10000)));
            String b = data.append(intFlag).toString();
            System.out.println(b);
        }
    }
}
