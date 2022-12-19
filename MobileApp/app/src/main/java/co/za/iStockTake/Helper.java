package co.za.iStockTake;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;

import co.za.iStockTake.Models.Product;

public class Helper
{
    private Context context;
    private SharedPreferences preferences;

    public Helper(Context context)
    {
        this.preferences = PreferenceManager.getDefaultSharedPreferences(context);
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

    private void checkForNullKey(String key)
    {
        if(key == null)
        {
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
