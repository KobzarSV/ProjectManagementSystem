package ua.goit.project.controller.projectsController;

import ua.goit.project.config.DatabaseManager;
import ua.goit.project.config.PostgresProvider;
import ua.goit.project.config.PropertiesUtil;
import ua.goit.project.dataLayer.ProjectsRepository;
import ua.goit.project.model.converter.ProjectsConverter;
import ua.goit.project.model.dto.ProjectsDto;
import ua.goit.project.service.ProjectsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/viewAllProjects")
public class ViewAllProjectsServlet extends HttpServlet {

    private ProjectsService projectsService;

    @Override
    public void init() throws ServletException {
        PropertiesUtil properties = new PropertiesUtil(getServletContext());
        DatabaseManager dbConnector = new PostgresProvider(properties.getHostname(), properties.getPort(), properties.getSchema(),
                properties.getUser(), properties.getPassword(), properties.getJdbcDriver());
        projectsService = new ProjectsService(new ProjectsConverter(), new ProjectsRepository(dbConnector));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProjectsDto> projectsDtoList = projectsService.find();
            req.setAttribute("projects", projectsDtoList);
            req.getRequestDispatcher("/WEB-INF/html/projects/viewAllProjects.jsp").forward(req, resp);
    }
}
