package clientManager.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Agustin Perez Garcia
 *
 */
@Entity
@Table
public class Client {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @ApiModelProperty(hidden = true)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private Date birthDate;

    public Client (){
    }

    public Client (String name, String surname, Integer age, Date birthDate){
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.birthDate = birthDate;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}