package com.tlahui.user;

public class UserService {
    UserDAO userDAO = new UserDAO();
    public User[] getAllUsers(){
        return userDAO.getUsers();
    }
}
