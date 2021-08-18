package edu.app.entity;
import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractEntity implements IEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    protected Long id;

    public AbstractEntity(long id) { this.id = id; }

    public AbstractEntity() { }

    public AbstractEntity(long id, Long time) { this.id = id;}

    @PrePersist
    public void init() {
        this.id = null;
    }

    public Long getId() { return null; }

    public void setId(long id) { this.id = id; }
}
