package gson_learn;

import com.google.gson.InstanceCreator;

import java.lang.reflect.Type;

/**
 * Created by useheart on 2020/6/20
 *
 * @author useheart
 */
public class TestInstanceCreator implements InstanceCreator<TestId<Long>> {
    @Override
    public TestId<Long> createInstance(Type type) {
        return new TestId<>(Long.class, 1);
    }
}
