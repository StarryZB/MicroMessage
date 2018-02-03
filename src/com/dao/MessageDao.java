package com.dao;

import com.bean.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by StarryZB on 2018/2/3.
 */
public class MessageDao {
    public List<Message> queryMessageList(String command, String description) {
        List<Message> messageList = new ArrayList<Message>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/micro_message","root","123456");
            StringBuilder sql = new StringBuilder("SELECT ID,COMMAND,DESCRIPTION,CONTENT FROM message WHERE 1=1");
            if (command != null && !"".equals(command.trim())) {
                sql.append(" AND command = '"+ command +"'");
            }
            if (description != null && !"".equals(description.trim())) {
                sql.append(" AND description LIKE '"+ "%" + description + "%" + "'");
            }
            PreparedStatement statement = connection.prepareStatement(sql.toString());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Message message = new Message();
                message.setId(resultSet.getString("ID"));
                message.setCommand(resultSet.getString("COMMAND"));
                message.setDescription(resultSet.getString("DESCRIPTION"));
                message.setContent(resultSet.getString("CONTENT"));
                messageList.add(message);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messageList;
    }
}
