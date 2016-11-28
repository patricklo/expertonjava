package expert.java.common.notify;

/**
 * Created by patrick on 2015/10/30.
 * Copyright @ EEGSmart
 */
public class MainTest {


    public static void main(String[] args){
        WhileTrueReturnTest whileTrueReturnTest = new WhileTrueReturnTest();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        WhileTrueReturnTest ww = WhileTrueReturnTest.getInstance();
                        ww.retrieve();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    }
            }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    WhileTrueReturnTest ww = WhileTrueReturnTest.getInstance();
                    ww.adds("1234");
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    WhileTrueReturnTest ww = WhileTrueReturnTest.getInstance();
                    ww.adds("23456");
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
