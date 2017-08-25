package anashkinv.androidschool2017.api;

import anashkinv.androidschool2017.model.DayPrice;
import anashkinv.androidschool2017.model.Price;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CryptoCompService {
    @GET("pricehistorical")
    Call<Price> getCoinPrice(@Query("fsym") String coinName, @Query("tsyms") String coinSymbols);

    @GET("histoday")
    Call<DayPrice> getDayCoinPrice(@Query("fsym") String fsym, @Query("tsym") String tsym, @Query("limit") int limit, @Query("aggregate") int aggregate);
}
