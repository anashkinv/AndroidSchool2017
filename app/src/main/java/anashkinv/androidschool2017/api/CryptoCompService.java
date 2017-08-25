package anashkinv.androidschool2017.api;

import anashkinv.androidschool2017.model.Price;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CryptoCompService {
    @GET("pricehistorical")
    Call<Price> getCoinPrice(@Query("fsym") String coinName, @Query("symbols") String coinSymbols, @Query("ts") Long timeStamp, @Query("extraParams") String appName);
}
