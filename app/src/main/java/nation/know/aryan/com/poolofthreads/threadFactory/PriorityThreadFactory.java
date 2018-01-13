package nation.know.aryan.com.poolofthreads.threadFactory;

import android.os.Process;
import android.support.annotation.NonNull;

import java.util.concurrent.ThreadFactory;

/**
 * Created by user on 8/1/18.
 */

public class PriorityThreadFactory implements ThreadFactory {
    private final int mThreadPriority;

    public PriorityThreadFactory(int threadPriority) {
        mThreadPriority = threadPriority;
    }

    @Override
    public Thread newThread(@NonNull final Runnable runnable) {
     Runnable wrapperRunnable=new Runnable() {
         @Override
         public void run() {
try {
    Process.setThreadPriority(mThreadPriority);
}catch (Throwable throwable){

}
runnable.run();

         }
     };
        return new Thread(wrapperRunnable);
    }
}
