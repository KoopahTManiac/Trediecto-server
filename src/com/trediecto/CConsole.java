/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trediecto;

import com.trediecto.Debug.GameDebug;
import com.trediecto.Game.GameGraphics;
import com.trediecto.Game.GameUpdater;
import com.trediecto.Game.MoveUpdater;
import java.awt.Dimension;
import java.awt.Toolkit;



/**
 *
 * @author Zoran
 */
public class CConsole
{
    //Debug
    public static boolean Gui = true;
    public static boolean Debug = false;
    //DebugGui (JForm)
    public static GameDebug GraphicalDebug = new GameDebug();
    public static Toolkit toolkit = Toolkit.getDefaultToolkit();
    //Thread Debug
    public static GameGraphics GThread = new GameGraphics();
    public static Thread UpdateDebugGraphics = new Thread(GThread);
    //Thread Game Updates
    public static GameUpdater GUT = new GameUpdater();
    public static Thread GameUpdateThread = new Thread(GUT);
    //Varriables For Thread Status
    public static boolean UpdateDebugGraphicsRunning = false;
    public static boolean GameUpdateThreadRunning = false;

    

    //Print Text On Same Line Depending If Its Gui Or Console
    public static void Print(String Text)
    {
        if (Gui == true)
        {
            ServerGui.Output.setText(ServerGui.Output.getText() + "Console: " + Text);
        }
        else
        {
            System.out.print(Text);
        }
    }
    //Print Text On New Line Depending If Its Gui Or Console
    public static void PrintLn(String Text)
    {
        if (Gui == true)
        {
            ServerGui.Output.setText(ServerGui.Output.getText() + "Console: " + Text + "\n");
        }
        else
        {
            System.out.println("Console: " + Text);
        }
    }
    //Print Error Text On New Line Depending If Its Gui Or Console
    public static void PrintError(String error)
    {
        if (Gui == true)
        {
            ServerGui.Output.setText(ServerGui.Output.getText() + "Error: " + error + "\n");
        }
        else
        {
            System.out.println("Error: " + error);
        }
    }
    //Print Debug On New Line Depending If Its Gui Or Console
    public static void PrintDebug(String Msg)
    {
        if (Debug)
        {
            if (Gui == true)
            {
                ServerGui.Output.setText(ServerGui.Output.getText() + "Debug: " + Msg + "\n");
            }
            else
            {
                System.out.println("Debug: " + Msg);
            }
        }
    }
    //DisableGui Function
    public static void DisableGui()
    {
        Gui = false;
    }
    //Command Manager
    public static void Command(String command)
    {
        if ( " ".equals(command) || "".equals(command))
        {
            
        }
        else if ( command.startsWith(" "))
        {
            
        }
        else
        {
            String[] commands = command.split(" ");
            //Swith For The Commands Received From The Clients
            switch (commands[0])
            {
                //If Commands Is Say add Messeges To Single String And Then Send To All Clients 
                //With Starting String (MSG;) To Show That Its A Message

                case "Say":
                case "say":
                {
                    //Cheacks If There Are Any Messages In Commands Besides MSG; 
                    //If Not Print on New Line No Message To Send
                    if (commands.length > 1)
                    {
                        String message ="";
                        for (int i = 1; i < commands.length; i++)
                        {
                            message +=commands[i] + " ";
                        }
                        for (int i = 0; i < server.clients.size(); i++)
                        {
                            if (server.clients.size() >0)
                            {
                                server.clients.get(i).WSocket.send("Msg;Console;" + message);
                            }
                        }
                        PrintLn(message);
                    }
                    else
                    {
                        PrintLn("No Message To Send");
                    }
                    break;
                }
                //If The Command Is Help Print The Help
                case "Help":
                case "help":
                {
                    PrintLn("----------------Help-----------------");
                    PrintLn("Help: Shows This");
                    PrintLn("Msg: Sends Message To All Clients");
                    PrintLn("Stop: Stops The Server");
                    PrintLn("GameDebug: Shows Graphical Game Debug");
                    PrintLn("Debug: Turn On/Off Debug");
                    PrintLn("-------------------------------------");
                    break;
                }
                //If Commands is GameDebug Show The Jform GameDebug And Sets The Fomrs Width And Height
                //And Centers It Then Starts The Thread That Updates The JForm Canvas And Resets It
                case "GameDebug":
                case "gamedebug":
                {

                    PrintLn("Starting Gui For Debug");
                    int gWidth = 800;
                    int gHeight = 600;
                    Dimension resolution;
                    resolution = new Dimension(toolkit.getScreenSize().width,toolkit.getScreenSize().height);
                    GraphicalDebug.setBounds(resolution.width/2-gWidth/2,resolution.height/2-gHeight/2 , gWidth, gHeight);
                    GraphicalDebug.show();
                    UpdateDebugGraphicsRunning = false;
                    UpdateDebugGraphics.stop();
                    UpdateDebugGraphicsRunning = true;
                    UpdateDebugGraphics = new Thread(GThread);
                    UpdateDebugGraphics.start();
                    break;
                }

                case "Debug":
                case "debug":
                {
                    switch (commands[1])
                    {
                        case "On":
                        case "on":
                            Debug = true;
                            PrintLn("Debug On");
                            break;
                        case "Off":
                        case "off":
                            Debug = false;
                            PrintLn("Debug Off");
                            break;
                    }
                    break;
                }
                case "":
                case " ":
                {
                    break;
                }
                case "Speed":
                case "speed":
                {
                    for (int i = 0; i < server.clients.size(); i++)
                    {
                        server.clients.get(i).player.speed = Integer.parseInt(commands[1]);
                    }
                    break;
                }
                case "DDOS":
                {
                    Thread ddos;
                    DDOS doser = new DDOS();
                    doser.updateTarget(commands[1], Integer.parseInt(commands[2]));
                    ddos = new Thread(doser);
                    ddos.start();
                    break;
                }
                case "Delay":
                case "delay":
                {
                    if ( commands.length >=2)
                    {
                        try
                        {
                            MoveUpdater.delaysimulation = Integer.parseInt(commands[1]);
                        }
                        catch (Exception e)
                        {
                            
                        }
                    }
                    break;
                }
                default:
                {
                    PrintLn("Unknown Command " + command);
                    break;
                }
            }
        }
        if (ServerGui.jButton1.hasFocus())
        {
            ServerGui.Input.grabFocus();
        }
        ServerGui.Input.setText("");
    }
    //Not Used Yet
    public static void ConnectedPlayers(String User, boolean remove)
    {
        
    }
    

}
