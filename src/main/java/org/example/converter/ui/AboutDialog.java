package org.example.converter.ui;

import javax.swing.*;
import java.awt.*;

public class AboutDialog extends JDialog {

    public AboutDialog(JFrame parent) {
        super(parent, "О программе", true);
        initComponents();
    }

    private void initComponents() {
        setSize(400, 300);
        setLocationRelativeTo(getOwner());
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JLabel title = new JLabel("Конвертер систем счисления");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel version = new JLabel("Версия 1.0");
        version.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel desc = new JLabel("<html><center>Конвертация чисел<br>между системами счисления 2–16</center></html>");
        desc.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton close = new JButton("Закрыть");
        close.setAlignmentX(Component.CENTER_ALIGNMENT);
        close.addActionListener(e -> dispose());

        panel.add(title);
        panel.add(Box.createVerticalStrut(10));
        panel.add(version);
        panel.add(Box.createVerticalStrut(20));
        panel.add(desc);
        panel.add(Box.createVerticalStrut(30));
        panel.add(close);

        add(panel, BorderLayout.CENTER);
    }
}
