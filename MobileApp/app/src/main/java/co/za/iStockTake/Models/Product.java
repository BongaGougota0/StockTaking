package co.za.iStockTake.Models;

import android.widget.ImageView;

import java.io.Serializable;

public class Product implements Serializable
{
    private String productName;
    private ImageView productImage;
    private double productPrice;
    private String storeName;
    private String storeBranch;
    private String productCategory;
    private String productDescription;

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    private int productCount = 1;

    public Product(String productName, ImageView productImage)
    {
        this.productName = productName;
        this.productImage = productImage;
    }

    public Product(String productName, ImageView productImage, double productPrice, String storeName, String productCategory, String productDescription)
    {
        this.productName = productName;
        this.productImage = productImage;
        this.productPrice = productPrice;
        this.storeName = storeName;
        this.productCategory = productCategory;
        this.productDescription = productDescription;
    }

    public Product(String productName, Integer productPrice, String productCategory, String productDescription)
    {
        this.productName = productName;
        this.productPrice = productPrice;
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

    public ImageView getProductImage()
    {
        return productImage;
    }

    public double getProductPrice()
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
