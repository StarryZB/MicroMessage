package com.dao;

import com.bean.Message;
import com.db.DBAccess;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by StarryZB on 2018/2/3.
 */
public class MessageDao {
    public List<Message> queryMessageList(String command, String description) {
        List<Message> messageList = new ArrayList<Message>();
        DBAccess dbAccess = new DBAccess();
        Message message = new Message();
        message.setCommand(command);
        message.setDescription(description);
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            messageList = sqlSession.selectList("Message.queryMessageList",message);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/micro_message","root","123456");
//            StringBuilder sql = new StringBuilder("SELECT ID,COMMAND,DESCRIPTION,CONTENT FROM message WHERE 1=1");
//            if (command != null && !"".equals(command.trim())) {
//                sql.append(" AND command = '"+ command +"'");
//            }
//            if (description != null && !"".equals(description.trim())) {
//                sql.append(" AND description LIKE '"+ "%" + description + "%" + "'");
//            }
//            PreparedStatement statement = connection.prepareStatement(sql.toString());
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                Message message = new Message();
//                message.setId(resultSet.getString("ID"));
//                message.setCommand(resultSet.getString("COMMAND"));
//                message.setDescription(resultSet.getString("DESCRIPTION"));
//                message.setContent(resultSet.getString("CONTENT"));
//                messageList.add(message);
//            }
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return messageList;
    }

    public void deleteOne(int id) {
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            sqlSession.delete("Message.deleteOne",id);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
