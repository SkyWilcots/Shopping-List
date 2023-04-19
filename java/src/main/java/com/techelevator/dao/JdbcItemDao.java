package com.techelevator.dao;

import com.techelevator.model.Item;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcItemDao implements ItemDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcItemDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Item> getItemsByListId(int listId) {
        final String sql = "\tSELECT i.item_id, i.item_name, i.quantity, i.list_id, i.user_id, i.date_added\n" +
                "\tFROM items i\n" +
                "\tJOIN lists l ON l.list_id = i.list_id\n" +
                "\tWHERE l.list_id = ?\n" +
                "\tORDER BY item_name";
        final SqlRowSet result = this.jdbcTemplate.queryForRowSet(sql, listId);
        List<Item> listOfItems = new ArrayList<>();
        while(result.next()) {
            Item listItems = mapRowToItem(result);
            listOfItems.add(listItems);
        }
        return listOfItems;
    }

    @Override
    public boolean addItem(int listId, String itemName,int quantity, int userId) {
        final String sql = "INSERT INTO items(\n" +
                "item_name, quantity, list_id, user_id)\n" +
                "VALUES (?, ?, ?, ?)\n" +
                "RETURNING item_id;";

        try{
            Integer newItemId = jdbcTemplate.queryForObject(sql, Integer.class, itemName, quantity, listId, userId);
            return true;
        }catch (DataAccessException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public void removeFromList(int listId, int itemId) {
        String sql = "DELETE FROM items\n" +
                "WHERE list_id = ? AND item_id = ?;";
        this.jdbcTemplate.update(sql, listId, itemId);

    }

    private Item mapRowToItem(SqlRowSet sqlRowSet){
        Item item = new Item();
        item.setItemId(sqlRowSet.getInt("item_id"));
        item.setItemName(sqlRowSet.getString("item_name"));
        item.setQuantity(sqlRowSet.getInt("quantity"));
        item.setListId(sqlRowSet.getInt("list_id"));
        item.setUserId(sqlRowSet.getInt("user_id"));
        item.setDateAdded(sqlRowSet.getDate("date_added").toLocalDate());
        return item;
    }

}
