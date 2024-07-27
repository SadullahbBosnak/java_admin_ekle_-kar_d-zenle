package yenii;

import javax.swing.*;

public class baslat {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Captcha Panel");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(300, 250);
                frame.setLocationRelativeTo(null);
                frame.add(new CaptchaPanel());
                frame.setVisible(true);
            }
        });
    }
}
