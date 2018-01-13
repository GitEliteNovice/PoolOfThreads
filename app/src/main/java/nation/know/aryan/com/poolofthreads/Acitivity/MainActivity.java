package nation.know.aryan.com.poolofthreads.Acitivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ThreadPoolExecutor;

import nation.know.aryan.com.poolofthreads.runnable.MyRunnable;
import nation.know.aryan.com.poolofthreads.R;
import nation.know.aryan.com.poolofthreads.Interface.ShowDataFromBackground;
import nation.know.aryan.com.poolofthreads.adapter.DataAdapter;
import nation.know.aryan.com.poolofthreads.threadPoolClass.MyPool;

public class MainActivity extends AppCompatActivity implements ShowDataFromBackground {
ThreadPoolExecutor poolExecutor;

    Button button;
    MyPool instance;

    ArrayList<String> info;
    RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // creeate instance of thread pool
        instance=MyPool.getInstance(this);
        //
        initViews();
        //to get threadpool for background task
        poolExecutor=instance.forBackgroundTasks();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(int i=0; i<50; i++){
                    poolExecutor.execute(new MyRunnable(i,instance));
                }
            }
        });
    }
    private void initViews(){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
   //     recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        info = new ArrayList<>();

         adapter = new DataAdapter(info);
        recyclerView.setAdapter(adapter);

        button=(Button)findViewById(R.id.button);

    }
    @Override
    public void showit(String info) {
     this.info.add("Runnable "+info);
    adapter.notifyDataSetChanged();
        //    textview.setText(info+" show");
    }
}
