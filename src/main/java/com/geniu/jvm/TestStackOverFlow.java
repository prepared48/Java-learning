package com.geniu.jvm;

public class TestStackOverFlow {

	private int i = 0;


	public static void main(String[] args) {
		TestStackOverFlow testStackOverFlow = new TestStackOverFlow();
		try {
			testStackOverFlow.abc();
		} catch (Exception e) {
			System.out.println("Exception:stack length:" + testStackOverFlow.i);
		} catch (Error e) {

			System.out.println("Error:stack length:" + testStackOverFlow.i);

			e.printStackTrace();

		}

	}

	public void abc() {
		i++;
		abc();

	}
}
