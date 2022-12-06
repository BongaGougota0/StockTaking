package co.za.iStockTake.Models;

import java.io.File;

public class Product
{
    public String productName;
    public String productBarCode;
    public File productImage;
    public Double productPrice;
    private String storeName;
    private String storeBranch;

    //************************************************Setters
    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public void setProductBarCode(String productBarCode)
    {
        this.productBarCode = productBarCode;
    }

    public void setProductImage(File productImage)
    {
        this.productImage = productImage;
    }

    public void setProductPrice(Double productPrice)
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

    public File getProductImage()
    {
        return productImage;
    }

    public Double getProductPrice()
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
