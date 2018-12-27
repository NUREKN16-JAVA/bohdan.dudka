package ua.nure.kn.dudka.usermanagment.db;

import ua.nure.kn.dudka.usermanagment.db.exception.DataBaseException;

import java.sql.Connection;

/**
 * Interface to create connection with DB
 */
public interface ConnectionFactory {
    Connection createConnection () throws DataBaseException;
}
