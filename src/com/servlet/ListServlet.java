package com.servlet;

import com.bean.Message;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by StarryZB on 2018/2/3.
 * 列表页面初始化
 */
public class ListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");
            String command = req.getParameter("command");
            String description = req.getParameter("description");
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/micro_message?characterEncoding=utf8","root","123456");
            StringBuilder sql = new StringBuilder("SELECT ID,COMMAND,DESCRIPTION,CONTENT FROM message WHERE 1=1");
            List<String> paramList = new ArrayList<String>();
            if (command != null && !"".equals(command.trim())) {
                sql.append(" AND command = '"+ command +"'");
                paramList.add(command);
            }
            if (description != null && !"".equals(description.trim())) {
                sql.append(" AND description LIKE '"+ "%" + description + "%" + "'");
                paramList.add(description);
            }
            PreparedStatement statement = connection.prepareStatement(sql.toString());
            ResultSet resultSet = statement.executeQuery();
            List<Message> messageList = new ArrayList<Message>();
            while (resultSet.next()) {
                Message message = new Message();
                message.setId(resultSet.getString("ID"));
                message.setCommand(resultSet.getString("COMMAND"));
                message.setDescription(resultSet.getString("DESCRIPTION"));
                message.setContent(resultSet.getString("CONTENT"));
                messageList.add(message);
            }
            req.setAttribute("messageList",messageList);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
