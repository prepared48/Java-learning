package com.geniu.SPEL;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.GregorianCalendar;

/**
 * @Author: zhongshibo
 * @Date: 2022/7/20 15:27
 */
public class ParserTest {
	public static void main(String[] args) {
////		testParse1();
////		testParse2();
////		testParse3();
////		testParse4();
////		testParse5();
//		testParse6();
//		testParse7();
//		testParse8();
		testParse9();
	}

	public static void testParse1() {
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression("'Hello World'");
		String message = (String) exp.getValue();
		System.out.println("result ==> " + message);
	}

	/**
	 * 表达式中 使用 concat 方法
	 */
	public static void testParse2() {
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression("'Hello World'.concat('!!!!')");
		String message = (String) exp.getValue();
		System.out.println("result ==> " + message);
	}

	/**
	 * 调用 javaBean 的属性
	 */
	public static void testParse3() {
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression("'Hello World'.bytes");
		byte[] bytes = (byte[])exp.getValue();
		System.out.println("result ==> " + bytes);
	}

	/**
	 * 点 表示法 嵌套
	 */
	public static void testParse4() {
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression("'Hello World'.bytes.length");
		int length = (Integer)exp.getValue();
		System.out.println("result ==> " + length);
	}

	/**
	 * 表达式中 使用构造函数
	 */
	public static void testParse5() {
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression("new String('Hello World').toUpperCase()");
		String message = exp.getValue(String.class);
		System.out.println("result ==> " + message);
	}

	/**
	 * 针对特定对象实例 进行评估
	 */
	public static void testParse6() {
		// Create and set a calendar
		GregorianCalendar c = new GregorianCalendar();
		c.set(1856, 7, 9);

//  The constructor arguments are name, birthday, and nationality.
		Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");

		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression("name=='Nikola Tesla'");
		EvaluationContext context = new StandardEvaluationContext(tesla);

		Boolean name = (Boolean) exp.getValue(context);
		System.out.println("result ==> " + name); // 返回 true
	}


	/**
	 * 如果 root Object 会变，root Object 放在 getValue 参数中
	 */
	public static void testParse7() {
		// Create and set a calendar
		GregorianCalendar c = new GregorianCalendar();
		c.set(1856, 7, 9);

//  The constructor arguments are name, birthday, and nationality.
		Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");

		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression("name=='Nikola Tesla'");

		Boolean name = (Boolean) exp.getValue(tesla);
		System.out.println("result ==> " + name); // 返回 true
	}

	/**
	 * setValue 自动类型转换。String 自动转成 Boolean
	 */
	public static void testParse8() {
		Simple simple = new Simple();

		simple.booleanList.add(true);
		ExpressionParser parser = new SpelExpressionParser();
		StandardEvaluationContext simpleContext = new StandardEvaluationContext(simple);

		// false is passed in here as a string.  SpEL and the conversion service will
		// correctly recognize that it needs to be a Boolean and convert it
		parser.parseExpression("booleanList[0]").setValue(simpleContext, "true");

		// b will be false
		Boolean b = simple.booleanList.get(0);
		System.out.println("result.b == >" + b);
	}


	public static void testParse9() {
		// Create and set a calendar
		GregorianCalendar c = new GregorianCalendar();
		c.set(1856, 7, 9);

//  The constructor arguments are name, birthday, and nationality.
		TestCity tesla = new TestCity("Nikola Tesla", "上海");

		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression("name=='Nikola Tesla' and (#jumpCity=='北京' or #jumpCity=='上海')");

		Boolean name = (Boolean) exp.getValue(tesla);
		System.out.println("result ==> " + name); // 返回 true
	}

}
