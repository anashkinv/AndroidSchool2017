package anashkinv.androidschool2017.recycleview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import anashkinv.androidschool2017.CoinActivity;
import anashkinv.androidschool2017.R;
import anashkinv.androidschool2017.model.Coin;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
        Intent intent = new Intent(context, CoinActivity.class);
        intent.putExtra(CoinActivity.ABBR, coin.getAbbr());
        intent.putExtra(CoinActivity.NAME, coin.getName());
        context.startActivity(intent);
    }
}