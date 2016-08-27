package practice.java.immutable;

/**
 * Created by patrick on 12/18/14.
 *
 * Immutable calss example
 */
public final class Contacts {

    private final String name;
    private final String mobile;

    public Contacts(String name, String mobile) {
        this.name = name;
        this.mobile = mobile;
    }

    public String getName(){
        return name;
    }

    public String getMobile(){
        return mobile;
    }
}
