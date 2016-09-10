/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Housingapp;


 
import javax.swing.*;
 
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.*;
 
public class Coucou extends JPanel {
    public Connection con = null;
    public Statement requete = null;
    public ResultSet rs = null;
    public Coucou(){
        try{
            String url = "jdbc:mysql://localhost:3306/university?";
            String login = "root";
            String paswordd = "";    
            con = DriverManager.getConnection(url,login,paswordd);
            requete = con.createStatement();
            rs = requete.executeQuery("SELECT * FROM site_properties");
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
     
            Vector columns = new Vector(columnCount);
     
            //store column names
            for(int i=1; i<=columnCount; i++)
                columns.add(md.getColumnName(i));
            Vector data = new Vector();
            Vector row;
     
            //store row data
            while(rs.next())
            {
                row = new Vector(columnCount);
                for(int i=1; i<=columnCount; i++)
                {
                    row.add(rs.getString(1));
                }
                data.add(row);
            }
            JTable table = new JTable(data, columns);
            table.setPreferredScrollableViewportSize(new Dimension(500, 70));
            table.setFillsViewportHeight(true);
            JFrame frame = new JFrame("View Doctors");
            frame.add(table);
            table.setVisible(true);
            table.validate();
 
        }
        catch(SQLException sqle){
            //cf Comment gérer les erreurs ? 
            System.out.println(sqle);
            sqle.printStackTrace();
        }
    }   
 
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        JFrame frame = new JFrame("View Doctors");
        JScrollPane scrollPane = new JScrollPane(new Coucou());
        frame.getContentPane().add(scrollPane);
     
        frame.setSize(300, 400);
        frame.setVisible(true);
    }
}
