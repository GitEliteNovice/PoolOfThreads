package nation.know.aryan.com.poolofthreads.runnable;

import nation.know.aryan.com.poolofthreads.threadPoolClass.MyPool;

/**
 * Created by user on 11/1/18.
 */

public class MyRunnable implements Runnable {
   MyPool info;
    int value;
    public MyRunnable(int value, MyPool context) {
        info=  context;
        this.value=value;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//if (value%2==0){
    info.PasInfofromRunnable(value);
/*
}else {
    info.PasInfofromRunnable(1);
}
*/

    }
}
