package sample.Server;

import sample.model.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class UserThread extends Thread{
    private Socket socket;
    private Server server;
    private static int index = 1;
    private int id;
    public User user;
    private boolean mute;
    private DataOutputStream out =null;
    private DataInputStream in = null;
    private int sleep=0;
    public UserThread(Socket socket, Server server,User user) {
        this.user=user;
        this.socket = socket;
        this.server = server;
        this.mute = false;
        this.id = index;
        index++;
    }
    @Override
    public void run() {
        try {
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            while (true)
            {

                String message = in.readUTF();
//                if(message.equals("Exit"))
//                {
//                    out.writeUTF("Close");
//                    socket.close();
//                    in.close();
//                    out.close();
//                    server.RemoveThread(this);
//                    break;
//                }
                this.server.SendAll(message,this);
            }
        }
        catch (IOException e)
        {
            System.err.println("Something went wrong about IO-UserThread");
        }
        finally {
            try {
                if(in!=null)
                {
                    in.close();
                }
                if(out!=null)
                {
                    out.close();
                }
            }
            catch (IOException e)
            {
                System.err.println("Something went wrong about IO");
            }

        }
    }
    public void Receive(String str)
    {
        try {
            this.out.writeUTF(str);
        } catch (IOException exception) {
            System.err.println("Error in Receive");
        }
    }
}
