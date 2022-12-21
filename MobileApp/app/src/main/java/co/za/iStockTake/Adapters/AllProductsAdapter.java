package co.za.iStockTake.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;

import co.za.iStockTake.Models.Product;
import co.za.iStockTake.R;

public class AllProductsAdapter extends RecyclerView.Adapter<AllProductsAdapter.ProductHolder>
{
    private Context context;
    private ArrayList<Product> products;

    public AllProductsAdapter(Context context, ArrayList<Product> products)
    {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public AllProductsAdapter.ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.col_product_item, null, false);
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllProductsAdapter.ProductHolder holder, int position)
    {
        //Demo
        holder.productName.setText(products.get(position).getProductName());
        holder.productPrice.setText(String.valueOf(products.get(position).getProductPrice()));

        Picasso.get().load(R.drawable.asian_coconut_40_ml)
                .fit()
                .centerInside()
                .into(holder.productImage);

        holder.btnAddToList.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Add this product to the list of items.
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return products.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView productName, productPrice;
        ImageView productImage;
        Button btnAddToList;

        public ProductHolder(@NonNull View itemView)
        {
            super(itemView);

            btnAddToList = itemView.findViewById(R.id.btnAddproToList);
            productImage = itemView.findViewById(R.id.imageproView);
            productName = itemView.findViewById(R.id.proName);
            productPrice = itemView.findViewById(R.id.proPrice);
        }

        //Viewing the product
        @Override
        public void onClick(View v)
        {

        }
    }
}
