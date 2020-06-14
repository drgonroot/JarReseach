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
    private Price _price;

    public Movie(String _title, int _priceCode) {
        this._title = _title;
        setPriceCode(_priceCode);
    }

    public void setPriceCode(int price) {
        switch (price) {
            case REGULAR:
                _price = new RegularPrice();
                break;
            case CHILDRENS:
                _price = new ChildrenPrice();
                break;
            case NEW_RELEASE:
                _price = new NewReleasePrice();
                break;
            default:
                throw new IllegalArgumentException("Incorrect Price Code");
        }
    }

    public String getTitle() {
        return _title;
    }

    public int getPriceCode() {
        return _price.getPriceCode();
    }

    /**
     * 获取租借的费用
     */
    public double getCharge(int dayRented) {
        return _price.getCharge(dayRented);
    }

    /**
     * 常客积分
     */
    public int getFrequentRenterPoints(int dayRented) {
        return this._price.getFrequentRenterPoints(dayRented);
    }
}
