package entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "med_history", schema = "zootest4migrate", catalog = "")
@IdClass(MedHistoryEntityPK.class)
public class MedHistoryEntity {
    private int idmedHistory;
    private Date date;
    private String medicament;
    private String description;
    private String count;
    private int medcardIdmedcard;
    private MedcardEntity medcardByMedcardIdmedcard;

    @Id
    @Column(name = "idmed_history", nullable = false)
    public int getIdmedHistory() {
        return idmedHistory;
    }

    public void setIdmedHistory(int idmedHistory) {
        this.idmedHistory = idmedHistory;
    }

    @Basic
    @Column(name = "date", nullable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "medicament", nullable = false, length = 45)
    public String getMedicament() {
        return medicament;
    }

    public void setMedicament(String medicament) {
        this.medicament = medicament;
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
    @Column(name = "count", nullable = false, length = 45)
    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Id
    @Column(name = "medcard_idmedcard", nullable = false)
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
        MedHistoryEntity that = (MedHistoryEntity) o;
        return idmedHistory == that.idmedHistory &&
                medcardIdmedcard == that.medcardIdmedcard &&
                Objects.equals(date, that.date) &&
                Objects.equals(medicament, that.medicament) &&
                Objects.equals(description, that.description) &&
                Objects.equals(count, that.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idmedHistory, date, medicament, description, count, medcardIdmedcard);
    }

    @ManyToOne
    @JoinColumn(name = "medcard_idmedcard", referencedColumnName = "idmedcard", nullable = false, insertable = false, updatable = false)
    public MedcardEntity getMedcardByMedcardIdmedcard() {
        return medcardByMedcardIdmedcard;
    }

    public void setMedcardByMedcardIdmedcard(MedcardEntity medcardByMedcardIdmedcard) {
        this.medcardByMedcardIdmedcard = medcardByMedcardIdmedcard;
    }
}
