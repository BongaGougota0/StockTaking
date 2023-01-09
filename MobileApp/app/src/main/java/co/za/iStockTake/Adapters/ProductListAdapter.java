package co.za.iStockTake.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import co.za.iStockTake.Interfaces.ChangeNumberItemsListener;
import co.za.iStockTake.ManagementList;
import co.za.iStockTake.Models.Product;
import co.za.iStockTake.R;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.Viewholder>
{
    ManagementList managelist;
    ArrayList<Product> products;
    ChangeNumberItemsListener changeNumberItemsListener;

    public ProductListAdapter(ArrayList<Product> products, Context context, ChangeNumberItemsListener changeNumberItemsListener)
    {
        this.managelist = new ManagementList(context);
        this.products = products;
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product_on_list,null,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position)
    {
        int itemPosition = holder.getAdapterPosition();

        Picasso.get().load(R.drawable.placeholder)
                .fit()
                .centerInside();
                //.into(holder.imgProdImage);

        holder.txtItemsTotal.setText(String.valueOf(products.get(position).getProductCount()));

        holder.txtProductName.setText(" "+products.get(position).getProductName());

        holder.txtProductPrice.setText("R "+String.valueOf(products.get(position).getProductPrice()));

        holder.txtProductCountOnList.setText("R "+String.valueOf(Math.round(products.get(position).getProductPrice()
                *products.get(position).getProductCount())*100/100));

        holder.lplus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                managelist.addToList(products, itemPosition, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });

        holder.lminus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                managelist.removefromList(products, itemPosition, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return products.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder
    {
        //Row views i.e Each product
        ImageView imgProduct;
        TextView txtProductName, txtProductPrice, txtItemsTotal, txtProductCountOnList, lminus, lplus;

        public Viewholder(@NonNull View itemView)
        {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.lproductImage);
            lminus = itemView.findViewById(R.id.lminusBtn);
            lplus = itemView.findViewById(R.id.lplusBtn);

            txtItemsTotal = itemView.findViewById(R.id.lproductCount);
            txtProductCountOnList = itemView.findViewById(R.id.lTotalProducts);
            txtProductName = itemView.findViewById(R.id.lproductName);
            txtProductPrice = itemView.findViewById(R.id.lproductPrice);
        }
    }
}
