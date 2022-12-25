package co.za.iStockTake;

import android.content.Context;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import co.za.iStockTake.Interfaces.ChangeNumberItemsListener;
import co.za.iStockTake.Models.Product;

public class ManagementList
{
    private Context context;
    private SharedPref sharedPref;

    public ManagementList(Context context)
    {
        this.context = context;
        this.sharedPref = new SharedPref(context);
    }

    public void inserToList(Product product)
    {
        ArrayList<Product> products = getList();
        boolean alreadyExist = false;
        int n =0;

        for(int i = 0; i < products.size(); i++)
        {
            if(products.get(i).getProductName().equals(product.getProductName()))
            {
                alreadyExist = true;
                n=i;
                break;
            }
        }

        if(alreadyExist)
        {
            products.get(n).setProductCount(product.getProductCount());
        }
        else
        {
            products.add(product);
        }
        sharedPref.putListObject("ProductList", products);
        Toast.makeText(context, "Product added to your list", Toast.LENGTH_SHORT).show();
    }

    public void addToList(ArrayList<Product> products, int position, ChangeNumberItemsListener changeNumberItemsListener)
    {
        products.get(position).setProductCount(products.get(position).getProductCount()+1);
        sharedPref.putListObject("ProductList", products);
        changeNumberItemsListener.changed();
    }

    public void removefromList(ArrayList<Product> products, int position, ChangeNumberItemsListener changeNumberItemsListener)
    {
        if(products.get(position).getProductCount() == 1)
        {
            products.remove(position);
        }
        else
        {
            products.get(position).setProductCount(products.get(position).getProductCount()-1);
        }
        sharedPref.putListObject("ProductList", products);
        changeNumberItemsListener.changed();
    }

    public double getTotalCost()
    {
        double total = 0;
        ArrayList<Product> products = getList();
        for(int i = 0; i < products.size(); i++)
        {
            total = total+ Math.round(products.get(i).getProductPrice()*products.get(i).getProductCount());
        }

        return total;
    }

    public ArrayList<Product> getList()
    {
        return sharedPref.getListObject("ProductList");
    }


}
