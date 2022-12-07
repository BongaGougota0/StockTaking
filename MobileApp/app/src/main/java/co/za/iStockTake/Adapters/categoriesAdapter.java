package co.za.iStockTake.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import co.za.iStockTake.Models.Category;

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
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull categoryHolder holder, int position)
    {

    }

    @Override
    public int getItemCount()
    {
        return categories.size();
    }

    public class categoryHolder extends RecyclerView.ViewHolder
    {
        public categoryHolder(@NonNull View itemView)
        {
            super(itemView);
        }
    }
}
