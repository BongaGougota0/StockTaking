package co.za.iStockTake.Activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import co.za.iStockTake.R;

public class allProductsActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        //ids
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_all_products_page);
        getSupportActionBar().hide();
    }
}
