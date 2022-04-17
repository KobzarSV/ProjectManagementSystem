package ua.goit.project.controller.developersController;

import ua.goit.project.config.DatabaseManager;
import ua.goit.project.config.PostgresProvider;
import ua.goit.project.config.PropertiesUtil;
import ua.goit.project.dataLayer.DevelopersRepository;
import ua.goit.project.model.converter.DevelopersConverter;
import ua.goit.project.model.dto.DevelopersDto;
import ua.goit.project.service.DevelopersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/viewAllDevelopers")
public class ViewAllDevelopersServlet extends HttpServlet {

    DevelopersService developersService;
    DevelopersRepository developersRepository;

    @Override
    public void init() throws ServletException {
        PropertiesUtil properties = new PropertiesUtil(getServletContext());
        DatabaseManager dbConnector = new PostgresProvider(properties.getHostname(), properties.getPort(), properties.getSchema(),
                properties.getUser(), properties.getPassword(), properties.getJdbcDriver());
        developersRepository = new DevelopersRepository(dbConnector);
        developersService = new DevelopersService(new DevelopersConverter(), developersRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<DevelopersDto> developersDtoList = developersService.find();
        List<Map<String, String>> allIndustry = developersRepository.findIndustryByAllDeveloper();
        req.setAttribute("developers", developersDtoList);
        req.setAttribute("industry", allIndustry);
        req.getRequestDispatcher("/WEB-INF/html/developers/viewAllDevelopers.jsp").forward(req, resp);
    }
}
