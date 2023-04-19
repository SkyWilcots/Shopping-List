package com.techelevator.controller;


import com.techelevator.dao.ItemDao;
import com.techelevator.model.Item;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@PreAuthorize("isAuthenticated()")
public class ItemController {
    private final ItemDao itemDao;
    public ItemController(ItemDao itemDao){
        this.itemDao = itemDao;
    }

    @GetMapping("items/{listId}")
    public List<Item> getItemsByListId(@PathVariable int listId){
        return this.itemDao.getItemsByListId(listId);
    }

//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping("/items/{listId}/additem")
//    public boolean createNewItem(@PathVariable int listId, @RequestParam String itemName, @RequestParam int quantity, @RequestParam int userId){
//        return this.itemDao.addItem(listId,itemName,quantity, userId);
//    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/items/{listId}/additem")
    public boolean createNewItem(@PathVariable int listId, @RequestBody Item item) {
        return this.itemDao.addItem(listId, item.getItemName(), item.getQuantity(), item.getUserId());

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("items/{listId}/removeItem")
    public void removeItemFromList(@PathVariable int listId, @RequestParam int itemId){
        this.itemDao.removeFromList(listId, itemId);
    }

}
