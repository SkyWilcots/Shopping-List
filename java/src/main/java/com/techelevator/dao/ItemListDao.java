package com.techelevator.dao;

import com.techelevator.model.ItemList;

import java.util.List;

public interface ItemListDao {
    public List<ItemList> getListsByGroup(int groupId);
    public boolean createItemList(String listName, int groupId);
    public void updateQuantityCountInList(int listId);

    public Integer countItemsOnList(int listId);
}
