package refactor.model;

/**
 * Created by useheart on 2020/6/13
 *
 * @author useheart
 */
public class Rental {

    private Movie _movie;
    private int _dayRented;

    public Rental(Movie _movie, int _dayRented) {
        this._movie = _movie;
        this._dayRented = _dayRented;
    }

    public Movie getMovie() {
        return _movie;
    }

    public int getDayRented() {
        return _dayRented;
    }

    /**
     * 获取租借的费用
     */
    public double getCharge() {
       return getMovie().getCharge(getDayRented());
    }

    /**
     * 常客积分
     */
    public int getFrequentRenterPoints() {
        return getMovie().getFrequentRenterPoints(getDayRented());
    }
}
