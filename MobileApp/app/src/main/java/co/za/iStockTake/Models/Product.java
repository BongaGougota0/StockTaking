package co.za.iStockTake.Models;

import android.media.Image;
import android.widget.ImageView;

import java.io.File;
import java.io.Serializable;

public class Product implements Serializable
{
    public String productName;
    public String productImage;
    public Integer productPrice;
    private String storeName;
    private String storeBranch;
    private String productCategory;
    private String productDescription;
    private Integer productCount;

    public Product(String productName, String productImage)
    {
        this.productName = productName;
        this.productImage = productImage;
    }

    public Product(String productName, String productImage, Integer productPrice, String storeName, String productCategory, String productDescription)
    {
        this.productName = productName;
        this.productImage = productImage;
        this.productPrice = productPrice;
        this.storeName = storeName;
        this.productCategory = productCategory;
        this.productDescription = productDescription;
    }

    public void setStoreName(String storeName)
    {
        this.storeName = storeName;
    }

    public void setStoreBranch(String storeBranch)
    {
        this.storeBranch = storeBranch;
    }

    public String getProdutCategory()
    {
        return productCategory;
    }

    public void setProdutCategory(String produtCategory)
    {
        this.productCategory = produtCategory;
    }

    //************************************************Setters
    public void setProductName(String productName)
    {
        this.productName = productName;
    }


    public void setProductImage(String productImage)
    {
        this.productImage = productImage;
    }

    public void setProductPrice(Integer productPrice)
    {
        this.productPrice = productPrice;
    }

    //********************************************Getters
    public String getProductName()
    {
        return productName;
    }

    public String getProductImage()
    {
        return productImage;
    }

    public int getProductPrice()
    {
        return productPrice;
    }

    public String getStoreName()
    {
        return storeName;
    }

    public String getStoreBranch()
    {
        return storeBranch;
    }

    public String getProductDescription() {
        return productDescription;
    }
}
