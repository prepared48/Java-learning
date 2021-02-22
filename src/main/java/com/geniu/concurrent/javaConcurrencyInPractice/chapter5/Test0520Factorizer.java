package com.geniu.concurrent.javaConcurrencyInPractice.chapter5;

import org.apache.commons.lang3.concurrent.Computable;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

/**
 * 在因式分解 servlet 中使用 Test0519Memoizer 来缓存结果
 *
 * @Author: zhongshibo
 * @Date: 2021/2/22 14:07
 */
public class Test0520Factorizer implements Servlet {

    private final Computable<BigInteger, BigInteger[]> c = new Computable<BigInteger, BigInteger[]>() {
        @Override
        public BigInteger[] compute(BigInteger arg) throws InterruptedException {
            return factor(arg);
        }
    };

    private BigInteger[] factor(BigInteger arg) {
        return null;
    }

    private final Computable<BigInteger, BigInteger[]> cache = new Test0519Memoizer<BigInteger, BigInteger[]>(c);

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse resp)
            throws ServletException, IOException {
        try {
            BigInteger i = extractFromRequest(req);
        } catch (InterruptedException e) {
            encodeError(resp, "factorization interrupted");
        }
    }

    private void encodeError(ServletResponse resp, String factorization_interrupted) {
    }

    private BigInteger extractFromRequest(ServletRequest req) throws InterruptedException {
        return null;
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
