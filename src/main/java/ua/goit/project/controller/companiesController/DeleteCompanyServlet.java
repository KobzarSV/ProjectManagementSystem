package ua.goit.project.controller.companiesController;

import ua.goit.project.config.DatabaseManager;
import ua.goit.project.config.PostgresProvider;
import ua.goit.project.config.PropertiesUtil;
import ua.goit.project.dataLayer.CompanyRepository;
import ua.goit.project.model.converter.CompanyConverter;
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

    @Override
    public void init() throws ServletException {
        PropertiesUtil properties = new PropertiesUtil(getServletContext());
        DatabaseManager dbConnector = new PostgresProvider(properties.getHostname(), properties.getPort(), properties.getSchema(),
                properties.getUser(), properties.getPassword(), properties.getJdbcDriver());
        companyRepository = new CompanyRepository(dbConnector);
        companyService = new CompanyService(new CompanyConverter(), new CompanyRepository(dbConnector));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int companyId = Integer.parseInt(req.getParameter("companyId"));
        try {
            companyService.find(companyId);
        } catch (Exception ex) {
            req.setAttribute("exception", "Company with id " + companyId + " not found!");
            req.getRequestDispatcher("/WEB-INF/html/error.jsp").forward(req, resp);
            return;
        }
        companyRepository.delete(companyId);
        req.getRequestDispatcher("/WEB-INF/html/companies/deleteCompany.jsp").forward(req, resp);
    }
}
