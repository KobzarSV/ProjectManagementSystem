package ua.goit.project.controller.developersController;

import ua.goit.project.config.DatabaseManager;
import ua.goit.project.config.PostgresProvider;
import ua.goit.project.config.PropertiesUtil;
import ua.goit.project.dataLayer.DevelopersRepository;
import ua.goit.project.model.converter.DevelopersConverter;
import ua.goit.project.service.DevelopersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = ("/deleteDeveloper"))
public class DeleteDeveloperServlet extends HttpServlet {

    private DevelopersRepository developersRepository;
    private DevelopersService developersService;

    @Override
    public void init() throws ServletException {
        PropertiesUtil properties = new PropertiesUtil(getServletContext());
        DatabaseManager dbConnector = new PostgresProvider(properties.getHostname(), properties.getPort(), properties.getSchema(),
                properties.getUser(), properties.getPassword(), properties.getJdbcDriver());
        developersRepository = new DevelopersRepository(dbConnector);
        developersService = new DevelopersService(new DevelopersConverter(), new DevelopersRepository(dbConnector));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String developerId = req.getParameter("developerId");
        try {
            developersService.find(Integer.parseInt(developerId));
        } catch (Exception ex) {
            req.setAttribute("exception", "Developer with id " + developerId + " not found!");
            req.getRequestDispatcher("/WEB-INF/html/error.jsp").forward(req, resp);
            return;
        }
        developersRepository.delete(Integer.parseInt(developerId));
        req.getRequestDispatcher("/WEB-INF/html/developers/deleteDeveloper.jsp").forward(req, resp);
    }
}
