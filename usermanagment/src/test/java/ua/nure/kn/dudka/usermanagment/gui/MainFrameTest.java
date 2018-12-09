package ua.nure.kn.dudka.usermanagment.gui;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.TestHelper;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.finder.NamedComponentFinder;
import org.junit.Assert;
import ua.nure.kn.dudka.usermanagment.util.Message;

import javax.swing.*;
import java.awt.*;

public class MainFrameTest extends JFCTestCase {
    private MainFrame mainFrame;

    public void setUp() throws Exception {
        super.setUp();
        setHelper(new JFCTestHelper());
        mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }

    public void tearDown() throws Exception {
        mainFrame.setVisible(false);
        TestHelper.cleanUp(this);
        super.tearDown();
    }

    private Component find(Class componentElement, String name) {
        NamedComponentFinder finder = new NamedComponentFinder(componentElement, name);
        finder.setWait(0);

        Component component = finder.find(mainFrame, 0);
        Assert.assertNotNull("Could not find element " + name + ",", component);

        return component;
    }

    public void testBrowseConsole () {
        find(JButton.class, "detailsButton");
        find(JButton.class, "deleteButton");
        find(JPanel.class, "browserPanel");
        find(JButton.class, "editButton");
        find(JButton.class, "addButton");

        int expectedRowCount = 3;
        String expectedFirstColumn = Message.getString("id");
        String expectedSecondColumn = Message.getString("name");
        String expectedThirdColumn = Message.getString("surname");

        JTable table = (JTable) find(JTable.class, "userTable");

        assertEquals(expectedRowCount, table.getColumnCount());

        assertEquals(expectedFirstColumn, table.getColumnName(0));
        assertEquals(expectedSecondColumn, table.getColumnName(1));
        assertEquals(expectedThirdColumn, table.getColumnName(2));


    }

    public void testAddUser() {
        JButton addButton = (JButton) find(JButton.class, "addButton");
        getHelper().enterClickAndLeave(new MouseEventData(this, addButton));

        find(JPanel.class, "addPanel");

        find(JTextField.class, "firstNameField");
        find(JTextField.class, "lastNameField");
        find(JTextField.class, "dateOfBirth");
        find(JButton.class, "cancelButton");
        find(JButton.class, "okButton");
    }
}
