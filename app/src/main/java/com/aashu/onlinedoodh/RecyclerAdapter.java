package com.aashu.onlinedoodh;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerAdapter extends FirebaseRecyclerAdapter<Modalclass,RecyclerAdapter.myViewholder> {

    private  Recyclerviewinterface recyclerviewinterface;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     * @param recyclerviewinterface
     */ RecyclerAdapter(@NonNull FirebaseRecyclerOptions<Modalclass> options, Recyclerviewinterface recyclerviewinterface) {
        super(options);
        this.recyclerviewinterface = recyclerviewinterface;
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewholder holder, int position, @NonNull Modalclass model) {
        holder.name_text.setText(model.getName());
        holder.shopname.setText(model.getShopname());
        holder.Mobilenumber.setText(model.getMobilenumber());

        Glide.with(holder.circle_image.getContext())
                .load(model.getSurl())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ccp_down_arrow)
                .into(holder.circle_image);

    }

    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recycle_item,parent,false);
        return new myViewholder(view,recyclerviewinterface);
    }

    class myViewholder extends RecyclerView.ViewHolder{

        CircleImageView circle_image;
        TextView name_text,shopname,Mobilenumber;

        public myViewholder(@NonNull View itemView,Recyclerviewinterface recyclerviewinterface) {
            super(itemView);

            name_text = (TextView)itemView.findViewById(R.id.name_text);
            shopname = (TextView)itemView.findViewById(R.id.shopname);
            Mobilenumber = (TextView)itemView.findViewById(R.id.Mobilenumber);
            circle_image = (CircleImageView)itemView.findViewById(R.id.circle_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recyclerviewinterface.Onitemclick(getAbsoluteAdapterPosition());
                }
            });




        }
    }
}
