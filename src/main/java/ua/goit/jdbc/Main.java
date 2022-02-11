package ua.goit.jdbc;

import ua.goit.jdbc.config.DatabaseManager;
import ua.goit.jdbc.config.PostgresProvider;
import ua.goit.jdbc.config.PropertiesUtil;
import ua.goit.jdbc.dataLayer.DevelopersRepository;
import ua.goit.jdbc.dataLayer.Repository;
import ua.goit.jdbc.model.converter.DevelopersConverter;
import ua.goit.jdbc.model.dao.DevelopersDao;
import ua.goit.jdbc.model.dto.DevelopersDto;
import ua.goit.jdbc.service.DevelopersService;

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

        DevelopersDto developersDto = new DevelopersDto();
        developersDto.setId(9);
        developersDto.setName("Igor Kovalev");
        developersDto.setAge(34);
        developersDto.setGender("male");
        developersDto.setMail("kovalevigor@i.ua");
        developersDto.setCompanyId(3);
        developersDto.setSalary(1300);

//        developersService.create(developersDto);

        DevelopersDto developerById = developersService.read(1);
        System.out.println(developerById.getId());
        System.out.println(developerById.getName());
        System.out.println(developerById.getAge());
        System.out.println(developerById.getGender());
        System.out.println(developerById.getMail());
        System.out.println(developerById.getCompanyId());
        System.out.println(developerById.getSalary());

        List<DevelopersDto> developersAll = developersService.read();
        System.out.println(developersAll.toString());

//        final int devUpdate = developersService.update(developersDto);
//        System.out.println("UPDATED columns count " + devUpdate);

//        developersService.delete(developersDto);
    }
}
