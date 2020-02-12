/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trediecto.Game;

import com.trediecto.server;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

/**
 *
 * @author Zoran
 */
public class MoveUpdater extends TimerTask
{
    public static long elapsedtime =0;
    public static int delaysimulation =0;
    public List<Double> temprot = new ArrayList<>(1);
    public kommunikation Kommunikation = new kommunikation();
    @Override
    public void run()
    {
        try
        {
            for (int i =0; i < server.clients.size(); i++)
            {
                if (server.clients.get(i).player.moving == true)
                {
                    server.clients.get(i).player.Move(server.clients.get(i).player.speed,server.clients.get(i).player.rotation);
                    if (System.nanoTime() > elapsedtime+delaysimulation*100000)
                    {
                        Kommunikation.UpdatePos();
                        elapsedtime = System.nanoTime();
                    }
                }

            }
        }
        catch (Exception e)
        {
            
        }
    }
}
