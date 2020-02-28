package cn.code.servlet;

import cn.code.dao.UserDao;
import cn.code.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User registerUser = new User();
        registerUser.setUsername(username);
        registerUser.setPassword(password);
        boolean flag = new UserDao().add(registerUser);
        if(flag) {
            request.setAttribute("register_success","注册成功");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
        }else{
            request.setAttribute("register_error","账号以被注册,注册失败");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
