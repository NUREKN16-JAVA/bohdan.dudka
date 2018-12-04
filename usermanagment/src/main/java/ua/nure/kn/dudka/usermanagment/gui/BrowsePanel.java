package ua.nure.kn.dudka.usermanagment.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrowsePanel extends JPanel implements ActionListener {
    private JScrollPane tablePanel;
    private JButton detailsButton;
    private JButton deleteButton;
    private JPanel buttonsPanel;
    private JButton editButton;
    private JButton addButton;
    private JTable userTable;
    private MainFrame parent;

    public BrowsePanel(MainFrame mainFrame) {
    parent = mainFrame;
    initialize();
    }

    private void initialize() {
        this.setName("browserPanel");
        this.setLayout(new BorderLayout());
        this.add(getTablePanel(), BorderLayout.CENTER);
        this.add(getButtonsPanel(), BorderLayout.SOUTH);

    }

    private JPanel getButtonsPanel() {
        if (buttonsPanel == null) {
            buttonsPanel = new JPanel();

            buttonsPanel.add(getDetailsButton(), null);
            buttonsPanel.add(getDeleteButton(), null);
            buttonsPanel.add(getEditButton(), null);
            buttonsPanel.add(getAddButton(), null);
        }

        return buttonsPanel;
    }

    private JButton getAddButton() {
        if (addButton == null) {
            addButton = new JButton();
            addButton.setText("Add user");
            addButton.setName("addButton");
            addButton.addActionListener(this);
        }

        return addButton;
    }

    private JButton getEditButton() {
        if (editButton == null) {
            editButton = new JButton();
            editButton.setText("Edit user");
            editButton.setName("editButton");
            editButton.addActionListener(this);
        }

        return editButton;
    }

    private JButton getDeleteButton() {
        if (deleteButton == null) {
            deleteButton = new JButton();
            deleteButton.setText("Delete user");
            deleteButton.setName("deleteButton");
            deleteButton.addActionListener(this);
        }

        return deleteButton;
    }

    private JButton getDetailsButton() {
        if (detailsButton == null) {
            detailsButton = new JButton();
            detailsButton.setText("User details");
            detailsButton.setName("detailsButton");
            detailsButton.addActionListener(this);
        }

        return detailsButton;
    }

    private JScrollPane getTablePanel() {
        if (tablePanel == null) {
            tablePanel = new JScrollPane(getUserTable());
        }

        return tablePanel;
    }

    private JTable getUserTable() {
        if (userTable == null) {
            userTable = new JTable();
            userTable.setName("userTable");
        }

        return userTable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
