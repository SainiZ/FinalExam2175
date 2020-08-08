package com.company;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Savings extends CompoundInterest{

    public Savings(){ };
    Connection conn;
    PreparedStatement query;
    String customerNumber, customerName, typeOfSavings, initialDeposit, numberOfYears;

    public void add(String customerNumber, String customerName,String initialDeposit ,String numberOfYears ,String typeOfSavings) throws ClassNotFoundException, SQLException {

        customerNumber=this.customerNumber;
        customerName=this.customerName;
        typeOfSavings=this.typeOfSavings;
        initialDeposit=this.initialDeposit;
        numberOfYears=this.numberOfYears;
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost/savings", "root", "");
        query = conn.prepareStatement("insert into savingstable values(?,?,?,?,?)");
        query.setString(1, customerNumber);
        query.setString(2, customerName);
        query.setString(3, initialDeposit.toString());
        query.setString(4, numberOfYears.toString());
        query.setString(5, typeOfSavings);
        query.executeUpdate();
        JOptionPane.showMessageDialog(null, "Record added");
        updateTable();
    }

    public void edit(String customerNumber, String customerName,String initialDeposit ,String numberOfYears ,String typeOfSavings, value)
            throws ClassNotFoundException, SQLException {
        customerNumber=this.customerNumber;
        customerName=this.customerName;
        typeOfSavings=this.typeOfSavings;
        initialDeposit=this.initialDeposit;
        numberOfYears=this.numberOfYears;
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost/savings", "root", "");
        query = conn.prepareStatement("update savingstable set custno=? , custname = ?, cdep = ?, nyears=?," +
                "savtype=? WHERE custno = ?");
        query.setString(1, customerNumber);
        query.setString(2, customerName);
        query.setString(3, initialDeposit);
        query.setString(4, numberOfYears.toString());
        query.setString(5, typeOfSavings);
        query.setString(6, value);
        query.executeUpdate();
        JOptionPane.showMessageDialog(null, "Record added");
        updateTable();
    }

    public void delete(String value)throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost/savings", "root", "");
        query = conn.prepareStatement("delete from savingstable WHERE custno = ?");
        query.setString(1,value);
        query.executeUpdate();
        JOptionPane.showMessageDialog(null, "Record added");
        updateTable();
    }

}
