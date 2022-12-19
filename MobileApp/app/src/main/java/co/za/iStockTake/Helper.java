package co.za.iStockTake;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;

import co.za.iStockTake.Models.Product;

public class Helper
{
    private Context context = null;
    private SharedPreferences preferences;

    public Helper(Context context)
    {
        this.preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void inserToList(Product product)
    {
        ArrayList<Product> products = getListObject("ProductList");
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
        this.putListObject("ProductList", products);
        Toast.makeText(context, "Product added to your list", Toast.LENGTH_SHORT);
    }
    /**
     * Class will be used to convert an arraylist into a json object for
     * easy sharing with other intents or fragments.
     *
     * @param key
     * @param playerList
     */
    public void putListObject(String key, ArrayList<Product> playerList)
    {
        checkForNullKey(key);
        Gson gson = new Gson();
        ArrayList<String> objStrings = new ArrayList<String>();
        for(Product product: playerList)
        {
            objStrings.add(gson.toJson(product));
        }
        putListString(key, objStrings);
    }

    public ArrayList<Product> getListObject(String key)
    {
        Gson gson = new Gson();

        ArrayList<String> objStrings = getListString(key);
        ArrayList<Product> productArrayList =  new ArrayList<Product>();

        for(String jObjString : objStrings)
        {
            Product player  = gson.fromJson(jObjString,  Product.class);
            productArrayList.add(player);
        }
        return productArrayList;
    }

    public void putObject(String key, Object obj)
    {
        checkForNullKey(key);
        Gson gson = new Gson();
        putString(key, gson.toJson(obj));
    }

    public <T> T getObject(String key, Class<T> classOfT)
    {

        String json = getString(key);
        Object value = new Gson().fromJson(json, classOfT);
        if (value == null)
            throw new NullPointerException();
        return (T)value;
    }

    /**
     * Put String value into SharedPreferences with 'key' and save
     * @param key SharedPreferences key
     * @param value String value to be added
     */
    public void putString(String key, String value) {
        checkForNullKey(key); checkForNullValue(value);
        preferences.edit().putString(key, value).apply();
    }

    /**
     * Get String value from SharedPreferences at 'key'. If key not found, return ""
     * @param key SharedPreferences key
     * @return String value at 'key' or "" (empty String) if key not found
     */
    public String getString(String key) {
        return preferences.getString(key, "");
    }

    private void checkForNullKey(String key)
    {
        if(key == null)
        {
            throw new NullPointerException();
        }
    }

    /**
     * null keys would corrupt the shared pref file and make them unreadable this is a preventive measure
     * @param value the pref value to check
     */
    private void checkForNullValue(String value){
        if (value == null){
            throw new NullPointerException();
        }
    }

    public void putListString(String key, ArrayList<String> stringList)
    {
        checkForNullKey(key);
        String[] myStringList = stringList.toArray(new String[stringList.size()]);
        preferences.edit().putString(key, TextUtils.join("‚‗‚", myStringList)).apply();
    }

    /**
     * Get parsed ArrayList of String from SharedPreferences at 'key'
     * @param key SharedPreferences key
     * @return ArrayList of String
     */
    public ArrayList<String> getListString(String key)
    {
        return new ArrayList<String>(Arrays.asList(TextUtils.split(preferences.getString(key, ""), "‚‗‚")));
    }
}
