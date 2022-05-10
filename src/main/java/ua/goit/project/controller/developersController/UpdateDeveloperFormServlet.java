package ua.goit.project.controller.developersController;

import ua.goit.project.config.DatabaseManager;
import ua.goit.project.config.HibernateProvider;
import ua.goit.project.dataLayer.CompanyRepository;
import ua.goit.project.dataLayer.DevelopersRepository;
import ua.goit.project.dataLayer.SkillsRepository;
import ua.goit.project.model.converter.CompanyConverter;
import ua.goit.project.model.converter.DevelopersConverter;
import ua.goit.project.model.converter.ProjectsConverter;
import ua.goit.project.model.converter.SkillsConverter;
import ua.goit.project.model.dto.CompaniesDto;
import ua.goit.project.model.dto.DevelopersDto;
import ua.goit.project.model.dto.SkillsDto;
import ua.goit.project.service.CompanyService;
import ua.goit.project.service.DevelopersService;
import ua.goit.project.service.SkillsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/updateDeveloperForm")
public class UpdateDeveloperFormServlet extends HttpServlet {

    private DevelopersService developersService;
    private CompanyService companyService;
    private SkillsService skillsService;

    @Override
    public void init() throws ServletException {
        DatabaseManager dbConnector = new HibernateProvider();
        SkillsConverter skillsConverter = new SkillsConverter();
        DevelopersConverter developersConverter = new DevelopersConverter(skillsConverter);
        ProjectsConverter projectsConverter = new ProjectsConverter(developersConverter);
        CompanyConverter companyConverter = new CompanyConverter(developersConverter, projectsConverter);
        developersService = new DevelopersService(
                new DevelopersRepository(dbConnector), new DevelopersConverter(skillsConverter));
        companyService = new CompanyService(new CompanyRepository(dbConnector),
                companyConverter, developersConverter, projectsConverter);
        skillsService = new SkillsService(new SkillsRepository(dbConnector), skillsConverter);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<DevelopersDto> developers = developersService.find();
        List<CompaniesDto> companies = companyService.find();
        List<SkillsDto> skills = skillsService.find();
        req.setAttribute("developers", developers);
        req.setAttribute("companies", companies);
        req.setAttribute("skills", skills);
        req.getRequestDispatcher("/WEB-INF/html/developers/updateDeveloperForm.jsp").forward(req, resp);
    }
}
