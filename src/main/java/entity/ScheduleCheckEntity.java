package entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "schedule_check", schema = "zootest4migrate", catalog = "")
public class ScheduleCheckEntity {
    private int idScheduleCheck;
    private int totalTime;
    private Date today;
    private Date endTime;
    private Date startTime;
    private String userUsername;
    private UserEntity userByUserUsername;

    @Id
    @Column(name = "id_schedule_check", nullable = false)
    public int getIdScheduleCheck() {
        return idScheduleCheck;
    }

    public void setIdScheduleCheck(int idScheduleCheck) {
        this.idScheduleCheck = idScheduleCheck;
    }

    @Basic
    @Column(name = "total_time", nullable = true)
    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    @Basic
    @Column(name = "today", nullable = true)
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    @Basic
    @Column(name = "start_time", nullable = true)
    @CreationTimestamp
    @Temporal(TemporalType.TIME)
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "end_time", nullable = true)
    @CreationTimestamp
    @Temporal(TemporalType.TIME)
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

        ScheduleCheckEntity that = (ScheduleCheckEntity) o;

        if (idScheduleCheck != that.idScheduleCheck) return false;
        if (totalTime != that.totalTime) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (userUsername != null ? !userUsername.equals(that.userUsername) : that.userUsername != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idScheduleCheck;
        result = 31 * result + totalTime;
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (userUsername != null ? userUsername.hashCode() : 0);
        return result;
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
