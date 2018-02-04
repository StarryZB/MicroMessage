package com.servlet;

import com.service.MaintainService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by StarryZB on 2018/2/4.
 */
public class DeleteBatchServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String[] ids = req.getParameterValues("id");
        MaintainService maintainService = new MaintainService();
        maintainService.deleteBatch(ids);
        req.getRequestDispatcher("/List.action").forward(req,resp);
    }
}
