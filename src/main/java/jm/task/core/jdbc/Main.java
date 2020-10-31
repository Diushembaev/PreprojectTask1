package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;

public class Main {


    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("name1","last1",(byte) 20);
        System.out.println("User name1 добавлен в базу данных");
        userService.saveUser("name2","last2",(byte) 20);
        System.out.println("User name2 добавлен в базу данных");
        userService.saveUser("name3","last3",(byte) 20);
        System.out.println("User name3 добавлен в базу данных");
        userService.saveUser("name4","last4",(byte) 20);
        System.out.println("User name4 добавлен в базу данных");

        Statement statement = Util.getConnection().createStatement();
        String query = "SELECT * FROM Users";
        ResultSet result = statement.executeQuery(query);
        while (result.next()) {
            User user = new User();
            user.setId(result.getLong(1));
            user.setName(result.getString(2));
            user.setLastName(result.getString(3));
            user.setAge(result.getByte(4));

            System.out.println(user.toString());
        }
        userService.cleanUsersTable();
        System.out.println("cleaned");

    }
}
