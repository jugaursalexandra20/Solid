package ro.ase.acs.main;

import ro.ase.acs.classes.DataInserter;
import ro.ase.acs.classes.DataReader;
import ro.ase.acs.classes.TableCreator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            connection.setAutoCommit(false);

            TableCreator createTable = new TableCreator();
            createTable.createTable(connection);

            DataInserter dataInserter = new DataInserter();
            dataInserter.insertData(connection);

            DataReader dataReader = new DataReader();
            dataReader.readData(connection);

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
