package entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "food_storage", schema = "zootest4migrate", catalog = "")
public class FoodStorageEntity {
    private int idfoodStorage;
    private double count;
    private String foodName;
    private int storageIdstorage;
    private String type;
    private StorageEntity storageByStorageIdstorage;
    private Collection<HistoryFoodEntity> historyFoodsByIdfoodStorage;

    @Id
    @Column(name = "idfood_storage", nullable = false)
    public int getIdfoodStorage() {
        return idfoodStorage;
    }

    public void setIdfoodStorage(int idfoodStorage) {
        this.idfoodStorage = idfoodStorage;
    }

    @Basic
    @Column(name = "count", nullable = true)
    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    @Basic
    @Column(name = "food_name", nullable = false, length = 45)
    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodStorageEntity that = (FoodStorageEntity) o;
        return idfoodStorage == that.idfoodStorage &&
                storageIdstorage == that.storageIdstorage &&
                Objects.equals(count, that.count) &&
                Objects.equals(foodName, that.foodName) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idfoodStorage, count, foodName, storageIdstorage, type);
    }

    @ManyToOne
    @JoinColumn(name = "storage_idstorage", referencedColumnName = "idstorage", nullable = false, insertable = false, updatable = false)
    public StorageEntity getStorageByStorageIdstorage() {
        return storageByStorageIdstorage;
    }

    public void setStorageByStorageIdstorage(StorageEntity storageByStorageIdstorage) {
        this.storageByStorageIdstorage = storageByStorageIdstorage;
    }

    @OneToMany(mappedBy = "foodStorageByFoodStorageIdfoodStorage")
    public Collection<HistoryFoodEntity> getHistoryFoodsByIdfoodStorage() {
        return historyFoodsByIdfoodStorage;
    }

    public void setHistoryFoodsByIdfoodStorage(Collection<HistoryFoodEntity> historyFoodsByIdfoodStorage) {
        this.historyFoodsByIdfoodStorage = historyFoodsByIdfoodStorage;
    }
}
