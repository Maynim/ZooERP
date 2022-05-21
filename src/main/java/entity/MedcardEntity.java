package entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "medcard", schema = "zootest4migrate", catalog = "")
public class MedcardEntity {
    private int idmedcard;
    private String health;
    private String description;
    private int infoAnimalIdinfoAnimal;
    private Collection<MedHistoryEntity> medHistoriesByIdmedcard;
    private InfoAnimalEntity infoAnimalByInfoAnimalIdinfoAnimal;

    @Id
    @Column(name = "idmedcard", nullable = false)
    public int getIdmedcard() {
        return idmedcard;
    }

    public void setIdmedcard(int idmedcard) {
        this.idmedcard = idmedcard;
    }

    @Basic
    @Column(name = "health", nullable = true, length = 45)
    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 45)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "info_animal_idinfo_animal", nullable = false)
    public int getInfoAnimalIdinfoAnimal() {
        return infoAnimalIdinfoAnimal;
    }

    public void setInfoAnimalIdinfoAnimal(int infoAnimalIdinfoAnimal) {
        this.infoAnimalIdinfoAnimal = infoAnimalIdinfoAnimal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedcardEntity that = (MedcardEntity) o;
        return idmedcard == that.idmedcard &&
                infoAnimalIdinfoAnimal == that.infoAnimalIdinfoAnimal &&
                Objects.equals(health, that.health) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idmedcard, health, description, infoAnimalIdinfoAnimal);
    }

    @OneToMany(mappedBy = "medcardByMedcardIdmedcard")
    public Collection<MedHistoryEntity> getMedHistoriesByIdmedcard() {
        return medHistoriesByIdmedcard;
    }

    public void setMedHistoriesByIdmedcard(Collection<MedHistoryEntity> medHistoriesByIdmedcard) {
        this.medHistoriesByIdmedcard = medHistoriesByIdmedcard;
    }

    @ManyToOne
    @JoinColumn(name = "info_animal_idinfo_animal", referencedColumnName = "idinfo_animal", nullable = false, insertable = false, updatable = false)
    public InfoAnimalEntity getInfoAnimalByInfoAnimalIdinfoAnimal() {
        return infoAnimalByInfoAnimalIdinfoAnimal;
    }

    public void setInfoAnimalByInfoAnimalIdinfoAnimal(InfoAnimalEntity infoAnimalByInfoAnimalIdinfoAnimal) {
        this.infoAnimalByInfoAnimalIdinfoAnimal = infoAnimalByInfoAnimalIdinfoAnimal;
    }
}
