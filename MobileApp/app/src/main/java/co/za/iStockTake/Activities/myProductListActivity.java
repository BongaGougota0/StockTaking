package co.za.iStockTake.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import co.za.iStockTake.Adapters.ProductListAdapter;
import co.za.iStockTake.Interfaces.ChangeNumberItemsListener;
import co.za.iStockTake.ManagementList;
import co.za.iStockTake.R;

public class myProductListActivity extends AppCompatActivity
{
    /**
     *---------------- Class resource ids
     * listScrollView - scrollview for products recycler
     * listProducts - recycler view products
     * emptyList - if on products on list
     *
     * -----
     * double - total - total value of cart
     * double - tax - tax calculated
     */

    ScrollView scrollView;
    ProductListAdapter adapter;
    RecyclerView recyclerView;
    TextView txtTax, txtTotal;
    ManagementList managementList;

    ImageView home_, logout_, specials_, trending_;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_list_products_view);
        getSupportActionBar().hide();

        managementList = new ManagementList(this);

        txtTax = findViewById(R.id.tax);
        txtTotal = findViewById(R.id.total);
        scrollView = findViewById(R.id.listScrollView);
        recyclerView = findViewById(R.id.listProducts);

        initlist();
        calculateListCost();
        bottomNavigation();
    }

    public void initlist()
    {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new ProductListAdapter(managementList.getList(),this,new ChangeNumberItemsListener(){
            @Override
            public void changed()
            {
                calculateListCost();
            }
        });

        recyclerView.setAdapter(adapter);
    }

    private void calculateListCost()
    {
        double vat = 0.15;

        double tax = Math.round((managementList.getTotalCost()*vat)*100/100);
        txtTax.setText(String.valueOf(tax));
        double total= Math.round((managementList.getTotalCost()*100)/100);
        txtTotal.setText("R "+total);
    }

    private void bottomNavigation()
    {
        FloatingActionButton fap = findViewById(R.id.myList);
        LinearLayout home = findViewById(R.id.btnLayout);
        home_ = findViewById(R.id.home);
        trending_ = findViewById(R.id.trending);
        specials_ = findViewById(R.id.sale);
        logout_ = findViewById(R.id.logout);

        fap.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //startActivity(new Intent(myProductListActivity.this, myProductListActivity.class));
                Toast.makeText(myProductListActivity.this, "Already here.", Toast.LENGTH_SHORT).show();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(myProductListActivity.this, homeActivity.class));
            }
        });

        trending_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(myProductListActivity.this,"View trending items", Toast.LENGTH_SHORT).show();
            }
        });

        specials_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(myProductListActivity.this,"Specials.", Toast.LENGTH_SHORT).show();
            }
        });

        logout_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(myProductListActivity.this,"Away!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
