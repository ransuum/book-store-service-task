package com.epam.rd.autocode.spring.project.tools;

import java.lang.reflect.Method;
import java.util.Arrays;

public class MethodChecker {

    public static boolean isMethodGetter(Method method) {
        return (method.getName().startsWith("get") || method.getName().startsWith("is")) && method.getParameterCount() == 0;
    }

    public static boolean isMethodSetter(Method method) {
        return method.getName().startsWith("set") && method.getParameterCount() == 1;
    }

    private static boolean isMethodAssignable(Method method, Class<?> aClass) {
        if (aClass.isAssignableFrom(method.getDeclaringClass())) {
            return Arrays.stream(aClass.getDeclaredMethods()).anyMatch(val -> isSameSignature(method, val));
        }
        return false;
    }

    private static boolean isSameSignature(Method method1, Method method2) {
        return method1.getName().equals(method2.getName()) &&
                method1.getReturnType().equals(method2.getReturnType()) &&
                Arrays.equals(method1.getParameterTypes(), method2.getParameterTypes());
    }

    public static boolean isMethodStartsWithAndIsAssignable(Method method, Class<?> clazz) {
        return isMethodAssignable(method, clazz);
    }
}
