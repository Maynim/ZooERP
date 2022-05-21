package entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TransactionEntityPK implements Serializable {
    private int idtransaction;
    private int budgetIdbudget;
    private String userUsername;

    @Column(name = "idtransaction", nullable = false)
    @Id
    public int getIdtransaction() {
        return idtransaction;
    }

    public void setIdtransaction(int idtransaction) {
        this.idtransaction = idtransaction;
    }

    @Column(name = "budget_idbudget", nullable = false)
    @Id
    public int getBudgetIdbudget() {
        return budgetIdbudget;
    }

    public void setBudgetIdbudget(int budgetIdbudget) {
        this.budgetIdbudget = budgetIdbudget;
    }

    @Column(name = "user_username", nullable = false, length = 16)
    @Id
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
        TransactionEntityPK that = (TransactionEntityPK) o;
        return idtransaction == that.idtransaction &&
                budgetIdbudget == that.budgetIdbudget &&
                Objects.equals(userUsername, that.userUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idtransaction, budgetIdbudget, userUsername);
    }
}
