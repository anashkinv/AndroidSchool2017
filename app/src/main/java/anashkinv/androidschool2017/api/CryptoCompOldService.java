package anashkinv.androidschool2017.api;

import anashkinv.androidschool2017.model.CoinList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CryptoCompOldService {
    @GET("coinlist")
    Call<CoinList> getCoinList();
}
