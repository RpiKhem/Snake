/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Rimóczi Loránd EOH12I
 */

public class Databases 
{
    private final String tableName = "HighScores";
    private final Connection conn;
    private final HashMap<String, Integer> highScores;
    
    //konstruktor
    public Databases()
    {
        Connection c = null;
        try 
        {
            c = Connector.getConnection();
        } 
        catch (Exception ex) 
        { 
            System.out.println("No connection");
        }
        this.conn = c;
        highScores = new HashMap<>();
        loadHighScores();
    }
    
    //mergelés meghívása
    public boolean storeHighScore(String Name, int newScore)
    {
        return mergeHighScores(Name, newScore, newScore > 0);
    }
    
    //pontszámok listába gyűjtése
    public ArrayList<HighScore> getHighScores()
    {
        ArrayList<HighScore> scores = new ArrayList<>();
        for (String name : highScores.keySet())
        {
            HighScore h = new HighScore(name, highScores.get(name));
            scores.add(h);
            System.out.println(h);
        }
        return scores;
    }
    
    //adatbázisból ranglista betöltése
    private void loadHighScores()
    {
        try (Statement stmt = conn.createStatement()) 
        {
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
            while (rs.next()){
                int score = rs.getInt("Score");
                String Name = rs.getString("Name");
                mergeHighScores(Name, score, false);
            }
        } 
        catch (Exception e)
        { 
            System.out.println("loadHighScores error");
        }
    }
    
    //adat mergelés
    private boolean mergeHighScores(String Name, int score, boolean store)
    {
        
        System.out.println("Merge: " + Name + "-" + score + "(" + store + ")");
        boolean doUpdate = true;
        highScores.put(Name, score);
        if (store)
        {
            return storeToDatabases(Name, score) > 0;
        }
        return true;
    }
    
    //adatbázisba mentés
    private int storeToDatabases(String Name, int score)
    {
        try (Statement stmt = conn.createStatement())
        {
            String s = "INSERT INTO " + tableName + 
                    " (,Score) " + 
                    "VALUES('" + Name+ "'," + score + 
                    ")";
            return stmt.executeUpdate(s);
        } 
        catch (Exception e)
        {
            System.out.println("storeToDatabase error");
        }
        return 0;
    }
}
