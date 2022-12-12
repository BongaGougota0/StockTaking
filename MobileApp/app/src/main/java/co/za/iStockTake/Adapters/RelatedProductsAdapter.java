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

public class RelatedProductsAdapter extends RecyclerView.Adapter<RelatedProductsAdapter.ProductViewHolder>
{

    private ArrayList<Product> productArrayList;
    private Context context;

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lyt_main_product_view,parent);
        return new ProductViewHolder(view);
    }

    public RelatedProductsAdapter(ArrayList<Product> productArrayList, Context context)
    {
        this.productArrayList = productArrayList;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position)
    {
        //Set recycler view of related products
    }


    @Override
    public int getItemCount()
    {
        return productArrayList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder
    {
        RecyclerView recyclerView;

        public ProductViewHolder(@NonNull View itemView)
        {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.relatedProductsRV);
        }
    }
}
