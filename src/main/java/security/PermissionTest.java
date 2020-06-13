package security;

import java.security.AccessController;
import java.security.PrivilegedExceptionAction;


public class PermissionTest {

    /**
     * test/foo.txt
     * Exception in thread "main" java.security.AccessControlException: access denied ("java.io.FilePermission" "test/foo.txt" "read")
     * at java.security.AccessControlContext.checkPermission(AccessControlContext.java:472)
     * at java.security.AccessController.checkPermission(AccessController.java:884)
     * at java.lang.SecurityManager.checkPermission(SecurityManager.java:549)
     * at java.lang.SecurityManager.checkRead(SecurityManager.java:888)
     * at java.io.FileInputStream.<init>(FileInputStream.java:127)
     * at java.io.FileInputStream.<init>(FileInputStream.java:93)
     * at security.FileReader.read(PermissionTest.java:15)
     * at security.PermissionTest.main(PermissionTest.java:25)
     */
    public static void main(String... args) throws Exception {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        AccessController.doPrivileged(new PrivilegedExceptionAction<Void>() {
            @Override
            public Void run() throws Exception {
                FileReader reader = new FileReader();
                reader.read("test/foo.txt");
                return null;
            }
        });
        FileReader reader = new FileReader();
        reader.read("test/foo.txt");
    }
}


