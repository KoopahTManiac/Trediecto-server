/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trediecto.Debug;

import com.trediecto.Game.GameGraphics;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
/**
 *
 * @author TE10ZoOs
 */
public class GameDebugUpdate extends Canvas
{
    //Overides The Paint Method
    @Override
    public void paint(Graphics g)
    {
        //Makes A Double Buffer To Prevent Flickering
        //<editor-fold defaultstate="collapsed" desc="{Double Buffer}">
        Graphics G; 
        Image offscreen;
        Dimension dim = this.getSize(); 
        offscreen = createImage(dim.width,dim.height);
        G = offscreen.getGraphics();
        //</editor-fold>
        //Runs Draw Function In Another Class
        GameGraphics.Draw(G);
        //<editor-fold defaultstate="collapsed" desc="{Double Buffer}">
        g.drawImage(offscreen,0,0,this);
        //</editor-fold>
    }
    //Overides The Update Method To Prevent Canvas Fill And Flickering
    @Override
    public void update(Graphics g)
    {
        //Runs The Paint Method
        paint(g);
    }
}
