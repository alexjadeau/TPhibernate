package fr.epsi.dao;

import fr.epsi.model.Admin;
import fr.epsi.model.PrivateChannel;
import fr.epsi.model.PublicChannel;
import fr.epsi.model.User;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.PersistenceException;
import java.util.Arrays;

public class UserDaoTest {

    @Test
    public void insertUser() {
        User user = new User();
        user.setFirstname("Testing");
        user.setLastname("User");

        long id = new UserDao().save(user);

        User u = new UserDao().get(id);
        Assert.assertEquals("Testing", u.getFirstname());
    }

    @Test(expected = PersistenceException.class)
    public void uniqueEmail() {
        User user1 = new User();
        user1.setFirstname("Testing");
        user1.setLastname("User");
        user1.setEmail("testing.user@gmail.com");

        User user2 = new User();
        user2.setFirstname("Testing");
        user2.setLastname("User");
        user2.setEmail("testing.user@gmail.com");

        new UserDao().save(user1);
        new UserDao().save(user2);
    }

    @Test
    public void insertPublicMessage() {
        User user = new User();
        user.setFirstname("Testing");
        user.setLastname("User");
        user.setEmail("testing.user@gmail.com");

        PublicChannel publicMessage1 = new PublicChannel();
        publicMessage1.setMessage("Test1");
        publicMessage1.setUserId(user);

        PublicChannel publicMessage2 = new PublicChannel();
        publicMessage2.setMessage("Test2");
        publicMessage2.setUserId(user);

        user.setPublicMessages(Arrays.asList(publicMessage1, publicMessage2));

        long id = new UserDao().save(user);
        User u = new UserDao().get(id);

        Assert.assertEquals(2, u.getPublicMessages().size());
    }

    @Test
    public void insertPrivateMessage() {
        User user = new User();
        user.setFirstname("Testing");
        user.setLastname("User");
        user.setEmail("testing.user@gmail.com");

        PrivateChannel privateMessage1 = new PrivateChannel();
        privateMessage1.setMessage("Test1");
        privateMessage1.setUserId(user);

        PrivateChannel privateMessage2 = new PrivateChannel();
        privateMessage2.setMessage("Test2");
        privateMessage2.setUserId(user);

        user.setPrivateMessages(Arrays.asList(privateMessage1, privateMessage2));

        long id = new UserDao().save(user);
        User u = new UserDao().get(id);

        Assert.assertEquals(2, u.getPrivateMessages().size());
    }

    @Test
    public void deletePublicChannel() {
        User user = new User();
        user.setFirstname("Testing");
        user.setLastname("User");
        user.setEmail("testing.user@gmail.com");

        PublicChannel publicChannel = new PublicChannel();
        publicChannel.setMessage("Test1");
        publicChannel.setUserId(user);

        user.setPublicMessages(Arrays.asList(publicChannel));

        long id = new UserDao().save(user);
        User u = new UserDao().get(id);
        long messageId = u.getPrivateMessages().get(0).getId();

        new UserDao().delete(u);
        Assert.assertNull(new PublicChannelDao().getId(messageId));
    }

    @Test
    public void deletePrivateChannel() {
        User user = new User();
        user.setFirstname("Testing");
        user.setLastname("User");
        user.setEmail("testing.user@gmail.com");

        PrivateChannel privateMessage = new PrivateChannel();
        privateMessage.setMessage("Test1");
        privateMessage.setUserId(user);

        user.setPrivateMessages(Arrays.asList(privateMessage));

        long id = new UserDao().save(user);
        User u = new UserDao().get(id);
        long messageId = u.getPrivateMessages().get(0).getId();

        new UserDao().delete(u);
        Assert.assertNull(new PublicChannelDao().getId(messageId));
    }

    @Test
    public void insertAdmin() {
        Admin admin = new Admin();
        admin.setFirstname("Testing");
        admin.setLastname("User");
        admin.setEmail("testing.user@gmail.com");
        admin.setNickname("Test");

        long id = new AdminDao().save(admin);
        Admin a = new AdminDao().get(id);

        Assert.assertEquals("Test", a.getNickname());
    }

    @Test
    public void searchUserMessage() {
        User user = new User();
        user.setFirstname("Testing");
        user.setLastname("User");
        user.setEmail("testing.user@gmail.com");

        PublicChannel tweet1 = new PublicChannel();
        tweet1.setMessage("Premier test #test hashtag #marchebien");
        tweet1.setUserId(user);

        PublicChannel tweet2 = new PublicChannel();
        tweet2.setMessage("Je vais bien #test2");
        tweet2.setUserId(user);

        user.setPublicMessages(Arrays.asList(tweet1, tweet2));

        new UserDao().save(user);

        Assert.assertEquals(1, new PublicChannelDao().searchByHashtag(Arrays.asList("test", "marchebien")).size());

    }
}
