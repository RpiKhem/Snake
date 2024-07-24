/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Rimóczi Loránd EOH12I
 */

public class Connector 
{
    private static Connection conn;
     
    //üres konstruktor
    public Connector()
    {
         
    }
    
    //kapcsolat létrehozása az adatbázissal
    public static Connection  getConnection() 
    {
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HighScores" +    
                    "serverTimezone=UTC&user=root&password=12345678");
        }
        catch (Exception e)
        {
            System.out.println("" + e);
        }
        return conn;
    }
}
