package anashkinv.androidschool2017.model;

import com.google.gson.annotations.SerializedName;

public class Price {
    private String symbol;
    @SerializedName("BTC")
    private Double btc;
    @SerializedName("USD")
    private Double usd;
    @SerializedName("EUR")
    private Double eur;

    public Price() {
    }

    public Price(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getBtc() {
        return btc;
    }

    public void setBtc(Double btc) {
        this.btc = btc;
    }

    public Double getUsd() {
        return usd;
    }

    public void setUsd(Double usd) {
        this.usd = usd;
    }

    public Double getEur() {
        return eur;
    }

    public void setEur(Double eur) {
        this.eur = eur;
    }
}
