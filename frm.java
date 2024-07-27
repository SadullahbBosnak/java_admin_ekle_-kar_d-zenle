package yenii;

import javax.swing.*;
import java.awt.*;

public class frm extends JFrame {
    private JPanel panel;
    private JTextField kullaniciAdiText;
    private JTextField newKullaniciAdiText;
    private JPasswordField sifrePasswordField;
    private JPasswordField newPasswordField;
    private JPasswordField cntNewPasswordField;
    private JCheckBox beniHatirlaCheckBox;
    private JButton kullaniciButton;
    private JButton adminButton;
    private JButton yeniKullaniciButton;
    private JTextField adminKullaniciAdiText;
    private JPasswordField adminSifrePasswordField;
    private JButton adminGirisButton;
    private JButton kullaniciOlusturButton;
    private JCheckBox capchaCheckBox;

    public frm() {
        createUIComponents();
        add(panel);
        setTitle("Giriş Paneli");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createUIComponents() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel ustPanel = new JPanel();
        ustPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        kullaniciButton = new JButton("Kullanıcı");
        adminButton = new JButton("Admin");
        yeniKullaniciButton = new JButton("Yeni Kullanıcı");

        kullaniciButton.addActionListener(e -> showKullaniciPanel());
        adminButton.addActionListener(e -> showAdminPanel());
        yeniKullaniciButton.addActionListener(e -> showNewKullaniciPanel());

        ustPanel.add(kullaniciButton);
        ustPanel.add(adminButton);
        ustPanel.add(yeniKullaniciButton);

        panel.add(ustPanel, BorderLayout.NORTH);

        JPanel kullaniciPanel = createKullaniciPanel();
        JPanel adminPanel = createAdminPanel();
        JPanel newKullaniciPanel = createNewKullaniciPanel();

        panel.add(kullaniciPanel, BorderLayout.CENTER);
    }

    private JPanel createKullaniciPanel() {
        JPanel kullaniciPanel = new JPanel();
        kullaniciPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        kullaniciAdiText = new JTextField();
        kullaniciAdiText.setPreferredSize(new Dimension(150, 25));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;  // Sola hizalamak için
        kullaniciPanel.add(new JLabel("Kullanıcı Adı:"), gbc);
        gbc.gridy++;
        kullaniciPanel.add(kullaniciAdiText, gbc);
        gbc.gridy++;
        sifrePasswordField = new JPasswordField();
        sifrePasswordField.setPreferredSize(new Dimension(150, 25));
        kullaniciPanel.add(new JLabel("Şifre:"), gbc);
        gbc.gridy++;
        kullaniciPanel.add(sifrePasswordField, gbc);
        gbc.gridy++;
        beniHatirlaCheckBox = new JCheckBox("Beni Hatırla");
        kullaniciPanel.add(beniHatirlaCheckBox, gbc);
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        JButton girisButton = new JButton("Kullanıcı Giriş");
        girisButton.setPreferredSize(new Dimension(120, 30));
        girisButton.addActionListener(e -> kullaniciGirisKontrol());
        kullaniciPanel.add(girisButton, gbc);

        return kullaniciPanel;
    }

    private JPanel createAdminPanel() {
        JPanel adminPanel = new JPanel();
        adminPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        adminKullaniciAdiText = new JTextField();
        adminKullaniciAdiText.setPreferredSize(new Dimension(150, 25));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;  // Sola hizalamak için
        adminPanel.add(new JLabel("Admin Kullanıcı Adı:"), gbc);
        gbc.gridy++;
        adminPanel.add(adminKullaniciAdiText, gbc);
        gbc.gridy++;
        adminSifrePasswordField = new JPasswordField();
        adminSifrePasswordField.setPreferredSize(new Dimension(150, 25));
        adminPanel.add(new JLabel("Admin Şifre:"), gbc);
        gbc.gridy++;
        adminPanel.add(adminSifrePasswordField, gbc);
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        adminGirisButton = new JButton("Admin Giriş");
        adminGirisButton.setPreferredSize(new Dimension(120, 30));
        adminGirisButton.addActionListener(e -> adminGirisKontrol());
        adminPanel.add(adminGirisButton, gbc);

        return adminPanel;
    }

