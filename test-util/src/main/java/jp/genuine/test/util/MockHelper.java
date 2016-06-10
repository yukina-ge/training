package jp.genuine.test.util;

import java.lang.reflect.Field;

import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.aop.target.dynamic.AbstractRefreshableTargetSource;
import org.springframework.util.ReflectionUtils;

public class MockHelper {

	@SuppressWarnings("unchecked")
	public static <T> T unwrap(T target) throws Exception {
		if (isWrapped(target)) {
			return (T) ((Advised) target).getTargetSource().getTarget();
		}
		return target;
	}

	public static boolean isWrapped(Object target) {
		return AopUtils.isAopProxy(target) && target instanceof Advised;
	}

	public static <T> T ingectMock(T target, String fieldName, Object mock) throws Exception {
		Field field = ReflectionUtils.findField(target.getClass(), fieldName);
		field.setAccessible(true);
		
		Object mocked = ReflectionUtils.getField(field, target);

		if (!isWrapped(mocked)) {
			ReflectionUtils.setField(field, unwrap(target), mock);
			return target;
		}

		((Advised) mocked).setTargetSource(new AbstractRefreshableTargetSource() {
			@Override
			protected Object freshTarget() {
				return mock;
			}
		});

		return target;
	}
}
