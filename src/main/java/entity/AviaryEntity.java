package entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "aviary", schema = "zootest4migrate", catalog = "")
public class AviaryEntity {
    private int idaviary;
    private double length;
    private double width;
    private double height;
    private int countAnimals;
    private String description;
    private Collection<InfoAnimalEntity> infoAnimalsByIdaviary;

    @Id
    @Column(name = "idaviary", nullable = false)
    public int getIdaviary() {
        return idaviary;
    }

    public void setIdaviary(int idaviary) {
        this.idaviary = idaviary;
    }

    @Basic
    @Column(name = "length", nullable = false, precision = 0)
    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @Basic
    @Column(name = "width", nullable = false, precision = 0)
    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Basic
    @Column(name = "count_animals", nullable = false)
    public int getCountAnimals() {
        return countAnimals;
    }

    public void setCountAnimals(int countAnimals) {
        this.countAnimals = countAnimals;
    }

    @Basic
    @Column(name = "height", nullable = false, precision = 0)
    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 45)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AviaryEntity that = (AviaryEntity) o;
        return idaviary == that.idaviary &&
                Double.compare(that.length, length) == 0 &&
                Double.compare(that.width, width) == 0 &&
                Double.compare(that.height, height) == 0 &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idaviary, length, width, height, description);
    }


    @OneToMany(mappedBy = "aviaryByAviaryIdaviary")
    public Collection<InfoAnimalEntity> getInfoAnimalsByIdaviary() {
        return infoAnimalsByIdaviary;
    }

    public void setInfoAnimalsByIdaviary(Collection<InfoAnimalEntity> infoAnimalsByIdaviary) {
        this.infoAnimalsByIdaviary = infoAnimalsByIdaviary;
    }
}
