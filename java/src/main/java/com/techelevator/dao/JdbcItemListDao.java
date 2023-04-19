package com.techelevator.dao;

import com.techelevator.model.ItemList;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcItemListDao implements ItemListDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcItemListDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ItemList> getListsByGroup(int groupId) {
        final String sql = " SELECT l.list_id, l.list_name, l.group_id, COALESCE(COUNT(ITEMS), 0) AS num_of_items " +
                " FROM lists l " +
                "  LEFT JOIN ITEMS ON l.LIST_ID = ITEMS.LIST_ID  " +
                "  WHERE l.group_id = ? " +
                " group by l.group_id, l.list_id " +
                "  ORDER By l.list_name;";

        final SqlRowSet results = this.jdbcTemplate.queryForRowSet(sql, groupId);
        List<ItemList> listOfItemLists = new ArrayList<>();
        while (results.next()) {
            ItemList itemList = mapRowToLists(results);
            listOfItemLists.add(itemList);
        }
        return listOfItemLists;
    }

    @Override
    public boolean createItemList(String listName, int groupId) {
        final String sql = "INSERT INTO lists(list_name, num_of_items, group_id)\n" +
                "VALUES (?, 0, ?)\n" +
                "RETURNING list_id;";

        try {
            Integer newListId = jdbcTemplate.queryForObject(sql, Integer.class, listName, groupId);
            return true;
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public void updateQuantityCountInList(int listId) {
        final String sql = "select count(*) from items where list_id = ?;";
        try {
            Integer quantity = jdbcTemplate.queryForObject(sql, Integer.class, listId);
            final String sql2 = "UPDATE lists SET num_of_items = ? WHERE list_id = ?;";
            this.jdbcTemplate.update(sql2, quantity, listId);
            final String sql3 = "SELECT num_of_items FROM lists WHERE list_id = ?;";
            Integer result = this.jdbcTemplate.queryForObject(sql3, Integer.class, listId);

        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Integer countItemsOnList(int listId) {
        final String Sql = "  " +
                " FROM LISTS " +
                "  " +
                " WHERE lists.list_id = ?; ";
        Integer quantity = jdbcTemplate.queryForObject(Sql, Integer.class, listId);
        return quantity;
    }


    private ItemList mapRowToLists(SqlRowSet sqlRowSet) {
        ItemList itemList = new ItemList();
        itemList.setListId(sqlRowSet.getInt("list_id"));
        itemList.setListName(sqlRowSet.getString("list_name"));
        itemList.setNumOfItems(sqlRowSet.getInt("num_of_items"));
        itemList.setGroupId(sqlRowSet.getInt("group_id"));
        return itemList;
    }
}
