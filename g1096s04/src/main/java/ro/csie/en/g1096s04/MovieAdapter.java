package ro.csie.en.g1096s04;

import static android.os.Looper.getMainLooper;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class MovieAdapter extends  RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private List<Movie> mItems;
    private Context mContext;
    ExecutorService executorService;
    Handler uiHandler;
    public MovieAdapter(Context context, List<Movie> movieList, ExecutorService executorService) {
        this.mContext = context;
        this.mItems = movieList;
        this.executorService = executorService;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.movie_item, parent, false);
        MovieHolder viewHolder = new MovieHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        Movie item = mItems.get(position);
        holder.tvTitle.setText("Title:" + item.getTitle());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        holder.tvRelease.setText("Release:" + sdf.format(item.getRelease()));
        String format = String.format("Length: %s min. - Genre: %s", item.getDuration(), item.getGenre());
        holder.tvBudgetGenre.setText(format);
        /// handle image download
        int resid = mContext.getResources().getIdentifier(item.getTitle(), "string", mContext.getPackageName());
        if(resid != 0)
        {
            uiHandler = new Handler(getMainLooper(), new Handler.Callback() {
                @Override
                public boolean handleMessage(@NonNull Message message) {
                    int receivedBitmapCode = 1122;
                    if(message.what == receivedBitmapCode)
                    {
                        holder.ivPoster.setImageBitmap((Bitmap) message.obj);
                    }
                    return  true;
                }
            });
            executorService.submit(new DownloadPosterTask(mContext.getString(resid), uiHandler));
        }
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Clicked: " + item, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class MovieHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle;
        public TextView tvRelease;
        public TextView tvBudgetGenre;
        public ImageView ivPoster;
        public View mView;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvRelease = itemView.findViewById(R.id.tvRelease);
            tvBudgetGenre = itemView.findViewById(R.id.tvBudgetGenre);
            ivPoster = itemView.findViewById(R.id.imageView);
        }
    }
}
