package by.application.client.main;

import by.application.client.dao.ClientDAOFactory;
import by.application.client.presentation.Presentation;
import by.application.client.service.ServiceClientFactory;

/**
 * Main client class
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
        // Initialize layers instances
        ServiceClientFactory factory = ServiceClientFactory.getInstance();
        ClientDAOFactory daoFactory = ClientDAOFactory.getInstance();

        // Show information via user role
        Presentation presentation = new Presentation(factory.getStudentService(daoFactory.getStudentDAO()));
        presentation.show();
    }
}
