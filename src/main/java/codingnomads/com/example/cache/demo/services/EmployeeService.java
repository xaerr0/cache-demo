package codingnomads.com.example.cache.demo.services;

import codingnomads.com.example.cache.demo.exceptions.ResourceNotFoundException;
import codingnomads.com.example.cache.demo.models.Employee;
import codingnomads.com.example.cache.demo.repos.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo employeeRepository;

//    can be used to inspect the cache during debug
//    @Autowired
//CacheManager cacheManager;

    @Cacheable
            (value = "employees", key = "#employeeId", sync = true)
    public Employee getEmployee(Integer employeeId) {

        fakeDelay("Fetching employee from the database...");

        return employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee not found: " + employeeId));
    }

    @Cacheable(value = "employees")
    public List<Employee> getAllEmployees() {
        fakeDelay("Fetching all employees from the database...");
        return employeeRepository.findAll();
    }

    @CachePut(value = "employees", key = "#employee.id")
    public Employee saveEmployee(Employee employee) {
        System.out.println("Saving user to the database and updating cache");
        return employeeRepository.save(employee);
    }

    @CacheEvict(value = "employees", allEntries = true)
    public void deleteEmployee(Integer employeeId) {
        System.out.println("Removing all users from the database and cache");
        employeeRepository.deleteById(employeeId);
    }

    public void fakeDelay(String message){
        for (int i = 0; i < 5; i++){
            System.out.println(message);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}