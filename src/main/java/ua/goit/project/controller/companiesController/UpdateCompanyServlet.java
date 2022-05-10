package ua.goit.project.controller.companiesController;

import ua.goit.project.config.DatabaseManager;
import ua.goit.project.config.HibernateProvider;
import ua.goit.project.dataLayer.CompanyRepository;
import ua.goit.project.model.converter.CompanyConverter;
import ua.goit.project.model.converter.DevelopersConverter;
import ua.goit.project.model.converter.ProjectsConverter;
import ua.goit.project.model.converter.SkillsConverter;
import ua.goit.project.model.dto.CompaniesDto;
import ua.goit.project.service.CompanyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet(urlPatterns = "/updateCompany")
public class UpdateCompanyServlet extends HttpServlet {

    private CompanyService companyService;

    @Override
    public void init() throws ServletException {
        DatabaseManager dbConnector = new HibernateProvider();
        DevelopersConverter developersConverter = new DevelopersConverter(new SkillsConverter());
        ProjectsConverter projectsConverter = new ProjectsConverter(developersConverter);
        CompanyConverter companyConverter = new CompanyConverter(developersConverter, projectsConverter);
        companyService = new CompanyService(new CompanyRepository(dbConnector),
                companyConverter, developersConverter, projectsConverter);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String companyName = req.getParameter("companyName");
        String companyDescription = req.getParameter("companyDescription");
        String numberOfEmployees = req.getParameter("numberOfEmployees");
        if (Objects.equals(numberOfEmployees, "")) {
            numberOfEmployees = "0";
        }
        String companyId = req.getParameter("companyId");
        CompaniesDto companyDto = new CompaniesDto();
        companyDto.setName(companyName);
        companyDto.setDescription(companyDescription);
        companyDto.setEmployees(Integer.parseInt(numberOfEmployees));
        try {
            companyService.find(Integer.parseInt(companyId));
        } catch (Exception ex) {
            req.setAttribute("exception", "Company with id " + companyId + " not found!");
            req.getRequestDispatcher("/WEB-INF/html/error.jsp").forward(req, resp);
            return;
        }
        companyDto.setId(Integer.parseInt(companyId));
        companyService.update(companyDto);
        req.getRequestDispatcher("/WEB-INF/html/companies/updatedCompany.jsp").forward(req, resp);
    }
}
