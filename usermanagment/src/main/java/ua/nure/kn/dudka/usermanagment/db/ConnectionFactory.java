package ua.nure.kn.dudka.usermanagment.db;

import java.sql.Connection;

public interface ConnectionFactory {
    Connection createConnection () throws DataBaseException;
}
