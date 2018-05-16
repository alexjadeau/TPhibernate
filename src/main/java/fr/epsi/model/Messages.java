package fr.epsi.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;

public class Messages implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    private int like;
    private int dislike;
    @OneToMany
    private String message;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int plusOne) {
        this.like = like;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int plusOne) {
        this.dislike = dislike;
    }
}
