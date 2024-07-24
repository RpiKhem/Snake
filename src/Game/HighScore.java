/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.util.Objects;

/**
 *
 * @author Rimóczi Loránd EOH12I
 */

//a pontszám "formája", osztályként
public class HighScore 
{
    
    public final String Name;
    public final int score;
    
    //konstruktor
    public HighScore(String name, int score){
        this.Name = name;
        this.score = score;
    }
    
    //hash-elés
    @Override
    public int hashCode() 
    {
       return Objects.hash(this.Name,this.score);
    }
    
    //egyezőség vizsgálat
    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HighScore other = (HighScore) obj;
        if (this.score != other.score) {
            return false;
        }
        if (this.Name != other.Name) {
            return false;
        }
        return true;
    }   

    @Override
    public String toString() 
    {
            return "Name: " + Name + " score: "+ score;
    }
}
