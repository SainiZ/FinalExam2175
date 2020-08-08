/*
 * Created by JFormDesigner on Sat Aug 08 09:25:14 PDT 2020
 */

package com.company;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.*;

import java.sql.*;
import java.util.Vector;

/**
 * @author Gursimran Saini
 */
public class CompoundInterest extends JFrame {

    String value;
    String customerNumber, customerName, typeOfSavings, initialDeposit, numberOfYears;
    Connection conn;
    PreparedStatement query;

    public void generateTable() {

        String[] cols = {"Number","Name","Deposit","Years","Type of Savings"};
        String[][] data = {{"d1", "d1.1"},{"d2", "d2.1"},{"d3", "d3.1"}};
        DefaultTableModel model = new DefaultTableModel(data, cols);
        table2.setModel(model);
        String[] cols1 = {"Year","Starting","Interest","Ending Value"};
        String[][] data1 = {{"d1", "d1.1"},{"d2", "d2.1"},{"d3", "d3.1"}};
        DefaultTableModel model2 = new DefaultTableModel(data1, cols1);
        table2.setModel(model2);
    }

        /*public void generateTable(){
            String[] cols = {"Year", "Starting", "Interest", "Ending Value"};
            String[][] data = {{"d1","d1.1"},{"d2","d2.1"},{"d3","d3.1"},{"d4","d4.1"},{"d5","d5.1"}};
            DefaultTableModel model = new DefaultTableModel(data, cols);
            table3.setModel(model);
        }*/


    public void updateTable() throws ClassNotFoundException, SQLException {

        int c;
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost/savings","root","");
        query = conn.prepareStatement("Select * from savingstable");
        ResultSet rs = query.executeQuery();
        ResultSetMetaData Res = rs.getMetaData();
        c = Res.getColumnCount();
        DefaultTableModel df = (DefaultTableModel) table2.getModel();
        df.setRowCount(0);
        while(rs.next()==true) {
            Vector v = new Vector();
            for(int a=1;a<=c;a++){
                v.add(rs.getString("custno"));
                v.add(rs.getString("custname"));
                v.add(rs.getString("cdep"));
                v.add(rs.getString("nyears"));
                v.add(rs.getString("savtype"));
            }
            df.addRow(v);
        }
    }


    public CompoundInterest() {

        initComponents();
    }

    private void btnAddKeyPressed(KeyEvent e) {
        // TODO add your code here
    }

    private void btnAddActionPerformed(ActionEvent e) {

        Savings sav = new Savings();
        customerNumber = txtCustNum.getText();
        customerName = txtCustName.getText();
        typeOfSavings = cbType.getSelectedItem().toString();
        initialDeposit = txtDeposit.getText()
        numberOfYears = txtYears.getText();
        sav.add(customerNumber,customerName,typeOfSavings,initialDeposit,numberOfYears);
    }

    private void btnEditActionPerformed(ActionEvent e) {

        Savings sav = new Savings();
        customerNumber = txtCustNum.getText();
        customerName = txtCustName.getText();
        typeOfSavings = cbType.getSelectedItem().toString();
        initialDeposit = txtDeposit.getText();
        numberOfYears = txtYears.getText();
        sav.edit(customerNumber,customerName,typeOfSavings,initialDeposit,numberOfYears,value);
    }

    private void btnDeleteActionPerformed(ActionEvent e) {

        Savings sav = new Savings();
        sav.delete(value);
    }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Gursimran Saini
        label1 = new JLabel();
        txtCustNum = new JTextField();
        label2 = new JLabel();
        txtCustName = new JTextField();
        label3 = new JLabel();
        txtDeposit = new JTextField();
        label4 = new JLabel();
        txtYears = new JTextField();
        label5 = new JLabel();
        cbType = new JComboBox<>();
        scrollPane1 = new JScrollPane();
        table2 = new JTable();
        scrollPane2 = new JScrollPane();
        table3 = new JTable();
        btnAdd = new JButton();
        btnEdit = new JButton();
        btnDelete = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[fill]" +
            "[208,fill]" +
            "[fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- label1 ----
        label1.setText("Enter the Customer Number");
        contentPane.add(label1, "cell 0 1");
        contentPane.add(txtCustNum, "cell 1 1 3 1");

        //---- label2 ----
        label2.setText("Enter the Customer Name");
        contentPane.add(label2, "cell 0 2");
        contentPane.add(txtCustName, "cell 1 2 3 1");

        //---- label3 ----
        label3.setText("Enter the Initail Deposit");
        contentPane.add(label3, "cell 0 3");
        contentPane.add(txtDeposit, "cell 1 3 3 1");

        //---- label4 ----
        label4.setText("Enter the Number of years");
        contentPane.add(label4, "cell 0 4");
        contentPane.add(txtYears, "cell 1 4 3 1");

        //---- label5 ----
        label5.setText("Enter the Type of savings");
        contentPane.add(label5, "cell 0 5");

        //---- cbType ----
        cbType.setModel(new DefaultComboBoxModel<>(new String[] {
            "Savings-Deluxe",
            "Savings-Regular"
        }));
        contentPane.add(cbType, "cell 1 5 3 1");

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table2);
        }
        contentPane.add(scrollPane1, "cell 0 6");

        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(table3);
        }
        contentPane.add(scrollPane2, "cell 1 6 3 1");

        //---- btnAdd ----
        btnAdd.setText("Add");
        btnAdd.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                btnAddKeyPressed(e);
            }
        });
        btnAdd.addActionListener(e -> btnAddActionPerformed(e));
        contentPane.add(btnAdd, "cell 0 7");

        //---- btnEdit ----
        btnEdit.setText("Edit");
        btnEdit.addActionListener(e -> btnEditActionPerformed(e));
        contentPane.add(btnEdit, "cell 0 7");

        //---- btnDelete ----
        btnDelete.setText("Delete");
        btnDelete.addActionListener(e -> btnDeleteActionPerformed(e));
        contentPane.add(btnDelete, "cell 0 7");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Gursimran Saini
    private JLabel label1;
    private JTextField txtCustNum;
    private JLabel label2;
    private JTextField txtCustName;
    private JLabel label3;
    private JTextField txtDeposit;
    private JLabel label4;
    private JTextField txtYears;
    private JLabel label5;
    private JComboBox<String> cbType;
    private JScrollPane scrollPane1;
    private JTable table2;
    private JScrollPane scrollPane2;
    private JTable table3;
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;
    // JFormDesigner - End of variables declaration  //GEN-END:variables


}
