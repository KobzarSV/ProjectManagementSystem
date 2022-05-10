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

@WebServlet(urlPatterns = ("/deleteDeveloper"))
public class DeleteDeveloperServlet extends HttpServlet {

    private DevelopersService developersService;

    @Override
    public void init() throws ServletException {
        DatabaseManager dbConnector = new HibernateProvider();
        DevelopersConverter developersConverter = new DevelopersConverter(new SkillsConverter());
        developersService = new DevelopersService(
                new DevelopersRepository(dbConnector), developersConverter);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String developerId = req.getParameter("developerId");
        DevelopersDto developersDto;
        try {
            developersDto = developersService.find(Integer.parseInt(developerId));
        } catch (Exception ex) {
            req.setAttribute("exception", "Developer with id " + developerId + " not found!");
            req.getRequestDispatcher("/WEB-INF/html/error.jsp").forward(req, resp);
            return;
        }
        developersService.delete(developersDto);
        req.getRequestDispatcher("/WEB-INF/html/developers/deleteDeveloper.jsp").forward(req, resp);
    }
}
