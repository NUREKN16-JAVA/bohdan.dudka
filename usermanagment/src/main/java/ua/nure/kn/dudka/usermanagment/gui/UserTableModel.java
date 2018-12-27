package ua.nure.kn.dudka.usermanagment.gui;

import ua.nure.kn.dudka.usermanagment.User;
import ua.nure.kn.dudka.usermanagment.util.Message;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserTableModel extends AbstractTableModel {
    private static  final String[] COLUMN_NAMES = {Message.getString("id"),
                                                    Message.getString("name"),
                                                    Message.getString("surname")};
    private static final Class[] COLUMN_CLASSES = {Long.class, String.class, String.class};
    private List usersList = null;

    public UserTableModel(Collection users) {
        this.usersList = new ArrayList(users);
    }

    @Override
    public int getRowCount() {
        return usersList.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }


    public Class getColumnClass(int columnIndex) {
        return COLUMN_CLASSES[columnIndex];
    }


    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    public void addUsers(Collection<User> users) {
        this.usersList.addAll(users);
    }

    public void clearUsers() {
        this.usersList = new ArrayList();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = (User) usersList.get(rowIndex);

        if (columnIndex == 0) {
            return user.getId();
        } else if (columnIndex == 1) {
            return user.getFirstName();
        } else if (columnIndex == 2) {
            return user.getLastName();
        }

        return null;
    }
}
