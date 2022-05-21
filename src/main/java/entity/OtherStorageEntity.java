package entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "other_storage", schema = "zootest4migrate", catalog = "")
public class OtherStorageEntity {
    private int idotherStorage;
    private String name;
    private Integer count;
    private String type;
    private int storageIdstorage;
    private Collection<HistoryOtherEntity> historyOthersByIdotherStorage;
    private StorageEntity storageByStorageIdstorage;

    @Id
    @Column(name = "idother_storage", nullable = false)
    public int getIdotherStorage() {
        return idotherStorage;
    }

    public void setIdotherStorage(int idotherStorage) {
        this.idotherStorage = idotherStorage;
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
    @Column(name = "count", nullable = true)
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Basic
    @Column(name = "type", nullable = true, length = 45)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "storage_idstorage", nullable = false)
    public int getStorageIdstorage() {
        return storageIdstorage;
    }

    public void setStorageIdstorage(int storageIdstorage) {
        this.storageIdstorage = storageIdstorage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OtherStorageEntity that = (OtherStorageEntity) o;
        return idotherStorage == that.idotherStorage &&
                storageIdstorage == that.storageIdstorage &&
                Objects.equals(name, that.name) &&
                Objects.equals(count, that.count) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idotherStorage, name, count, type, storageIdstorage);
    }

    @OneToMany(mappedBy = "otherStorageByOtherStorageIdotherStorage")
    public Collection<HistoryOtherEntity> getHistoryOthersByIdotherStorage() {
        return historyOthersByIdotherStorage;
    }

    public void setHistoryOthersByIdotherStorage(Collection<HistoryOtherEntity> historyOthersByIdotherStorage) {
        this.historyOthersByIdotherStorage = historyOthersByIdotherStorage;
    }

    @ManyToOne
    @JoinColumn(name = "storage_idstorage", referencedColumnName = "idstorage", nullable = false, insertable = false, updatable = false)
    public StorageEntity getStorageByStorageIdstorage() {
        return storageByStorageIdstorage;
    }

    public void setStorageByStorageIdstorage(StorageEntity storageByStorageIdstorage) {
        this.storageByStorageIdstorage = storageByStorageIdstorage;
    }
}
