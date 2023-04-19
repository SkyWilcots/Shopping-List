package com.techelevator.controller;

import com.techelevator.dao.GroupDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Group;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@PreAuthorize("isAuthenticated()")
public class GroupController {
    private GroupDao groupDao;
    private UserDao userDao;

    public GroupController(GroupDao groupDao, UserDao userDao) {
        this.groupDao = groupDao;
        this.userDao = userDao;
    }

    @GetMapping("/groups/{username}")
    public List<Group> getGroupsByUser(@PathVariable String username) {
        return this.groupDao.getGroups(username);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/addgroup")
    public boolean create(@RequestParam String username, @RequestParam String groupName) {
        return this.groupDao.createGroup(username, groupName);
    }

    //    @PreAuthorize("permitAll()")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/adduser")
    public boolean update(@RequestParam String code, Principal principal) {
        User loggedInUser = userDao.findByUsername(principal.getName());

        return this.groupDao.addUserToGroup(loggedInUser.getId(), code);

    }
}
