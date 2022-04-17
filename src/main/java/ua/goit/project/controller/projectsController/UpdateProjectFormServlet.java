package ua.goit.project.controller.projectsController;

import ua.goit.project.config.DatabaseManager;
import ua.goit.project.config.PostgresProvider;
import ua.goit.project.config.PropertiesUtil;
import ua.goit.project.dataLayer.CompanyRepository;
import ua.goit.project.dataLayer.CustomerRepository;
import ua.goit.project.model.converter.CompanyConverter;
import ua.goit.project.model.converter.CustomersConverter;
import ua.goit.project.model.dto.CompanyDto;
import ua.goit.project.model.dto.CustomersDto;
import ua.goit.project.service.CompanyService;
import ua.goit.project.service.CustomersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/updateProjectForm")
public class UpdateProjectFormServlet extends HttpServlet {

    private CompanyService companyService;
    private CustomersService customersService;

    @Override
    public void init() throws ServletException {
        PropertiesUtil properties = new PropertiesUtil(getServletContext());
        DatabaseManager dbConnector = new PostgresProvider(properties.getHostname(), properties.getPort(), properties.getSchema(),
                properties.getUser(), properties.getPassword(), properties.getJdbcDriver());
        companyService = new CompanyService(new CompanyConverter(), new CompanyRepository(dbConnector));
        customersService = new CustomersService(new CustomersConverter(), new CustomerRepository(dbConnector));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CompanyDto> companies = companyService.find();
        List<CustomersDto> customers = customersService.find();
        req.setAttribute("companies", companies);
        req.setAttribute("customers", customers);
        req.getRequestDispatcher("/WEB-INF/html/projects/updateProjectForm.jsp").forward(req, resp);
    }
}
