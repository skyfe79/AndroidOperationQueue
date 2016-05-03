package kr.pe.burt.android.multipleimagedownload;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import kr.pe.burt.android.lib.androidoperationqueue.AndroidOperation;
import kr.pe.burt.android.lib.androidoperationqueue.AndroidOperationQueue;
import kr.pe.burt.android.lib.androidoperationqueue.Operation;
import kr.pe.burt.android.multipleimagedownload.CardItem;
import kr.pe.burt.android.multipleimagedownload.R;

/**
 * Created by burt on 2016. 5. 2..
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    List<CardItem> items;
    AndroidOperationQueue downloadQueue = new AndroidOperationQueue("DownloadQueue");

    public RecyclerAdapter(List<CardItem> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final  CardItem item = items.get(position);
        
        downloadQueue.stop();

        downloadQueue.addOperation(new Operation() {
            @Override
            public void run() {
                AndroidOperation.runOnUiThread(new Operation() {
                    @Override
                    public void run() {
                        holder.image.setImageBitmap(null);
                        holder.line.setVisibility(View.INVISIBLE);
                        holder.progressBar.setVisibility(View.VISIBLE);
                    }
                });
            }
        });

        downloadQueue.addOperation(new Operation() {
            @Override
            public void run() {
                String url = item.getImageURL();

                final Bitmap bitmap = downloadBitmap(url);

                if(bitmap != null) {
                    AndroidOperation.runOnUiThread(new Operation() {
                        @Override
                        public void run() {
                            holder.image.setImageBitmap(bitmap);
                            holder.line.setVisibility(View.VISIBLE);
                            holder.progressBar.setVisibility(View.INVISIBLE);
                        }
                    });
                }
            }
        });

        downloadQueue.start();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        View line;
        CardView cardView;
        ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super(itemView);

            image = (ImageView)itemView.findViewById(R.id.image);
            line  = itemView.findViewById(R.id.line);
            cardView = (CardView)itemView.findViewById(R.id.cardView);
            progressBar = (ProgressBar)itemView.findViewById(R.id.progressBar);
        }
    }

    private Bitmap downloadBitmap(String url) {

        HttpURLConnection urlConnection = null;
        try {
            URL uri = new URL(url);
            urlConnection = (HttpURLConnection) uri.openConnection();

            int statusCode = urlConnection.getResponseCode();
            if (statusCode != 200) {
                return null;
            }

            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream != null) {
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }

            inputStream.close();
            inputStream = null;

        } catch (Exception e) {
            Log.d("downloadBitmap", e.toString());
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            Log.w("downloadBitmap", "Error downloading image from " + url);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();

            }
        }
        return null;
    }
}
