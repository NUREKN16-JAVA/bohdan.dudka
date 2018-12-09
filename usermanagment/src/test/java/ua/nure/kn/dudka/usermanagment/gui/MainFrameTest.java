package ua.nure.kn.dudka.usermanagment.gui;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.TestHelper;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.eventdata.StringEventData;
import junit.extensions.jfcunit.finder.NamedComponentFinder;
import org.junit.Assert;
import ua.nure.kn.dudka.usermanagment.db.DAOFactory;
import ua.nure.kn.dudka.usermanagment.db.DaoFactoryImpl;
import ua.nure.kn.dudka.usermanagment.db.MockUserDAO;
import ua.nure.kn.dudka.usermanagment.util.Message;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Properties;

public class MainFrameTest extends JFCTestCase {
    private MainFrame mainFrame;

    public void setUp() throws Exception {
        super.setUp();

        Properties properties = new Properties();
        properties.setProperty("dao.ua.nure.kn.dudka.usermanagment.db.UserDAO",
                MockUserDAO.class.getName());

        properties.setProperty("dao.factory", DaoFactoryImpl.class.getName());

        DAOFactory.init(properties);

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
        JTable table = (JTable) find(JTable.class, "userTable");
        int expectedRows = 0;
        assertEquals(expectedRows, table.getRowCount());

        JButton addButton = (JButton) find(JButton.class, "addButton");
        getHelper().enterClickAndLeave(new MouseEventData(this, addButton));

        find(JPanel.class, "addPanel");
        find(JButton.class, "cancelButton");

        JTextField firstNameField = (JTextField) find(JTextField.class, "firstNameField");
        JTextField dateOfBirthField = (JTextField) find(JTextField.class, "dateOfBirth");
        JTextField lastNameField = (JTextField) find(JTextField.class, "lastNameField");
        JButton okButton = (JButton) find(JButton.class, "okButton");

        LocalDate testLocalDate = LocalDate.now();
        String testDate = testLocalDate.toString();

        getHelper().sendString(new StringEventData(this, firstNameField, "Ivan"));
        getHelper().sendString(new StringEventData(this, lastNameField, "Ivanov"));
        getHelper().sendString(new StringEventData(this, dateOfBirthField, testDate));

        getHelper().enterClickAndLeave(new MouseEventData(this, okButton));

        find(JPanel.class, "browserPanel");
        table = (JTable) find(JTable.class, "userTable");
        expectedRows = 1;
        assertEquals(expectedRows, table.getRowCount());
    }
}
