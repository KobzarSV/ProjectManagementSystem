package ua.goit.project.controller.projectsController;

import ua.goit.project.config.DatabaseManager;
import ua.goit.project.config.HibernateProvider;
import ua.goit.project.dataLayer.CompanyRepository;
import ua.goit.project.dataLayer.CustomerRepository;
import ua.goit.project.dataLayer.DevelopersRepository;
import ua.goit.project.model.converter.*;
import ua.goit.project.model.dto.CompaniesDto;
import ua.goit.project.model.dto.CustomersDto;
import ua.goit.project.model.dto.DevelopersDto;
import ua.goit.project.service.CompanyService;
import ua.goit.project.service.CustomersService;
import ua.goit.project.service.DevelopersService;

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
    private DevelopersService developersService;

    @Override
    public void init() throws ServletException {
        DatabaseManager dbConnector = new HibernateProvider();
        DevelopersConverter developersConverter = new DevelopersConverter(new SkillsConverter());
        ProjectsConverter projectsConverter = new ProjectsConverter(developersConverter);
        CompanyConverter companyConverter = new CompanyConverter(developersConverter, projectsConverter);
        companyService = new CompanyService(new CompanyRepository(dbConnector),
                companyConverter, developersConverter, projectsConverter);
        customersService = new CustomersService(new CustomerRepository(dbConnector), new CustomersConverter());
        developersService = new DevelopersService(new DevelopersRepository(dbConnector), developersConverter);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CompaniesDto> companies = companyService.find();
        List<CustomersDto> customers = customersService.find();
        List<DevelopersDto> developers = developersService.find();
        req.setAttribute("companies", companies);
        req.setAttribute("customers", customers);
        req.setAttribute("developers", developers);
        req.getRequestDispatcher("/WEB-INF/html/projects/updateProjectForm.jsp").forward(req, resp);
    }
}
