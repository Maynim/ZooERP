package entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "job", schema = "zootest4migrate", catalog = "")
public class JobEntity {
    private String name;
    private Double salary;
    private String description;
    private int accessIdaccess;
    private AccessEntity accessByAccessIdaccess;
    private Collection<UserEntity> usersByName;

    @Id
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "salary", nullable = true, precision = 0)
    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 80)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "access_idaccess", nullable = false)
    public int getAccessIdaccess() {
        return accessIdaccess;
    }

    public void setAccessIdaccess(int accessIdaccess) {
        this.accessIdaccess = accessIdaccess;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobEntity jobEntity = (JobEntity) o;
        return accessIdaccess == jobEntity.accessIdaccess &&
                Objects.equals(name, jobEntity.name) &&
                Objects.equals(salary, jobEntity.salary) &&
                Objects.equals(description, jobEntity.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, salary, description, accessIdaccess);
    }

    @ManyToOne
    @JoinColumn(name = "access_idaccess", referencedColumnName = "idaccess", nullable = false, insertable = false, updatable = false)
    public AccessEntity getAccessByAccessIdaccess() {
        return accessByAccessIdaccess;
    }

    public void setAccessByAccessIdaccess(AccessEntity accessByAccessIdaccess) {
        this.accessByAccessIdaccess = accessByAccessIdaccess;
    }

    @OneToMany(mappedBy = "jobByJobName")
    public Collection<UserEntity> getUsersByName() {
        return usersByName;
    }

    public void setUsersByName(Collection<UserEntity> usersByName) {
        this.usersByName = usersByName;
    }
}
