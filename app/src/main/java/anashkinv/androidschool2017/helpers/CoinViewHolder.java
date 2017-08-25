package anashkinv.androidschool2017.helpers;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import anashkinv.androidschool2017.CoinActivity;
import anashkinv.androidschool2017.R;
import anashkinv.androidschool2017.api.CryptoCompApiFactory;
import anashkinv.androidschool2017.api.CryptoCompService;
import anashkinv.androidschool2017.model.Coin;
import anashkinv.androidschool2017.model.Price;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class CoinViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.coin_abbr)
    protected TextView abbr;

    @BindView(R.id.coin_name)
    protected TextView name;

    @BindView(R.id.coin_image)
    protected ImageView image;

    private Context context;
    private Coin coin;

    public CoinViewHolder(View itemView) {
        super(itemView);

        context = itemView.getContext();
        ButterKnife.bind(this, itemView);
    }

    void asset(Coin coin) {
        this.coin = coin;
        Context context = image.getContext();
        Picasso.with(context)
                .load(coin.getImageUrl())
                .into(image);

        abbr.setText(coin.getAbbr());
        name.setText(coin.getName());
    }

    @OnClick(R.id.coin_item)
    protected void openIntentExampleScreen() {
        Call<Price> call = CryptoCompApiFactory.getRetrofitInstance().create(CryptoCompService.class)
                .getCoinPrice(coin.getAbbr(), "BTC,USD,EUR");

        call.enqueue(new Callback<Price>() {

            @Override
            public void onResponse(@NonNull Call<Price> call, @NonNull Response<Price> response) {
                if (response.isSuccessful()) {
                    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                    DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
                    symbols.setGroupingSeparator(' ');
                    formatter.setDecimalFormatSymbols(symbols);

                    System.out.println(response.body().getUsd());
                    Intent intent = new Intent(context, CoinActivity.class);
                    intent.putExtra(CoinActivity.ABBR, coin.getAbbr());
                    intent.putExtra(CoinActivity.NAME, coin.getName());
                    intent.putExtra(CoinActivity.USD, formatter.format(response.body().getUsd()));
                    context.startActivity(intent);

                } else {
                    System.out.println(response);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Price> call, @NonNull Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}