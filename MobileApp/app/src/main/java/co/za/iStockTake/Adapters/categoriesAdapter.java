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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.col_category_item,null,false);
        return new categoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull categoryHolder holder, int position)
    {
        //Setup view here
        holder.imgcateImag.setImageResource(categories.get(position).getImgCategory());
        holder.txtcateTitle.setText(categories.get(position).getCategoryName());
    }

    @Override
    public int getItemCount()
    {
        return categories.size();
    }

    public class categoryHolder extends RecyclerView.ViewHolder
    {
        TextView txtcateTitle;
        ImageView imgcateImag;
        public categoryHolder(@NonNull View itemView)
        {
            super(itemView);
            txtcateTitle = itemView.findViewById(R.id.categoryTitle);
            imgcateImag = itemView.findViewById(R.id.categoryIcon);
        }
    }
}
