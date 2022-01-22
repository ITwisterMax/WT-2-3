package by.application.server.main;

import by.application.server.main.server.Server;

/**
 * Main server class
 */
public class Main
{
    /**
     * Entry point
     *
     * @param args - arguments
     */
    public static void main(String[] args)
    {
        // Start new server instance
        Server server = new Server(6666);
        server.start();

        // Wait for threads
        try
        {
            server.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
