package cc.java0.swing.d6;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author everforcc 2021-10-19
 */
public class DemoPic {

    public static void main(String[] args) throws IOException {
        // 读取图片
        BufferedImage bufImage = ImageIO.read(new File("src/main/resources/pic/tray.jpg"));

        // 获取图片的宽高
        final int width = bufImage.getWidth();
        final int height = bufImage.getHeight();

        // 读取出图片的所有像素
        int[] rgbs = bufImage.getRGB(0, 0, width, height, null, 0, width);

        // 对图片的像素矩阵进行水平镜像
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width / 2; col++) {
                int temp = rgbs[row * width + col];
                rgbs[row * width + col] = rgbs[row * width + (width - 1 - col)];
                rgbs[row * width + (width - 1 - col)] = temp;
            }
        }

        // 把水平镜像后的像素矩阵设置回 bufImage
        bufImage.setRGB(0, 0, width, height, rgbs, 0, width);

        // 把修改过的 bufImage 保存到本地
        ImageIO.write(bufImage, "JPEG", new File("src/main/resources/pic/tray-trans.jpg"));
    }

}
