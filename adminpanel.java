package yenii;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

public class adminpanel extends JFrame {

    private JPanel imagePanel;
    private List<ImageItem> imageItemList = new ArrayList<>();
    private ImageItem selectedItem;
    private JLabel messageLabel;
    private boolean editMode = false;
    private boolean removeMode = false;

    public adminpanel() {
        setTitle("Admin Paneli");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        add(panel);

        JLabel titleLabel = new JLabel("Admin Paneli", SwingConstants.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Ekle");
        JButton editButton = new JButton("Düzenle");
        JButton removeButton = new JButton("Çıkar");
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(removeButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        messageLabel = new JLabel("", SwingConstants.CENTER);
        panel.add(messageLabel, BorderLayout.CENTER);

        imagePanel = new JPanel(new GridLayout(0, 2, 10, 10));
        JScrollPane scrollPane = new JScrollPane(imagePanel);
        panel.add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String description = JOptionPane.showInputDialog("Açıklama girin:");
                if (description != null && !description.trim().isEmpty()) {
                    if (isImageFile(selectedFile)) {
                        try {
                            addImageToPanel(selectedFile, description);
                            messageLabel.setText("Dosya ve açıklama eklendi.");
                        } catch (IOException ex) {
                            messageLabel.setText("Resim yüklenirken hata oluştu.");
                        }
                    } else {
                        messageLabel.setText("Lütfen bir resim dosyası seçin.");
                    }
                } else {
                    messageLabel.setText("Açıklama girilmedi.");
                }
            } else {
                messageLabel.setText("Dosya seçilmedi.");
            }
        });

        editButton.addActionListener(e -> {
            editMode = true;
            removeMode = false;
            showCheckboxes(true);
            messageLabel.setText("Düzenlemek için bir resim seçin.");
        });

        removeButton.addActionListener(e -> {
            editMode = false;
            removeMode = true;
            showCheckboxes(true);
            messageLabel.setText("Çıkarmak için bir resim seçin.");
        });

        setVisible(true);
    }

    private boolean isImageFile(File file) {
        String[] imageExtensions = {"jpg", "jpeg", "png", "gif", "bmp"};
        String fileName = file.getName().toLowerCase();
        for (String extension : imageExtensions) {
            if (fileName.endsWith(extension)) {
                return true;
            }
        }
        return false;
    }

    private void addImageToPanel(File file, String description) throws IOException {
        ImageItem imageItem = new ImageItem(file, description);
        imageItemList.add(imageItem);
        imagePanel.add(imageItem.getPanel());
        imagePanel.revalidate();
        imagePanel.repaint();
    }

    private void removeImageFromPanel(ImageItem imageItem) {
        imagePanel.remove(imageItem.getPanel());
        imageItemList.remove(imageItem);
        imagePanel.revalidate();
        imagePanel.repaint();
    }

    private void editImageDescription(ImageItem imageItem) {
        String newDescription = JOptionPane.showInputDialog("Yeni açıklamayı girin:", imageItem.getDescription());
        if (newDescription != null && !newDescription.trim().isEmpty()) {
            imageItem.setDescription(newDescription);
            imagePanel.revalidate();
            imagePanel.repaint();
            messageLabel.setText("Açıklama düzenlendi.");
        }
    }

    private void showImageInDialog(BufferedImage image) {
        JDialog dialog = new JDialog(this, "Resim Görüntüleyici", true);
        dialog.setSize(800, 600);

        ImageIcon imageIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIcon);
        dialog.add(new JScrollPane(imageLabel));

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void showCheckboxes(boolean visible) {
        for (ImageItem item : imageItemList) {
            item.setCheckboxVisible(visible);
        }
    }

    private class ImageItem {
        private JPanel panel;
        private JLabel descriptionLabel;
        private JCheckBox checkBox;
        private File file;
        private String description;
        private BufferedImage originalImage;

        public ImageItem(File file, String description) throws IOException {
            this.file = file;
            this.description = description;
            this.panel = new JPanel(new BorderLayout());

            this.originalImage = ImageIO.read(file);
            Image resizedImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(resizedImage);
            JLabel imageLabel = new JLabel(imageIcon);
            imageLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (editMode || removeMode) {
                        selectedItem = ImageItem.this;
                        checkBox.setSelected(true);
                        if (editMode) {
                            editImageDescription(ImageItem.this);
                            editMode = false;
                            showCheckboxes(false);
                        } else if (removeMode) {
                            removeImageFromPanel(ImageItem.this);
                            removeMode = false;
                            showCheckboxes(false);
                        }
                    } else {
                        showImageInDialog(originalImage);
                    }
                }
            });
            panel.add(imageLabel, BorderLayout.CENTER);

            descriptionLabel = new JLabel(description, SwingConstants.CENTER);
            panel.add(descriptionLabel, BorderLayout.SOUTH);

            checkBox = new JCheckBox();
            checkBox.setVisible(false);
            panel.add(checkBox, BorderLayout.NORTH);
        }

        public JPanel getPanel() {
            return panel;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
            descriptionLabel.setText(description);
        }

        public void setCheckboxVisible(boolean visible) {
            checkBox.setVisible(visible);
            checkBox.setSelected(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(adminpanel::new);
    }
}
