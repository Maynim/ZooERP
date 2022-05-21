package entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "history_food", schema = "zootest4migrate", catalog = "")
public class HistoryFoodEntity {
    private int idhistoryFood;
    private int foodStorageIdfoodStorage;
    private String userUsername;
    private String desc;
    private Integer count;
    private Timestamp dateTime;
    private FoodStorageEntity foodStorageByFoodStorageIdfoodStorage;
    private UserEntity userByUserUsername;

    @Id
    @Column(name = "idhistory_food", nullable = false)
    public int getIdhistoryFood() {
        return idhistoryFood;
    }

    public void setIdhistoryFood(int idhistoryFood) {
        this.idhistoryFood = idhistoryFood;
    }

    @Basic
    @Column(name = "food_storage_idfood_storage", nullable = false)
    public int getFoodStorageIdfoodStorage() {
        return foodStorageIdfoodStorage;
    }

    public void setFoodStorageIdfoodStorage(int foodStorageIdfoodStorage) {
        this.foodStorageIdfoodStorage = foodStorageIdfoodStorage;
    }

    @Basic
    @Column(name = "desc", nullable = true, length = 80)
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
        HistoryFoodEntity that = (HistoryFoodEntity) o;
        return idhistoryFood == that.idhistoryFood &&
                foodStorageIdfoodStorage == that.foodStorageIdfoodStorage &&
                Objects.equals(userUsername, that.userUsername) &&
                Objects.equals(count, that.count) &&
                Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idhistoryFood, foodStorageIdfoodStorage, userUsername, count, dateTime);
    }

    @ManyToOne
    @JoinColumn(name = "food_storage_idfood_storage", referencedColumnName = "idfood_storage", nullable = false, insertable = false, updatable = false)
    public FoodStorageEntity getFoodStorageByFoodStorageIdfoodStorage() {
        return foodStorageByFoodStorageIdfoodStorage;
    }

    public void setFoodStorageByFoodStorageIdfoodStorage(FoodStorageEntity foodStorageByFoodStorageIdfoodStorage) {
        this.foodStorageByFoodStorageIdfoodStorage = foodStorageByFoodStorageIdfoodStorage;
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
