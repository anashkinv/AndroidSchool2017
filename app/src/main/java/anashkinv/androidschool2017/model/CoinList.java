package anashkinv.androidschool2017.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CoinList {
    @SerializedName("Response")
    private String response;
    @SerializedName("BaseImageUrl")
    private String baseImageUrl;
    @SerializedName("BaseLinkUrl")
    private String baseLinkUrl;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getBaseImageUrl() {
        return baseImageUrl;
    }

    public void setBaseImageUrl(String baseImageUrl) {
        this.baseImageUrl = baseImageUrl;
    }

    public String getBaseLinkUrl() {
        return baseLinkUrl;
    }

    public void setBaseLinkUrl(String baseLinkUrl) {
        this.baseLinkUrl = baseLinkUrl;
    }

    private ArrayList<Coin> coinList = new ArrayList<>();

    public ArrayList<Coin> getCoinList() {
        return coinList;
    }

    public void setCoinList(ArrayList<Coin> coinList) {
        this.coinList = coinList;
    }

    public void addCoin(Coin coin) {
        coinList.add(coin);
    }
}
