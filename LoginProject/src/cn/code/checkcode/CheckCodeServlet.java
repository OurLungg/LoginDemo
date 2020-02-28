package cn.code.checkcode;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

//简易验证码
@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int width = 100;
        int height = 50;
        //创建一对象，在内存中代表图片（验证码图片对象）
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);

        //美化图片
        //填充背景色
        Graphics g = image.getGraphics();//画笔对象
        g.setColor(Color.ORANGE);
        g.fillRect(0,0,width,height);
        //画边框
        g.setColor(Color.BLUE);
        g.drawRect(0,0,width-1,height-1);

        String str = "QWERTYUIOPASDFGHJKLZXCVBNM1234567890zxcvbnmlkjhgfdsaqwertyuiop";

        //生成随机角标
        StringBuilder sb = new StringBuilder();
        Random ran = new Random();
        for (int i = 1; i <= 4; i++) {
            int index = ran.nextInt(str.length());
            //获取字符
            char ch = str.charAt(index);
            sb.append(ch);
            //写验证码
            g.drawString(ch+"",width/5*i,height/2);
        }
        String checkcode_session = sb.toString();
        //将验证码存入session
        request.getSession().setAttribute("checkcode_session",checkcode_session);

        //画干扰线
        g.setColor(Color.RED);
        //随机生成坐标点
        for (int i = 0 ;i<6;i++) {
            int x1 = ran.nextInt(width);
            int x2 = ran.nextInt(width);
            int y1 = ran.nextInt(height);
            int y2 = ran.nextInt(height);
            g.drawLine(x1,y1,x2,y2);
        }
        //将图片输出到页面展示
        ImageIO.write(image, "jpg", response.getOutputStream());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
