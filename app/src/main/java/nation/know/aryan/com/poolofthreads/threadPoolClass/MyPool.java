package nation.know.aryan.com.poolofthreads.threadPoolClass;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import nation.know.aryan.com.poolofthreads.Acitivity.MainActivity;
import nation.know.aryan.com.poolofthreads.rejectedExecutionHandler.MyRejectedExecutionHandler;
import nation.know.aryan.com.poolofthreads.threadFactory.PriorityThreadFactory;
import nation.know.aryan.com.poolofthreads.Interface.ShowDataFromBackground;

/**
 * Created by user on 9/1/18.
 */

public class MyPool {
    public static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    /*
      * thread pool executor for background tasks
      *
      */
    static final int Done = 1;
    static final int Failed = 0;

    // Rejecton Handler
    MyRejectedExecutionHandler rejectionHandler;

    private ThreadPoolExecutor mForBackgroundTasks;
    /*
       * thread pool executor for light weight background tasks
       */
    private ThreadPoolExecutor mForLightWeightBackgroundTasks;
    /*
    * thread pool executor for main thread tasks
    */
    private Handler mHandler;


    private ShowDataFromBackground showDataFromBackground;


    /*
    * an instance of DefaultExecutorSupplier
    */
    private static MyPool sInstance;


    public static MyPool getInstance(Context context) {
        if (sInstance == null) {

            synchronized (MyPool.class) {

                sInstance = new MyPool(context);
            }
            return sInstance;
        }
        return null;
    }

    /*
    * constructor for  DefaultExecutorSupplier
    */

     public MyPool(Context context) {
         MainActivity mainActivity=new MainActivity();
        showDataFromBackground=(ShowDataFromBackground) context;
        // setting the thread factory
        ThreadFactory backgroundPriorityThreadFactory = new
                PriorityThreadFactory(Process.THREAD_PRIORITY_BACKGROUND);
          rejectionHandler = new MyRejectedExecutionHandler();
        // setting the thread pool executor for mForBackgroundTasks;
        mForBackgroundTasks = new ThreadPoolExecutor(
                NUMBER_OF_CORES * 2,
                NUMBER_OF_CORES * 2,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                backgroundPriorityThreadFactory,rejectionHandler
        );

        // setting the thread pool executor for mForLightWeightBackgroundTasks;
        mForLightWeightBackgroundTasks = new ThreadPoolExecutor(
                NUMBER_OF_CORES * 2,
                NUMBER_OF_CORES * 2,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                backgroundPriorityThreadFactory,rejectionHandler
        );


        mHandler=new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message inputMessage) {

                        showDataFromBackground.showit(String.valueOf(inputMessage.what));


            }
        };


    }

    /*
    * returns the thread pool executor for background task
    */
    public ThreadPoolExecutor forBackgroundTasks() {
        return mForBackgroundTasks;
    }

    /*
    * returns the thread pool executor for light weight background task
    */
    public ThreadPoolExecutor forLightWeightBackgroundTasks() {
        return mForLightWeightBackgroundTasks;
    }

    /*
    * returns the thread pool executor for main thread task
    */



    public void PasInfofromRunnable(int data) {
          Message completeMessage = mHandler.obtainMessage(data);
            completeMessage.sendToTarget();

    }
}
