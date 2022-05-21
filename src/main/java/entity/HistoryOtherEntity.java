package entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "history_other", schema = "zootest4migrate", catalog = "")
public class HistoryOtherEntity {
    private int idhistoryOther;
    private String userUsername;
    private int otherStorageIdotherStorage;
    private Integer count;
    private Timestamp dateTime;
    private UserEntity userByUserUsername;
    private OtherStorageEntity otherStorageByOtherStorageIdotherStorage;

    @Id
    @Column(name = "idhistory_other", nullable = false)
    public int getIdhistoryOther() {
        return idhistoryOther;
    }

    public void setIdhistoryOther(int idhistoryOther) {
        this.idhistoryOther = idhistoryOther;
    }

    @Basic
    @Column(name = "user_username", nullable = false, length = 16)
    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    @Basic
    @Column(name = "other_storage_idother_storage", nullable = false)
    public int getOtherStorageIdotherStorage() {
        return otherStorageIdotherStorage;
    }

    public void setOtherStorageIdotherStorage(int otherStorageIdotherStorage) {
        this.otherStorageIdotherStorage = otherStorageIdotherStorage;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryOtherEntity that = (HistoryOtherEntity) o;
        return idhistoryOther == that.idhistoryOther &&
                otherStorageIdotherStorage == that.otherStorageIdotherStorage &&
                Objects.equals(userUsername, that.userUsername) &&
                Objects.equals(count, that.count) &&
                Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idhistoryOther, userUsername, otherStorageIdotherStorage, count, dateTime);
    }

    @ManyToOne
    @JoinColumn(name = "user_username", referencedColumnName = "username", nullable = false, insertable = false, updatable = false)
    public UserEntity getUserByUserUsername() {
        return userByUserUsername;
    }

    public void setUserByUserUsername(UserEntity userByUserUsername) {
        this.userByUserUsername = userByUserUsername;
    }

    @ManyToOne
    @JoinColumn(name = "other_storage_idother_storage", referencedColumnName = "idother_storage", nullable = false, insertable = false, updatable = false)
    public OtherStorageEntity getOtherStorageByOtherStorageIdotherStorage() {
        return otherStorageByOtherStorageIdotherStorage;
    }

    public void setOtherStorageByOtherStorageIdotherStorage(OtherStorageEntity otherStorageByOtherStorageIdotherStorage) {
        this.otherStorageByOtherStorageIdotherStorage = otherStorageByOtherStorageIdotherStorage;
    }
}
