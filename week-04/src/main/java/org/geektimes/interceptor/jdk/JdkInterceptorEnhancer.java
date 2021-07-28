/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.geektimes.interceptor.jdk;


import net.sf.cglib.proxy.Enhancer;
import org.geektimes.interceptor.cglib.MethodInterceptorAdapter;

import javax.interceptor.Interceptor;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * {@link Interceptor @Interceptor} enhancer by CGLIB
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
public class JdkInterceptorEnhancer {

    public Object enhance(Object target, Object... interceptors) {
        Class<?> targetClass = target.getClass();
        JdkMethodInterceptorAdapter jdkMethodInterceptorAdapter = new JdkMethodInterceptorAdapter(target, interceptors);
        return Proxy.newProxyInstance(targetClass.getClassLoader(), targetClass.getInterfaces(), jdkMethodInterceptorAdapter);
    }

}
