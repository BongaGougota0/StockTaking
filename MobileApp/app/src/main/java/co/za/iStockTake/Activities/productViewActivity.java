package co.za.iStockTake.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

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
            btnMinus, btnAdd, productCount;
    ArrayList<Product> productArrayList;

    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_main_product_view);

        initViews();

        //get data from previous activity, set data;

        //add or minus product count
        btnMinus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });


        //Set for related products
        productArrayList = new ArrayList<>();
        productArrayList = getRelatedProducts(txtProductCategory.toString());
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
        productCount = findViewById(R.id.productCount);
    }

    public ArrayList<Product> getRelatedProducts(String productCategory)
    {
        ArrayList<Product> arrayList = new ArrayList<>();
        //get data from API

        return arrayList;
    }
}


