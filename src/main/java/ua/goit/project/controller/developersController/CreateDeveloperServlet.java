package ua.goit.project.controller.developersController;

import ua.goit.project.config.DatabaseManager;
import ua.goit.project.config.HibernateProvider;
import ua.goit.project.dataLayer.DevelopersRepository;
import ua.goit.project.dataLayer.SkillsRepository;
import ua.goit.project.model.converter.DevelopersConverter;
import ua.goit.project.model.converter.SkillsConverter;
import ua.goit.project.model.dto.DevelopersDto;
import ua.goit.project.model.dto.SkillsDto;
import ua.goit.project.service.DevelopersService;
import ua.goit.project.service.SkillsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/createDeveloper")
public class CreateDeveloperServlet extends HttpServlet {

    private DevelopersService developersService;
    private SkillsService skillsService;

    @Override
    public void init() throws ServletException {
        DatabaseManager dbConnector = new HibernateProvider();
        SkillsConverter skillsConverter = new SkillsConverter();
        developersService = new DevelopersService(
                new DevelopersRepository(dbConnector), new DevelopersConverter(skillsConverter));
        skillsService = new SkillsService(new SkillsRepository(dbConnector), skillsConverter);
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
        Set<Integer> skillsIds = Arrays.stream(req.getParameterValues("skillId"))
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
        DevelopersDto developersDto = new DevelopersDto();
        developersDto.setName(developerName);
        developersDto.setAge(Integer.parseInt(developerAge));
        developersDto.setGender(developerGender);
        developersDto.setMail(developerEmail);
        developersDto.setCompanyId(companyId);
        developersDto.setSalary(Integer.parseInt(developerSalary));
        Set<SkillsDto> skillsDtos = skillsService.findByIds(skillsIds);
        developersDto.setSkills(skillsDtos);
        developersService.create(developersDto);
        req.getRequestDispatcher("/WEB-INF/html/developers/createdDeveloper.jsp").forward(req, resp);
    }
}
