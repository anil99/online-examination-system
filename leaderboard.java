

import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class leaderboard extends JFrame
{
    public leaderboard()
    {
        ArrayList columnNames = new ArrayList();
        ArrayList data = new ArrayList();

        //  Connect to an MySQL Database, run query, get result set
        String url = "jdbc:mysql://localhost:3306/ooad";
        String userid = "root";
        String password = null;
        String sql = "SELECT * FROM regform";

        // Java SE 7 has try-with-resources
        // This will ensure that the sql objects are closed when the program 
        // is finished with them
        try (Connection connection = DriverManager.getConnection( url, userid, password );
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery( sql ))
        {
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();

            //  Get column names
            //for (int i = 1; i <= columns; i++)
            //{
                columnNames.add( md.getColumnName(1) );
                columnNames.add( md.getColumnName(5) );
                columnNames.add( md.getColumnName(6) );
                columnNames.add( md.getColumnName(7) );
                columnNames.add( md.getColumnName(8) );
            //}
           // System.out.println("----Aspirants List-----");
            //System.out.println("");
            //  Get row data
            while (rs.next())
            {
                ArrayList row = new ArrayList(columns);

                //for (int i = 1; i <= columns; i++)
                //{
                row.add( rs.getObject(1) );
                    row.add( rs.getObject(5) );
                    row.add( rs.getObject(6) );
                row.add( rs.getObject(7) );
                    row.add( rs.getObject(8) );
                   
                //}

                data.add( row );
            }
        }
        catch (SQLException e)
        {
            System.out.println( e.getMessage() );
        }

        // Create Vectors and copy over elements from ArrayLists to them
        // Vector is deprecated but I am using them in this example to keep 
        // things simple - the best practice would be to create a custom defined
        // class which inherits from the AbstractTableModel class
        Vector columnNamesVector = new Vector();
        Vector dataVector = new Vector();

        for (int i = 0; i < data.size(); i++)
        {
            ArrayList subArray = (ArrayList)data.get(i);
            Vector subVector = new Vector();
            for (int j = 0; j < subArray.size(); j++)
            {
                subVector.add(subArray.get(j));
            }
            dataVector.add(subVector);
        }

        for (int i = 0; i < columnNames.size(); i++ )
            columnNamesVector.add(columnNames.get(i));

        //  Create table with database data    
        JTable table = new JTable(dataVector, columnNamesVector)
        {
            public Class getColumnClass(int column)
            {
                for (int row = 0; row < getRowCount(); row++)
                {
                    Object o = getValueAt(row, column);

                    if (o != null)
                    {
                        return o.getClass();
                    }
                }

                return Object.class;
            }
        };

        JScrollPane scrollPane = new JScrollPane( table );
        getContentPane().add( scrollPane );

        JPanel buttonPanel = new JPanel();
        getContentPane().add( buttonPanel, BorderLayout.SOUTH );
    }

    public static void main(String[] args)
    {
        leaderboard frame = new leaderboard();
        frame.setDefaultCloseOperation( EXIT_ON_CLOSE );
        frame.pack();
        frame.setVisible(true);
    }
}