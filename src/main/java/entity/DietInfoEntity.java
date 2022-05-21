package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "diet_info", schema = "zootest4migrate", catalog = "")
public class DietInfoEntity {
    private int iddietInfo;
    private String food;
    private Integer count;
    private Double kcal;
    private int dietIddiet;
    private DietEntity dietByDietIddiet;

    @Id
    @Column(name = "iddiet_info", nullable = false)
    public int getIddietInfo() {
        return iddietInfo;
    }

    public void setIddietInfo(int iddietInfo) {
        this.iddietInfo = iddietInfo;
    }

    @Basic
    @Column(name = "food", nullable = true, length = 45)
    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
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
    @Column(name = "kcal", nullable = true, precision = 0)
    public Double getKcal() {
        return kcal;
    }

    public void setKcal(Double kcal) {
        this.kcal = kcal;
    }

    @Basic
    @Column(name = "diet_iddiet", nullable = false)
    public int getDietIddiet() {
        return dietIddiet;
    }

    public void setDietIddiet(int dietIddiet) {
        this.dietIddiet = dietIddiet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DietInfoEntity that = (DietInfoEntity) o;
        return iddietInfo == that.iddietInfo &&
                dietIddiet == that.dietIddiet &&
                Objects.equals(food, that.food) &&
                Objects.equals(count, that.count) &&
                Objects.equals(kcal, that.kcal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iddietInfo, food, count, kcal, dietIddiet);
    }

    @ManyToOne
    @JoinColumn(name = "diet_iddiet", referencedColumnName = "iddiet", nullable = false, insertable = false, updatable = false)
    public DietEntity getDietByDietIddiet() {
        return dietByDietIddiet;
    }

    public void setDietByDietIddiet(DietEntity dietByDietIddiet) {
        this.dietByDietIddiet = dietByDietIddiet;
    }
}
