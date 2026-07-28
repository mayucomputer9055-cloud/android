package Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.first_application.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import classes.TableItems;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.AdapterHolder> {

    private List<TableItems> list;

    public RecyclerAdapter(List<TableItems> list)
    {
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout,parent,false);
        return  new AdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHolder holder, int position) {
        TableItems ti = list.get(position);
        holder.T_ID.setText(ti.getId());
        holder.T_NAME.setText(ti.getName());
        holder.T_EMAIL.setText(ti.getEmail());

        holder.cv.setOnClickListener(view ->{
            Snackbar.make(view,ti.getName() + " clicked...",Snackbar.LENGTH_LONG).show();
        });

    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public void updateData(List<TableItems> l)
    {
        list = l;
        notifyDataSetChanged();
    }

    static class AdapterHolder extends RecyclerView.ViewHolder{
        TextView T_ID,T_NAME,T_EMAIL;
        CardView cv;

        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            T_ID = itemView.findViewById(R.id.text_id);
            T_NAME = itemView.findViewById(R.id.text_name);
            T_EMAIL = itemView.findViewById(R.id.text_email);
            cv = itemView.findViewById(R.id.dataCard);

        }
    }
}

