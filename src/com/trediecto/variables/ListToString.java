/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trediecto.variables;

import com.trediecto.Game.bullet;
import java.util.List;

/**
 *
 * @author Zoran
 */
public class ListToString
{
    public String bulletsToString(List<bullet> bullets)
    {
        String temp = "";
        for (int i = 0; i < bullets.size(); i++)
        {
            temp += "" + (int)bullets.get(i).X + "." + (int)bullets.get(i).Y + "¤";
        }
        return temp;
    }
}
