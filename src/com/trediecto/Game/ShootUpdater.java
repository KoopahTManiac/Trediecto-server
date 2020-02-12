/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trediecto.Game;

import com.trediecto.server;
import com.trediecto.variables.ListToString;
import com.trediecto.variables.Vector2;
import java.util.Random;
import java.util.TimerTask;

/**
 *
 * @author Zoran
 */
public class ShootUpdater extends TimerTask
{
    kommunikation kom = new kommunikation();
    @Override
    public void run()
    {
        try
        {
            for (int i =0; i < server.clients.size(); i++)
            {
                if (server.clients.get(i).player.shoot && System.nanoTime() > server.clients.get(i).player.ShootTime + server.clients.get(i).player.ShootSpeed)
                {
                    server.clients.get(i).player.ShootTime = System.nanoTime();
                    server.clients.get(i).player.bullets.add(new bullet((double)server.clients.get(i).player.POS.X, (double)server.clients.get(i).player.POS.Y,server.clients.get(i).player.rotationT));
                }
                for (int g =0; g < server.clients.get(i).player.bullets.size(); g++)
                {
                    if (server.clients.get(i).player.bullets.get(g).rage <0)
                    {
                       server.clients.get(i).player.bullets.remove(g);
                    }
                    else
                    {
                        server.clients.get(i).player.bullets.get(g).Move();

                        for (int t=0; t <  server.clients.size(); t++)
                        {
                            if (i == t)
                            {

                            }
                            else
                            {
                                if (server.clients.get(i).player.bullets.get(g).Collision(new Vector2(server.clients.get(t).player.POS.X-10, server.clients.get(t).player.POS.Y-10), new Vector2(20, 20)))
                                {
                                    server.clients.get(i).player.bullets.remove(g);
                                    server.clients.get(t).player.HP -= 5;
                                    kom.UpdateHp();
                                    if (server.clients.get(t).player.HP <0)
                                    {
                                        server.clients.get(t).player.POS = new Vector2(new Random().nextInt(800), new Random().nextInt(800));
                                        server.clients.get(t).player.HP = 100;
                                        kom.UpdatePos();
                                    }


                                }
                            }
                        }
                    }


                }



                if (server.clients.get(i).player.bullets.size() > 0)
                {
                    ListToString ar = new ListToString();
                    for (int b=0; b < server.clients.size();b++)
                    {
                        if (server.clients.get(i).player.CheackRage(server.clients.get(b).player, server.clients.get(b).player.FieldOfView))
                        {

                            server.clients.get(b).WSocket.send("PosB;" + server.clients.get(i).username + ";" + ar.bulletsToString(server.clients.get(i).player.bullets));
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
        
        }
    }
}
