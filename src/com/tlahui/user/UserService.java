package com.tlahui.user;

public class UserService {
    UserDAO userDAO = new UserDAO();
    public User[] getAllUsers(){
        return userDAO.getUsers();
    }

    public User getUserByID(int id){
        for (User user : userDAO.getUsers()){
            if(user.getId() == id)
                return user;
        }
        return null;
    }
}
