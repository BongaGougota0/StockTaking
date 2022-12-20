package co.za.iStockTake.Activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import co.za.iStockTake.Adapters.CategoriesAdapter;
import co.za.iStockTake.Adapters.FeaturedStoresAdapter;
import co.za.iStockTake.Adapters.TrendingAdapter;
import co.za.iStockTake.Models.Category;
import co.za.iStockTake.Models.Product;
import co.za.iStockTake.Models.Store;
import co.za.iStockTake.R;

public class homeActivity extends AppCompatActivity
{
    /**Home ID's
     * homeHeaderImage - image view
     * searchText - input text
     * searchGo - image (button)
     * categoriesRecycler -
     * homeData - image view (on click listener image)
     * homeList - image viwe (on click listener image)
     * trendingRecycler -
     * articleHeaderImage - image view s(on click listener image)
     * featuredStoresRecycler -
     */

    private RecyclerView recyclerViewCategories, recyclerViewTrending, featuredStores;
    ImageView headerHomeImg, searchGoImg, homeDataImg, homeListImg, articleHeaderImg;
    TextView txtSearchText;

    ArrayList<Product> trendingProducts;
    ArrayList<Store> arrayStores;
    ArrayList<Category> categories;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_home_page);
        getSupportActionBar().hide();

        initViews();

        trendingProducts = new ArrayList<>();
        categories = new ArrayList<>();
        arrayStores = new ArrayList<>();

        //once api done comment out.
        /**
         * trendingProducts = getTrending();
         * categories = getCategories();
         * arrayStores = getFeaturedStores();
         */

        String str = "R.drawable.asian_coconut_40_ml";
        //demo data- testing
        trendingProducts.add(new Product("milk",50, "milk","this is milk"));
        trendingProducts.add(new Product("milk",5, "milk","this is milk"));
        trendingProducts.add(new Product("milk",10, "milk","this is milk"));
        trendingProducts.add(new Product("milk",80, "milk","this is milk"));
        trendingProducts.add(new Product("milk",35, "milk","this is milk"));

        categories.add(new Category("cat 1", R.drawable.black_background));
        categories.add(new Category("Cereals", R.drawable.black_background));
        categories.add(new Category("Confectionary", R.drawable.black_background));
        categories.add(new Category("cat 4", R.drawable.black_background));
        categories.add(new Category("HouseHolds", R.drawable.black_background));
        categories.add(new Category("Cleaning Agents", R.drawable.black_background));
        categories.add(new Category("Sugars", R.drawable.black_background));

        arrayStores.add(new Store("Shoprite",R.drawable.demo_ico));
        arrayStores.add(new Store("Viva",R.drawable.demo_ico));
        arrayStores.add(new Store("General",R.drawable.demo_ico));
        arrayStores.add(new Store("Supasave",R.drawable.demo_ico));
        arrayStores.add(new Store("Pick'n'Pay",R.drawable.demo_ico));

        //Home Recycler Views
        LinearLayoutManager linearLayoutManager0 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL, false);
        recyclerViewTrending.setLayoutManager(linearLayoutManager0);
        TrendingAdapter trendingAdapter = new TrendingAdapter(trendingProducts,homeActivity.this);
        recyclerViewTrending.setAdapter(trendingAdapter);

         LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL, false);
         recyclerViewCategories.setLayoutManager(linearLayoutManager1);
         CategoriesAdapter categoriesAdapter = new CategoriesAdapter(categories,homeActivity.this);
         recyclerViewCategories.setAdapter(categoriesAdapter);


        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL, false);
         featuredStores.setLayoutManager(linearLayoutManager2);
        FeaturedStoresAdapter arrayStoreAdapter = new FeaturedStoresAdapter(arrayStores,homeActivity.this);
        featuredStores.setAdapter(arrayStoreAdapter);

    }

    public void initViews()
    {
        txtSearchText = findViewById(R.id.searchText);
        headerHomeImg = findViewById(R.id.homeHeaderImage);
        searchGoImg = findViewById(R.id.searchGo);
        homeDataImg =  findViewById(R.id.homeData);
        homeListImg = findViewById(R.id.homeList);
        articleHeaderImg = findViewById(R.id.articleHeaderImage);

        recyclerViewCategories = findViewById(R.id.categoriesRecycler);
        recyclerViewTrending = findViewById(R.id.trendingRecycler);
        featuredStores = findViewById(R.id.featuredStoresRecycler);
    }

    //get data from API
    public ArrayList<Category> getCategories()
    {
        ArrayList<Category> categories = new ArrayList<>();

        return categories;
    }

    public ArrayList<Product> getTrending()
    {
        ArrayList<Product> trending = new ArrayList<>();

        return trending;
    }

    public ArrayList<Store> getFeaturedStores()
    {
        ArrayList<Store> stores = new ArrayList<>();

        return stores;
    }
}
