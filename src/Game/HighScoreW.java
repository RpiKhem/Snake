/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.WindowConstants;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Rimóczi Loránd EOH12I
 */

//grafikus megjelenítése a pontszámoknak
public class HighScoreW extends JDialog
{
    private final JTable table;
    
    public HighScoreW(ArrayList<HighScore> highScores, JFrame parent)
    {
        table = new JTable(new HighScoresT(highScores));
        table.setFillsViewportHeight(true);
        
        TableRowSorter<TableModel> sorter =
                new TableRowSorter<TableModel>(table.getModel());
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        //sorrend változtatási lehetőség
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        table.setRowSorter(sorter);
        
        add(new JScrollPane(table));
        setSize(400,400);
        setTitle("Ranglista");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

