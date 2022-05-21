package entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "info_animal", schema = "zoodatabase", catalog = "")
public class InfoAnimalEntity {
    private int idinfoAnimal;
    private String animalSubspecies;
    private String name;
    private Date birthdayDate;
    private Time birthdayTime;
    private Integer idfather;
    private Integer idmother;
    private int aviaryIdaviary;
    private String gender;
    private AnimalEntity animalByAnimalSubspecies;
    private AviaryEntity aviaryByAviaryIdaviary;
    private Collection<MedcardEntity> medcardsByIdinfoAnimal;

    @Id
    @Column(name = "idinfo_animal", nullable = false)
    public int getIdinfoAnimal() {
        return idinfoAnimal;
    }

    public void setIdinfoAnimal(int idinfoAnimal) {
        this.idinfoAnimal = idinfoAnimal;
    }

    @Basic
    @Column(name = "animal_subspecies", nullable = false, length = 45)
    public String getAnimalSubspecies() {
        return animalSubspecies;
    }

    public void setAnimalSubspecies(String animalSubspecies) {
        this.animalSubspecies = animalSubspecies;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "birthday_date", nullable = false)
    public Date getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(Date birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    @Basic
    @Column(name = "birthday_time", nullable = true)
    public Time getBirthdayTime() {
        return birthdayTime;
    }

    public void setBirthdayTime(Time birthdayTime) {
        this.birthdayTime = birthdayTime;
    }

    @Basic
    @Column(name = "idfather", nullable = true)
    public Integer getIdfather() {
        return idfather;
    }

    public void setIdfather(Integer idfather) {
        this.idfather = idfather;
    }

    @Basic
    @Column(name = "idmother", nullable = true)
    public Integer getIdmother() {
        return idmother;
    }

    public void setIdmother(Integer idmother) {
        this.idmother = idmother;
    }

    @Basic
    @Column(name = "aviary_idaviary", nullable = false)
    public int getAviaryIdaviary() {
        return aviaryIdaviary;
    }

    public void setAviaryIdaviary(int aviaryIdaviary) {
        this.aviaryIdaviary = aviaryIdaviary;
    }

    @Basic
    @Column(name = "gender", nullable = true, length = 10)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfoAnimalEntity that = (InfoAnimalEntity) o;
        return idinfoAnimal == that.idinfoAnimal &&
                aviaryIdaviary == that.aviaryIdaviary &&
                Objects.equals(animalSubspecies, that.animalSubspecies) &&
                Objects.equals(name, that.name) &&
                Objects.equals(birthdayDate, that.birthdayDate) &&
                Objects.equals(birthdayTime, that.birthdayTime) &&
                Objects.equals(idfather, that.idfather) &&
                Objects.equals(idmother, that.idmother) &&
                Objects.equals(gender, that.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idinfoAnimal, animalSubspecies, name, birthdayDate, birthdayTime, idfather, idmother, aviaryIdaviary, gender);
    }

    @ManyToOne
    @JoinColumn(name = "animal_subspecies", referencedColumnName = "subspecies", nullable = false, insertable = false, updatable = false)
    public AnimalEntity getAnimalByAnimalSubspecies() {
        return animalByAnimalSubspecies;
    }

    public void setAnimalByAnimalSubspecies(AnimalEntity animalByAnimalSubspecies) {
        this.animalByAnimalSubspecies = animalByAnimalSubspecies;
    }

    @ManyToOne
    @JoinColumn(name = "aviary_idaviary", referencedColumnName = "idaviary", nullable = false, insertable = false, updatable = false)
    public AviaryEntity getAviaryByAviaryIdaviary() {
        return aviaryByAviaryIdaviary;
    }

    public void setAviaryByAviaryIdaviary(AviaryEntity aviaryByAviaryIdaviary) {
        this.aviaryByAviaryIdaviary = aviaryByAviaryIdaviary;
    }

    @OneToMany(mappedBy = "infoAnimalByInfoAnimalIdinfoAnimal")
    public Collection<MedcardEntity> getMedcardsByIdinfoAnimal() {
        return medcardsByIdinfoAnimal;
    }

    public void setMedcardsByIdinfoAnimal(Collection<MedcardEntity> medcardsByIdinfoAnimal) {
        this.medcardsByIdinfoAnimal = medcardsByIdinfoAnimal;
    }
}
