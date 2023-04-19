package com.techelevator.dao;

import com.techelevator.model.ItemList;

import java.util.List;


public interface ListDao {
    List<ItemList> sortByAlphabet();
    int getListItemCount();


}
