package Ricard.code.web.servlet;

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

@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int width = 80;
        int height = 40;
        // 1.创建BufferedImage对象
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // 2.美化图片
        // 2.1填充背景色
        Graphics g = img.getGraphics();   // 画笔对象
        // 设置背景颜色
        g.setColor(Color.PINK);
        g.fillRect(0, 0, width, height);

        // 产生四个随机验证码
        String checkCode_Session = getCheckCode();

        // 将产生的验证码存放到Session中
        request.getSession().setAttribute("checkCode_Session", checkCode_Session);

        // 设置画笔为黄色
        g.setColor(Color.YELLOW);
        // 设置字体大小
        g.setFont(new Font("黑体", Font.BOLD, 20));
        // 像图片写入验证码
        g.drawString(checkCode_Session, 15, 25);
        // 3将内存的图片展示到页面中
        ImageIO.write(img, "png", response.getOutputStream());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected String getCheckCode() {
        String code = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        // 生成随机角标
        Random ran = new Random();

        StringBuffer sb = new StringBuffer();
        for(int i = 1; i <= 4; i++) {
            int index = ran.nextInt(code.length());
            // 获取字符
            char c = code.charAt(index);
            sb.append(c);
        }

        // 得到内存中产生的验证码
        return sb.toString();
    }
}
