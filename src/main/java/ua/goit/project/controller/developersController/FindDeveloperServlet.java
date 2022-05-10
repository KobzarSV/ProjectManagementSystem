package ua.goit.project.controller.developersController;

import ua.goit.project.config.DatabaseManager;
import ua.goit.project.config.HibernateProvider;
import ua.goit.project.dataLayer.DevelopersRepository;
import ua.goit.project.model.converter.DevelopersConverter;
import ua.goit.project.model.converter.SkillsConverter;
import ua.goit.project.model.dto.DevelopersDto;
import ua.goit.project.service.DevelopersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/findDeveloper")
public class FindDeveloperServlet extends HttpServlet {
    DevelopersService developersService;

    @Override
    public void init() throws ServletException {
        DatabaseManager dbConnector = new HibernateProvider();
        SkillsConverter skillsConverter = new SkillsConverter();
        developersService = new DevelopersService(
                new DevelopersRepository(dbConnector), new DevelopersConverter(skillsConverter));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer developerId = Integer.parseInt(req.getParameter("developerId"));
        DevelopersDto developersDto;
        try {
            developersDto = developersService.find(developerId);
        } catch (Exception ex) {
            req.setAttribute("exception", "Developer with id " + developerId + " not found!");
            req.getRequestDispatcher("/WEB-INF/html/error.jsp").forward(req, resp);
            return;
        }
        req.setAttribute("developer", developersDto);
        req.getRequestDispatcher("/WEB-INF/html/developers/findDeveloper.jsp").forward(req, resp);
    }
}
