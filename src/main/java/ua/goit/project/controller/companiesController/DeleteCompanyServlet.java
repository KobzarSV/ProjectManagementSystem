package ua.goit.project.controller.companiesController;

import ua.goit.project.config.DatabaseManager;
import ua.goit.project.config.HibernateProvider;
import ua.goit.project.dataLayer.CompanyRepository;
import ua.goit.project.model.converter.CompanyConverter;
import ua.goit.project.model.converter.DevelopersConverter;
import ua.goit.project.model.converter.ProjectsConverter;
import ua.goit.project.model.converter.SkillsConverter;
import ua.goit.project.model.dao.CompaniesDao;
import ua.goit.project.model.dto.CompaniesDto;
import ua.goit.project.service.CompanyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = ("/deleteCompany"))
public class DeleteCompanyServlet extends HttpServlet {
    private CompanyRepository companyRepository;
    private CompanyService companyService;
    private CompanyConverter companyConverter;

    @Override
    public void init() throws ServletException {
        DatabaseManager dbConnector = new HibernateProvider();
        companyRepository = new CompanyRepository(dbConnector);
        DevelopersConverter developersConverter = new DevelopersConverter(new SkillsConverter());
        ProjectsConverter projectsConverter = new ProjectsConverter(developersConverter);
        companyConverter = new CompanyConverter(developersConverter, projectsConverter);
        companyService = new CompanyService(new CompanyRepository(dbConnector),
                companyConverter, developersConverter, projectsConverter);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int companyId = Integer.parseInt(req.getParameter("companyId"));
        CompaniesDto companiesDto;
        try {
            companiesDto = companyService.find(companyId);
        } catch (Exception ex) {
            req.setAttribute("exception", "Company with id " + companyId + " not found!");
            req.getRequestDispatcher("/WEB-INF/html/error.jsp").forward(req, resp);
            return;
        }
        CompaniesDao companiesDao = companyConverter.toDao(companiesDto);
        companyRepository.delete(companiesDao);
        req.getRequestDispatcher("/WEB-INF/html/companies/deleteCompany.jsp").forward(req, resp);
    }
}
