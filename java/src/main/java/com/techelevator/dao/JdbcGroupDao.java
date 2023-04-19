package com.techelevator.dao;

import com.techelevator.model.Group;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class JdbcGroupDao implements GroupDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcGroupDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Group> getGroups(String username) {
        final String sql = "SELECT g.group_id, g.group_name, g.create_date, g.group_code\n" +
                "FROM groups g\n" +
                "JOIN group_member as gm ON g.group_id = gm.group_id\n" +
                "JOIN users as u ON gm.user_id = u.user_id\n" +
                "WHERE username = ?\n" +
                "ORDER BY group_name;";
        final SqlRowSet results = this.jdbcTemplate.queryForRowSet(sql, username);
        final List<Group> groups = new ArrayList<>();
        while (results.next()) {
            Group group = mapRowToGroup(results);
            groups.add(group);
        }
        return groups;
    }

    public boolean createGroup(String username, String groupName) {
        final String code = generateGroupCode();
        final String sql = "INSERT INTO groups(admin_id, group_name, group_code)\n" +
                "VALUES ((SELECT user_id FROM users WHERE username = ?), ?, ?)\n" +
                "RETURNING group_id;";
        Integer newGroupId;
        final String sql2 = "INSERT INTO group_member(user_id, group_id)\n" +
                "VALUES ((SELECT user_id FROM users WHERE username = ?), ?);";

        try {
            newGroupId = jdbcTemplate.queryForObject(sql, Integer.class, username, groupName, code);
            jdbcTemplate.update(sql2, username, newGroupId);
            return true;

        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public String generateGroupCode() {
        final String legalChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        final Random randomGenerator = new Random();
        String inviteCode="";
        while(inviteCode.length() <= 4){
            int randomCharIndex = randomGenerator.nextInt(legalChars.length());
            inviteCode += legalChars.substring(randomCharIndex, randomCharIndex+1);
        }
        return inviteCode;
    }



    public boolean addUserToGroup(int userId, String inviteCode) {
        final String sql = "INSERT INTO group_member (user_id, group_id)\n" +
                "VALUES (?, (SELECT group_id FROM groups WHERE group_code = ?))";
        try {
            this.jdbcTemplate.update(sql, userId, inviteCode);
            return true;
        } catch (DataAccessException e) {
            System.out.println("Failed to update due to an exception." + e.getMessage());
            return false;
        }
    }

    public int getGroupIdByCode(String code) {
        final String sql = "SELECT group_id FROM groups WHERE group_code = ?;";
        try {
            int result = this.jdbcTemplate.queryForObject(sql, Integer.class, code);
            return result;
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
        public boolean inviteCodeConfirmed(String inputCode)
        {
            final String sql = "SELECT group_code FROM groups WHERE group_code = ?;";
            String codeResult = this.jdbcTemplate.queryForObject(sql, String.class, inputCode);
//            String codeResult = sqlCode.getString("group_code");
            if(codeResult != null && inputCode.equalsIgnoreCase(codeResult))
            {
                return true;
            }
            else return false;
        }



    // public List<User> getMembersByGroup
//    List<String> memberList = new ArrayList<>();
//    memberList.add(sqlRowSet.getString("username"));
//    group.setMembers(memberList);
    private Group mapRowToGroup(SqlRowSet sqlRowSet) {
        Group group = new Group();
        group.setGroupId(sqlRowSet.getInt("group_id"));
        group.setGroupName(sqlRowSet.getString("group_name"));
        group.setCreateDate(sqlRowSet.getDate("create_date").toLocalDate());
        group.setGroupCode(sqlRowSet.getString("group_code"));
        return group;

    }

}
