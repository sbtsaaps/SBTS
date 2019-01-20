package sbts.dmw.com.sbts.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import sbts.dmw.com.sbts.R;
import sbts.dmw.com.sbts.model.Anime;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.viewHolder>{

    private Context mContext;
    private List<Anime> mData;
    RequestOptions options;

    public RecyclerViewAdapter(Context mContext, List<Anime> mData) {
        this.mContext = mContext;
        this.mData = mData;

        options = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.anime_row_item, viewGroup, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {

        viewHolder.textView_Name.setText(mData.get(i).getName());
        viewHolder.textView_Rating.setText(mData.get(i).getRating());
        viewHolder.textView_Studio.setText(mData.get(i).getStudio());
        viewHolder.textView_Category.setText(mData.get(i).getCategories());

        Glide.with(mContext).load(mData.get(i).getImageURL()).apply(options).into(viewHolder.imageView_Thumbnail);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{

        TextView textView_Name;
        TextView textView_Rating;
        TextView textView_Studio;
        TextView textView_Category;
        ImageView imageView_Thumbnail;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            textView_Name = itemView.findViewById(R.id.animeName);
            textView_Category = itemView.findViewById(R.id.animeCategory);
            textView_Rating = itemView.findViewById(R.id.animeRating);
            textView_Studio = itemView.findViewById(R.id.animeStudio);
            imageView_Thumbnail = itemView.findViewById(R.id.animeThumbnail);
        }
    }

}
