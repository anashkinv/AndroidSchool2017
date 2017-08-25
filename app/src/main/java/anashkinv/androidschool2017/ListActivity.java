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
import anashkinv.androidschool2017.recycleview.CoinAdapter;
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
        // Создаем экземпляр запроса со всем необходимыми настройками
        Call<CoinList> call = cryptoService.getCoinList();

        // Отображаем progress bar
        loadingDialog.show();

        // Выполняем запрос асинхронно
        call.enqueue(new Callback<CoinList>() {

            // В случае если запрос выполнился успешно, то мы переходим в метод onResponse(...)
            @Override
            public void onResponse(@NonNull Call<CoinList> call, @NonNull Response<CoinList> response) {
                if (response.isSuccessful()) {
                    // Если в ответ нам пришел код 2xx, то отображаем содержимое запроса
                    fillCoinInfo(response.body().getCoinList());

                } else {
                    System.out.println(response);
                    // Если пришел код ошибки, то обрабатываем её
                    Toast.makeText(ListActivity.this, R.string.network_error, Toast.LENGTH_SHORT).show();
                }

                // Скрываем progress bar
                loadingDialog.dismiss();
            }

            // Если запрос не удалось выполнить, например, на телефоне отсутствует подключение к интернету
            @Override
            public void onFailure(@NonNull Call<CoinList> call, @NonNull Throwable t) {
                // Скрываем progress bar
                loadingDialog.dismiss();

                Toast.makeText(ListActivity.this, R.string.network_error, Toast.LENGTH_SHORT).show();
                Log.d("Error", t.getMessage());
            }
        });
    }

    private void fillCoinInfo(ArrayList<Coin> coinList) {
        // Создаём и задаём адаптер данных
        coinListView.setAdapter(new CoinAdapter((List) coinList));
    }
}
