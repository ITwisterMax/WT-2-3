package by.application.server.main.server.handle;

import by.application.client.entity.request.StudentRequest;
import by.application.client.entity.response.StudentResponse;
import by.application.server.dao.ServerDAOFactory;
import by.application.server.main.server.handle.controller.StudentController;
import by.application.server.service.StudentServerService;
import by.application.server.service.impl.StudentServerServiceImpl;
import java.io.*;
import java.net.Socket;

/**
 * Client handler class
 */
public class ClientHandler extends Thread
{
    /**
     * Input stream
     */
    private final ObjectInputStream in;

    /**
     * Output stream
     */
    private final ObjectOutputStream out;

    /**
     * Request/response controller
     */
    private final StudentController controller;

    /**
     * Constructor
     *
     * @param socket server socket
     *
     * @throws IOException
     */
    public ClientHandler(Socket socket) throws IOException
    {
        // Create new service instance
        StudentServerService service = new StudentServerServiceImpl(ServerDAOFactory.getInstance().getStudentDAO());

        // Initialize fields
        this.in = new ObjectInputStream(socket.getInputStream());
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.controller = new StudentController(service);

        start();
    }

    /**
     * Start request/response thread
     */
    @Override
    public void run()
    {
        try {
            while (true)
            {
                // Get new request and create response
                StudentRequest request = (StudentRequest) this.in.readObject();
                StudentResponse response = switch (request.getRequestType())
                {
                    case LOGIN -> this.controller.login(request);
                    case REGISTER -> this.controller.register(request);
                    case GET_ALL -> this.controller.getAll();
                    case GET -> this.controller.get(request);
                    case EDIT -> this.controller.edit(request);
                    case CREATE -> this.controller.create(request);
                    default -> this.controller.notFound();
                };

                // Write response in out stream
                this.out.writeObject(response);
                this.out.flush();
            }
        }
        catch (EOFException ignored)
        {
            // Stop thread
        }
        catch (IOException | ClassNotFoundException e)
        {
            System.out.printf("Failed read: %s%n", e.getMessage());
        }
    }
}
