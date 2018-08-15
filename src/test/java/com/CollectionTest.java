package com;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 白超 on 2018/8/15.
 */
public class CollectionTest {

    @Test
    void ListVsMap() {

        List<String> list = new ArrayList<>();

        Map<String, String> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 10000; i++) {
            list.add(i + "");
            map.put(i + "", i + "");
        }

        String find = "5000";
        String result = null;
        Long st = System.nanoTime();
/*        for (String s : list) {
            if (find.equals(s)) {
                result = s;
                break;
            }
        }*/
        result = map.get(find);
        Long et = System.nanoTime() - st;

        System.out.println("耗时" + et + "纳秒 " + et / 1000000 + "毫秒 结果:" + result);
    }
}
