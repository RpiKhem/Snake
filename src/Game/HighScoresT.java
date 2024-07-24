/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Rimóczi Loránd EOH12I
 */

//a megjelenítendő táblázat létrehozása
public class HighScoresT extends AbstractTableModel
{

    private final ArrayList<HighScore> HighScores;
    private final String[] colName = new String[]{ "Név", "Pontszám"};
    
    public HighScoresT(ArrayList<HighScore> highScores)
    {
        this.HighScores = highScores;
    }

    @Override
    public int getRowCount() 
    { 
        return HighScores.size(); 
    }

    @Override
    public int getColumnCount() 
    { 
        return 2; 
    }

    @Override
    public Object getValueAt(int r, int c) 
    {
        HighScore h = HighScores.get(r);
        if      (c == 0) return h.Name;
        else if (c == 1) return h.score;
        return h.score;
    }

    @Override
    public String getColumnName(int i) 
    { 
        return colName[i]; 
    }    
}
