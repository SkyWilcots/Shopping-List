package com.techelevator.dao;

import com.techelevator.model.Item;

import java.util.List;

public interface ItemDao {
    public List<Item> getItemsByListId(int listId);
    boolean addItem(int listId, String itemName,int quantity, int userId);
    public void removeFromList(int listId, int itemId);
}
