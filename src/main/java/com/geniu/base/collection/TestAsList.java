package com.geniu.base.collection;

import java.util.Arrays;
import java.util.List;

/**
 * 测试 asList 方法
 *
 * @Author: zhongshibo
 * @Date: 2020/9/25 09:59
 */
public class TestAsList {


	public static void main(String[] args) {
		String[] headers = {"序号", "路线名称", "任务名称", "开始时间", "结束时间", "路线长度/km", "历史拥堵指数", "实际拥堵指数", "交通状态恢复时间/minin", "特勤交通", "影响指数"};
		List<String> strings = Arrays.asList(headers);
		System.out.println(strings.size());

	}
}
