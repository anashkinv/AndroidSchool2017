package anashkinv.androidschool2017;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;

import anashkinv.androidschool2017.api.CryptoCompApiFactory;
import anashkinv.androidschool2017.api.CryptoCompService;
import anashkinv.androidschool2017.helpers.LineChartBuilder;
import anashkinv.androidschool2017.model.DayPrice;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoinActivity extends AppCompatActivity {
    public static final String ABBR = "abbr";
    public static final String NAME = "name";
    public static final String USD = "usd";

    String coinAbbr;
    String coinName;
    String coinUsdPrice;

    @BindView(R.id.current_course)
    protected TextView currentCourse;
    @BindView(R.id.chart)
    LineChart chart;

    private CryptoCompService cryptoService;
    private ProgressDialog loadingDialog;
    private LineChartBuilder chartBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin);
        ButterKnife.bind(this);

        coinAbbr = getIntent().getStringExtra(ABBR);
        coinName = getIntent().getStringExtra(NAME);
        coinUsdPrice = getIntent().getStringExtra(USD);
        cryptoService = CryptoCompApiFactory.getRetrofitInstance().create(CryptoCompService.class);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.main_collapsing);
        collapsingToolbarLayout.setTitle(getCoinTitle());
        currentCourse.setText(getCurrentCourseText());

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getDayCoinHistory();
    }

    private String getCurrentCourseText() {
        return "1 " + coinAbbr + " = " + coinUsdPrice + " $";
    }

    private String getCoinTitle() {
        return coinName + " (" + coinAbbr + ")";
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.share)
    protected void share() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, getCoinTitle() + ": " + getCurrentCourseText());
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    private void getDayCoinHistory() {
        chartBuilder = new LineChartBuilder(chart);
        loadingDialog = ProgressDialog.show(this, "", getString(R.string.network_loading), true);

        Call<DayPrice> call = cryptoService.getDayCoinPrice(coinAbbr, "USD", 30, 1);

        loadingDialog.show();

        call.enqueue(new Callback<DayPrice>() {
            @Override
            public void onResponse(@NonNull Call<DayPrice> call, @NonNull Response<DayPrice> response) {
                if (response.isSuccessful()) {
                    chartBuilder.addData(response.body().getHistory());
                } else {
                    System.out.println(response);
                    Toast.makeText(CoinActivity.this, R.string.network_error, Toast.LENGTH_SHORT).show();
                }

                loadingDialog.dismiss();
            }

            @Override
            public void onFailure(@NonNull Call<DayPrice> call, @NonNull Throwable t) {
                loadingDialog.dismiss();

                Toast.makeText(CoinActivity.this, R.string.network_error, Toast.LENGTH_SHORT).show();
                Log.d("Error", t.getMessage());
            }
        });
    }
}
