package com.xxd.wildfly.singleton.timer;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "\"user\"")
@NamedNativeQueries({
    @NamedNativeQuery(name = "User.findAll",
        query = "SELECT u.* FROM \"user\" u;", resultClass = User.class)
})
public class User {

    @Id
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    protected User() {
    }

    public User(String name) {
        this.name = name;
    }

    @PrePersist
    public void populateBeforeInsert() {
        if (this.created == null) {
            this.created = new Date();
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreated() {
        return created;
    }
}
