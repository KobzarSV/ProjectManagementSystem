package ua.goit.project.controller.customersController;

import ua.goit.project.config.DatabaseManager;
import ua.goit.project.config.HibernateProvider;
import ua.goit.project.dataLayer.CustomerRepository;
import ua.goit.project.model.converter.CustomersConverter;
import ua.goit.project.model.dao.CustomersDao;
import ua.goit.project.model.dto.CustomersDto;
import ua.goit.project.service.CustomersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = ("/deleteCustomer"))
public class DeleteCustomerServlet extends HttpServlet {
    private CustomerRepository customerRepository;
    private CustomersService customersService;
    private CustomersConverter customersConverter;

    @Override
    public void init() throws ServletException {
        DatabaseManager dbConnector = new HibernateProvider();
        customerRepository = new CustomerRepository(dbConnector);
        customersConverter = new CustomersConverter();
        customersService = new CustomersService(new CustomerRepository(dbConnector), customersConverter);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int customerId = Integer.parseInt(req.getParameter("customerId"));
        CustomersDto customersDto;
        try {
            customersDto = customersService.find(customerId);
        } catch (Exception ex) {
            req.setAttribute("exception", "Customer with id " + customerId + " not found!");
            req.getRequestDispatcher("/WEB-INF/html/error.jsp").forward(req, resp);
            return;
        }
        CustomersDao customersDao = customersConverter.toDao(customersDto);
        customerRepository.delete(customersDao);
        req.getRequestDispatcher("/WEB-INF/html/customers/deleteCustomer.jsp").forward(req, resp);
    }
}
