package gsonlearn;

/**
 * Created by useheart on 2020/6/20
 *
 * @author useheart
 */
public class TestId<T> {
    private final Class<T> clazz;
    private final long value;

    public TestId(Class<T> clazz, long value) {
        this.clazz = clazz;
        this.value = value;
    }
}
