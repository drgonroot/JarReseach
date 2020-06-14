package refactor.model;

/**
 * Created by useheart on 2020/6/14
 *
 * @author useheart
 */
public abstract class Price {
    public abstract int getPriceCode();

    /**
     * 获取租借的费用
     */
    public abstract double getCharge(int dayRented);

    /**
     * 常客积分
     */
    public int getFrequentRenterPoints(int dayRented) {
        return 1;
    }
}
