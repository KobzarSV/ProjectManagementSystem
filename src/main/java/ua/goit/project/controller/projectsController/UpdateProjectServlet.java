package ua.goit.project.controller.projectsController;

import ua.goit.project.config.DatabaseManager;
import ua.goit.project.config.HibernateProvider;
import ua.goit.project.dataLayer.DevelopersRepository;
import ua.goit.project.dataLayer.ProjectsRepository;
import ua.goit.project.model.converter.DevelopersConverter;
import ua.goit.project.model.converter.ProjectsConverter;
import ua.goit.project.model.converter.SkillsConverter;
import ua.goit.project.model.dto.DevelopersDto;
import ua.goit.project.model.dto.ProjectsDto;
import ua.goit.project.service.DevelopersService;
import ua.goit.project.service.ProjectsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/updateProject")
public class UpdateProjectServlet extends HttpServlet {

    private ProjectsService projectsService;
    private DevelopersService developersService;

    @Override
    public void init() throws ServletException {
        DatabaseManager dbConnector = new HibernateProvider();
        DevelopersConverter developersConverter = new DevelopersConverter(new SkillsConverter());
        projectsService = new ProjectsService(new ProjectsRepository(dbConnector),
                new ProjectsConverter(developersConverter), developersConverter);
        developersService = new DevelopersService(new DevelopersRepository(dbConnector), developersConverter);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer projectId = Integer.parseInt(req.getParameter("projectId"));
        String projectName = req.getParameter("projectName");
        String projectDescription = req.getParameter("projectDescription");
        int companyId = Integer.parseInt(req.getParameter("companyId"));
        int customerId = Integer.parseInt(req.getParameter("customerId"));
        String projectDate = req.getParameter("projectDate");
        if (Objects.equals(projectDate, "")) {
            projectDate = "1970-01-01";
        }
        Set<Integer> developerIds = Arrays.stream(req.getParameterValues("developerId"))
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
        try {
            projectsService.find(projectId);
        } catch (Exception ex) {
            req.setAttribute("exception", "Project with id " + projectId + " not found!");
            req.getRequestDispatcher("/WEB-INF/html/error.jsp").forward(req, resp);
            return;
        }
        ProjectsDto projectsDto = new ProjectsDto();
        projectsDto.setId(projectId);
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
        Set<DevelopersDto> developers = developersService.findByIds(developerIds);
        projectsDto.setDevelopers(developers);
        projectsService.update(projectsDto);
        req.getRequestDispatcher("/WEB-INF/html/projects/updatedProject.jsp").forward(req, resp);
    }
}
