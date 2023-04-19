package com.techelevator.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class Group {
    private int groupId;
    private String groupName;
    //private List<String> members;
    //private List<List> groupList;
    private LocalDate createDate;
    private String groupCode;

    public Group(int groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupCode = groupCode;
    }


    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public Group() {
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupCode() {
        return groupCode;
    }




}