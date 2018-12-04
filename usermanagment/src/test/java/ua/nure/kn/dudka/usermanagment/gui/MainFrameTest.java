package ua.nure.kn.dudka.usermanagment.gui;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.finder.NamedComponentFinder;
import org.junit.Assert;

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
        getHelper().cleanUp(this);
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
        find(JTable.class, "userTable");
    }
}
