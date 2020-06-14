package refactor.model;

/**
 * Created by useheart on 2020/6/14
 *
 * @author useheart
 */
public class NewReleasePrice extends Price {
    @Override
    public int getPriceCode() {
        return Movie.NEW_RELEASE;
    }

    @Override
    public double getCharge(int dayRented) {
        return dayRented * 3;
    }

    @Override
    public int getFrequentRenterPoints(int dayRented) {
        return dayRented > 1 ? 2 : 1;
    }
}
