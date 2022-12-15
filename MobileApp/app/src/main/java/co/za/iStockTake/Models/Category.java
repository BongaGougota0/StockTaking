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

    public Integer getImgCategory()
    {
        return imgCategory;
    }

    public void setImgCategory(Integer imgCategory)
    {
        this.imgCategory = imgCategory;
    }

    private Integer imgCategory;

    public Category(String categoryName, Integer imgCategory)
    {
        this.categoryName = categoryName;
        this.imgCategory = imgCategory;
    }
}
