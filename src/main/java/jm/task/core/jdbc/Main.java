package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
//import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("name1","last1",(byte) 20);
        System.out.println("User name1 добавлен в базу данных");
        //System.out.println(userService.getAllUsers().get(0));
        userService.saveUser("name2","last2",(byte) 20);
        System.out.println("User name2 добавлен в базу данных");
        userService.saveUser("name3","last3",(byte) 20);
        System.out.println("User name3 добавлен в базу данных");
        userService.saveUser("name4","last4",(byte) 20);
        System.out.println("User name4 добавлен в базу данных");



        List<User> users =  userService.getAllUsers();

        System.out.println(users.toString());


        System.out.println("cleaned");
    }
}
