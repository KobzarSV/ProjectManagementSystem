package ua.goit.jdbc;

import ua.goit.jdbc.config.DatabaseManager;
import ua.goit.jdbc.config.PostgresProvider;
import ua.goit.jdbc.config.PropertiesUtil;
import ua.goit.jdbc.dataLayer.DevelopersRepository;
import ua.goit.jdbc.dataLayer.ProjectsRepository;
import ua.goit.jdbc.model.converter.DevelopersConverter;
import ua.goit.jdbc.model.converter.ProjectsConverter;
import ua.goit.jdbc.model.dto.DevelopersDto;
import ua.goit.jdbc.model.dto.ProjectsDto;
import ua.goit.jdbc.service.DevelopersService;
import ua.goit.jdbc.service.ProjectsService;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        PropertiesUtil util = new PropertiesUtil();

        DatabaseManager dbConnector = new PostgresProvider(
                util.getHostname(), util.getPort(), util.getSchema(), util.getUser(), util.getPassword());

        DevelopersRepository developersRepository = new DevelopersRepository(dbConnector);
        DevelopersConverter developersConverter = new DevelopersConverter();
        DevelopersService developersService = new DevelopersService(developersConverter, developersRepository);

        ProjectsRepository projectsRepository = new ProjectsRepository(dbConnector);
        ProjectsConverter projectsConverter = new ProjectsConverter();
        ProjectsService projectsService = new ProjectsService(projectsConverter, projectsRepository);

        // form to create/update developer
        DevelopersDto developersDto = new DevelopersDto();
        developersDto.setId(9);
        developersDto.setName("Igor Kovalev");
        developersDto.setAge(34);
        developersDto.setGender("male");
        developersDto.setMail("kovalevigor@i.ua");
        developersDto.setCompanyId(3);
        developersDto.setSalary(1300);

        // create developer
//        developersService.create(developersDto);

        // update developer
//        final int devUpdate = developersService.update(developersDto);
//        System.out.println("UPDATED columns count " + devUpdate);

        // show developer by id
//        DevelopersDto developerById = developersService.read(1);
//        System.out.println(developerById.toString());

        // show all developers
//        List<DevelopersDto> developersAll = developersService.read();
//        System.out.println(developersAll.toString());

        // delete developer
//        developersService.delete(developersDto);

        // form to create/update developer
        ProjectsDto projectDto = new ProjectsDto();
        projectDto.setId(8);
        projectDto.setName("Hotel booking");
        projectDto.setDescription("Application for booking hotels around the world");
        projectDto.setCompanyId(1);
        projectDto.setCustomerId(2);

        // create project
//        projectsService.create(projectDto);

        // show project by id
//        ProjectsDto projectById = projectsService.read(1);
//        System.out.println(projectById.toString());

        // show all projects
//        List<ProjectsDto> projectsAll = projectsService.read();
//        System.out.println(projectsAll.toString());

        // update project
//        final int projectUpdate = projectsService.update(projectDto);
//        System.out.println("UPDATED columns count " + projectUpdate);

        // delete project
//        projectsService.delete(projectDto);

        // show amount of salary for one project by id
//        final int sumSalary = projectsRepository.getAmountOfSalaryForOneProject(1);
//        System.out.println("The amount salary developers of the project " + sumSalary);

        // show developers of project by id
//        List<DevelopersDto> listOfDevelopersOfProjectById = developersService.developersOfProjectById(1);
//        System.out.println(listOfDevelopersOfProjectById);

        // show developers of project by name
//        List<DevelopersDto> listOfDevelopersOfProjectByName = developersService.developersOfProjectByName("Autopilot");
//        System.out.println(listOfDevelopersOfProjectByName);

        // show developers by industry
//        List<DevelopersDto> listOfDevelopersByIndustry = developersService.developersOfIndustry("Java");
//        System.out.println(listOfDevelopersByIndustry);

        // show developers by skill level
//        List<DevelopersDto> listOfDevelopersBySkillLevel = developersService.developersBySkillLevel("Middle");
//        System.out.println(listOfDevelopersBySkillLevel);
    }
}
