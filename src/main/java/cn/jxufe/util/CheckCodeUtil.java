package cn.jxufe.util;

import org.apache.shiro.web.session.HttpServletSession;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @ClassName: CheckCodeUtil
 * @author: hsw
 * @date: 2019/4/20 20:26
 * @Description: TODO
 */
public class CheckCodeUtil {
    private final static int WIDTH = 120;
    private final static int HEIGHT = 50;

    private Random random = new Random();
    private StringBuilder builder = new StringBuilder();

    public boolean codeChecking(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String inputCheckCode = request.getParameter("checkCode");
        if (session != null && !"".equals(inputCheckCode)) {
            return session.getAttribute("GeneratedCode").equals(inputCheckCode);
        }
        return false;
    }

    /**
     * 生成图片
     * @param request
     * @param response
     * @throws IOException
     */
    public void generateImg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);
        // 相当于画笔
        Graphics g = img.getGraphics();
        g.setColor(Color.yellow);
        g.fillRect(350, 500, WIDTH, HEIGHT);
        drawLines(g);
        drawCharacter(g);
        HttpSession session = request.getSession(true);

        session.setAttribute("GeneratedCode", builder.toString());
        // 设置session过期时间为1分钟！
        session.setMaxInactiveInterval(-1);

        System.out.println("验证码生成成功 ： " + session.getAttribute("GeneratedCode"));

        ImageIO.write(img, "png", response.getOutputStream());
        builder.setLength(0);
        g.dispose();//释放资源！
    }

    /**
     * 生成的图片字符数
     */
    private final static int CHARACTER_NUM_IN_IMG = 3;

    /**
     * 在图片上写下随机数字！
     * @param g
     */
    private void drawCharacter(Graphics g) {
        String str = "0123456789";

        for (int i = 0; i < CHARACTER_NUM_IN_IMG; i++) {
            int index = random.nextInt(str.length());
            //第一位不能为0
            if (i == 0 && index == 0) {
                i--;
                continue;
            }
            setColor(g);
            g.setFont(new Font("宋体", Font.BOLD, 30));
            g.drawString("" + str.charAt(index), 20 * i + 20, 30);
            builder.append(str.charAt(index));
        }
    }

    /**
     * 画随机线
     *
     * @param graphics
     */
    private void drawLines(Graphics graphics) {
        int count = random.nextInt(150);
        count = count > 100 ? count : count + 50;

        for (int i = 0; i < count; i++) {
            int x1 = random.nextInt(WIDTH);
            int x2 = random.nextInt(WIDTH);
            int y1 = random.nextInt(HEIGHT);
            int y2 = random.nextInt(HEIGHT);
            setColor(graphics);
            graphics.drawLine(x1, y1, x2, y2);
        }
    }

    /**
     * 随机选择画笔颜色！
     *
     * @param graphics 画笔
     */
    private void setColor(Graphics graphics) {
        int r = random.nextInt(256);
        int b = random.nextInt(256);
        int g = random.nextInt(256);
        Color c = new Color(r, b, g);
        graphics.setColor(c);
    }
}
