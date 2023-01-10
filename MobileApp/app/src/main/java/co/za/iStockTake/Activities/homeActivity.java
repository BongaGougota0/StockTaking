package co.za.iStockTake.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
    ImageView home_, logout_, specials_, trending_;

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
        trendingProducts.add(new Product("milk 0",50, "milk","this is milk"));
        trendingProducts.add(new Product("milk 1",5, "milk","this is milk"));
        trendingProducts.add(new Product("milk 2",10, "milk","this is milk"));
        trendingProducts.add(new Product("milk 3",80, "milk","this is milk"));
        trendingProducts.add(new Product("milk 4",35, "milk","this is milk"));

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

        bottomNavigation();

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

    private void bottomNavigation()
    {
        FloatingActionButton fap = (FloatingActionButton) findViewById(R.id.myList);
        LinearLayout home = findViewById(R.id.btnLayout);
        //ImageView home_ = findViewById(R.id.home);


        fap.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(homeActivity.this, myProductListActivity.class));
            }
        });

        home_ = findViewById(R.id.home);
        trending_ = findViewById(R.id.trending);
        specials_ = findViewById(R.id.sale);
        logout_ = findViewById(R.id.logout);

        home_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do nothing
                Toast.makeText(homeActivity.this,"You are home.", Toast.LENGTH_SHORT).show();
            }
        });

        trending_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(homeActivity.this,"View trending items", Toast.LENGTH_SHORT).show();
            }
        });

        specials_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(homeActivity.this,"Specials.", Toast.LENGTH_SHORT).show();
            }
        });

        logout_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(homeActivity.this,"Away!", Toast.LENGTH_SHORT).show();
            }
        });
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
