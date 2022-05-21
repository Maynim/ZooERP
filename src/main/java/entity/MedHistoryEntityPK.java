package entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class MedHistoryEntityPK implements Serializable {
    private int idmedHistory;
    private int medcardIdmedcard;

    @Column(name = "idmed_history", nullable = false)
    @Id
    public int getIdmedHistory() {
        return idmedHistory;
    }

    public void setIdmedHistory(int idmedHistory) {
        this.idmedHistory = idmedHistory;
    }

    @Column(name = "medcard_idmedcard", nullable = false)
    @Id
    public int getMedcardIdmedcard() {
        return medcardIdmedcard;
    }

    public void setMedcardIdmedcard(int medcardIdmedcard) {
        this.medcardIdmedcard = medcardIdmedcard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedHistoryEntityPK that = (MedHistoryEntityPK) o;
        return idmedHistory == that.idmedHistory &&
                medcardIdmedcard == that.medcardIdmedcard;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idmedHistory, medcardIdmedcard);
    }
}
