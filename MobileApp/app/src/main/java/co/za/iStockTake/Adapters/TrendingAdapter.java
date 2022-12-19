package co.za.iStockTake.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import co.za.iStockTake.Models.Category;
import co.za.iStockTake.Models.Product;
import co.za.iStockTake.R;

public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.trendHolder>
{
    private ArrayList<Product> trendingItems;
    private Context context;

    public TrendingAdapter(ArrayList<Product> trendingItems, Context context)
    {
        this.trendingItems = trendingItems;
        this.context = context;
    }

    @NonNull
    @Override
    public trendHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.col_trending_item,null,false);
        return new trendHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull trendHolder holder, int position)
    {
        //Setup view here
        holder.txtTrendTitle.setText(trendingItems.get(position).getProductName());

        Picasso.get().load(R.drawable.placeholder)
                .fit()
                .centerInside()
                .into(holder.imgProdImage);

        holder.addtolist.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
    }


    @Override
    public int getItemCount()
    {
        return trendingItems.size();
    }

    public class trendHolder extends RecyclerView.ViewHolder
    {
        TextView txtTrendTitle, addtolist;
        ImageView imgProdImage;

        public trendHolder(@NonNull View itemView)
        {
            super(itemView);
            txtTrendTitle = itemView.findViewById(R.id.trendingProductTitle);
            imgProdImage = itemView.findViewById(R.id.trendingProductImage);
            addtolist = itemView.findViewById(R.id.addToList);
        }
    }
}
