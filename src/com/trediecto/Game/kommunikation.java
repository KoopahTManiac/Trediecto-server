/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trediecto.Game;

import com.trediecto.server;

/**
 *
 * @author Zoran
 */
public class kommunikation
{
    
    public void UpdatePos()
    {
        try
        {
            for (int i =0; i < server.clients.size(); i++)
            {
                for (int j = 0; j < server.clients.size(); j++)
                {
                    if (i == j)
                    {
                        AddPlayer(i, j);
                        SendPos(i, j);
                    }
                    else
                    {
                        if (server.clients.get(i).player.CheackRage(server.clients.get(j).player, server.clients.get(j).player.FieldOfView))
                        {
                            if (server.clients.get(i).player.VisiblePlayers.contains(server.clients.get(j).player.ID))
                            {
                                SendPos(i, j);
                            }
                            else
                            {
                                AddPlayer(i, j);
                                SendPos(i, j);
                                server.clients.get(i).player.VisiblePlayers.add(server.clients.get(j).player.ID);
                            }
                        }
                        else
                        {
                            if (server.clients.get(i).player.VisiblePlayers.contains(server.clients.get(j).player.ID))
                            {
                                for (int g = 0; g < server.clients.get(i).player.VisiblePlayers.size(); g++)
                                {
                                    if (server.clients.get(i).player.VisiblePlayers.get(g).equals(server.clients.get(j).player.ID))
                                    {
                                        server.clients.get(i).player.VisiblePlayers.remove(g);
                                    }
                                }
                                RemovePlayer(i, j);
                            }
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            
        }
    }
    public void UpdateRotation()
    {
        for (int i =0; i < server.clients.size(); i++)
        {
            for (int j = 0; j < server.clients.size(); j++)
            {
                SendRotation(i, j);
            }
        }
    }
    public void UpdateHp()
    {
        for (int i =0; i < server.clients.size(); i++)
        {
            for (int j = 0; j < server.clients.size(); j++)
            {
                if (server.clients.get(i).player.CheackRage(server.clients.get(j).player, server.clients.get(j).player.FieldOfView))
                {
                    SendHp(i, j);
                }
            }
        }
    }
    public void AddPlayer(int x, int y)
    {
        server.clients.get(x).WSocket.send("Add;" + server.clients.get(y).player.ID +";" + server.clients.get(y).username + ";" + server.clients.get(y).player.POS.X + ";" + server.clients.get(y).player.POS.Y +";" + server.clients.get(y).player.HP + ";" +server.clients.get(y).player.HP_MAX );
    }
    public void RemovePlayer(int x, int y)
    {
        server.clients.get(x).WSocket.send("Del;" + server.clients.get(y).player.ID);
    }
    public void SendPos(int To, int From)
    {
        server.clients.get(To).WSocket.send("Pos;" + server.clients.get(From).player.ID + ";" + server.clients.get(From).player.POS.X + ";" + server.clients.get(From).player.POS.Y + ";" + server.clients.get(From).player.HP + ";" + server.clients.get(From).player.HP_MAX);
//        ByteBuffer buf = ByteBuffer.allocate(22);
//        buf.putInt(buf.capacity());
//        buf.put((byte) 1); // TODO Bestäm dessa i protokollet
//        Client cFrom  = server.clients.get(From);
//        buf.putInt(cFrom.player.ID);
//        buf.putInt(cFrom.player.POS.X);
//        buf.putInt(cFrom.player.POS.Y);
//        try
//        {
//            server.clients.get(To).WSocket.send(buf);
//        }
//        catch (IllegalArgumentException | NotYetConnectedException | InterruptedException ex)
//        {
//            CConsole.PrintDebug(ex.getMessage());
//        }
        
        //server.clients.get(from).WSocket.
    }
    public void SendRotation(int To, int From)
    {
        server.clients.get(To).WSocket.send("Rot;" + server.clients.get(From).player.ID + ";" +server.clients.get(From).player.rotationT); 
//        Client cTo= server.clients.get(To);
//        Client cFrom  = server.clients.get(From);
//
//        ByteBuffer buf = ByteBuffer.allocate(22);
//
//        buf.put((byte) 1); // TODO Bestäm dessa i protokollet
//        buf.putInt(cFrom.player.ID);
//        buf.putDouble(cFrom.player.rotationT);
//        try
//        {
//            cTo.WSocket.send(buf);
//        }
//        catch (IllegalArgumentException | NotYetConnectedException | InterruptedException ex)
//        {
//            Logger.getLogger(kommunikation.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }
    
    public void SendHp(int To, int From)
    {
        
//        Client cTo= server.clients.get(To);
//        Client cFrom  = server.clients.get(From);
//        
//        ByteBuffer buf = ByteBuffer.allocate(22);
//        buf.putInt(buf.capacity());
//        buf.put((byte) 1); // TODO Bestäm dessa i protokollet
//        buf.putInt(cFrom.player.ID);
//        buf.putInt(cFrom.player.HP);
//        buf.putInt(cFrom.player.HP_MAX);
//        try
//        {
//            cTo.WSocket.send(buf);
//        }
//        catch (IllegalArgumentException | NotYetConnectedException | InterruptedException ex)
//        {
//            CConsole.PrintDebug(ex.getMessage());
//        }
    }
    public void UpdateShot()
    {
        
    }
    public void SendShotPos()
    {
        
    }
}
