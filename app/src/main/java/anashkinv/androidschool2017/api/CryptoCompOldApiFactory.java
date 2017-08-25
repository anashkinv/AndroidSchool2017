package anashkinv.androidschool2017.api;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import anashkinv.androidschool2017.api.Deserializer.CoinListDeserializer;
import anashkinv.androidschool2017.model.CoinList;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class CryptoCompOldApiFactory {

    private static final String API_BASE_URL = "https://www.cryptocompare.com/api/data/";
    private static Retrofit sRetrofit;
    private static OkHttpClient sHttpClient;

    private CryptoCompOldApiFactory() {
        throw new IllegalStateException("Final class can not be instantiated");
    }

    @NonNull
    public static Retrofit getRetrofitInstance() {
        if (sRetrofit == null) {
            Gson gson = (new GsonBuilder())
                    .registerTypeAdapter(CoinList.class, new CoinListDeserializer())
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
