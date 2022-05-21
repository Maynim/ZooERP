package entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "transaction", schema = "zootest4migrate", catalog = "")
@IdClass(TransactionEntityPK.class)

public class TransactionEntity {
    private int idtransaction;
    private int budgetIdbudget;
    private String userUsername;
    private Double summ;
    private Date datetime;
    private Double summBefore;
    private String descrip;
    private BudgetEntity budgetByBudgetIdbudget;
    private UserEntity userByUserUsername;

    @Id
    @Column(name = "idtransaction", nullable = false)
    public int getIdtransaction() {
        return idtransaction;
    }

    public void setIdtransaction(int idtransaction) {
        this.idtransaction = idtransaction;
    }

    @Id
    @Column(name = "budget_idbudget", nullable = false)
    public int getBudgetIdbudget() {
        return budgetIdbudget;
    }

    public void setBudgetIdbudget(int budgetIdbudget) {
        this.budgetIdbudget = budgetIdbudget;
    }

    @Id
    @Column(name = "user_username", nullable = false, length = 16)
    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    @Basic
    @Column(name = "summ", nullable = true, precision = 0)
    public Double getSumm() {
        return summ;
    }

    public void setSumm(Double summ) {
        this.summ = summ;
    }

    @Basic
    @Column(name = "datetime", nullable = true)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    @Basic
    @Column(name = "summ_before", nullable = true, precision = 0)
    public Double getSummBefore() {
        return summBefore;
    }

    public void setSummBefore(Double summBefore) {
        this.summBefore = summBefore;
    }

    @Basic
    @Column(name = "descrip", nullable = true, length = 60)
    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionEntity that = (TransactionEntity) o;
        return idtransaction == that.idtransaction &&
                budgetIdbudget == that.budgetIdbudget &&
                Objects.equals(userUsername, that.userUsername) &&
                Objects.equals(summ, that.summ) &&
                Objects.equals(datetime, that.datetime) &&
                Objects.equals(summBefore, that.summBefore) &&
                Objects.equals(descrip, that.descrip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idtransaction, budgetIdbudget, userUsername, summ, datetime, summBefore, descrip);
    }

    @ManyToOne
    @JoinColumn(name = "budget_idbudget", referencedColumnName = "idbudget", nullable = false, insertable = false, updatable = false)
    public BudgetEntity getBudgetByBudgetIdbudget() {
        return budgetByBudgetIdbudget;
    }

    public void setBudgetByBudgetIdbudget(BudgetEntity budgetByBudgetIdbudget) {
        this.budgetByBudgetIdbudget = budgetByBudgetIdbudget;
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
