package entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "zootest4migrate", catalog = "")
public class UserEntity {
    private String username;
    private String password;
    private Timestamp createTime;
    private String firstName;
    private String secondName;
    private String middleName;
    private Date birthday;
    private String  gender;
    private String phoneNumber;
    private String jobName;
    private double moneyEarned;
    private Date lastPayout;
    private Collection<HistoryFoodEntity> historyFoodsByUsername;
    private Collection<HistoryMaterialEntity> historyMaterialsByUsername;
    private Collection<HistoryMedEntity> historyMedsByUsername;
    private Collection<HistoryOtherEntity> historyOthersByUsername;
    private Collection<PalnEntity> palnsByUsername;
    private Collection<PersPlanEntity> persPlansByUsername;
    private Collection<SheduleEntity> shedulesByUsername;
    private Collection<TransactionEntity> transactionsByUsername;
    private JobEntity jobByJobName;
    private Collection<ScheduleCheckEntity> scheduleChecksByUsername;

    @Id
    @Column(name = "username", nullable = false, length = 16)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 32)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "create_time", nullable = false)
    @Generated(GenerationTime.ALWAYS)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }


    @Basic
    @Column(name = "first_name", nullable = false, length = 45)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "second_name", nullable = false, length = 45)
    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Basic
    @Column(name = "middle_name", nullable = true, length = 45)
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Basic
    @Column(name = "birthday", nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "last_payout", nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    public Date getLastPayout() {
        return lastPayout;
    }

    public void setLastPayout(Date lastPayout) {
        this.lastPayout = lastPayout;
    }

    @Basic
    @Column(name = "gender", nullable = false, length = 10)
    public String getGender() {
        return gender;
    }

    public void setGender(String  gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "phone_number", nullable = false, length = 45)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "money_earned", nullable = false)
    public double getMoneyEarned() {
        return moneyEarned;
    }

    public void setMoneyEarned(double moneyEarned) {
        this.moneyEarned = moneyEarned;
    }


    @Basic
    @Column(name = "job_name", nullable = false, length = 45)
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return gender == that.gender &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(secondName, that.secondName) &&
                Objects.equals(middleName, that.middleName) &&
                Objects.equals(birthday, that.birthday) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(jobName, that.jobName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, createTime, firstName, secondName, middleName, birthday, gender, phoneNumber, jobName);
    }

    @OneToMany(mappedBy = "userByUserUsername")
    public Collection<HistoryFoodEntity> getHistoryFoodsByUsername() {
        return historyFoodsByUsername;
    }

    public void setHistoryFoodsByUsername(Collection<HistoryFoodEntity> historyFoodsByUsername) {
        this.historyFoodsByUsername = historyFoodsByUsername;
    }

    @OneToMany(mappedBy = "userByUserUsername")
    public Collection<HistoryMaterialEntity> getHistoryMaterialsByUsername() {
        return historyMaterialsByUsername;
    }

    public void setHistoryMaterialsByUsername(Collection<HistoryMaterialEntity> historyMaterialsByUsername) {
        this.historyMaterialsByUsername = historyMaterialsByUsername;
    }

    @OneToMany(mappedBy = "userByUserUsername")
    public Collection<HistoryMedEntity> getHistoryMedsByUsername() {
        return historyMedsByUsername;
    }

    public void setHistoryMedsByUsername(Collection<HistoryMedEntity> historyMedsByUsername) {
        this.historyMedsByUsername = historyMedsByUsername;
    }

    @OneToMany(mappedBy = "userByUserUsername")
    public Collection<HistoryOtherEntity> getHistoryOthersByUsername() {
        return historyOthersByUsername;
    }

    public void setHistoryOthersByUsername(Collection<HistoryOtherEntity> historyOthersByUsername) {
        this.historyOthersByUsername = historyOthersByUsername;
    }

    @OneToMany(mappedBy = "userByUserUsername")
    public Collection<PalnEntity> getPalnsByUsername() {
        return palnsByUsername;
    }

    public void setPalnsByUsername(Collection<PalnEntity> palnsByUsername) {
        this.palnsByUsername = palnsByUsername;
    }

    @OneToMany(mappedBy = "userByUserUsername")
    public Collection<PersPlanEntity> getPersPlansByUsername() {
        return persPlansByUsername;
    }

    public void setPersPlansByUsername(Collection<PersPlanEntity> persPlansByUsername) {
        this.persPlansByUsername = persPlansByUsername;
    }

    @OneToMany(mappedBy = "userByUserUsername")
    public Collection<SheduleEntity> getShedulesByUsername() {
        return shedulesByUsername;
    }

    public void setShedulesByUsername(Collection<SheduleEntity> shedulesByUsername) {
        this.shedulesByUsername = shedulesByUsername;
    }

    @OneToMany(mappedBy = "userByUserUsername")
    public Collection<TransactionEntity> getTransactionsByUsername() {
        return transactionsByUsername;
    }

    public void setTransactionsByUsername(Collection<TransactionEntity> transactionsByUsername) {
        this.transactionsByUsername = transactionsByUsername;
    }

    @ManyToOne
    @JoinColumn(name = "job_name", referencedColumnName = "name", nullable = false, insertable = false, updatable = false)
    public JobEntity getJobByJobName() {
        return jobByJobName;
    }

    public void setJobByJobName(JobEntity jobByJobName) {
        this.jobByJobName = jobByJobName;
    }

    @OneToMany(mappedBy = "userByUserUsername")
    public Collection<ScheduleCheckEntity> getScheduleChecksByUsername() {
        return scheduleChecksByUsername;
    }

    public void setScheduleChecksByUsername(Collection<ScheduleCheckEntity> scheduleChecksByUsername) {
        this.scheduleChecksByUsername = scheduleChecksByUsername;
    }
}
