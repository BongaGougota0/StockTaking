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

import co.za.iStockTake.Models.Store;
import co.za.iStockTake.R;

public class FeaturedStoresAdapter extends RecyclerView.Adapter<FeaturedStoresAdapter.storeHolder>
{
    private ArrayList<Store> stores;
    private Context context;

    public FeaturedStoresAdapter(ArrayList<Store> stores, Context context)
    {
        this.stores = stores;
        this.context = context;
    }

    @NonNull
    @Override
    public storeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.col_featured_store,null,false);
        return new storeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull storeHolder holder, int position)
    {
        //Setup view here
        holder.imgStoreIcon.setImageResource(R.drawable.demo_ico);
        holder.txtStoreName.setText(stores.get(position).getStoreName());
    }

    @Override
    public int getItemCount()
    {
        return stores.size();
    }

    public class storeHolder extends RecyclerView.ViewHolder
    {
        TextView txtStoreName;
        ImageView imgStoreIcon;
        public storeHolder(@NonNull View itemView)
        {
            super(itemView);
            txtStoreName = itemView.findViewById(R.id.storeName);
            imgStoreIcon = itemView.findViewById(R.id.storeIcon);
        }
    }
}
