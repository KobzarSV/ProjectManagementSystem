package ua.goit.project.controller.companiesController;

import ua.goit.project.config.DatabaseManager;
import ua.goit.project.config.PostgresProvider;
import ua.goit.project.config.PropertiesUtil;
import ua.goit.project.dataLayer.CompanyRepository;
import ua.goit.project.model.converter.CompanyConverter;
import ua.goit.project.model.dto.CompanyDto;
import ua.goit.project.service.CompanyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet(urlPatterns = "/createCompany")
public class CreateCompanyServlet extends HttpServlet {

    private CompanyService companyService;

    @Override
    public void init() throws ServletException {
        PropertiesUtil properties = new PropertiesUtil(getServletContext());
        DatabaseManager dbConnector = new PostgresProvider(properties.getHostname(), properties.getPort(), properties.getSchema(),
                properties.getUser(), properties.getPassword(), properties.getJdbcDriver());
        companyService = new CompanyService(new CompanyConverter(), new CompanyRepository(dbConnector));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String companyName = req.getParameter("companyName");
        String companyDescription = req.getParameter("companyDescription");
        String numberOfEmployees = req.getParameter("numberOfEmployees");
        if (Objects.equals(numberOfEmployees, "")) {
            numberOfEmployees = "0";
        }
        CompanyDto companyDto = new CompanyDto();
        companyDto.setId(0);
        companyDto.setName(companyName);
        companyDto.setDescription(companyDescription);
        companyDto.setNumberOfEmployees(Integer.parseInt(numberOfEmployees));
        companyService.create(companyDto);
        req.getRequestDispatcher("/WEB-INF/html/companies/createdCompany.jsp").forward(req, resp);
    }
}
