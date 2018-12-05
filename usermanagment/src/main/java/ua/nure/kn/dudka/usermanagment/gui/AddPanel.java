package ua.nure.kn.dudka.usermanagment.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPanel extends JPanel implements ActionListener {
    private MainFrame parent;
    private JButton cancelButton;
    private JButton okButton;
    private JPanel buttonPanel;
    private JPanel fieldPanel;
    private JTextField dateOfBirth;
    private JTextField lastNameField;
    private JTextField firstNameField;

    AddPanel(MainFrame frame) {
        parent = frame;
        initialize();
    }

    private void initialize() {
        this.setName("addPanel");
        this.setLayout(new BorderLayout());
        this.add(getFieldPanel(), BorderLayout.NORTH);
        this.add(getButtonPanel(), BorderLayout.SOUTH);

    }

    private JPanel getButtonPanel() {
        if (buttonPanel == null) {
            buttonPanel = new JPanel();
            buttonPanel.add(getOkButton(), null);
            buttonPanel.add(getCancelButton(), null);
        }

        return buttonPanel;
    }

    private JButton getCancelButton() {
        if (cancelButton == null) {
            cancelButton = new JButton();
            cancelButton.setName("cancelButton");
            cancelButton.setText("Cancel");
            cancelButton.setActionCommand("cancel");
            cancelButton.addActionListener(this);
        }

        return cancelButton;
    }

    private JButton getOkButton() {
        if (okButton == null) {
            okButton = new JButton();
            okButton.setName("okButton");
            okButton.setText("Add");
            okButton.setActionCommand("ok");
            okButton.addActionListener(this);
        }

        return okButton;
    }

    private JPanel getFieldPanel() {
        if (fieldPanel == null) {
            fieldPanel = new JPanel();
            fieldPanel.setLayout(new GridLayout(3, 2));
            addLabelField(fieldPanel, "Name", getFirstNameField());
            addLabelField(fieldPanel, "Surname", getLastNameField());
            addLabelField(fieldPanel, "Date of Birth", getDateOfBirthField());
        }

        return fieldPanel;
    }

    private JTextField getDateOfBirthField() {
        if (dateOfBirth == null) {
            dateOfBirth = new JTextField();
            dateOfBirth.setName("dateOfBirthField");
        }

        return dateOfBirth;
    }

    private JTextField getLastNameField() {
        if (lastNameField == null) {
            lastNameField = new JTextField();
            lastNameField.setName("lastNameField");
        }

        return lastNameField;
    }

    private void addLabelField(JPanel fieldPanel, String labelText, JTextField textField) {
        JLabel label = new JLabel(labelText);
        label.setLabelFor(textField);
        fieldPanel.add(label);
        fieldPanel.add(textField);
    }

    private JTextField getFirstNameField() {
        if (firstNameField == null) {
            firstNameField = new JTextField();
            firstNameField.setName("firstNameField");
        }

        return firstNameField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
