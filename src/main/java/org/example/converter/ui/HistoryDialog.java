package org.example.converter.ui;

import org.example.converter.History;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HistoryDialog extends JDialog {

    private JTable historyTable;
    private DefaultTableModel tableModel;

    public HistoryDialog(JFrame parent, History history) {
        super(parent, "История конвертации", true);
        initComponents(history);
    }

    private void initComponents(History history) {
        setSize(600, 400);
        setLocationRelativeTo(getOwner());
        setLayout(new BorderLayout(10,10));

        String[] columns = {"p1", "Исходное", "p2", "Результат"};
        tableModel = new DefaultTableModel(columns, 0);

        historyTable = new JTable(tableModel);
        historyTable.setRowHeight(24);

        add(new JScrollPane(historyTable), BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        JButton clear = new JButton("Очистить историю");
        JButton close = new JButton("Закрыть");

        clear.addActionListener(e -> {
            history.clear();
            loadHistory(history);
        });

        close.addActionListener(e -> dispose());

        buttons.add(clear);
        buttons.add(close);

        add(buttons, BorderLayout.SOUTH);

        loadHistory(history);
    }

    private void loadHistory(History history) {
        tableModel.setRowCount(0);
        for (History.Record r : history.recordList) {
            tableModel.addRow(new Object[]{
                    r.p1(),
                    r.number1(),
                    r.p2(),
                    r.number2()
            });
        }
    }
}
