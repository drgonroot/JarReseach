package refactor.model;

/**
 * Created by useheart on 2020/6/14
 *
 * @author useheart
 */
public class RegularPrice extends Price {
    @Override
    public int getPriceCode() {
        return Movie.REGULAR;
    }

    @Override
    public double getCharge(int dayRented) {
        double result = 2;
        if (dayRented > 2) {
            result += (dayRented) - 2 * 1.5;
        }
        return result;
    }
}
