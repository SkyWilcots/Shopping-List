package com.techelevator.dao;

import com.techelevator.model.Group;

import java.util.List;

public interface GroupDao {
    List<Group> getGroups(String username);
    boolean createGroup(String username, String groupName);
    boolean addUserToGroup(int userId, String inviteCode);
    String generateGroupCode();
    int getGroupIdByCode(String code);

    boolean inviteCodeConfirmed(String code);
}
