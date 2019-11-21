package com.heartsun.informer;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

/**
 * Created by Bijay on 11/19/2019.
 */

public class StaggeredAdapter extends RecyclerView.Adapter<StaggeredAdapter.MyViewHolder> {

   Context context;
   List<Message_model>message_models;

     ItemClickListener clickListener;

    public StaggeredAdapter(Context context, List<Message_model> message_models) {
        this.context = context;
        this.message_models = message_models;
    }

    @NonNull
    @Override
    public StaggeredAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.staggered_recyclerview, viewGroup,false);
        return new StaggeredAdapter.MyViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull StaggeredAdapter.MyViewHolder holder, int position) {

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background);

        final Message_model message_model = message_models.get(position);
        holder.title.setText(message_model.getHeadline());
        holder.date_created.setText(message_model.getCreatedDate());
        holder.message.setText(message_model.getMsgDesc());

        if (message_model.getPhoto().equals("")){
            holder.image.setVisibility(View.GONE);
        }else{
            holder.image.setVisibility(View.VISIBLE);
            byte[] imageBytes = Base64.decode(message_model.getPhoto(), Base64.DEFAULT);
            Glide.with(context).asBitmap().load(imageBytes).apply(requestOptions).into(holder.image);
        }

    }

    @Override
    public int getItemCount() {
        return message_models.size();
    }


    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView image;
        TextView title,message,date_created;

        public MyViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.titletxt);
            message = itemView.findViewById(R.id.messagetxt);
            date_created = itemView.findViewById(R.id.date_created);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }
}
