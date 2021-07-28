package org.geektimes.interceptor.jdk;

import org.geektimes.interceptor.ChainableInvocationContext;
import org.geektimes.interceptor.ReflectiveMethodInvocationContext;

import javax.interceptor.InvocationContext;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JdkMethodInterceptorAdapter implements InvocationHandler {
    private final Object target;

    private final Object[] interceptors;

    public JdkMethodInterceptorAdapter(Object target, Object[] interceptors) {
        this.target = target;
        this.interceptors = interceptors;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        InvocationContext delegateContext = new ReflectiveMethodInvocationContext(proxy, method, args);
        ChainableInvocationContext context = new ChainableInvocationContext(delegateContext, interceptors);
        return context.proceed();
    }
}
