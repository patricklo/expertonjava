package practice.java.designpatterns.singleton;

/**
 * Created by patrick on 12/18/14.
 */

    // Works with acquire/release semantics for volatile
// Broken under current semantics for volatile
    class Foo {
        private volatile Helper helper = null;
        public Helper getHelper() {
            if (helper == null) {
                synchronized (this) {
                    if (helper == null)
                        helper = new Helper();
                }
            }
            return helper;
        }
}
