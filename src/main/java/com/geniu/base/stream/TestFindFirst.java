package com.geniu.base.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * @Author: zhongshibo
 * @Date: 2022/8/25 17:36
 */
public class TestFindFirst {

	public static void main(String[] args) {
		List<User> userList = new ArrayList<>();
		userList.add(new User() {{
			setAge(10);
		}});
		userList.add(new User() {{
			setAge(12);
		}});
		userList.add(new User() {{
			setAge(8);
		}});
		Optional<User> first = userList.stream().sorted(new Comparator<User>() {
			@Override
			public int compare(User o1, User o2) {
				return o1.getAge() - o2.getAge();
			}
		}).findFirst();
		System.out.println(first.get().getAge());
	}
}
