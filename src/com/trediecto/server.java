/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trediecto;

import com.trediecto.Game.Game;
import com.trediecto.Game.MoveUpdater;
import com.trediecto.Game.Player;
import com.trediecto.Game.RotationUpdater;
import com.trediecto.Game.ShootUpdater;
import com.trediecto.Game.kommunikation;
import com.trediecto.variables.Vector2;
import java.net.InetSocketAddress;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import javax.swing.*;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.*;

/**
 *
 * @author Zoran Ostojic
 */
public class server {

    /**
     * @param args the command line arguments
     */
    //Variables For Clients and Server
    public static List<Client> clients = new ArrayList<>();
    public static int port = 8181;
    public static List<WebSocket> sockets = new ArrayList<>();
    public static MoveUpdater MovUpdater = new MoveUpdater();
    public static Timer MoveUpdaterTimer = new Timer();
    public static RotationUpdater rotUpdater= new RotationUpdater();
    public static Timer RotationUpdaterTimer = new Timer();
    public static ShootUpdater shootUpdater = new ShootUpdater();
    public static Timer ShootUpdaterTimer = new Timer();
    public static String stored_error ="";
    //database
    public static String dbUser="root";
    public static String dbPass="tigrica123";
    public static java.sql.Connection myConnection;
    public static Statement stat;
    public static ResultSet results;
    public static List<Player> playersDb = new ArrayList<>();
    public static kommunikation Kommunikation = new kommunikation();

