package anashkinv.androidschool2017.api;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

class AuthenticationInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();
        HttpUrl url = originalHttpUrl.newBuilder().build();
        Request.Builder requestBuilder = original.newBuilder().url(url);

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}