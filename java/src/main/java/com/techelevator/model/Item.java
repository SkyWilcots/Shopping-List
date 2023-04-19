package com.techelevator.model;

import java.time.LocalDate;

public class Item {
    private int itemId;
    private int quantity;
    private String itemName;
    private int listId;
    private int userId;
    private LocalDate dateAdded;

    public Item(int quantity, String itemName, int listId, int userId
    ) {
        this.quantity = quantity;
        this.itemName = itemName;
        this.listId = listId;
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Item() {
    }

    ;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }
}
