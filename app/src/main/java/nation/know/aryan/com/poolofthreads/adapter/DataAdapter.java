package nation.know.aryan.com.poolofthreads.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import nation.know.aryan.com.poolofthreads.R;

/**
 * Created by dell on 1/12/2018.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<String> info;

    public DataAdapter(ArrayList<String> info) {
        this.info = info;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_show, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {

        viewHolder.count_number.setText(info.get(i));
    }

    @Override
    public int getItemCount() {
        return info.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView count_number;
        public ViewHolder(View view) {
            super(view);

            count_number = (TextView)view.findViewById(R.id.count_number);
        }
    }

}
