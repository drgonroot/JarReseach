package security;

/**
 * Created by useheart on 2020/5/15
 *
 * @author useheart
 */
public class SecurityTest {
    /**
     * Exception in thread "main" java.security.AccessControlException: access denied ("java.io.FilePermission" "foo.txt" "read")
     * at java.security.AccessControlContext.checkPermission(AccessControlContext.java:472)
     * at java.security.AccessController.checkPermission(AccessController.java:884)
     * at java.lang.SecurityManager.checkPermission(SecurityManager.java:549)
     * at java.lang.SecurityManager.checkRead(SecurityManager.java:888)
     * at security.SecurityTest.main(SecurityTest.java:14)
     */
    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        System.out.println(System.getProperty("java.version"));
        System.setProperty("java.version", "123");
        System.out.println(System.getProperty("java.version"));
    }
}

