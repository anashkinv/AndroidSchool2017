package anashkinv.androidschool2017.model;

import com.google.gson.annotations.SerializedName;

public class Coin {

    @SerializedName("Id")
    private String id;
    @SerializedName("Url")
    private String url;
    @SerializedName("ImageUrl")
    private String imageUrl;
    @SerializedName("Name")
    private String abbr;
    @SerializedName("CoinName")
    private String name;
    @SerializedName("FullName")
    private String fullName;
    @SerializedName("Algorithm")
    private String algorithm;
    @SerializedName("ProofType")
    private String proofType;
    @SerializedName("FullyPremined")
    private String fullyPremined;
    @SerializedName("TotalCoinSupply")
    private String totalCoinSupply;
    @SerializedName("PreMinedValue")
    private String preMinedValue;
    @SerializedName("TotalCoinsFreeFloat")
    private String totalCoinsFreeFloat;
    @SerializedName("SortOrder")
    private String sortOrder;

    private String baseImageUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return baseImageUrl + imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getProofType() {
        return proofType;
    }

    public void setProofType(String proofType) {
        this.proofType = proofType;
    }

    public String getFullyPremined() {
        return fullyPremined;
    }

    public void setFullyPremined(String fullyPremined) {
        this.fullyPremined = fullyPremined;
    }

    public String getTotalCoinSupply() {
        return totalCoinSupply;
    }

    public void setTotalCoinSupply(String totalCoinSupply) {
        this.totalCoinSupply = totalCoinSupply;
    }

    public String getPreMinedValue() {
        return preMinedValue;
    }

    public void setPreMinedValue(String preMinedValue) {
        this.preMinedValue = preMinedValue;
    }

    public String getTotalCoinsFreeFloat() {
        return totalCoinsFreeFloat;
    }

    public void setTotalCoinsFreeFloat(String totalCoinsFreeFloat) {
        this.totalCoinsFreeFloat = totalCoinsFreeFloat;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public void setBaseImageUrl(String baseImageUrl) {
        this.baseImageUrl = baseImageUrl;
    }
}
