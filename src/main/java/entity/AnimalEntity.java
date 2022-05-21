package entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "animal", schema = "zoodatabase", catalog = "")
public class AnimalEntity {
    private String subspecies;
    private String species;
    private Integer count;
    private String breeding;
    private String disc;
    private int adult;
    private Collection<DietEntity> dietsBySubspecies;
    private Collection<InfoAnimalEntity> infoAnimalsBySubspecies;

    @Id
    @Column(name = "subspecies", nullable = false, length = 45)
    public String getSubspecies() {
        return subspecies;
    }

    public void setSubspecies(String subspecies) {
        this.subspecies = subspecies;
    }

    @Basic
    @Column(name = "species", nullable = true, length = 40)
    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
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
    @Column(name = "breeding", nullable = true, length = 45)
    public String getBreeding() {
        return breeding;
    }

    public void setBreeding(String breeding) {
        this.breeding = breeding;
    }

    @Basic
    @Column(name = "disc", nullable = true, length = 50)
    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    @Basic
    @Column(name = "adult", nullable = false)
    public int getAdult() {
        return adult;
    }

    public void setAdult(int adult) {
        this.adult = adult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalEntity that = (AnimalEntity) o;
        return adult == that.adult &&
                Objects.equals(subspecies, that.subspecies) &&
                Objects.equals(species, that.species) &&
                Objects.equals(count, that.count) &&
                Objects.equals(breeding, that.breeding) &&
                Objects.equals(disc, that.disc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subspecies, species, count, breeding, disc, adult);
    }

    @OneToMany(mappedBy = "animalByAnimalSubspecies")
    public Collection<DietEntity> getDietsBySubspecies() {
        return dietsBySubspecies;
    }

    public void setDietsBySubspecies(Collection<DietEntity> dietsBySubspecies) {
        this.dietsBySubspecies = dietsBySubspecies;
    }

    @OneToMany(mappedBy = "animalByAnimalSubspecies")
    public Collection<InfoAnimalEntity> getInfoAnimalsBySubspecies() {
        return infoAnimalsBySubspecies;
    }

    public void setInfoAnimalsBySubspecies(Collection<InfoAnimalEntity> infoAnimalsBySubspecies) {
        this.infoAnimalsBySubspecies = infoAnimalsBySubspecies;
    }
}
