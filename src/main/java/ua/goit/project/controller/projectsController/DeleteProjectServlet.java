package ua.goit.project.controller.projectsController;

import ua.goit.project.config.DatabaseManager;
import ua.goit.project.config.PostgresProvider;
import ua.goit.project.config.PropertiesUtil;
import ua.goit.project.dataLayer.ProjectsRepository;
import ua.goit.project.model.converter.ProjectsConverter;
import ua.goit.project.service.ProjectsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = ("/deleteProject"))
public class DeleteProjectServlet extends HttpServlet {

    private ProjectsRepository projectsRepository;
    private ProjectsService projectsService;

    @Override
    public void init() throws ServletException {
        PropertiesUtil properties = new PropertiesUtil(getServletContext());
        DatabaseManager dbConnector = new PostgresProvider(properties.getHostname(), properties.getPort(), properties.getSchema(),
                properties.getUser(), properties.getPassword(), properties.getJdbcDriver());
        projectsRepository = new ProjectsRepository(dbConnector);
        projectsService = new ProjectsService(new ProjectsConverter(), new ProjectsRepository(dbConnector));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int projectId = Integer.parseInt(req.getParameter("projectId"));
        try {
            projectsService.find(projectId);
        } catch (Exception ex) {
            req.setAttribute("exception", "Project with id " + projectId + " not found!");
            req.getRequestDispatcher("/WEB-INF/html/error.jsp").forward(req, resp);
            return;
        }
        projectsRepository.delete(projectId);
        req.getRequestDispatcher("/WEB-INF/html/projects/deleteProject.jsp").forward(req, resp);
    }
}
