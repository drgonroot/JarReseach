package guava_learn;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by useheart on 2019-12-22
 *
 * @author useheart
 */
public class MockDB {
    private static Map<String, String> mockPersistence = new HashMap<String, String>() {
        private static final long serialVersionUID = 581022599304612774L;

        {
            this.put("github", "codedrinker");
        }
    };

    public static String loadFromPersistence(String key) {
        System.out.println("load key from persistence : " + key);
        return mockPersistence.get(key);
    }
}
