package co.za.iStockTake.Models;

public class Store
{
    String storeName;
    Integer storeNumbers;
    String storeDescription;
    String storeLocation;

    public Store(String storeName, Integer storeNumbers, String storeDescription, String storeLocation)
    {
        this.storeName = storeName;
        this.storeNumbers = storeNumbers;
        this.storeDescription = storeDescription;
        this.storeLocation = storeLocation;
    }

    public Store(String storeName, String storeLocation)
    {
        this.storeName = storeName;
        this.storeLocation = storeLocation;
    }

    public String getStoreName()
    {
        return storeName;
    }

    public void setStoreName(String storeName)
    {
        this.storeName = storeName;
    }

    public Integer getStoreNumbers()
    {
        return storeNumbers;
    }

    public void setStoreNumbers(Integer storeNumbers)
    {
        this.storeNumbers = storeNumbers;
    }

    public String getStoreDescription()
    {
        return storeDescription;
    }

    public void setStoreDescription(String storeDescription)
    {
        this.storeDescription = storeDescription;
    }

    public String getStoreLocation()
    {
        return storeLocation;
    }

    public void setStoreLocation(String storeLocation)
    {
        this.storeLocation = storeLocation;
    }
}
