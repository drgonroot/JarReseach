package gson_learn;

/**
 * Created by useheart on 2020/6/20
 *
 * @author useheart
 */
public enum TestEnum {
    APrint() {
        @Override
        public String print() {
            return "test String";
        }
    },
    ;

    public abstract String print();
}