    //main Method
    public static void main(String[] args) 
    {
        /*
        try
        {
            
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://83.252.177.80:8282/trediecto?user=" + dbUser + "&password=" + dbPass;
            myConnection = DriverManager.getConnection(dbURL);
            stat = myConnection.createStatement();
            
            
            String selectQuery = "Select * from Players";
            
            results = stat.executeQuery(selectQuery);
            
            while (results.next())
            {
                playersDb.add(new Player(100, 100, 100, 100, 100, 100, new Vector2(100, 100),results.getString("User"),results.getString("Pass"),results.getInt("ID")));
            }
        }
        catch (ClassNotFoundException | SQLException e)
        {
            
            stored_error = e.getMessage();
        }
        
        */
        
        
        //Creates A JFrame For ServerGui And Shows The JFram
        JFrame servergui = new ServerGui();
        servergui.show();
        ServerGui.Input.grabFocus();
        //Creates A Socket Listener For The Server
        InetSocketAddress hosts =  new InetSocketAddress(port);
        WebSocketServer Server = new WebSocketServer(hosts)
        {

            @Override
            public void onOpen(WebSocket ws, ClientHandshake ch) 
            {
                CConsole.PrintDebug("New Socket:"+ ws.getLocalSocketAddress() + ":" + ws.getRemoteSocketAddress());
            }
            
            @Override
            public void onClose(WebSocket ws, int i, String string, boolean bln) 
            {
                CConsole.PrintDebug("Disconected Socket:"+ ws.toString());
                for (int j = 0; j < clients.size(); j++)
                {
                    if (clients.get(j).WSocket == ws)
                    {
                        CConsole.PrintLn(clients.get(j).username + " Disconnected");
                        for (int k = 0; k < clients.size(); k++)
                        {
                            if (k == j)
                            {
                                
                            }
                            else
                            {
                                Kommunikation.RemovePlayer(k, j);
                            }
                        }
                        clients.remove(j);
                        Game.UpdateOnlinePlayers();
                    }
                }
            }

            @Override
            public void onMessage(WebSocket ws, String string) 
            {
                CConsole.PrintDebug(string);
                String[] data = {" "};
                if (string.contains(";"))
                {
                    data = string.split(";");
                }
                else
                {
                    data[0] = string;
                }
                boolean Found = false;
                for (int i = 0; i < clients.size(); i++)
                {
                    if (clients.get(i).WSocket.equals(ws))
                    {
                        Found = true;
                    }
                }
                if (Found)
                {
                    switch (data[0])
                    {
                        case "Msg":
                        {
                            CConsole.PrintLn(data[1]+" Sent: "+ data[2]);
                            Msg.SendToAll(data[2] , clients, data[1]);
                            break;
                        }
                        case "Move":
                        {
                            for (int i = 0; i < clients.size(); i++)
                            {
                                if (ws.equals(clients.get(i).WSocket))
                                {
                                    clients.get(i).player.Move(data[1]);
                                    clients.get(i).WSocket.send("GK;");
                                }
                            }
                            break;
                        }
                        case "Shoot":
                        {
                            for (int i = 0; i < clients.size(); i++)
                            {
                                if (ws.equals(clients.get(i).WSocket))
                                {
                                    clients.get(i).player.shoot = true;
                                }
                            }
                            break;
                        }
                        case "ShootC":
                        {
                            for (int i = 0; i < clients.size(); i++)
                            {
                                if (ws.equals(clients.get(i).WSocket))
                                {
                                    clients.get(i).player.shoot = false;
                                }
                            }
                            break;
                        }
                        case "Mouse":
                        {
                            for (int i = 0; i < clients.size(); i++)
                            {
                                if (ws.equals(clients.get(i).WSocket))
                                {
                                    clients.get(i).player.Mouse = new Vector2(Integer.parseInt(data[1]),Integer.parseInt(data[2]));
                                }
                            }
                            break;
                        }
                        case "UPO":
                        {
                            Kommunikation.UpdatePos();
                            break;
                        }
                        default:
                        {
                            CConsole.PrintDebug("Unknown Command: \"" + data[0] + "\" Received From: " + ws.getRemoteSocketAddress().toString());
                            ws.close(1);
                            break;
                        }
                    }
                }
                else
                {
                    switch (data[0])
                    {
                        case "Login":
                        {
                            boolean LogedIn = false;
                            String selectQuery = "Select * from Players";
                            try
                            {
                                results = stat.executeQuery(selectQuery);
                                playersDb = new ArrayList<>();
                                while (results.next())
                                {
                                    playersDb.add(new Player(100, 100, 100, 100, 100, 100, new Vector2(100, 100),results.getString("User"),results.getString("Pass"), results.getInt("ID")));
                                }
                            }
                            catch (Exception e)
                            {
                                
                            }
                            for (int i = 0; i < clients.size(); i++)
                            {
                                if (ws.equals(clients.get(i).WSocket))
                                {
                                    LogedIn = true;
                                    CConsole.PrintLn(data[1] + " Connected");
                                    Kommunikation.UpdatePos();
                                }
                            }
                            if (LogedIn == false)
                            {
                                CConsole.PrintDebug("User: " + data[1]+" Sent: "+ data[2]);
                                String temp_username = data[1];
                                String temp_password = data[2];
                                boolean AllReadyLogedIn = false;
                                Player temp_pl= new Player(100, 100, 100, 100, 100, 100, new Vector2(100, 100), "fail", "fail",1);
                                boolean Login = false;
                                for (int i = 0; i < clients.size(); i++)
                                {
                                    if (clients.get(i).username.equals(temp_username))
                                    {
                                        AllReadyLogedIn = true;
                                    }
                                }
                                if (AllReadyLogedIn == false)
                                {
                                    for (int i = 0; i < playersDb.size(); i++)
                                    {
                                        if (playersDb.get(i).username.equals(temp_username))
                                        {
                                            if (playersDb.get(i).passwords.equals(temp_password))
                                            {
                                                temp_pl = playersDb.get(i);
                                                Login = true;
                                                Kommunikation.UpdatePos();
                                                break;
                                            }
                                        }
                                    }
                                }
                                if (Login)
                                {
                                    try
                                    {
                                        clients.add(new Client(temp_username, ws , temp_pl));
                                    }
                                    catch (Exception e)
                                    {
                                        
                                    }
                                    
                                    ws.send("Success!");
                                    Game.UpdateOnlinePlayers();
                                }
                                else
                                {
                                    ws.send("Failed!");
                                    ws.close(0);
                                }
                            }
                            else
                            {
                                ws.send("Failed!");
                                ws.close(0);
                            }
                            break;
                        }
                        case "Register":
                        {
                            break;
                        }
                            
                        default:
                        {
                            CConsole.PrintError("Unknown Command: \"" + data[0] + "\" Received From: " + ws.getLocalSocketAddress().toString());
                            ws.close(1);
                            break;
                        }
                    }
                }
                
                
                
            }

            @Override
            public void onError(WebSocket ws, Exception excptn) 
            {
              CConsole.PrintDebug(excptn.getMessage());
            }
        };
        CConsole.PrintLn("Starting Server");
        //Trys To Start The Server
        try
        {
            Server.start();
            CConsole.PrintLn("Server Has Started!!");
            CConsole.PrintLn("On Port: " + port);
            CConsole.PrintLn(stored_error);
            MoveUpdaterTimer.schedule(MovUpdater, 0 , 1000/60);
            RotationUpdaterTimer.schedule(rotUpdater, 0 , 1000/60);
            ShootUpdaterTimer.schedule(shootUpdater, 0 , 1000/480);
            Game.UpdateOnlinePlayers();
        }
        catch (Exception e)
        {
            CConsole.PrintLn("Cant Start Server Becouse Of:");
            CConsole.PrintLn(e.getMessage());
        }
    }     
}

