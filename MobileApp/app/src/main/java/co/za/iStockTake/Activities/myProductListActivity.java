package co.za.iStockTake.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
<<<<<<< HEAD
        txtTax.setText("Tax @ 15% :"+tax);
=======
        txtTax.setText(String.valueOf(tax));
>>>>>>> ListManagerBranch
        double total= Math.round((managementList.getTotalCost()*100)/100);
        txtTotal.setText("R "+total);
    }

    private void bottomNavigation()
    {
        FloatingActionButton fap = findViewById(R.id.myList);
        LinearLayout home = findViewById(R.id.btnLayout);

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
    }
}
