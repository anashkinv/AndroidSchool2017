package anashkinv.androidschool2017.api;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import anashkinv.androidschool2017.api.Deserializer.PriceDeserializer;
import anashkinv.androidschool2017.model.Price;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class CryptoCompApiFactory {

    private static final String API_BASE_URL = "https://min-api.cryptocompare.com/data/";
    private static Retrofit sRetrofit;
    private static OkHttpClient sHttpClient;

    private CryptoCompApiFactory() {
        throw new IllegalStateException("Final class can not be instantiated");
    }

    @NonNull
    public static Retrofit getRetrofitInstance() {
        if (sRetrofit == null) {
            Gson gson = (new GsonBuilder())
                    .registerTypeAdapter(Price.class, new PriceDeserializer())
                    .create();

            sRetrofit = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .client(sHttpClient == null ? sHttpClient = provideClient() : sHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        return sRetrofit;
    }

    private static OkHttpClient provideClient() {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new AuthenticationInterceptor());

        return builder.build();
    }
}
