package com.geniu.base.collection.set;

import org.junit.Assert;
import org.junit.Test;

import java.util.BitSet;

/**
 * @Author: zhongshibo
 * @Date: 2022/8/22 14:11
 */
public class TestBitSetT {

	public static void main(String[] args) {
		BitSet bitSet = new BitSet(1000000);
		for(int i = 100000; i < 1000000; i++ ) {
			bitSet.set(i);
		}
		Assert.assertEquals(true, bitSet.get(100001));
		Assert.assertEquals(false, bitSet.get(1000001));
	}

	@Test
	public void testSetAndGet() {
		BitSet bitSet = new BitSet(1000000);
		for(int i = 100000; i < 1000000; i++ ) {
			bitSet.set(i);
		}
		Assert.assertEquals(true, bitSet.get(100001));
		Assert.assertEquals(false, bitSet.get(1000001));
	}

	@Test
	public void testSize() {
		BitSet bitSet = new BitSet(1000000);
		Assert.assertEquals(1000000, bitSet.size());
	}

	@Test
	public void testCardinality() {
		BitSet bitSet = new BitSet(1000000);
		Assert.assertEquals(0, bitSet.cardinality());
		bitSet.set(10, 30);
		Assert.assertEquals(20, bitSet.cardinality());
	}

	/**
	 * 有交集为true，则返 true
	 */
	@Test
	public void testIntersects() {
		BitSet first = new BitSet();
		first.set(5, 10);

		BitSet second = new BitSet();
		second.set(7, 15);

		boolean intersects = first.intersects(second);
		Assert.assertEquals(true, intersects);

		// 逻辑与操作
		first.and(second);
		Assert.assertEquals(true, first.get(7));
		Assert.assertEquals(true, first.get(8));
		Assert.assertEquals(true, first.get(9));
		Assert.assertEquals(false, first.get(10));

		first.clear();
		first.set(5, 10);
		// 逻辑异或
		first.xor(second);
		for (int i = 5; i < 7; i++) {
			Assert.assertEquals(true, first.get(i));
		}
		for (int i = 10; i < 15; i++) {
			Assert.assertEquals(true, first.get(i));
		}
	}

	@Test
	public void testStream() {
		BitSet bitSet = new BitSet();
		bitSet.set(15, 25);

		bitSet.stream().forEach(System.out::println);
		// fromIndex本身包含在这个计算中。当BitSet中没有任何真位时，它将返回-1:
		Assert.assertEquals(15, bitSet.nextSetBit(13));
		Assert.assertEquals(16, bitSet.nextSetBit(16));
		Assert.assertEquals(-1, bitSet.nextSetBit(25));

		// 注意：位运算
		// nextClearBit(fromIndex) 返回从 fromIndex 开始的下一个清除索引
		Assert.assertEquals(25, bitSet.nextClearBit(23));
		Assert.assertEquals(14, bitSet.previousClearBit(23));
	}

	@Test
	public void testToByte() {
		BitSet bitSet = new BitSet();
		bitSet.set(15, 25);
		byte[] bytes = bitSet.toByteArray();
		long[] longs = bitSet.toLongArray();
	}

}
