/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.rest.impl.retrofit;

import java.lang.reflect.ParameterizedType;

import com.kumex.core.factory.RetrofitFactory;

/**
 * Created by chenshiwei on 2019/1/10.
 */
public class PublicRetrofitAPIImpl<T> extends AbstractRetrofitAPIImpl<T> {

    private volatile boolean inited;
    private T apiImpl;

    @Override
    public T getAPIImpl() {
        if (inited)
            return apiImpl;
        synchronized (getClass()) {
            if (inited)
                return apiImpl;
            @SuppressWarnings("unchecked")
            Class<T> tClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                    .getActualTypeArguments()[0];
            T t = RetrofitFactory.getPublicRetorfit(baseUrl).create(tClass);
            apiImpl = t;
            return t;
        }
    }
}