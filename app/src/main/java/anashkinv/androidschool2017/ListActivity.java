package anashkinv.androidschool2017;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import anashkinv.androidschool2017.api.CryptoCompOldApiFactory;
import anashkinv.androidschool2017.api.CryptoCompOldService;
import anashkinv.androidschool2017.model.Coin;
import anashkinv.androidschool2017.model.CoinList;
import anashkinv.androidschool2017.helpers.CoinAdapter;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {
    @BindView(R.id.coin_list)
    RecyclerView coinListView;

    @BindDrawable(R.drawable.divider_view)
    Drawable dividerView;

    private CryptoCompOldService cryptoService;
    private ProgressDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_list);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle(R.string.page_list_name);

        loadingDialog = ProgressDialog.show(this, "", getString(R.string.network_loading), true);
        cryptoService = CryptoCompOldApiFactory.getRetrofitInstance().create(CryptoCompOldService.class);

        coinListView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(dividerView);
        coinListView.addItemDecoration(itemDecoration);
        coinListView.setItemAnimator(new DefaultItemAnimator());

        getCoinList();
    }

    private void getCoinList() {
        Call<CoinList> call = cryptoService.getCoinList();

        loadingDialog.show();
        call.enqueue(new Callback<CoinList>() {

            @Override
            public void onResponse(@NonNull Call<CoinList> call, @NonNull Response<CoinList> response) {
                if (response.isSuccessful()) {
                    fillCoinInfo(response.body().getCoinList());

                } else {
                    System.out.println(response);
                    Toast.makeText(ListActivity.this, R.string.network_error, Toast.LENGTH_SHORT).show();
                }

                loadingDialog.dismiss();
            }

            @Override
            public void onFailure(@NonNull Call<CoinList> call, @NonNull Throwable t) {
                loadingDialog.dismiss();

                Toast.makeText(ListActivity.this, R.string.network_error, Toast.LENGTH_SHORT).show();
                Log.d("Error", t.getMessage());
            }
        });
    }

    private void fillCoinInfo(ArrayList<Coin> coinList) {
        coinListView.setAdapter(new CoinAdapter((List) coinList));
    }
}
