package com.geniu.base.stream;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Auther: zhongshibo
 * @Date: 2020/12/3 19:26
 */
public class TestStream {

    public static void main(String[] args) {
        TestStream testStream = new TestStream();
        testStream.test2();
    }
    public void test1() {
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
    public void test2() {
        List<SugarDetail> sugarDetails = new ArrayList<>();
        List<Integer> userIdList = Arrays.asList(160328866, 1231541541,123154131, 34765654, 1231415, 453453245, 12353443, 1231234423, 13466767, 676786767);
        for(int i = 0; i < 50000000; i++) {
            sugarDetails.add(new SugarDetail(new Random().nextInt(),userIdList.get(new Random().nextInt(10))));
        }
        for(int i = 0; i <100; i++) {
            Map<Integer, List<SugarDetail>> userSugarDetail = sugarDetails.stream().collect(Collectors.groupingBy(SugarDetail::getUserId));
            List<SugarDetail> sugarDetailList = userSugarDetail.get(160328866);
            long count = sugarDetailList.stream().mapToLong(SugarDetail::getDelta).count();

            System.out.println("i = " + i + ", count = " + count);
        }

    }

    @Data
    @AllArgsConstructor
    public static class SugarDetail {
        private long delta;
        private Integer userId;
    }
}
