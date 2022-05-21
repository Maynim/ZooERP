package entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "storage", schema = "zootest4migrate", catalog = "")
public class StorageEntity {
    private int idstorage;
    private String desc;
    private String type;
    private Collection<FoodStorageEntity> foodStoragesByIdstorage;
    private Collection<MaterialStorageEntity> materialStoragesByIdstorage;
    private Collection<MedStorageEntity> medStoragesByIdstorage;
    private Collection<OtherStorageEntity> otherStoragesByIdstorage;

    @Id
    @Column(name = "idstorage", nullable = false)
    public int getIdstorage() {
        return idstorage;
    }

    public void setIdstorage(int idstorage) {
        this.idstorage = idstorage;
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
        StorageEntity that = (StorageEntity) o;
        return idstorage == that.idstorage &&
                Objects.equals(desc, that.desc) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idstorage, desc, type);
    }

    @OneToMany(mappedBy = "storageByStorageIdstorage")
    public Collection<FoodStorageEntity> getFoodStoragesByIdstorage() {
        return foodStoragesByIdstorage;
    }

    public void setFoodStoragesByIdstorage(Collection<FoodStorageEntity> foodStoragesByIdstorage) {
        this.foodStoragesByIdstorage = foodStoragesByIdstorage;
    }

    @OneToMany(mappedBy = "storageByStorageIdstorage")
    public Collection<MaterialStorageEntity> getMaterialStoragesByIdstorage() {
        return materialStoragesByIdstorage;
    }

    public void setMaterialStoragesByIdstorage(Collection<MaterialStorageEntity> materialStoragesByIdstorage) {
        this.materialStoragesByIdstorage = materialStoragesByIdstorage;
    }

    @OneToMany(mappedBy = "storageByStorageIdstorage")
    public Collection<MedStorageEntity> getMedStoragesByIdstorage() {
        return medStoragesByIdstorage;
    }

    public void setMedStoragesByIdstorage(Collection<MedStorageEntity> medStoragesByIdstorage) {
        this.medStoragesByIdstorage = medStoragesByIdstorage;
    }

    @OneToMany(mappedBy = "storageByStorageIdstorage")
    public Collection<OtherStorageEntity> getOtherStoragesByIdstorage() {
        return otherStoragesByIdstorage;
    }

    public void setOtherStoragesByIdstorage(Collection<OtherStorageEntity> otherStoragesByIdstorage) {
        this.otherStoragesByIdstorage = otherStoragesByIdstorage;
    }
}
