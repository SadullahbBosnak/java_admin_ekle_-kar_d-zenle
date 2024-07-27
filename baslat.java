package yenii;

import yenii.frm;

import javax.swing.*;

public class baslat {    public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            yenii.frm f1 = new frm();
            f1.setVisible(true);
        }
    });

}
}
