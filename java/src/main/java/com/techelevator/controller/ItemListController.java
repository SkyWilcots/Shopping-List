package com.techelevator.controller;


import com.techelevator.dao.ItemListDao;
import com.techelevator.model.ItemList;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@PreAuthorize("isAuthenticated()")
public class ItemListController {

    private final ItemListDao itemListDao;
    public ItemListController (ItemListDao itemListDao){
        this.itemListDao= itemListDao;
    }


    @GetMapping("/lists/{groupId}")
        public List<ItemList> getListsByGroupName(@PathVariable int groupId){
            return this.itemListDao.getListsByGroup(groupId);
        }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/lists/{groupId}/addlist")
    public boolean createNewList(@PathVariable int groupId, @RequestParam String listName){
        return this.itemListDao.createItemList(listName, groupId);
    }

    @PreAuthorize("permitAll")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/lists/test/")
    public Integer countItems(@RequestParam int listId) {
        return this.itemListDao.countItemsOnList(listId);
    }




}
