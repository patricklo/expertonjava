package practice.java.reflection;

/**
 * Created by Patrick on 21/2/16.
 */
public class DiffClassAndClassLoader {
    public static void main(String[] args){
        try{

            //一次执行一个,不然看不到效果
            //ClassLoader
//            System.out.println("before loadClass...");
//            Class c  = DiffClassAndClassLoader.class.getClassLoader().loadClass("practice.java.reflection.ClassInfo");
//            System.out.println("after loadClass...");
//            System.out.println("Before newInstance");
//            ClassInfo info1 = (ClassInfo) c.newInstance();
//            System.out.println("after newInstance");


            //Class.forName
            System.out.println("before Class.forName...");
            Class c2  = Class.forName("practice.java.reflection.ClassInfo");
            System.out.println("after Class.forName...");
            System.out.println("Before newInstance");
            ClassInfo info2 = (ClassInfo) c2.newInstance();
            System.out.println("after newInstance");




//            Class.forName("");
//            ClassLoader.getSystemClassLoader().loadClass()
        }
                catch(Exception e){
                    e.printStackTrace();

        }
    }
}
