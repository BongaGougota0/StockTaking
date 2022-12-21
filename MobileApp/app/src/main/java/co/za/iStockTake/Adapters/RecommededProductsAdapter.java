package co.za.iStockTake.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import co.za.iStockTake.Models.Product;
import co.za.iStockTake.R;


public class RecommededProductsAdapter extends RecyclerView.Adapter<RecommededProductsAdapter.ProductHolder>
{
    private ArrayList<Product> products;
    private Context context;

    public RecommededProductsAdapter(ArrayList<Product> products, Context context)
    {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public RecommededProductsAdapter.ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lyt_product_view,parent);
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position)
    {
        //holder.imgView.setImageResource(products.get(position).getProductImage());
        holder.tvProductName.setText(products.get(position).getProductName());
        holder.tvProductCategory.setText(products.get(position).getProdutCategory());
        holder.tvProductPrice.setText(String.valueOf(products.get(position).getProductPrice()));
    }

    @Override
    public int getItemCount()
    {
        return this.products.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder
    {
        ImageView imgView;
        TextView tvProductName, tvProductPrice, tvProductCategory;

        public ProductHolder(@NonNull View itemView)
        {
            super(itemView);

            imgView = itemView.findViewById(R.id.imageView);
            tvProductName = itemView.findViewById(R.id.productName);
            tvProductPrice = itemView.findViewById(R.id.productPrice);
            tvProductCategory = itemView.findViewById(R.id.productCategory);
        }
    }
}
