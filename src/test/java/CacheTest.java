import com.javarush.kotovych.entity.Quest;
import com.javarush.kotovych.entity.User;
import com.javarush.kotovych.config.SessionCreator;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

public class CacheTest {

    @Test
    public void readUser(){
        for(int i = 0; i < 1000; i++){
            try(Session session = SessionCreator.getSession()){
                User user = session.get(User.class, 1L);
                System.out.println(user);
            }
        }
    }


    @Test
    public void readQuest(){
        for(int i = 0; i < 1000; i++){
            try(Session session = SessionCreator.getSession()){
                Quest quest = session.get(Quest.class, 1L);
                System.out.println(quest);
            }
        }
    }
}
