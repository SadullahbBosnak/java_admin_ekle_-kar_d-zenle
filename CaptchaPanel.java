package yenii;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

public class CaptchaPanel extends JPanel {

    private final JTextField txtInput;
    private BufferedImage captchaImage;
    private String captchaText; // Captcha metnini saklamak için bir değişken

    public CaptchaPanel() {
        setLayout(null);

        JPanel captchaPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (captchaImage != null) {
                    g.drawImage(captchaImage, 0, 0, null);
                }
            }
        };
        captchaPanel.setBounds(10, 10, 280, 100);
        captchaPanel.setBackground(Color.LIGHT_GRAY);
        add(captchaPanel);

        JButton btnCreateCaptcha = new JButton("Captcha Oluştur");
        btnCreateCaptcha.setBounds(10, 120, 150, 30);
        btnCreateCaptcha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtInput.setText("");
                drawCaptcha(captchaPanel);
            }
        });
        add(btnCreateCaptcha);

        txtInput = new JTextField();
        txtInput.setBounds(170, 120, 120, 30);
        add(txtInput);

        JButton btnCheckInput = new JButton("Metni Kontrol Et");
        btnCheckInput.setBounds(10, 160, 280, 30);
        btnCheckInput.addActionListener(e -> checkInput());
        add(btnCheckInput);

        drawCaptcha(captchaPanel); // Başlangıçta bir captcha oluştur
    }

    private void drawCaptcha(JPanel panel) {
        captchaImage = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = captchaImage.createGraphics();
        g2d.setColor(Color.GRAY);
        g2d.fillRect(0, 0, panel.getWidth(), panel.getHeight());;

        Random random = new Random();
        String[] fonts = {"Verdana", "Georgia", "Calibri", "Times", "Arial","Palatino Linotype","Liberation Mono"};
        int[] fontStyles = {Font.PLAIN, Font.BOLD, Font.ITALIC}; // Font stilleri
        String[] colors = {
                "#000000", // 0 -> black
                "#FFFFFF", // 1 -> white
                "#FF0000", // 2 -> red
                "#0000FF", // 3 -> blue
                "#00FF00", // 4 -> green
                "#800080"  // 5 -> purple
        };

        StringBuilder captchaTextBuilder = new StringBuilder(); // Captcha metnini oluşturmak için kullanılacak bir StringBuilder
        for (int i = 0; i < 5; i++) {
            Font font = new Font(fonts[random.nextInt(fonts.length)], fontStyles[random.nextInt(fontStyles.length)], random.nextInt(16) + 20);
            g2d.setFont(font);
            g2d.setColor(Color.decode(colors[random.nextInt(colors.length)]));
            char ch = (char) (random.nextInt(26) + 'A');
            captchaTextBuilder.append(ch);
            g2d.drawString(String.valueOf(ch), 40 + i * 40 + random.nextInt(10), 60 + random.nextInt(20));
        }
        captchaText = captchaTextBuilder.toString(); // Oluşturulan captcha metnini değişkene atama
        //Rastgele çizgileri ekleme
        for (int i = 0; i < 10; i++) {
            g2d.setColor(Color.decode(colors[random.nextInt(colors.length)]));
            int x1 = random.nextInt(panel.getWidth());
            int y1 = random.nextInt(panel.getHeight());
            int x2 = random.nextInt(panel.getWidth());
            int y2 = random.nextInt(panel.getHeight());
            g2d.setStroke(new BasicStroke(random.nextInt(3) + 1)); // Rastgele kalınlıkta çizgi
            g2d.drawLine(x1, y1, x2, y2);
        }

        g2d.dispose();
        panel.repaint();
    }






    private void checkInput() {
        String input = txtInput.getText().toUpperCase();

        if (input.equals(captchaText.toUpperCase())) { // Captcha metniyle girilen metni karşılaştırma
            JOptionPane.showMessageDialog(this, "Doğru metin girişi!");
        } else {
            JOptionPane.showMessageDialog(this, "Yanlış metin girişi!", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Captcha Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 250);
        frame.setLocationRelativeTo(null);
        frame.add(new CaptchaPanel());
        frame.setVisible(true);
    }
}
