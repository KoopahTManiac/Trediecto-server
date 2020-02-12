/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trediecto.Game;

import com.trediecto.CConsole;

/**
 *
 * @author Zoran
 */
public class GameUpdater implements Runnable
{
    //Overrides The Run Method To Be Run In The Thread
    @Override
    public void run()
    {
        while (CConsole.GameUpdateThreadRunning)
        {
            
            Game.Update();
        }
    }
}
