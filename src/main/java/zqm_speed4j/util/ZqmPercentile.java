package zqm_speed4j.util;

import java.util.Arrays;

/**
 * Created by useheart on 2019-03-17
 */
public class ZqmPercentile {

    private double quantile = 0.0;

    public ZqmPercentile() {
        setQuantile(50.0);
    }

    public ZqmPercentile(final double p) {
        setQuantile(p);
    }

    public double evaluate(final double[] values, final int begin, final int length, final double p) {
        test(values, begin, length);

        if ((p > 100) || (p <= 0)) {
            throw new IllegalArgumentException("invalid quantile value: " + p);
        }
        if (length == 0) {
            return Double.NaN;
        }
        if (length == 1) {
            return values[begin]; // always return single value for n = 1
        }

        // sort array
        double[] sorted = new double[length];
        System.arraycopy(values, begin, sorted, 0, length);
        Arrays.sort(sorted);

        return evaluateSorted(sorted, p);
    }

    private double evaluateSorted(double[] sorted, double p) {
        double n = sorted.length;
        double pos = p * (n + 1) / 100;
        double fpos = Math.floor(pos);
        int intPos = (int) fpos;
        double dif = pos - fpos;

        if (pos < 1) {
            return sorted[0];
        }
        if (pos >= n) {
            return sorted[sorted.length - 1];
        }
        double lower = sorted[intPos - 1];
        double upper = sorted[intPos];
        return lower + dif * (upper - lower);
    }

    private void test(double[] values, int start, int end) {
        if (start < 0 || start > values.length || end < start || end > values.length) {
            throw new IllegalArgumentException("This is not a valid subrange");
        }
    }

    public double getQuantile() {
        return quantile;
    }

    private void setQuantile(double p) {
        if (p <= 0 || p > 100) {
            throw new IllegalArgumentException("Illegal quantile value:" + p);
        }
        quantile = p;
    }
}
