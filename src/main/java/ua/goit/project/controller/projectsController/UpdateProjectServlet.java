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
import java.sql.Date;
import java.util.Objects;

@WebServlet(urlPatterns = "/updateProject")
public class UpdateProjectServlet extends HttpServlet {

    private ProjectsService projectsService;

    @Override
    public void init() throws ServletException {
        PropertiesUtil properties = new PropertiesUtil(getServletContext());
        DatabaseManager dbConnector = new PostgresProvider(properties.getHostname(), properties.getPort(), properties.getSchema(),
                properties.getUser(), properties.getPassword(), properties.getJdbcDriver());
        projectsService = new ProjectsService(new ProjectsConverter(), new ProjectsRepository(dbConnector));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String projectName = req.getParameter("projectName");
        String projectDescription = req.getParameter("projectDescription");
        int companyId = Integer.parseInt(req.getParameter("companyId"));
        int customerId = Integer.parseInt(req.getParameter("customerId"));
        String projectDate = req.getParameter("projectDate");
        if (Objects.equals(projectDate, "")) {
            projectDate = "1970-01-01";
        }
        String projectId = req.getParameter("projectId");
        ProjectsDto projectsDto = new ProjectsDto();
        projectsDto.setName(projectName);
        projectsDto.setDescription(projectDescription);
        projectsDto.setCompanyId(companyId);
        projectsDto.setCustomerId(customerId);
        try {
            projectsDto.setDate(Date.valueOf(projectDate));
        } catch (Exception ex) {
            req.setAttribute("exception", "Invalid field \"Date\". Please try again");
            req.getRequestDispatcher("/WEB-INF/html/error.jsp").forward(req, resp);
            return;
        }
        try {
            projectsService.find(Integer.parseInt(projectId));
        } catch (Exception ex) {
            req.setAttribute("exception", "Project with id " + projectId + " not found!");
            req.getRequestDispatcher("/WEB-INF/html/error.jsp").forward(req, resp);
            return;
        }
        projectsDto.setId(Integer.parseInt(projectId));
        projectsService.update(projectsDto);
        req.getRequestDispatcher("/WEB-INF/html/projects/updatedProject.jsp").forward(req, resp);
    }
}
