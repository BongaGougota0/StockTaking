package co.za.iStockTake.Models;

import android.media.Image;
import android.widget.ImageView;

import java.io.File;

public class Product
{
    public String productName;
    public String productBarCode;
    public Integer productImage;
    public Integer productPrice;
    private String storeName;
    private String storeBranch;
    private String productCategory;
    private String productDescription;

    public Product(String productName, int productImage)
    {
        this.productName = productName;
        this.productImage = productImage;
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

    public void setProductBarCode(String productBarCode)
    {
        this.productBarCode = productBarCode;
    }

    public void setProductImage(Integer productImage)
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

    public Integer getProductImage()
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
