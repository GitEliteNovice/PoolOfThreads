package nation.know.aryan.com.poolofthreads.Executor;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

/**
 * Created by user on 8/1/18.
 */

public class MainThreadExecutor implements Executor {
    private final Handler handler=new Handler(Looper.getMainLooper());
    @Override
    public void execute(@NonNull Runnable runnable) {
handler.post(runnable);
    }
}
