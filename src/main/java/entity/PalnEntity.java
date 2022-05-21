package entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "paln", schema = "zootest4migrate", catalog = "")
public class PalnEntity {
    private int idpaln;
    private Date startDate;
    private String name;
    private String description;
    private String userUsername;
    private Date endDate;
    private double resource;
    private String status;
    private UserEntity userByUserUsername;

    @Id
    @Column(name = "idpaln", nullable = false)
    public int getIdpaln() {
        return idpaln;
    }

    public void setIdpaln(int idpaln) {
        this.idpaln = idpaln;
    }

    @Basic
    @Column(name = "start_date", nullable = false)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 90)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 140)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "user_username", nullable = false, length = 16)
    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    @Basic
    @Column(name = "end_date", nullable = false)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "resource", nullable = false, precision = 0)
    public double getResource() {
        return resource;
    }

    public void setResource(double resource) {
        this.resource = resource;
    }

    @Basic
    @Column(name = "status", nullable = false, length = 20)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PalnEntity that = (PalnEntity) o;
        return idpaln == that.idpaln &&
                Double.compare(that.resource, resource) == 0 &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(userUsername, that.userUsername) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idpaln, startDate, name, description, userUsername, endDate, resource, status);
    }

    @ManyToOne
    @JoinColumn(name = "user_username", referencedColumnName = "username", nullable = false, insertable = false, updatable = false)
    public UserEntity getUserByUserUsername() {
        return userByUserUsername;
    }

    public void setUserByUserUsername(UserEntity userByUserUsername) {
        this.userByUserUsername = userByUserUsername;
    }
}
