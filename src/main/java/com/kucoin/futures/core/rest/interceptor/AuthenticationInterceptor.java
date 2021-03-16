/*
 * Copyright 2019 Mek Global Limited
 */
package com.kucoin.futures.core.rest.interceptor;

import com.google.common.base.Strings;
import com.kucoin.futures.core.constants.APIConstants;
import com.kucoin.futures.core.exception.KucoinFuturesApiException;
import lombok.Getter;
import lombok.Setter;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;

import static com.kucoin.futures.core.constants.APIConstants.API_HEADER_PASSPHRASE;

/**
 * Created by zicong.lu on 2018/12/14.
 */
@Getter
@Setter
public class AuthenticationInterceptor implements Interceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationInterceptor.class);

    private FuturesApiKey apiKey;

    /**
     * Constructor of API - keys are loaded from VM options, environment variables, resource files
     *
     * @param apiKey The API key.
     * @throws KucoinFuturesApiException in case of any error
     */
    public AuthenticationInterceptor(FuturesApiKey apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Validation we have API keys set up
     *
     * @throws KucoinFuturesApiException in case of any error
     */
    protected void validateCredentials() throws KucoinFuturesApiException {
        String humanMessage = ". Please check environment variables or VM options";
        if (Strings.isNullOrEmpty(this.apiKey.getKey())) {
            throw new KucoinFuturesApiException("Missing " + APIConstants.API_HEADER_KEY + humanMessage);
        }
        if (Strings.isNullOrEmpty(this.apiKey.getSecret())) {
            throw new KucoinFuturesApiException("Missing " + APIConstants.USER_API_SECRET + humanMessage);
        }
        if (Strings.isNullOrEmpty(this.apiKey.getPassPhrase())) {
            throw new KucoinFuturesApiException("Missing " + APIConstants.API_HEADER_PASSPHRASE + humanMessage);
        }
        if (StringUtils.isEmpty(this.apiKey.getKeyVersion())) {
            this.apiKey.setKeyVersion(APIConstants.DEFAULT_API_KEY_VERSION);
            LOGGER.info("Set keyVersion to default " + APIConstants.DEFAULT_API_KEY_VERSION);
        }
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        validateCredentials();
        Request original = chain.request();
        Request.Builder newRequestBuilder = original.newBuilder();

        String timestamp = String.valueOf(System.currentTimeMillis());
        String signature = genSignature(original, apiKey.getSecret(), timestamp);

        newRequestBuilder.addHeader(APIConstants.API_HEADER_KEY, apiKey.getKey());
        newRequestBuilder.addHeader(APIConstants.API_HEADER_SIGN, signature);
        newRequestBuilder.addHeader(APIConstants.API_HEADER_TIMESTAMP, timestamp);

        String passPhrase = apiKey.getPassPhrase();
        if (apiKey.getKeyVersion().equals(APIConstants.DEFAULT_API_KEY_VERSION)) {
            passPhrase = Base64.encodeBase64String(HmacUtils.hmacSha256(apiKey.getSecret(), passPhrase));
            newRequestBuilder.addHeader(APIConstants.API_HEADER_KEY_VERSION, apiKey.getKeyVersion());
        }
        newRequestBuilder.addHeader(API_HEADER_PASSPHRASE, passPhrase);

        // Build new request after adding the necessary authentication information
        Request newRequest = newRequestBuilder.build();
        return chain.proceed(newRequest);
    }

    /**
     * Generates signature info.
     *
     * @param request   The HTTP request.
     * @param apiSecret API secret.
     * @param timestamp Timestamp.
     * @return THe signature.
     */
    public static String genSignature(Request request, String apiSecret, String timestamp) {
        String endpoint = request.url().encodedPath();
        String requestUriParams = request.url().query();
        String requestBody = getRequestBody(request);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(timestamp);
        stringBuilder.append(request.method());
        stringBuilder.append(endpoint);

        stringBuilder.append((StringUtils.isBlank(requestUriParams) ? "" : "?" + requestUriParams));
        stringBuilder.append((StringUtils.isBlank(requestBody) ? "" : "" + requestBody));
        String originToSign = stringBuilder.toString();

        String signature = Base64.encodeBase64String(HmacUtils.hmacSha256(apiSecret, originToSign));

        LOGGER.debug("originToSign={}", originToSign);
        LOGGER.debug("method={},endpoint={}", request.method(), endpoint);
        LOGGER.debug("signature={}", signature);

        return signature;
    }

    /**
     * Get http request body info.
     *
     * @param request The request
     * @return The request body.
     */
    public static String getRequestBody(Request request) {
        if (request.body() == null) {
            return null;
        }
        Buffer buffer = new Buffer();
        try {
            request.body().writeTo(buffer);
        } catch (IOException e) {
            throw new RuntimeException("I/O error fetching request body", e);
        }

        //编码设为UTF-8
        Charset charset = Charset.forName("UTF-8");
        MediaType contentType = request.body().contentType();
        if (contentType != null) {
            charset = contentType.charset(Charset.forName("UTF-8"));
        }

        return buffer.readString(charset);
    }
}
