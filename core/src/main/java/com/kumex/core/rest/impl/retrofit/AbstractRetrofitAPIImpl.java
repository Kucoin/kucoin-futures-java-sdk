/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kumex.core.rest.impl.retrofit;

import com.kumex.core.factory.KumexObjectMapper;
import com.kumex.core.exception.KumexApiException;
import com.kumex.core.rest.response.KumexResponse;
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
public abstract class AbstractRetrofitAPIImpl<T> {

    private static final Converter.Factory jacksonConverterFactory = JacksonConverterFactory.create(KumexObjectMapper.INSTANCE);

    @SuppressWarnings("unchecked")
    private static final Converter<ResponseBody, KumexResponse<?>> errorBodyConverter =
            (Converter<ResponseBody, KumexResponse<?>>) jacksonConverterFactory.responseBodyConverter(
                    KumexResponse.class, new Annotation[0], null);

    protected String baseUrl;

    protected String apiKey;

    protected String secret;

    protected String passPhrase;

    public abstract T getAPIImpl();

    /**
     * Execute a REST call and block until the response is received.
     * @throws IOException On socket related errors.
     */
    public <R> R executeSync(Call<KumexResponse<R>> call) throws IOException {
        Response<KumexResponse<R>> response = call.execute();
        if (response.isSuccessful() && response.body().isSuccessful()) {
            return response.body().getData();
        } else {
            KumexResponse<?> errorResponse;
            if (response.isSuccessful()) {
                errorResponse = response.body();
            } else {
                errorResponse = getErrorResponse(response);
            }
            throw new KumexApiException(errorResponse.getCode(), errorResponse.getMsg());
        }
    }

    /**
     * Extracts and converts the response error body into an object.
     */
    public KumexResponse getErrorResponse(Response<?> response) throws IOException, KumexApiException {
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
