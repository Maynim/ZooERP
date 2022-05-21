package entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "med_storage", schema = "zootest4migrate", catalog = "")
public class MedStorageEntity {
    private int idmedStorage;
    private String medicineName;
    private int storageIdstorage;
    private Integer count;
    private String desc;
    private String type;
    private Collection<HistoryMedEntity> historyMedsByIdmedStorage;
    private StorageEntity storageByStorageIdstorage;

    @Id
    @Column(name = "idmed_storage", nullable = false)
    public int getIdmedStorage() {
        return idmedStorage;
    }

    public void setIdmedStorage(int idmedStorage) {
        this.idmedStorage = idmedStorage;
    }

    @Basic
    @Column(name = "medicine_name", nullable = false, length = 45)
    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    @Basic
    @Column(name = "storage_idstorage", nullable = false)
    public int getStorageIdstorage() {
        return storageIdstorage;
    }

    public void setStorageIdstorage(int storageIdstorage) {
        this.storageIdstorage = storageIdstorage;
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
    @Column(name = "desc", nullable = true, length = 45)
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Basic
    @Column(name = "type", nullable = true, length = 45)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedStorageEntity that = (MedStorageEntity) o;
        return idmedStorage == that.idmedStorage &&
                storageIdstorage == that.storageIdstorage &&
                Objects.equals(medicineName, that.medicineName) &&
                Objects.equals(count, that.count) &&
                Objects.equals(desc, that.desc) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idmedStorage, medicineName, storageIdstorage, count, desc, type);
    }

    @OneToMany(mappedBy = "medStorageByMedStorageIdmedStorage")
    public Collection<HistoryMedEntity> getHistoryMedsByIdmedStorage() {
        return historyMedsByIdmedStorage;
    }

    public void setHistoryMedsByIdmedStorage(Collection<HistoryMedEntity> historyMedsByIdmedStorage) {
        this.historyMedsByIdmedStorage = historyMedsByIdmedStorage;
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
