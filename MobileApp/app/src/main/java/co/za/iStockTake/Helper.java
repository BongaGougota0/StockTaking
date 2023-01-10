package co.za.iStockTake;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

import co.za.iStockTake.Models.Product;

public class Helper
{
    //private Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public Helper(Context context)
    {
        //this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public ArrayList<String> getListString(String key)
    {
        return new ArrayList<String>(Arrays.asList(TextUtils.split(sharedPreferences.getString(key, ""), "‚‗‚")));
    }

    public void putListString(String key, ArrayList<String> stringList)
    {
        checkForNullKey(key);
        String[] myStringList = stringList.toArray(new String[stringList.size()]);
        sharedPreferences.edit().putString(key, TextUtils.join("‚‗‚", myStringList)).apply();
    }

    public ArrayList<Product> getListObject(String key)
    {
        Gson gson = new Gson();

        ArrayList<String> objStrings = getListString(key);
        ArrayList<Product> productsList =  new ArrayList<Product>();

        for(String jObjString : objStrings){
            Product item  = gson.fromJson(jObjString,  Product.class);
            productsList.add(item);
        }
        return productsList;
    }

    public void putListObject(String key, ArrayList<Product> products)
    {
        checkForNullKey(key);
        Gson gson = new Gson();
        ArrayList<String> objStrings = new ArrayList<String>();
        for(Product item: products){
            objStrings.add(gson.toJson(item));
        }
        putListString(key, objStrings);
    }


    private void checkForNullKey(String key)
    {
        if (key == null)
        {
            throw new NullPointerException();
        }
    }

}
