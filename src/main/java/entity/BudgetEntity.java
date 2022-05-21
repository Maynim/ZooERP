package entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "budget", schema = "zootest4migrate", catalog = "")
public class BudgetEntity {
    private int idbudget;
    private String name;
    private String description;
    private double balance;
    private Collection<TransactionEntity> transactionsByIdbudget;

    @Id
    @Column(name = "idbudget", nullable = false)
    public int getIdbudget() {
        return idbudget;
    }

    public void setIdbudget(int idbudget) {
        this.idbudget = idbudget;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 45)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "balance", nullable = false, precision = 0)
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetEntity that = (BudgetEntity) o;
        return idbudget == that.idbudget &&
                Double.compare(that.balance, balance) == 0 &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idbudget, name, description, balance);
    }

    @OneToMany(mappedBy = "budgetByBudgetIdbudget")
    public Collection<TransactionEntity> getTransactionsByIdbudget() {
        return transactionsByIdbudget;
    }

    public void setTransactionsByIdbudget(Collection<TransactionEntity> transactionsByIdbudget) {
        this.transactionsByIdbudget = transactionsByIdbudget;
    }
}
