package codingnomads.com.example.cache.demo.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Employee implements Serializable {

    private static final long serialVersionUID = 7002457751121028479L;

    @Id
    @GeneratedValue
    private int id;

    private String name;
}