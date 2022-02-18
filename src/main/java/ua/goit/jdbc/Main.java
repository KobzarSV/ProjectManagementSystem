package ua.goit.jdbc;

import ua.goit.jdbc.config.DatabaseManager;
import ua.goit.jdbc.config.PostgresProvider;
import ua.goit.jdbc.config.PropertiesUtil;
import ua.goit.jdbc.dataLayer.CustomerRepository;
import ua.goit.jdbc.dataLayer.DevelopersRepository;
import ua.goit.jdbc.dataLayer.ProjectsRepository;
import ua.goit.jdbc.dataLayer.SkillsRepository;
import ua.goit.jdbc.model.converter.CustomersConverter;
import ua.goit.jdbc.model.converter.DevelopersConverter;
import ua.goit.jdbc.model.converter.ProjectsConverter;
import ua.goit.jdbc.model.converter.SkillsConverter;
import ua.goit.jdbc.model.dto.CustomersDto;
import ua.goit.jdbc.model.dto.DevelopersDto;
import ua.goit.jdbc.model.dto.ProjectsDto;
import ua.goit.jdbc.model.dto.SkillsDto;
import ua.goit.jdbc.service.CustomersService;
import ua.goit.jdbc.service.DevelopersService;
import ua.goit.jdbc.service.ProjectsService;
import ua.goit.jdbc.service.SkillsService;

import java.sql.Date;
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

        CustomerRepository customerRepository = new CustomerRepository(dbConnector);
        CustomersConverter customersConverter = new CustomersConverter();
        CustomersService customersService = new CustomersService(customersConverter, customerRepository);

        SkillsRepository skillsRepository = new SkillsRepository(dbConnector);
        SkillsConverter skillsConverter = new SkillsConverter();
        SkillsService skillsService = new SkillsService(skillsConverter, skillsRepository);

        System.out.println("================================================================================");
        System.out.println("1. Show amount of salary for one project by id");
        final int sumSalary = projectsRepository.getAmountOfSalaryForOneProject(1);
        System.out.println("The amount salary developers of the project " + sumSalary);
        System.out.println("================================================================================");

        System.out.println("2. Show developers of project by id");
        List<DevelopersDto> listOfDevelopersOfProjectById = developersService.developersOfProjectById(5);
        System.out.println(listOfDevelopersOfProjectById);
        System.out.println("================================================================================");

        System.out.println("3. Show developers of project by name");
        List<DevelopersDto> listOfDevelopersOfProjectByName =
                developersService.developersOfProjectByName("Autopilot");
        System.out.println(listOfDevelopersOfProjectByName);
        System.out.println("================================================================================");

        System.out.println("4. Show developers by industry");
        List<DevelopersDto> listOfDevelopersByIndustry = developersService.developersByIndustry("Java");
        System.out.println(listOfDevelopersByIndustry);
        System.out.println("================================================================================");

        System.out.println("5. Show developers by skill level");
        List<DevelopersDto> listOfDevelopersBySkillLevel = developersService.developersBySkillLevel("Middle");
        System.out.println(listOfDevelopersBySkillLevel);
        System.out.println("================================================================================");

        System.out.println("6. Show projects: date, name and count developers");
        List<ProjectsDto> listOfProjectsDateAndCountDev = projectsService.projectsDateCountDevDto();
        System.out.println(listOfProjectsDateAndCountDev);
        System.out.println("================================================================================");

        // form to create/update developer
        DevelopersDto developer = new DevelopersDto();
        developer.setId(8);
        developer.setName("Igor Kovalev");
        developer.setAge(34);
        developer.setGender("male");
        developer.setMail("kovalevigor@gi.ua");
        developer.setCompanyId(3);
        developer.setSalary(1300);

        // create developer
//        developersService.create(developer);

        // form to create/update skills
        SkillsDto skills = new SkillsDto();
        skills.setDevelopersId(8);
        skills.setIndustry("C++");
        skills.setSkillLevel("Middle");

        // create developer skills
//        skillsService.createDeveloperSkills(skills);

        // update developer
//        final int devUpdate = developersService.update(developer);
//        System.out.println("UPDATED columns count " + devUpdate);

        // show developer by id
//        DevelopersDto developerById = developersService.read(8);
//        System.out.println(developerById.toString());

        // show all developers
//        List<DevelopersDto> developersAll = developersService.read();
//        System.out.println(developersAll.toString());

        // delete developer
//        developersService.delete(developer);

        // form to create/update project
        ProjectsDto project = new ProjectsDto();
        project.setId(7);
        project.setName("Hotel booking");
        project.setDescription("Application for booking hotels around the world.");
        project.setCompanyId(1);
        project.setCustomerId(2);
        project.setDate(Date.valueOf("2022-02-15"));

        // create project
//        projectsService.create(project);

        // update project
//        final int projectUpdate = projectsService.update(project);
//        System.out.println("UPDATED columns count " + projectUpdate);

        // show project by id
//        ProjectsDto projectById = projectsService.read(1);
//        System.out.println(projectById.toString());

        // show all projects
//        List<ProjectsDto> projectsAll = projectsService.read();
//        System.out.println(projectsAll.toString());

        // delete project
//        projectsService.delete(project);

        // form to create/update customer
        CustomersDto customer = new CustomersDto();
        customer.setId(4);
        customer.setName("Mark Zuckerberg");
        customer.setBusiness("Meta Platforms Inc");

        // create customer
//        customersService.create(customer);

        // update customer
//        final int customerUpdate = customersService.update(customer);
//        System.out.println("UPDATED columns count " + customerUpdate);

        // show customer by id
//        CustomersDto customerById = customersService.read(1);
//        System.out.println(customerById.toString());

        // show all customers
//        List<CustomersDto> customersAll = customersService.read();
//        System.out.println(customersAll.toString());

        // delete customer
//        customersService.delete(customer);
    }
}
