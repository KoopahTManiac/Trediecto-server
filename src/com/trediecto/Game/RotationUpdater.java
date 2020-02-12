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
public class RotationUpdater extends TimerTask
{
    public static long elapsedtime =0;
    public static int delaysimulation =0;
    public List<Double> temprot = new ArrayList<>(1);
    public kommunikation kom = new kommunikation();
    @Override
    public void run()
    {
        for (int i =0; i < server.clients.size(); i++)
        {
            
            double x = server.clients.get(i).player.POS.X - server.clients.get(i).player.Mouse.X;
            double y = server.clients.get(i).player.POS.Y - server.clients.get(i).player.Mouse.Y;
            double angle = (Math.atan2(y, x) * 180-280) / Math.PI;
            server.clients.get(i).player.rotationT = angle;
            kom.UpdateRotation();
        }
    }
}
