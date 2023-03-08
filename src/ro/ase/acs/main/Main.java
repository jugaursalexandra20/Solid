package ro.ase.acs.main;

import ro.ase.acs.classes.EmployeeInserter;
import ro.ase.acs.classes.EmployeeReader;
import ro.ase.acs.classes.EmployeeCreator;
import ro.ase.acs.interfaces.DataInserter;
import ro.ase.acs.interfaces.DataReader;
import ro.ase.acs.interfaces.TableCreator;

import java.sql.Connection;
import java.sql.DriverManager;


public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:database.db");
            connection.setAutoCommit(false);

            TableCreator createTable = new EmployeeCreator();
            createTable.createTable(connection);

            DataInserter dataInserter = new EmployeeInserter();
            dataInserter.insertData(connection);

            DataReader dataReader = new EmployeeReader();
            dataReader.readData(connection);

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
