package com.tlahui.user;

import java.util.UUID;

public class UserService {
    UserArrayDataAccessService userDAO = new UserArrayDataAccessService();
    public User[] getAllUsers(){
        return userDAO.getUsers();
    }

    public User getUserByID(UUID id){
        for (User user : userDAO.getUsers()){
            if(user.getId().equals(id))
                return user;
        }
        return null;
    }
}
