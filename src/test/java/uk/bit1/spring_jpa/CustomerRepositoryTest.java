package uk.bit1.spring_jpa;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    private Customer testCustomer;

    @BeforeEach
    public void setUp() {
        testCustomer = new Customer("Bloggs", "Jo");
        customerRepository.save(testCustomer);
    }

    @AfterEach
    public void tearDown() {
        customerRepository.delete(testCustomer);

    }

    @Test
    void customer_can_be_found_by_id() {
        Customer customer = customerRepository.findById(testCustomer.getId()).orElse(null);
        assertNotNull(customer);
        assertEquals(testCustomer.getLastName(), customer.getLastName());
        assertEquals(testCustomer.getFirstName(), customer.getFirstName());
    }

    @Test
    void customers_can_be_found_by_lastName() {
        List<Customer> customers = customerRepository.findByLastName("Bloggs");
        assertNotNull(customers);
        assertEquals(testCustomer.getId(), customers.get(0).getId());
    }

    @Test
    void customer_details_can_be_updated() {
        testCustomer.setLastName("Mouse");
        testCustomer.setFirstName("Mickey");
        customerRepository.save(testCustomer);

        Customer customer = customerRepository.findById(testCustomer.getId()).orElse(null);
        assertNotNull(customer);
        assertEquals("Mouse", customer.getLastName());
        assertEquals("Mickey", customer.getFirstName());
    }
}
