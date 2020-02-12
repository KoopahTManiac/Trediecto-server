/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trediecto.Game;

import com.trediecto.ServerGui;
import com.trediecto.Client;
import com.trediecto.server;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zoran
 */
public class Game
{
    server Server = new server();
    public static int PlayerOnlineCount =0;
    public static List<String> PlayersOnline = new ArrayList<>();
    public static List<Client> Clients = new ArrayList<>();
    
    //Update Function For The Game
    public static void Update()
    {
        //ToDo Add Updates
        
        
    }
    public static void Update30()
    {
        for (int i = 0; i < Clients.size(); i++)
        {
            Clients.get(i).player.Move(2, 0);
        }
    }
    public static void Update60()
    {
       
    }
    //updates The JTextArea Content To The Players Online Stats
    public static void UpdateOnlinePlayers()
    {
        PlayerOnlineCount =0;
        PlayersOnline = new ArrayList<>();
        for (int i = 0; i < server.clients.size(); i++)
        {
            PlayerOnlineCount += 1;
            PlayersOnline.add(server.clients.get(i).username);
            
        }
        String temp_string ="--Players Online--  ";
        temp_string += "(" + String.valueOf(PlayerOnlineCount) +")" +"\n";
        for (int i = 0; i < server.clients.size(); i++)
        {
            temp_string += "( " +PlayersOnline.get(i) +" )"+ "\n";
            
        }
        
        ServerGui.jTextPane1.setText(temp_string);
        ServerGui.CenterJTextPane();
    }
    
}
