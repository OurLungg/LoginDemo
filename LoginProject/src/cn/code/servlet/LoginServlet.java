package cn.code.servlet;

import cn.code.dao.UserDao;
import cn.code.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置字符编码
        request.setCharacterEncoding("utf-8");
        //获取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checkcode = request.getParameter("checkcode");
        //获取生成的验证码
        HttpSession session = request.getSession();
        String checkcode_session =(String) session.getAttribute("checkcode_session");
        //防止验证码存储 删除Session中存储的验证码
        session.removeAttribute("checkcode_session");

        //判断验证码是否正确
        if(checkcode_session != null && checkcode_session.equalsIgnoreCase(checkcode)){
            //equalsIgnoreCase忽略大小写的比较
            //验证码正确
            User loginUser = new User();
            loginUser.setUsername(username);
            loginUser.setPassword(password);
            User user = new UserDao().login(loginUser);

            if(user != null){
                //用户名以及密码正确
                //登录成功
                //存储用户信息
                session.setAttribute("user",username); //因为是两次请求，所以用Session
                //重定向到success.jsp
                response.sendRedirect(request.getContextPath()+"/success.jsp");
            }else{
                //用户名及密码错误
                //登录失败
                //存储提示信息到request
                request.setAttribute("login_error","用户名或密码错误");
                //转发到登录页面
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }
        }else{
            //验证码不正确
            //存储提示信息到request
            request.setAttribute("checkcode_error","验证码错误");
            //转发到登录页面
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
