package codingnomads.com.example.cache.demo.repos;

import codingnomads.com.example.cache.demo.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
}