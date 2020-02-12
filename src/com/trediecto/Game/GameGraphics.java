/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trediecto.Game;

import com.trediecto.CConsole;
import com.trediecto.Debug.GameDebug;
import com.trediecto.variables.Vector2;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TE10ZoOs
 */
public class GameGraphics implements Runnable
{
    //Adds An Player For The GameDebug Gui To Test The Game
    public static Player player1 = new Player(100, 100, 100, 100, 0, 1, new Vector2(100, 100),"test","test",1);
    //Creates A Canvas From GameDebug
    public Canvas canvas = GameDebug.Canvas1;
    //OnKeyDown Event To Cheack What Key Was Pressed
    public static void onKeyDown(int keycode) 
    { 
        switch (keycode)
        {
            case KeyEvent.VK_UP:
            {
                player1.Move(5, 270);
                CConsole.PrintDebug("KeyDown");
                break;
            }
            case KeyEvent.VK_RIGHT:
            {
                player1.Move(5, 0);
                break;
            }
            case KeyEvent.VK_DOWN:
            {
                player1.Move(5, 90);
                break;
            }
            case KeyEvent.VK_LEFT:
            {
                player1.Move(5, 180);
                break;
            }
        }
    }
    //Main Function
    public static void Main()
    {
        
    }
    //Overrides The Run Method To Be Run In A Thread
    @Override
    public void run()
    {
        while (CConsole.UpdateDebugGraphicsRunning = true)
        {
            try
            {
                Thread.sleep(10);
            }
            catch (InterruptedException ex)
            {
                Logger.getLogger(GameGraphics.class.getName()).log(Level.SEVERE, null, ex);
            }
            canvas.repaint();
            Update();
        }
    }
    //Update Function For GameDebug
    public static void Update()
    {
        
    }
    //Draw Function For GameDebug Draw
    public static void Draw(Graphics g)
    {
        g.setColor(Color.RED);
        g.fillRect(GameGraphics.player1.POS.X, GameGraphics.player1.POS.Y, (int)GameGraphics.player1.BoundsRectangle.Width, (int)GameGraphics.player1.BoundsRectangle.Height);
    }
}
