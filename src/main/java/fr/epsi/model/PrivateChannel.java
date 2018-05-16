package fr.epsi.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "privateChannel")
public class PrivateChannel implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User userId;
    private Messages message;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User user) {
        this.userId = user;
    }

    public Messages getMessage() {
        return message;
    }

    public void setMessage(Messages message) {
        this.message = message;
    }
}
