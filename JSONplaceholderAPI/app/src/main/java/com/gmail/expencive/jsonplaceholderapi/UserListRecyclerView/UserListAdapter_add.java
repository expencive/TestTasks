package com.gmail.expencive.jsonplaceholderapi.UserListRecyclerView;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gmail.expencive.jsonplaceholderapi.AlbumsActivity;
import com.gmail.expencive.jsonplaceholderapi.R;

import java.util.List;

class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView mTextViewUserName, mTextViewId;

    private ItemClickListener itemClickListener;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        mTextViewUserName = itemView.findViewById(R.id.text_view_user_name);
        mTextViewId = itemView.findViewById(R.id.text_view_id);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition());
    }
}

public class UserListAdapter_add extends RecyclerView.Adapter<RecyclerViewHolder> {

    public List<UserListItem> userNameList;
    public Context context;





    public UserListAdapter_add(List<UserListItem> userNameList, Context context) {
        this.userNameList = userNameList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.userlist_item, parent, false);

        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        holder.mTextViewUserName.setText(userNameList.get(position).getUserName());
        holder.mTextViewId.setText(userNameList.get(position).getId());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {

                Intent intent = new Intent(context, AlbumsActivity.class);

                intent.putExtra("intent_userid", userNameList.get(position).getId());

                context.startActivity(intent);



















            }
        });

    }


    @Override
    public int getItemCount() {
        return userNameList.size();
    }





}

