package fr.epsi.dao;

import fr.epsi.model.PrivateChannel;
import org.hibernate.query.Query;

import java.util.List;
import java.util.stream.IntStream;

public class PrivateChannelDao {
    public List<PrivateChannel> searchByHashtag(List<String> hashtags) {
        return this.execute((session) -> {
            int size = hashtags.size();
            StringBuilder builder = new StringBuilder("from Tweet where message like :hashtag0");
            IntStream.range(1, size).forEach(i -> builder.append(" and message like :hashtag" + i));
            Query query = session.createQuery(builder.toString());

            IntStream.range(0, size).forEach(i -> query.setParameter("hashtag" + i, "%" + hashtags.get(i) + "%"));
            return query.list();
        });
    }
}
