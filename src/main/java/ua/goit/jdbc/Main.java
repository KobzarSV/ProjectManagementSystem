package ua.goit.jdbc;

import ua.goit.jdbc.config.DatabaseManager;
import ua.goit.jdbc.config.PostgresProvider;
import ua.goit.jdbc.config.PropertiesUtil;
import ua.goit.jdbc.dataLayer.DevelopersRepository;
import ua.goit.jdbc.dataLayer.ProjectsRepository;
import ua.goit.jdbc.dataLayer.Repository;
import ua.goit.jdbc.model.converter.DevelopersConverter;
import ua.goit.jdbc.model.converter.ProjectsConverter;
import ua.goit.jdbc.model.dao.DevelopersDao;
import ua.goit.jdbc.model.dao.ProjectsDao;
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

        Repository<DevelopersDao> devDaoRepository = new DevelopersRepository(dbConnector);
        DevelopersConverter devConvertor = new DevelopersConverter();
        DevelopersService developersService = new DevelopersService(devConvertor, devDaoRepository);

        Repository<ProjectsDao> projectsDaoRepository = new ProjectsRepository(dbConnector);
        ProjectsConverter projectsConverter = new ProjectsConverter();
        ProjectsService projectsService = new ProjectsService(projectsConverter, projectsDaoRepository);

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
        projectDto.setId(7);
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

        // delete project
//        projectsService.delete(projectDto);
    }
}
