package com.example.muhammadjon.myproject.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.muhammadjon.myproject.R;
import com.example.muhammadjon.myproject.dbase.Merchants;

import java.util.ArrayList;

public class WindowAdapter extends RecyclerView.Adapter<WindowAdapter.windowVH> {
    private LayoutInflater inflater;
    private ArrayList<Merchants> merchants;
    private Context ctx;

    public WindowAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.ctx = context;
    }

    @NonNull
    @Override
    public windowVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = inflater.inflate(R.layout.window_merchant, viewGroup, false);
        return new windowVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull windowVH windowVH, int i) {
        windowVH.onBind(merchants.get(i));
    }

    @Override
    public int getItemCount() {
        return merchants.size();
    }

    public void setItemMarches(ArrayList<Merchants> list) {
if (merchants==null)
    merchants=new ArrayList<>();
else
    merchants.clear();
    merchants.addAll(list);
        notifyDataSetChanged();
    }

    class windowVH extends RecyclerView.ViewHolder {
        AppCompatTextView title, text;
        AppCompatImageView imageView;

        public windowVH(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.name_window);
            text = itemView.findViewById(R.id.ligel_name);
            imageView = itemView.findViewById(R.id.window_ii);
        }

        public void onBind(Merchants merchants) {
            title.setText(merchants.getName_uz());
            text.setText(merchants.getLegal_name());
            Glide.with(ctx)
                    .load("https://picsum.photos/100/100/?random")
                    .into(imageView);
        }
    }
}
