package entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "access", schema = "zootest4migrate", catalog = "")
public class AccessEntity {
    private int idaccess;
    private String description;
    private Collection<JobEntity> jobsByIdaccess;

    @Id
    @Column(name = "idaccess", nullable = false)
    public int getIdaccess() {
        return idaccess;
    }

    public void setIdaccess(int idaccess) {
        this.idaccess = idaccess;
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
        AccessEntity that = (AccessEntity) o;
        return idaccess == that.idaccess &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idaccess, description);
    }

    @OneToMany(mappedBy = "accessByAccessIdaccess")
    public Collection<JobEntity> getJobsByIdaccess() {
        return jobsByIdaccess;
    }

    public void setJobsByIdaccess(Collection<JobEntity> jobsByIdaccess) {
        this.jobsByIdaccess = jobsByIdaccess;
    }
}
