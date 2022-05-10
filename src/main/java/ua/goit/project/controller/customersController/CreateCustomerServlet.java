package ua.goit.project.controller.customersController;

import ua.goit.project.config.DatabaseManager;
import ua.goit.project.config.HibernateProvider;
import ua.goit.project.dataLayer.CustomerRepository;
import ua.goit.project.model.converter.CustomersConverter;
import ua.goit.project.model.dto.CustomersDto;
import ua.goit.project.service.CustomersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/createCustomer")
public class CreateCustomerServlet extends HttpServlet {

    private CustomersService customersService;

    @Override
    public void init() throws ServletException {
        DatabaseManager dbConnector = new HibernateProvider();
        customersService = new CustomersService(new CustomerRepository(dbConnector), new CustomersConverter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerName = req.getParameter("customerName");
        String customerBusiness = req.getParameter("customerBusiness");
        CustomersDto customersDto = new CustomersDto();
        customersDto.setName(customerName);
        customersDto.setBusiness(customerBusiness);
        customersService.create(customersDto);
        req.getRequestDispatcher("/WEB-INF/html/customers/createdCustomer.jsp").forward(req, resp);
    }
}
