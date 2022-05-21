package entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "history_med", schema = "zootest4migrate", catalog = "")
public class HistoryMedEntity {
    private int idmedHistory;
    private int medStorageIdmedStorage;
    private Integer count;
    private Timestamp dateTime;
    private String userUsername;
    private MedStorageEntity medStorageByMedStorageIdmedStorage;
    private UserEntity userByUserUsername;

    @Id
    @Column(name = "idmed_history", nullable = false)
    public int getIdmedHistory() {
        return idmedHistory;
    }

    public void setIdmedHistory(int idmedHistory) {
        this.idmedHistory = idmedHistory;
    }

    @Basic
    @Column(name = "med_storage_idmed_storage", nullable = false)
    public int getMedStorageIdmedStorage() {
        return medStorageIdmedStorage;
    }

    public void setMedStorageIdmedStorage(int medStorageIdmedStorage) {
        this.medStorageIdmedStorage = medStorageIdmedStorage;
    }

    @Basic
    @Column(name = "count", nullable = true)
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Basic
    @Column(name = "date_time", nullable = true)
    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
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
        HistoryMedEntity that = (HistoryMedEntity) o;
        return idmedHistory == that.idmedHistory &&
                medStorageIdmedStorage == that.medStorageIdmedStorage &&
                Objects.equals(count, that.count) &&
                Objects.equals(dateTime, that.dateTime) &&
                Objects.equals(userUsername, that.userUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idmedHistory, medStorageIdmedStorage, count, dateTime, userUsername);
    }

    @ManyToOne
    @JoinColumn(name = "med_storage_idmed_storage", referencedColumnName = "idmed_storage", nullable = false, insertable = false, updatable = false)
    public MedStorageEntity getMedStorageByMedStorageIdmedStorage() {
        return medStorageByMedStorageIdmedStorage;
    }

    public void setMedStorageByMedStorageIdmedStorage(MedStorageEntity medStorageByMedStorageIdmedStorage) {
        this.medStorageByMedStorageIdmedStorage = medStorageByMedStorageIdmedStorage;
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
