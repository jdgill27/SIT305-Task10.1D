package com.example.food_rescue_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    Context context;
    List<Food_Item> list;

    public Adapter(Context context, List<Food_Item> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_list_view,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.MyViewHolder holder, int position) {
        Food_Item food_item = list.get(position);
        holder.name.setText(food_item.title);
        holder.details.setText(food_item.desc);
        holder.qty.setText(food_item.quantity);
        Glide.with(context).load(list.get(position).getImage()).into(holder.image);
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, "Lets order- " + list.get(holder.getAdapterPosition()).title);
                intent.setType("text/plain");
                context.startActivity(Intent.createChooser(intent, "send to"));
            }
        });

        holder.pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "items: burgers added to the cart", Toast.LENGTH_SHORT).show();
            }
        });
        //holder.image.setImageResource(Integer.parseInt(food_item.image));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name,details,qty;
        ImageView share, pay;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_display);
            name = itemView.findViewById(R.id.title_display);
            details = itemView.findViewById(R.id.desc_display);
            qty = itemView.findViewById(R.id.qty_display);
            pay = itemView.findViewById(R.id.paypal);
            share = itemView.findViewById(R.id.img_btn);
        }
    }


}
