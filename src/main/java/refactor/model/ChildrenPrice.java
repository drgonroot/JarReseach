package refactor.model;

/**
 * Created by useheart on 2020/6/14
 *
 * @author useheart
 */
public class ChildrenPrice extends Price {
    @Override
    public int getPriceCode() {
        return Movie.CHILDRENS;
    }

    @Override
    public double getCharge(int dayRented) {
        double result = 1.5;
        if (dayRented > 3) {
            result += (dayRented - 3) * 1.5;
        }
        return result;
    }
}
