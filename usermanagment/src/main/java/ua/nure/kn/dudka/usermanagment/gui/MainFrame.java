package ua.nure.kn.dudka.usermanagment.gui;

import ua.nure.kn.dudka.usermanagment.User;
import ua.nure.kn.dudka.usermanagment.db.DAOFactory;
import ua.nure.kn.dudka.usermanagment.db.UserDAO;
import ua.nure.kn.dudka.usermanagment.util.Message;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private static final int FRAME_HEIGTH = 600;
    private static final int FRAME_WIDTH = 1200;
    private EditPanel editPanel;
    private JPanel contentPanel;
    private JPanel browsePanel;
    private AddPanel addPanel;
    private UserDAO userDAO;

    public UserDAO getUserDAO() {
        return userDAO;
    }

    MainFrame () {
        super();
        try {
            userDAO = DAOFactory.getInstance().getUserDAO();
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        initialize();
    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setVisible(true);


    }

    private void initialize() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(FRAME_WIDTH, FRAME_HEIGTH);
        this.setTitle(Message.getString("user_management"));
        this.setContentPane(getContentPanel());
    }

    private JPanel getContentPanel() {
        if (contentPanel == null) {
            contentPanel = new JPanel();
            contentPanel.setLayout(new BorderLayout());
            contentPanel.add(getBrowsePanel(), BorderLayout.CENTER);

        }
        return contentPanel;
    }

    private JPanel getBrowsePanel() {
        if (browsePanel == null) {
            browsePanel = new BrowsePanel(this);
        }
        ((BrowsePanel)browsePanel).initTable();

        return browsePanel;
    }

    private void showPanel(JPanel panel) {
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setVisible(true);
        panel.repaint();
    }

    public void showAddPanel() {
        showPanel(getAddPanel());
    }

    private AddPanel getAddPanel() {
        if (addPanel == null) {
            addPanel = new AddPanel(this);

        }

        return addPanel;
    }

    public void showBrowsePanel() {
        showPanel(getBrowsePanel());
    }

    public void showEditPanel() {
        showPanel(getEditPanel());
    }

    private EditPanel getEditPanel() {
        if (editPanel == null) {
            editPanel = new EditPanel(this);
        }
        return editPanel;
    }

    public User getSelectedUser() {
        return ((BrowsePanel)browsePanel).getSelectedUser();
    }
}
