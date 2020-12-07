package com.geniu.base.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Auther: zhongshibo
 * @Date: 2020/12/3 19:26
 */
public class TestStream {

    public static void main(String[] args) {
        List<User> testList = new ArrayList<>();
        User user = new User();
        user.setUserId(1L);
        user.setUserName("test");
        testList.add(user);
        User user2 = new User();
        user2.setUserId(1L);
        user2.setUserName("test2");
        testList.add(user2);
        Map<Long, String> doctorIdLogoMap = testList.stream()
                .collect(Collectors.toMap(User::getUserId, val -> val.getUserName(), (v1, v2) -> v2));
        System.out.println(doctorIdLogoMap);
    }
}
