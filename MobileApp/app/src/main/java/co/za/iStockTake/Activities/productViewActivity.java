package co.za.iStockTake.Activities;

import android.os.Bundle;
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
     *
     * ------------Recycler view Use Related Products Adapter
     * relatedProductsRV - recycler view of related products
     */
    ImageView imageView;
    TextView txtProductName, txtProductSellPrice, txtProductStoreName, txtProductDescription, txtProductCategory;
    ArrayList<Product> productArrayList;

    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_main_product_view);

        imageView = findViewById(R.id.productImageView);
        txtProductName = findViewById(R.id.productName);
        txtProductSellPrice = findViewById(R.id.productSellPrice);
        txtProductDescription = findViewById(R.id.productDescription);
        txtProductStoreName = findViewById(R.id.storeName);


        //Set for related products
        productArrayList = new ArrayList<>();
        productArrayList = getRelatedProducts(txtProductCategory.toString());
    }

    public ArrayList<Product> getRelatedProducts(String productCategory)
    {
        ArrayList<Product> arrayList = new ArrayList<>();
        //get data from API

        return arrayList;
    }
}


