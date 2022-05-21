package entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "pers_plan", schema = "zootest4migrate", catalog = "")
public class PersPlanEntity {
    private int idpaln;
    private Date date;
    private String name;
    private String description;
    private Date endDate;
    private String status;
    private String userUsername;
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
    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
    @Column(name = "end_date", nullable = false)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "status", nullable = false, length = 20)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "user_username", nullable = false, length = 16)
    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersPlanEntity that = (PersPlanEntity) o;
        return idpaln == that.idpaln &&
                Objects.equals(date, that.date) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(status, that.status) &&
                Objects.equals(userUsername, that.userUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idpaln, date, name, description, endDate, status, userUsername);
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