    private JPanel createNewKullaniciPanel() {
        JPanel newKullaniciPanel = new JPanel();
        newKullaniciPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        newKullaniciAdiText = new JTextField();
        newKullaniciAdiText.setPreferredSize(new Dimension(150, 25));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;  // Sola hizalamak için
        newKullaniciPanel.add(new JLabel("Kullanıcı Adı:"), gbc);
        gbc.gridy++;
        newKullaniciPanel.add(newKullaniciAdiText, gbc);
        gbc.gridy++;
        newPasswordField = new JPasswordField();
        newPasswordField.setPreferredSize(new Dimension(150, 25));
        newKullaniciPanel.add(new JLabel("Şifre:"), gbc);
        gbc.gridy++;
        newKullaniciPanel.add(newPasswordField, gbc);
        gbc.gridy++;
        cntNewPasswordField = new JPasswordField();
        cntNewPasswordField.setPreferredSize(new Dimension(150, 25));
        newKullaniciPanel.add(new JLabel("Şifreyi Tekrar Girin:"), gbc);
        gbc.gridy++;
        newKullaniciPanel.add(cntNewPasswordField, gbc);
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        kullaniciOlusturButton = new JButton("Kullanıcı Oluştur");
        kullaniciOlusturButton.setPreferredSize(new Dimension(150, 30));
        kullaniciOlusturButton.addActionListener(e -> kullaniciOlustur());
        newKullaniciPanel.add(kullaniciOlusturButton, gbc);

        return newKullaniciPanel;
    }

    private void showKullaniciPanel() {
        panel.remove(1);
        panel.add(createKullaniciPanel(), BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();
    }

    private void showAdminPanel() {
        panel.remove(1);
        panel.add(createAdminPanel(), BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();
    }

    private void showNewKullaniciPanel() {
        panel.remove(1);
        panel.add(createNewKullaniciPanel(), BorderLayout.CENTER);
        panel.revalidate();
        panel.repaint();
    }

    private void kullaniciGirisKontrol() {
        String kullaniciAdi = kullaniciAdiText.getText();
        String sifre = new String(sifrePasswordField.getPassword());

        if (kullaniciAdi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Kullanıcı adı boş olamaz.", "Hata", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (sifre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Şifre boş olamaz.", "Hata", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (kullaniciAdi.equals("gercek_kullanici") && sifre.equals("1234")) {
            JOptionPane.showMessageDialog(this, "Giriş başarılı!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Kullanıcı adı veya şifre hatalı. Lütfen tekrar deneyin.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void adminGirisKontrol() {
        String adminKullaniciAdi = adminKullaniciAdiText.getText();
        String adminSifre = new String(adminSifrePasswordField.getPassword());

        if (adminKullaniciAdi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Admin kullanıcı adı boş olamaz.", "Hata", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (adminSifre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Admin şifre boş olamaz.", "Hata", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (adminKullaniciAdi.equals("gercek_admin") && adminSifre.equals("admin123")) {
            JOptionPane.showMessageDialog(this, "Giriş başarılı!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Admin kullanıcı adı veya şifre hatalı. Lütfen tekrar deneyin.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void kullaniciOlustur() {
        String kullaniciAdi = newKullaniciAdiText.getText();
        String sifre = new String(newPasswordField.getPassword());
        String sifreTekrar = new String(cntNewPasswordField.getPassword());

        if (kullaniciAdi.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Kullanıcı adı boş olamaz.", "Hata", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (sifre.isEmpty() || sifreTekrar.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Şifre alanları boş olamaz.", "Hata", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!sifre.equals(sifreTekrar)) {
            JOptionPane.showMessageDialog(this, "Şifreler uyuşmuyor. Lütfen tekrar deneyin.", "Hata", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Yeni kullanıcı başarıyla oluşturuldu.", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(frm::new);
    }
}