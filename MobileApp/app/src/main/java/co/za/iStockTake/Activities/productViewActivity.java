package co.za.iStockTake.Activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
    TextView txtProductName, txtProductSellPrice, txtProductStoreName, txtProductDescription;

    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_home_page);

        imageView = findViewById(R.id.productImageView);
        txtProductName = findViewById(R.id.productName);
        txtProductSellPrice = findViewById(R.id.productSellPrice);
        txtProductDescription = findViewById(R.id.productDescription);
        txtProductStoreName = findViewById(R.id.storeName);
    }
}


