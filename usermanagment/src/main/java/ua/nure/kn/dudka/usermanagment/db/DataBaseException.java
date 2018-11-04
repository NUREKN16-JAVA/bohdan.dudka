package ua.nure.kn.dudka.usermanagment.db;

public class DataBaseException extends Exception {
    private String name;

    DataBaseException (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
