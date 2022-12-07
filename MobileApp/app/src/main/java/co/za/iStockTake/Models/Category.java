package co.za.iStockTake.Models;

import android.widget.ImageView;

public class Category
{
    private String categoryName;

    public String getCategoryName()
    {
        return categoryName;
    }

    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }

    public ImageView getImgCategory()
    {
        return imgCategory;
    }

    public void setImgCategory(ImageView imgCategory)
    {
        this.imgCategory = imgCategory;
    }

    private ImageView imgCategory;

    public Category(String categoryName, ImageView imgCategory)
    {
        this.categoryName = categoryName;
        this.imgCategory = imgCategory;
    }
}
