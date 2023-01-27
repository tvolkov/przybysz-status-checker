package com.tvolkov.pbs.client;


import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.DefaultServiceUnavailableRetryStrategy;
import org.apache.http.impl.client.HttpClientBuilder;

import java.util.concurrent.TimeUnit;

public class TestClient {

    public static HttpClient httpClient() {
        return HttpClientBuilder.create()
                .useSystemProperties()
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setMaxConnTotal(40)
                .setMaxConnPerRoute(20)
                .setConnectionTimeToLive(60, TimeUnit.SECONDS)
                .evictExpiredConnections()
                .setDefaultRequestConfig(RequestConfig.copy(RequestConfig.DEFAULT)
                        .setConnectionRequestTimeout(10000)
                        .setConnectTimeout(10000)
                        .setSocketTimeout(20000)
                        .build())
                .setRetryHandler(new DefaultHttpRequestRetryHandler())
                .setServiceUnavailableRetryStrategy(new DefaultServiceUnavailableRetryStrategy())
                .build();
    }
}
