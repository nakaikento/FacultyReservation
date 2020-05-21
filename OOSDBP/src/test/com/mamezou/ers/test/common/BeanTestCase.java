/*
 * BeanTestCase.java
 * Copyright (C) 2005 Mamezou Co., Ltd. All rights reserved.
 */
package com.mamezou.ers.test.common;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Beanクラスのテストクラスの、抽象クラスです。
 * @author Mamezou
 *
 */
public abstract class BeanTestCase {
    protected Map<String, Field> fields;

    public BeanTestCase() {
        super();
        setFieldsAccessible();
    }

    /**
     * テスト対象クラスのprivateフィールド(変数)にアクセスできるように
     * 設定する.
     */
    private void setFieldsAccessible() {
        Field[] fields = getBeanClass().getDeclaredFields();
        AccessibleObject.setAccessible(fields, true);
        this.fields = new HashMap<String, Field>();
        for (int i = 0; i < fields.length; i++) {
            this.fields.put(fields[i].getName(), fields[i]);
        }
    }

    protected void setMockObject(Object target, String fieldName, Object value) throws Exception {
        Field targetField = fields.get(fieldName);
        targetField.set(target, value);
    }
    
    /**
     * テスト対象クラスのClassオブジェクトを取得します.
     * 
     * @return
     */
    protected abstract Class<?> getBeanClass();
}
