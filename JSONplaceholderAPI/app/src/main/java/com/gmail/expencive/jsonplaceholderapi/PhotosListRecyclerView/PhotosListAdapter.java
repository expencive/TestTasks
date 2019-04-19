package com.gmail.expencive.jsonplaceholderapi.PhotosListRecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmail.expencive.jsonplaceholderapi.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PhotosListAdapter extends RecyclerView.Adapter<PhotosListAdapter.PhotosListViewHolder> {

    private Context mContext;
    private ArrayList<PhotosListItem> mPhotosList;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    /*public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }*/

    public PhotosListAdapter(Context context, ArrayList<PhotosListItem> photosList) {
        mContext = context;
        mPhotosList = photosList;
    }

    @Override
    public PhotosListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.photoslist_item, parent, false);
        return new PhotosListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PhotosListViewHolder holder, int position) {
        PhotosListItem currentItem = mPhotosList.get(position);

        String imageUrl = currentItem.getImageUrl();
        String titleName = currentItem.getTitle();
        int photoId = currentItem.getPhotoId();

        holder.mTextViewTitle.setText(titleName);
        holder.mTextViewPhotoId.setText("PhotoId: " + photoId);

        Picasso.with(mContext).load(imageUrl).fit().centerInside().into(holder.mImageView);
        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return mPhotosList.size();
    }

    public class PhotosListViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView mTextViewTitle, mTextViewPhotoId;

        public PhotosListViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
            mTextViewTitle = itemView.findViewById(R.id.text_view_title);
            mTextViewPhotoId = itemView.findViewById(R.id.text_view_photoId);

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });*/
        }


    }
}