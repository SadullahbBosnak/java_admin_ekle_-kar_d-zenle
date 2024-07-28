package yenii;

import yenid.CaptchaPanel;

import javax.swing.*;

public class baslat {    public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            adminpanel f1 = new adminpanel();
            f1.setVisible(true);
        }
    });

}
}
