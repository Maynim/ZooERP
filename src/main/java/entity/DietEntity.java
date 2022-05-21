package entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "diet", schema = "zoodatabase", catalog = "")
public class DietEntity {
    private int iddiet;
    private String season;
    private String time;
    private String type;
    private String age;
    private String animalSubspecies;
    private AnimalEntity animalByAnimalSubspecies;
    private Collection<DietInfoEntity> dietInfosByIddiet;

    @Id
    @Column(name = "iddiet", nullable = false)
    public int getIddiet() {
        return iddiet;
    }

    public void setIddiet(int iddiet) {
        this.iddiet = iddiet;
    }

    @Basic
    @Column(name = "season", nullable = false, length = 45)
    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    @Basic
    @Column(name = "time", nullable = false, length = 45)
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
    @Column(name = "age", nullable = true, length = 45)
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Basic
    @Column(name = "animal_subspecies", nullable = false, length = 45)
    public String getAnimalSubspecies() {
        return animalSubspecies;
    }

    public void setAnimalSubspecies(String animalSubspecies) {
        this.animalSubspecies = animalSubspecies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DietEntity that = (DietEntity) o;
        return iddiet == that.iddiet &&
                Objects.equals(season, that.season) &&
                Objects.equals(time, that.time) &&
                Objects.equals(type, that.type) &&
                Objects.equals(age, that.age) &&
                Objects.equals(animalSubspecies, that.animalSubspecies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iddiet, season, time, type, age, animalSubspecies);
    }

    @ManyToOne
    @JoinColumn(name = "animal_subspecies", referencedColumnName = "subspecies", nullable = false, insertable = false, updatable = false)
    public AnimalEntity getAnimalByAnimalSubspecies() {
        return animalByAnimalSubspecies;
    }

    public void setAnimalByAnimalSubspecies(AnimalEntity animalByAnimalSubspecies) {
        this.animalByAnimalSubspecies = animalByAnimalSubspecies;
    }

    @OneToMany(mappedBy = "dietByDietIddiet")
    public Collection<DietInfoEntity> getDietInfosByIddiet() {
        return dietInfosByIddiet;
    }

    public void setDietInfosByIddiet(Collection<DietInfoEntity> dietInfosByIddiet) {
        this.dietInfosByIddiet = dietInfosByIddiet;
    }
}
