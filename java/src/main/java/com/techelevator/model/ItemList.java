package com.techelevator.model;

public class ItemList {
    String listName;
    private int listId;
    private int ownerId;
    private String contents;
    private int numOfItems;
    private int groupId;

    public ItemList(String listName, int listId, int ownerId)
    {
        this.listName = listName;
        this.listId = listId;
        this.ownerId = ownerId;
    }

    public ItemList(String listName, int listId, int ownerId, String contents)
    {
        this.listName = listName;
        this.listId = listId;
        this.ownerId = ownerId;
        this.contents = contents;
    }

    public ItemList()
    {
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public int getNumOfItems() {
        return numOfItems;
    }

    public void setNumOfItems(int numOfItems) {
        this.numOfItems = numOfItems;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
