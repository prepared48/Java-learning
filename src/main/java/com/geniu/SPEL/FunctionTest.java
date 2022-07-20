package com.geniu.SPEL;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @Author: zhongshibo
 * @Date: 2022/7/20 17:30
 */
public class FunctionTest {

	public static void main(String[] args) {
		testFunction();
	}

	/**
	 * 注册自定义函数
	 */
	public static void testFunction() {
		ExpressionParser parser = new SpelExpressionParser();
		StandardEvaluationContext context = new StandardEvaluationContext();

		try {
			context.registerFunction("reverseString",
					StringUtils.class.getDeclaredMethod("reverseString",
							new Class[] { String.class }));
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}

		String helloWorldReversed =
				parser.parseExpression("#reverseString('hello')").getValue(context, String.class);
	}

}
