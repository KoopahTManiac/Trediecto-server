/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trediecto;

import java.nio.channels.NotYetConnectedException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.java_websocket.WebSocket;

/**
 *
 * @author Zoran
 */
public class Msg
{
    //Sends A Message To A Client
    public static void Send(String Message, WebSocket ws)
    {
        ws.send("Msg;" + Message);
    }
    //Sends A Byte Array To A Client
    public static void Send(byte[] Message, WebSocket ws)
    {
        try
        {
            ws.send(Message);
        }
        catch (IllegalArgumentException | NotYetConnectedException | InterruptedException ex)
        {
            Logger.getLogger(Msg.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Sends A Message To All Clients Connected
    public static void SendToAll(String Message, List<Client> ws, String Sender)
    {
        for (int i = 0; i < ws.size(); i++)
        {
            ws.get(i).WSocket.send("Msg;"+ Sender +";" + Message);
        }
    }
    //Sends A Byte Array To All Clients Connected
    public static void SendToAll(byte[] Message, List<Client> ws)
    {
        for (int i = 0; i < ws.size(); i++)
        {
            try
            {
                ws.get(i).WSocket.send(Message);
            }
            catch (IllegalArgumentException | NotYetConnectedException | InterruptedException ex)
            {
                Logger.getLogger(Msg.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
