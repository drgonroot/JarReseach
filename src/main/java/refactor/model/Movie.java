package refactor.model;

/**
 * Created by useheart on 2020/6/13
 *
 * @author useheart
 */
public class Movie {

    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;

    private String _title;
    private int _priceCode;

    public Movie(String _title, int _priceCode) {
        this._title = _title;
        this._priceCode = _priceCode;
    }

    public String getTitle() {
        return _title;
    }

    public int getPriceCode() {
        return _priceCode;
    }

    /**
     * 获取租借的费用
     */
    public double getCharge(int dayRented) {
        double result = 0;
        switch (getPriceCode()) {
            case Movie.REGULAR:
                result += 2;
                if (dayRented > 2) {
                    result += (dayRented) - 2 * 1.5;
                }
                break;
            case Movie.NEW_RELEASE:
                result += dayRented * 3;
                break;
            case Movie.CHILDRENS:
                result += 1.5;
                if (dayRented > 3) {
                    result += (dayRented - 3) * 1.5;
                }
                break;
            default:
                break;
        }

        return result;
    }

    /**
     * 常客积分
     */
    public int getFrequentRenterPoints(int dayRented) {
        // add frequent renter points
        // add bonus for two day new release rental
        if ((getPriceCode() == Movie.NEW_RELEASE) && dayRented > 1) {
            return 2;
        }
        return 1;
    }
}
