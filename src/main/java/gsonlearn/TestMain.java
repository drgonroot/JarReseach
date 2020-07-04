package gsonlearn;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;

/**
 * Created by useheart on 2020/6/20
 *
 * @author useheart
 */
//@JsonAdapter(value = TestMain.class)
public class TestMain {
    @Since(value = 0.6d)
    @Until(value = 1.0d)
    private String name;

    public TestMain(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("java.version"));
        System.out.println(0 & 1);
        TestMain testMain = new TestMain("你好");
        Gson gson = new GsonBuilder()
                .setVersion(0.7d)
                .create();
        System.out.println(gson.toJson(testMain));
        //{"name":"你好"}
        TestMain jsonTestMain = gson.fromJson("{\"name\":\"你好\"}", TestMain.class);
        System.out.println(jsonTestMain);
        try {
            TestMain.class.getDeclaredConstructor(String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
