package co.za.iStockTake;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

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

    private ArrayList<Product> getList()
    {
        return sharedPref.getListObject("ProductList");
    }


}
