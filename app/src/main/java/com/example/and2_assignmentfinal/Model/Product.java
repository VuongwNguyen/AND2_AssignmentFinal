package com.example.and2_assignmentfinal.Model;

public class Product {
    private int ID;
    private String Name;
    private int Price;
    private int Amount;
    private String Avatar;
    private boolean isPinned;

    public Product(int ID, String name, int price, int amount, String avatar, boolean isPinned) {
        this.ID = ID;
        Name = name;
        Price = price;
        Amount = amount;
        Avatar = avatar;
        this.isPinned = isPinned;
    }

    public Product(int ID, String name, int price, int amount, String avatar) {
        this.ID = ID;
        Name = name;
        Price = price;
        Amount = amount;
        Avatar = avatar;
    }

    public Product() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public boolean isPinned() {
        return isPinned;
    }

    public void setPinned(boolean pinned) {
        isPinned = pinned;
    }
}
