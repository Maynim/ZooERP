package entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "shedule", schema = "zootest4migrate", catalog = "")
public class SheduleEntity {
    private int idshedule;
    private Date date;
    private String status;
    private Time start;
    private Time end;
    private String userUsername;
    private UserEntity userByUserUsername;

    @Id
    @Column(name = "idshedule", nullable = false)
    public int getIdshedule() {
        return idshedule;
    }

    public void setIdshedule(int idshedule) {
        this.idshedule = idshedule;
    }

    @Basic
    @Column(name = "date", nullable = true)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "status", nullable = false, length = 12)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "start", nullable = true)
    public Time getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    @Basic
    @Column(name = "end", nullable = true)
    public Time getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        this.end = end;
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
        SheduleEntity that = (SheduleEntity) o;
        return idshedule == that.idshedule &&
                Objects.equals(date, that.date) &&
                Objects.equals(status, that.status) &&
                Objects.equals(start, that.start) &&
                Objects.equals(end, that.end) &&
                Objects.equals(userUsername, that.userUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idshedule, date, status, start, end, userUsername);
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
