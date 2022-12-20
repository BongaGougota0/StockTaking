package co.za.iStockTake.Activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import co.za.iStockTake.R;

public class myProductListActivity extends AppCompatActivity
{
    /**
     *---------------- Class resource ids
     * listProducts - recycler view products
     * double - total - total value of cart
     * double - tax - tax
     */

    RecyclerView recyclerView;
    TextView txtTax, txtTotal;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_list_products_view);

        initViews();
    }

    public void initViews()
    {
        txtTax = findViewById(R.id.tax);
        txtTotal = findViewById(R.id.total);

        //recycler
        recyclerView = findViewById(R.id.listProducts);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}
