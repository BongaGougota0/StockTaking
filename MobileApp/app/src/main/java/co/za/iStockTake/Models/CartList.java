package co.za.iStockTake.Models;

import java.util.ArrayList;
import java.util.Date;

public class CartList
{
    private Integer list_id;
    private String list_user;
    private Date date_of_list;
    private ArrayList<Product> products;
    private boolean completed;
    //My lists
    private ArrayList<CartList> carts; // a list of carts the user has on the database

    /**
     * @param list_user - the creator, the current user of this list
     * @param date_of_list - date this list was created
     * @param products - the list of products the user would like to but
     * @param completed - has the customer completed in store purchase? (Yes - true), (False - false)
     */
    public CartList(String list_user, Date date_of_list, ArrayList<Product> products, boolean completed)
    {
        this.list_user = list_user;
        this.date_of_list = date_of_list;
        this.products = products;
        this.completed = completed;
    }

    public Integer getList_id() {
        return list_id;
    }

    public void setList_id(Integer list_id) {
        this.list_id = list_id;
    }

    public String getList_user() {
        return list_user;
    }

    public void setList_user(String list_user) {
        this.list_user = list_user;
    }

    public Date getDate_of_list() {
        return date_of_list;
    }

    public void setDate_of_list(Date date_of_list) {
        this.date_of_list = date_of_list;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
