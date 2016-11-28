package expert.java.common.innerclass;

/**
 * Created by Patrick on 6/3/16.
 */
public class InnerClassTypes {
    public static int x=100;

//一般来说，有4中内部类：1.常规内部类、2.静态内部类、3.局部内部类、4.匿名内部类。
        public static void main(String args[]) {

            //3.局部内部类creating local inner class inside method
            /*
            *
            * .局部内部类只在方法体中有效，就想定义的局部变量一样，在定义的方法体外不能创建局部内部类的对象
               2.在方法内部定义类时，应注意以下问题：
   1.方法定义局部内部类同方法定义局部变量一样，不能使用private、protected、public等访问修饰说明符修饰，也不能使用static修饰，但可以使用final和   abstract修饰
   2.方法中的内部类可以访问外部类成员。对于方法的参数和局部变量，必须有final修饰才可以访问。
   3.static方法中定义的内部类可以访问外部类定义的static成员
             */
            class Local {
                public void name() {
                    System.out.println("Example of Local class in Java");

                }
            }

            //creating instance of local inner class
            Local local = new Local();
            local.name(); //calling method from local inner class

            //4.匿名内部类 Creating anonymous inner class in java for implementing thread
            Thread anonymous = new Thread(){
                @Override
                public void run(){
                    System.out.println("Anonymous class example in java");
                }
            };
            anonymous.start();

            //example of creating instance of inner class
            InnerClassTypes test = new InnerClassTypes();
            InnerClassTypes.Inner inner = test.new Inner();
            inner.name(); //calling method of inner class

        }

        /*
         * 1.常规内部类没有用static修饰且定义在在外部类类体中
         * Creating Inner class in Java
         * 1.常规内部类中的方法可以直接使用外部类的实例变量和实例方法。
         * 2.在常规内部类中可以直接用内部类创建对象
         */
        private class Inner{
            public void name(){
                System.out.println("Inner class example in java");
            }
        }
    /*
     * 2.静态内部类：与类的其他成员相似，可以用static修饰内部类，这样的类称为静态内部类。
     * (1)静态内部类与静态内部方法相似，只能访问外部类的static成员，不能直接访问外部类的实例变量，与实例方法，只有通过对象引用才能访问。
     * (2)由于static内部类不具有任何对外部类实例的引用，因此static内部类中不能使用this关键字来访问外部类中的实例成员，
     * 但是可以访问外部类中的static成员。这与一般类的static方法想通
     */

    public static class MyStaticInner {
        private String y = "Hello!";

        public void innerMethod() {
            System.out.println("x=" + x);
            System.out.println("y=" + y);

        }
    }

}
