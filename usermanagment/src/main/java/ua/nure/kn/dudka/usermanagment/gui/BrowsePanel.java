package ua.nure.kn.dudka.usermanagment.gui;

import ua.nure.kn.dudka.usermanagment.User;
import ua.nure.kn.dudka.usermanagment.db.DataBaseException;
import ua.nure.kn.dudka.usermanagment.util.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
            addButton.setText(Message.getString("add.user_button"));
            addButton.setName("addButton");
            addButton.setActionCommand("add");
            addButton.addActionListener(this);
        }

        return addButton;
    }

    private JButton getEditButton() {
        if (editButton == null) {
            editButton = new JButton();
            editButton.setText(Message.getString("edit.user_button"));
            editButton.setName("editButton");
            editButton.setActionCommand("edit");
            editButton.addActionListener(this);
        }

        return editButton;
    }

    private JButton getDeleteButton() {
        if (deleteButton == null) {
            deleteButton = new JButton();
            deleteButton.setText(Message.getString("delete.user_button"));
            deleteButton.setName("deleteButton");
            deleteButton.setActionCommand("delete");
            deleteButton.addActionListener(this);
        }

        return deleteButton;
    }

    private JButton getDetailsButton() {
        if (detailsButton == null) {
            detailsButton = new JButton();
            detailsButton.setText(Message.getString("user.details_button"));
            detailsButton.setName("detailsButton");
            detailsButton.setActionCommand("details");
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

    public void initTable() {
        UserTableModel model = null;
        try {
            model = new UserTableModel(parent.getUserDAO().findAll());
        } catch (DataBaseException e) {
            model = new UserTableModel(new ArrayList());
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        getUserTable().setModel(model);
    }

    private User getUserDetails() {
        User user = null;
        Long userId = null;

        try {
            userId = (Long) userTable.getValueAt(userTable.getSelectedRow(), userTable.getSelectedColumn());
            user =  parent.getUserDAO().find(userId);
        } catch (DataBaseException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid field selected! Select field with user ID", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return user;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equalsIgnoreCase("add")) {
            this.setVisible(false);
            parent.showAddPanel();

        } else if (action.equalsIgnoreCase("details")) {
            JOptionPane.showMessageDialog(this, getUserDetails().toString(), "User info", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
