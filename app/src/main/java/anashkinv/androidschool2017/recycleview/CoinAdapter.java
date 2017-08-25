package anashkinv.androidschool2017.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import anashkinv.androidschool2017.R;
import anashkinv.androidschool2017.model.Coin;

public class CoinAdapter extends RecyclerView.Adapter<CoinViewHolder> {

    private List<Coin> coins;

    public CoinAdapter(List<Coin> coins) {
        this.coins = coins;
    }

    @Override
    public CoinViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View rowView = layoutInflater.inflate(R.layout.activity_coin_item, parent, false);
        return new CoinViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(CoinViewHolder holder, int position) {
        holder.asset(coins.get(position));
    }

    @Override
    public int getItemCount() {
        return coins.size();
    }

    public void add(int i, List<Coin> coinList) {
        coins.addAll(i, coinList);
        notifyItemRangeInserted(i, coinList.size());
    }
}