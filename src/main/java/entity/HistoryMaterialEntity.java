package entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "history_material", schema = "zootest4migrate", catalog = "")
public class HistoryMaterialEntity {
    private int idhistoryMaterial;
    private int materialStorageIdmaterialStorage;
    private String userUsername;
    private Integer count;
    private Timestamp dateTime;
    private MaterialStorageEntity materialStorageByMaterialStorageIdmaterialStorage;
    private UserEntity userByUserUsername;

    @Id
    @Column(name = "idhistory_material", nullable = false)
    public int getIdhistoryMaterial() {
        return idhistoryMaterial;
    }

    public void setIdhistoryMaterial(int idhistoryMaterial) {
        this.idhistoryMaterial = idhistoryMaterial;
    }

    @Basic
    @Column(name = "material_storage_idmaterial_storage", nullable = false)
    public int getMaterialStorageIdmaterialStorage() {
        return materialStorageIdmaterialStorage;
    }

    public void setMaterialStorageIdmaterialStorage(int materialStorageIdmaterialStorage) {
        this.materialStorageIdmaterialStorage = materialStorageIdmaterialStorage;
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
        HistoryMaterialEntity that = (HistoryMaterialEntity) o;
        return idhistoryMaterial == that.idhistoryMaterial &&
                materialStorageIdmaterialStorage == that.materialStorageIdmaterialStorage &&
                Objects.equals(userUsername, that.userUsername) &&
                Objects.equals(count, that.count) &&
                Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idhistoryMaterial, materialStorageIdmaterialStorage, userUsername, count, dateTime);
    }

    @ManyToOne
    @JoinColumn(name = "material_storage_idmaterial_storage", referencedColumnName = "idmaterial_storage", nullable = false, insertable = false, updatable = false)
    public MaterialStorageEntity getMaterialStorageByMaterialStorageIdmaterialStorage() {
        return materialStorageByMaterialStorageIdmaterialStorage;
    }

    public void setMaterialStorageByMaterialStorageIdmaterialStorage(MaterialStorageEntity materialStorageByMaterialStorageIdmaterialStorage) {
        this.materialStorageByMaterialStorageIdmaterialStorage = materialStorageByMaterialStorageIdmaterialStorage;
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
