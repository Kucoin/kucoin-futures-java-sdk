/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.impl.retrofit;

import com.kucoin.futures.core.factory.KucoinFuturesObjectMapper;
import com.kucoin.futures.core.rest.response.KucoinFuturesResponse;
import com.kucoin.futures.core.exception.KucoinFuturesApiException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;

/**
 * Created by chenshiwei on 2019/1/10.
 */
@Slf4j
public abstract class AbstractRetrofitAPIImpl<T> {

    private static final Converter.Factory jacksonConverterFactory = JacksonConverterFactory.create(KucoinFuturesObjectMapper.INSTANCE);

    @SuppressWarnings("unchecked")
    private static final Converter<ResponseBody, KucoinFuturesResponse<?>> errorBodyConverter =
            (Converter<ResponseBody, KucoinFuturesResponse<?>>) jacksonConverterFactory.responseBodyConverter(
                    KucoinFuturesResponse.class, new Annotation[0], null);

    protected String baseUrl;

    protected String apiKey;

    protected String secret;

    protected String passPhrase;

    public abstract T getAPIImpl();

    /**
     * Execute a REST call and block until the response is received.
     * @throws IOException On socket related errors.
     */
    public <R> R executeSync(Call<KucoinFuturesResponse<R>> call) throws IOException {
        Response<KucoinFuturesResponse<R>> response = call.execute();
        if (response.isSuccessful() && response.body().isSuccessful()) {
            log.debug(response.body().toString());
            return response.body().getData();
        } else {
            KucoinFuturesResponse<?> errorResponse;
            if (response.isSuccessful()) {
                errorResponse = response.body();
            } else {
                errorResponse = getErrorResponse(response);
            }
            throw new KucoinFuturesApiException(errorResponse.getCode(), errorResponse.getMsg());
        }
    }

    /**
     * Extracts and converts the response error body into an object.
     */
    public KucoinFuturesResponse getErrorResponse(Response<?> response) throws IOException, KucoinFuturesApiException {
        return errorBodyConverter.convert(response.errorBody());
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getPassPhrase() {
        return passPhrase;
    }

    public void setPassPhrase(String passPhrase) {
        this.passPhrase = passPhrase;
    }
}
