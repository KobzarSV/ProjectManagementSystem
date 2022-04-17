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

@WebServlet(urlPatterns = "/createDeveloper")
public class CreateDeveloperServlet extends HttpServlet {

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
        DevelopersDto developersDto = new DevelopersDto();
        developersDto.setId(0);
        developersDto.setName(developerName);
        developersDto.setAge(Integer.parseInt(developerAge));
        developersDto.setGender(developerGender);
        developersDto.setMail(developerEmail);
        developersDto.setCompanyId(companyId);
        developersDto.setSalary(Integer.parseInt(developerSalary));
        developersService.create(developersDto);
        req.getRequestDispatcher("/WEB-INF/html/developers/createdDeveloper.jsp").forward(req, resp);
    }
}
