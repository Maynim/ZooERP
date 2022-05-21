package entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "material_storage", schema = "zootest4migrate", catalog = "")
public class MaterialStorageEntity {
    private int idmaterialStorage;
    private String materialName;
    private int storageIdstorage;
    private String type;
    private Integer count;
    private Collection<HistoryMaterialEntity> historyMaterialsByIdmaterialStorage;
    private StorageEntity storageByStorageIdstorage;

    @Id
    @Column(name = "idmaterial_storage", nullable = false)
    public int getIdmaterialStorage() {
        return idmaterialStorage;
    }

    public void setIdmaterialStorage(int idmaterialStorage) {
        this.idmaterialStorage = idmaterialStorage;
    }

    @Basic
    @Column(name = "material_name", nullable = false, length = 45)
    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
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
    @Column(name = "type", nullable = true, length = 45)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "count", nullable = true)
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaterialStorageEntity that = (MaterialStorageEntity) o;
        return idmaterialStorage == that.idmaterialStorage &&
                storageIdstorage == that.storageIdstorage &&
                Objects.equals(materialName, that.materialName) &&
                Objects.equals(type, that.type) &&
                Objects.equals(count, that.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idmaterialStorage, materialName, storageIdstorage, type, count);
    }

    @OneToMany(mappedBy = "materialStorageByMaterialStorageIdmaterialStorage")
    public Collection<HistoryMaterialEntity> getHistoryMaterialsByIdmaterialStorage() {
        return historyMaterialsByIdmaterialStorage;
    }

    public void setHistoryMaterialsByIdmaterialStorage(Collection<HistoryMaterialEntity> historyMaterialsByIdmaterialStorage) {
        this.historyMaterialsByIdmaterialStorage = historyMaterialsByIdmaterialStorage;
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
