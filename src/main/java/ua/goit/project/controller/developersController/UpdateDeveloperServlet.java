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
import java.util.Objects;

@WebServlet(urlPatterns = "/updateDeveloper")
public class UpdateDeveloperServlet extends HttpServlet {

    private DevelopersService developersService;

    @Override
    public void init() throws ServletException {
        PropertiesUtil properties = new PropertiesUtil(getServletContext());
        DatabaseManager dbConnector = new PostgresProvider(properties.getHostname(), properties.getPort(), properties.getSchema(),
                properties.getUser(), properties.getPassword(), properties.getJdbcDriver());
        developersService = new DevelopersService(new DevelopersConverter(), new DevelopersRepository(dbConnector));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String developerName = req.getParameter("developerName");
        String developerAge = req.getParameter("developerAge");
        if (Objects.equals(developerAge, "")) {
            developerAge = "0";
        }
        String developerGender = req.getParameter("developerGender");
        String developerEmail = req.getParameter("developerEmail");
        int companyId = Integer.parseInt(req.getParameter("companyId"));
        String developerSalary = req.getParameter("developerSalary");
        if (Objects.equals(developerSalary, "")) {
            developerSalary = "0";
        }
        int developerId = Integer.parseInt(req.getParameter("developerId"));
        DevelopersDto developersDto = new DevelopersDto();
        developersDto.setName(developerName);
        developersDto.setAge(Integer.parseInt(developerAge));
        developersDto.setGender(developerGender);
        developersDto.setMail(developerEmail);
        developersDto.setCompanyId(companyId);
        developersDto.setSalary(Integer.parseInt(developerSalary));
        try {
            developersService.find(developerId);
        } catch (Exception ex) {
            req.setAttribute("exception", "Developer with id " + developerId + " not found!");
            req.getRequestDispatcher("/WEB-INF/html/error.jsp").forward(req, resp);
            return;
        }
        developersDto.setId(developerId);
        developersService.update(developersDto);
        req.getRequestDispatcher("/WEB-INF/html/developers/updatedDeveloper.jsp").forward(req, resp);
    }
}
