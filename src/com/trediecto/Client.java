/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trediecto;

import com.trediecto.Game.Player;
import org.java_websocket.WebSocket;

/**
 *
 * @author Zoran
 */
public class Client
{
    public String username;
    public WebSocket WSocket;
    public Player player;
    //Constructor For Client
    public Client(String username, WebSocket ws, Player player) 
    {
        this.username = username;
        this.WSocket = ws;
        this.player = player;
    }
}
