package co.za.iStockTake.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import co.za.iStockTake.Models.Category;
import co.za.iStockTake.R;

public class categoriesAdapter extends RecyclerView.Adapter<categoriesAdapter.categoryHolder>
{
    private ArrayList<Category> categories;
    private Context context;

    public categoriesAdapter(ArrayList<Category> categories, Context context)
    {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public categoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lyt_home_page,null,false);
        return new categoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull categoryHolder holder, int position)
    {
        //Setup view here
    }

    @Override
    public int getItemCount()
    {
        return categories.size();
    }

    public class categoryHolder extends RecyclerView.ViewHolder
    {
        RecyclerView recyclerView;
        public categoryHolder(@NonNull View itemView)
        {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.categoriesRecycler);
        }
    }
}
