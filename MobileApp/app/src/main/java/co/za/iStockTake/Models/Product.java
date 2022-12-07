package co.za.iStockTake.Models;

import android.media.Image;
import android.widget.ImageView;

import java.io.File;

public class Product
{
    public String productName;
    public String productBarCode;
    public ImageView productImage;
    public Integer productPrice;
    private String storeName;
    private String storeBranch;
    private String produtCategory;

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
        return produtCategory;
    }

    public void setProdutCategory(String produtCategory)
    {
        this.produtCategory = produtCategory;
    }

    //************************************************Setters
    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public void setProductBarCode(String productBarCode)
    {
        this.productBarCode = productBarCode;
    }

    public void setProductImage(ImageView productImage)
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

    public String getProductBarCode()
    {
        return productBarCode;
    }

    public ImageView getProductImage()
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
}
