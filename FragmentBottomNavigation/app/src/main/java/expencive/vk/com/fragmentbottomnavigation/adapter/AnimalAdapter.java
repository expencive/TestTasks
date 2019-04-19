package expencive.vk.com.fragmentbottomnavigation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import expencive.vk.com.fragmentbottomnavigation.R;
import expencive.vk.com.fragmentbottomnavigation.models.Animal;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder> {

    private Context mContext;
    private ArrayList<Animal> mAnimalList;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }



    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public AnimalAdapter(Context context, ArrayList<Animal> animalList) {
        mContext = context;
        mAnimalList = animalList;
    }

    @Override
    public AnimalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.animal_item, parent, false);
        return new AnimalViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AnimalViewHolder holder, int position) {
        Animal currentItem = mAnimalList.get(position);

        int currentPosition = position+1;

        String imageUrl = currentItem.getImageUrl();

        String title = currentItem.getImageTitle();


        holder.mTextViewNumber.setText(String.valueOf(currentPosition));
        holder.mTextViewTitle.setText(title);

        Picasso.get().load(imageUrl).fit().centerInside().into(holder.mImageView);
        holder.itemView.setTag(position);


    }



    @Override
    public int getItemCount() {
        return mAnimalList.size();
    }



    public class AnimalViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView mTextViewNumber, mTextViewTitle;




        public AnimalViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
            mTextViewNumber = itemView.findViewById(R.id.text_view_number);
            mTextViewTitle = itemView.findViewById(R.id.text_view_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
