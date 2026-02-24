package org.example.converter.ui;

import org.example.converter.ADTControl;
import org.example.converter.Editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConverterFrame extends JFrame {

    private final ADTControl controller;

    private JSlider trackBar1;
    private JSpinner numericUpDown1;
    private JLabel labelP1;

    private JSlider trackBar2;
    private JSpinner numericUpDown2;
    private JLabel labelP2;

    private JLabel labelInput;
    private JLabel labelOutput;
    private JLabel labelInputCaption;
    private JLabel labelOutputCaption;

    private final JButton[] digitButtons = new JButton[16];

    private JMenuItem exitMenuItem;
    private JMenuItem historyMenuItem;
    private JMenuItem aboutMenuItem;

    public ConverterFrame() {
        controller = new ADTControl();
        initComponents();
        setupEventHandlers();
        updateFromController();
    }

    private void initComponents() {
        setTitle("Конвертер систем счисления");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        createMenuBar();

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        mainPanel.add(createTopPanel(), BorderLayout.NORTH);
        mainPanel.add(createDisplayPanel(), BorderLayout.CENTER);
        mainPanel.add(createButtonPanel(), BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu mainMenu = new JMenu("Меню");

        historyMenuItem = new JMenuItem("История");
        aboutMenuItem = new JMenuItem("Справка");
        exitMenuItem = new JMenuItem("Выход");

        mainMenu.add(historyMenuItem);
        mainMenu.add(aboutMenuItem);
        mainMenu.addSeparator();
        mainMenu.add(exitMenuItem);

        menuBar.add(mainMenu);
        setJMenuBar(menuBar);
    }

    private JPanel createTopPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));

        JPanel p1Panel = new JPanel(new BorderLayout(10, 10));
        JLabel label1 = new JLabel("Основание исходного числа");
        trackBar1 = new JSlider(2, 16, controller.getPin());
        trackBar1.setMajorTickSpacing(2);
        trackBar1.setPaintTicks(true);
        trackBar1.setPaintLabels(true);

        numericUpDown1 = new JSpinner(new SpinnerNumberModel(controller.getPin(), 2, 16, 1));
        labelP1 = new JLabel("p1 = " + controller.getPin());

        p1Panel.add(label1, BorderLayout.NORTH);
        p1Panel.add(trackBar1, BorderLayout.CENTER);

        JPanel right1 = new JPanel(new FlowLayout());
        right1.add(numericUpDown1);
        right1.add(labelP1);
        p1Panel.add(right1, BorderLayout.EAST);

        JPanel p2Panel = new JPanel(new BorderLayout(10, 10));
        JLabel label2 = new JLabel("Основание результата");
        trackBar2 = new JSlider(2, 16, controller.getPout());
        trackBar2.setMajorTickSpacing(2);
        trackBar2.setPaintTicks(true);
        trackBar2.setPaintLabels(true);

        numericUpDown2 = new JSpinner(new SpinnerNumberModel(controller.getPout(), 2, 16, 1));
        labelP2 = new JLabel("p2 = " + controller.getPout());

        p2Panel.add(label2, BorderLayout.NORTH);
        p2Panel.add(trackBar2, BorderLayout.CENTER);

        JPanel right2 = new JPanel(new FlowLayout());
        right2.add(numericUpDown2);
        right2.add(labelP2);
        p2Panel.add(right2, BorderLayout.EAST);

        panel.add(p1Panel);
        panel.add(p2Panel);

        return panel;
    }

    private JPanel createDisplayPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        labelInputCaption = new JLabel("Исходное число (p=" + controller.getPin() + ")");
        labelInputCaption.setFont(new Font("Segoe UI", Font.BOLD, 14));

        labelInput = new JLabel("0");
        labelInput.setFont(new Font("Consolas", Font.BOLD, 24));
        labelInput.setHorizontalAlignment(SwingConstants.LEFT);
        labelInput.setOpaque(true);
        labelInput.setBackground(Color.WHITE);
        labelInput.setForeground(Color.BLACK);
        labelInput.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));

        labelOutputCaption = new JLabel("Результат (p=" + controller.getPout() + ")");
        labelOutputCaption.setFont(new Font("Segoe UI", Font.BOLD, 14));

        labelOutput = new JLabel("0");
        labelOutput.setFont(new Font("Consolas", Font.BOLD, 24));
        labelOutput.setHorizontalAlignment(SwingConstants.LEFT);
        labelOutput.setOpaque(true);
        labelOutput.setBackground(new Color(235, 255, 235));
        labelOutput.setForeground(Color.BLACK);
        labelOutput.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));

        panel.add(labelInputCaption);
        panel.add(labelInput);
        panel.add(labelOutputCaption);
        panel.add(labelOutput);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 4, 6, 6));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[] layout = {
                "A","B","C","D",
                "E","F","7","8",
                "9","4","5","6",
                "1","2","3","0"
        };

        for (String s : layout) {
            int digit = Integer.parseInt(s, 16);
            JButton btn = createDigitButton(s, digit);
            digitButtons[digit] = btn;
            panel.add(btn);
        }

        JButton buttonDot = createSpecialButton(".", Editor.Commands.addDot);
        JButton buttonBS = createSpecialButton("BS", Editor.Commands.bs);
        JButton buttonCL = createSpecialButton("CL", Editor.Commands.clear);
        JButton buttonExec = createSpecialButton("=", Editor.Commands.exec);

        buttonExec.setBackground(new Color(120, 200, 120));
        buttonExec.setFont(new Font("Segoe UI", Font.BOLD, 16));

        panel.add(buttonDot);
        panel.add(buttonBS);
        panel.add(buttonCL);
        panel.add(buttonExec);

        return panel;
    }

    private JButton createDigitButton(String text, int digit) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.putClientProperty("digit", digit);

        button.addActionListener(e -> {
            int d = (int) button.getClientProperty("digit");
            doCommand(Editor.Commands.values()[d]);
        });

        return button;
    }

    private JButton createSpecialButton(String text, Editor.Commands command) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setFocusPainted(false);

        button.addActionListener(e -> doCommand(command));
        return button;
    }

    private void setupEventHandlers() {
        trackBar1.addChangeListener(e -> {
            numericUpDown1.setValue(trackBar1.getValue());
            updateP1();
        });

        numericUpDown1.addChangeListener(e -> {
            trackBar1.setValue((Integer) numericUpDown1.getValue());
            updateP1();
        });

        trackBar2.addChangeListener(e -> {
            numericUpDown2.setValue(trackBar2.getValue());
            updateP2();
        });

        numericUpDown2.addChangeListener(e -> {
            trackBar2.setValue((Integer) numericUpDown2.getValue());
            updateP2();
        });

        exitMenuItem.addActionListener(e -> System.exit(0));

        historyMenuItem.addActionListener(e -> {
            HistoryDialog dialog = new HistoryDialog(this, controller.history);
            dialog.setVisible(true);
        });

        aboutMenuItem.addActionListener(e -> {
            AboutDialog dialog = new AboutDialog(this);
            dialog.setVisible(true);
        });
    }

    private void doCommand(Editor.Commands command) {
        String result;

        if (command == Editor.Commands.exec) {
            result = controller.doCommand(command);
            labelOutput.setText(result);
        } else {
            if (controller.getState() == ADTControl.State.CONVERTED) {
                labelInput.setText(controller.doCommand(Editor.Commands.clear));
                labelOutput.setText("0");
            }

            result = controller.doCommand(command);
            labelInput.setText(result.isEmpty() ? "0" : result);
            labelOutput.setText("0");
        }

        updateButtons();
    }

    private void updateP1() {
        int newP1 = (Integer) numericUpDown1.getValue();
        controller.setPin(newP1);
        labelP1.setText("p1 = " + newP1);
        labelInputCaption.setText("Исходное число (p=" + newP1 + ")");

        labelInput.setText("0");
        labelOutput.setText("0");
        updateButtons();
    }

    private void updateP2() {
        int newP2 = (Integer) numericUpDown2.getValue();
        controller.setPout(newP2);
        labelP2.setText("p2 = " + newP2);
        labelOutputCaption.setText("Результат (p=" + newP2 + ")");
    }

    private void updateButtons() {
        int base = controller.getPin();
        for (int i = 0; i < digitButtons.length; i++) {
            if (digitButtons[i] != null) {
                digitButtons[i].setEnabled(i < base);
            }
        }
    }

    private void updateFromController() {
        trackBar1.setValue(controller.getPin());
        trackBar2.setValue(controller.getPout());
        numericUpDown1.setValue(controller.getPin());
        numericUpDown2.setValue(controller.getPout());
        updateButtons();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}

            new ConverterFrame().setVisible(true);
        });
    }
}
