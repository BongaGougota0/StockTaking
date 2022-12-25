package co.za.iStockTake.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import co.za.iStockTake.ManagementList;
import co.za.iStockTake.Models.Product;
import co.za.iStockTake.R;

public class productViewActivity extends AppCompatActivity
{
    /**
     * --Product view page ids
     * productImageView - image of the product
     * productName - name of product..
     * productDescription -
     * productSellPrice -
     * storeName -
     * productCount - the number to add to cart (variables size + or -)
     * plusBtn - button to decrement productCount
     * minusBtn - button to increment productCount
     *
     * ------------Recycler view Use Related Products Adapter
     * relatedProductsRV - recycler view of related products
     */
    ImageView imageView;
    TextView txtProductName, txtProductSellPrice, txtProductStoreName, txtProductDescription, txtProductCategory,
            btnMinus, btnAdd, productCount, addtolist;

    RecyclerView relatedProducts;
    int numberOrder = 1;
    ArrayList<Product> productArrayList;
    private Product product;
    private ManagementList managementList;


    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_main_product_view);
        getSupportActionBar().hide();

        initViews();

        getBundle();
        bottomNavigation();

        managementList = new ManagementList(this);

        //add or minus product count
        btnMinus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(numberOrder > 1)
                {
                    numberOrder = numberOrder -1;
                }
                productCount.setText(String.valueOf(numberOrder));
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                numberOrder = numberOrder + 1;
                productCount.setText(String.valueOf(numberOrder));
            }
        });

        addtolist.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //take the number of this product and add to list
                product.setProductCount(numberOrder);
                managementList.inserToList(product);
            }
        });

        //Set for related products
//        productArrayList = new ArrayList<>();
//        productArrayList = getRelatedProducts(txtProductCategory.toString());
    }

    public void initViews()
    {
        imageView = findViewById(R.id.productImageView);
        txtProductName = findViewById(R.id.productName);
        txtProductSellPrice = findViewById(R.id.productSellPrice);
        txtProductDescription = findViewById(R.id.productDescription);
        txtProductStoreName = findViewById(R.id.storeName);
        btnAdd = findViewById(R.id.plusBtn);
        btnMinus = findViewById(R.id.minusBtn);
        addtolist = findViewById(R.id.addToMyList);
        relatedProducts = findViewById(R.id.relatedProductsRV);
        productCount = findViewById(R.id.productCount);
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
                startActivity(new Intent(productViewActivity.this, myProductListActivity.class));
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent( productViewActivity.this, homeActivity.class));
            }
        });
    }

    //get data from previous activity, set data;
    public void getBundle()
    {
        product = (Product) getIntent().getSerializableExtra("product");

        txtProductName.setText(product.getProductName());
        txtProductDescription.setText(product.getProductDescription());
        txtProductSellPrice.setText(String.valueOf(product.getProductPrice()));
        txtProductStoreName.setText(product.getProdutCategory());

        Picasso.get().load(R.drawable.placeholder)
                .fit()
                .centerInside();
                //.into(product.getProductImage());
    }

    public ArrayList<Product> getRelatedProducts(String productCategory)
    {
        ArrayList<Product> arrayList = new ArrayList<>();
        //get data from API

        return arrayList;
    }
}


