package cc.java0.swing.d1;

import javax.swing.*;
import java.awt.*;

/**
 * @author everforcc 2021-10-15
 */
public class Demo4 {

    // BoxLayout（箱式布局）
    public static void main(String[] args) {
        JFrame jf = new JFrame("测试窗口");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JButton btn01 = new JButton("Button01");
        JButton btn02 = new JButton("Button02");
        JButton btn03 = new JButton("Button03");
        JButton btn04 = new JButton("Button04");
        JButton btn05 = new JButton("Button05");

        // 创建第一个水平箱容器
        Box hBox01 = Box.createHorizontalBox();
        Component hStrut1 = Box.createHorizontalStrut(10);
        Component hStrut2 = Box.createHorizontalStrut(10);
        hBox01.add(btn01);
        hBox01.add(hStrut1);
        hBox01.add(btn02);
        hBox01.add(hStrut2);
        hBox01.add(btn03);

        // 创建第二水平箱容器
        Box hBox02 = Box.createHorizontalBox();
        hBox02.add(btn04);
        hBox02.add(Box.createHorizontalGlue()); // 添加一个水平方向胶状的不可见组件，撑满剩余水平空间
        hBox02.add(btn05);

        // 创建一个垂直箱容器，放置上面两个水平箱（Box组合嵌套）
        Component vStrut = Box.createVerticalStrut(10);
        Box vBox = Box.createVerticalBox();
        vBox.add(hBox01);
        vBox.add(vStrut);
        vBox.add(hBox02);

        // 把垂直箱容器作为内容面板设置到窗口
        jf.setContentPane(vBox);

        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }

}
