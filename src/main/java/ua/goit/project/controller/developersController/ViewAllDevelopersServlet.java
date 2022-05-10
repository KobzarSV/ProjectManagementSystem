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
import java.util.List;

@WebServlet(urlPatterns = "/viewAllDevelopers")
public class ViewAllDevelopersServlet extends HttpServlet {

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
        List<DevelopersDto> developersDtoList = developersService.find();
        req.setAttribute("developers", developersDtoList);
        req.getRequestDispatcher("/WEB-INF/html/developers/viewAllDevelopers.jsp").forward(req, resp);
    }
}
