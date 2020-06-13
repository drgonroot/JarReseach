package levenshteinlearn;

/**
 * Created by useheart on 2020/4/29
 *
 * @author useheart
 */
public class LevenshteinDistanceLearn {
    public static void main(String[] args) throws Exception {
        String sourceName = "张*狗";
        String sourcePhone = "123****8910";
        String sourceIdentityNo = "123456****8765****";
        String targetName = "张大狗";
        String targetPhone = "12345678910";
        String targetIdentityNo = "123456789987654321";
        boolean match = LevenshteinDistance.X.ld(sourceName, targetName) == 1 &&
                LevenshteinDistance.X.ld(sourcePhone, targetPhone) == 4 &&
                LevenshteinDistance.X.ld(sourceIdentityNo, targetIdentityNo) == 8;
        System.out.println("是否匹配:" + match);
        targetName = "张大doge";
        match = LevenshteinDistance.X.ld(sourceName, targetName) == 1 &&
                LevenshteinDistance.X.ld(sourcePhone, targetPhone) == 4 &&
                LevenshteinDistance.X.ld(sourceIdentityNo, targetIdentityNo) == 8;
        System.out.println("是否匹配:" + match);
    }
}
