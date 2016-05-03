package kr.pe.burt.android.multipleimagedownload;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import kr.pe.burt.android.multipleimagedownload.CardItem;
import kr.pe.burt.android.multipleimagedownload.R;

/**
 * Created by burt on 2016. 5. 2..
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    List<CardItem> items;

    public RecyclerAdapter(List<CardItem> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final  CardItem item = items.get(position);
        //Drawable drawable = context.getResources().getDrawable(item.getImage());
        //holder.image.setBackgroundDrawable(drawable);
        holder.title.setText(item.getTitle());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            image = (ImageView)itemView.findViewById(R.id.image);
            title = (TextView)itemView.findViewById(R.id.title);
            cardView = (CardView)itemView.findViewById(R.id.cardView);
        }
    }
}
