package co.za.iStockTake.Activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_home_page);

        txtSearchText = findViewById(R.id.searchText);
        headerHomeImg = findViewById(R.id.homeHeaderImage);
        searchGoImg = findViewById(R.id.searchGo);
        homeDataImg =  findViewById(R.id.homeData);
        homeListImg = findViewById(R.id.homeList);
        articleHeaderImg = findViewById(R.id.articleHeaderImage);

        recyclerViewCategories = findViewById(R.id.categoriesRecycler);
        recyclerViewTrending = findViewById(R.id.trendingRecycler);
        featuredStores = findViewById(R.id.featuredStoresRecycler);

        //Home Recycler Views
        LinearLayoutManager linearLayoutManager0 = new LinearLayoutManager(getApplicationContext());
        recyclerViewTrending.setLayoutManager(linearLayoutManager0);

         LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getApplicationContext());
         recyclerViewCategories.setLayoutManager(linearLayoutManager1);

         LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getApplicationContext());
         featuredStores.setLayoutManager(linearLayoutManager2);
    }
}
