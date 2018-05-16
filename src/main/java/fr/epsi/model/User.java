package fr.epsi.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "User", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"userId"})
})
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    private String userId;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    @OneToMany(mappedBy = "userId", fetch = FetchType.EAGER)
    private List<PublicChannel> publicMessages;
    @OneToMany(mappedBy = "userId", fetch = FetchType.EAGER)
    private List<PrivateChannel> privateMessages;
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() { return userId; }

    public void setUserId(String userId) { this.userId = "@" + userId; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<PublicChannel> getPublicMessages() {
        return publicMessages;
    }

    public void setPublicMessages(List<PublicChannel> publicMessages) {
        this.publicMessages = publicMessages;
    }

    public List<PrivateChannel> getPrivateMessages() {
        return privateMessages;
    }

    public void setPrivateMessages(List<PrivateChannel> privateMessages) {
        this.privateMessages = privateMessages;
    }
}
