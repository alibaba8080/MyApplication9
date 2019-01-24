package images;

import android.content.Context;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.List;
import java.util.Map;

import util.SampleSzie;


class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder>{

    private Context context;
    private ImagesAdapter.OnItemClickListener onItemClickListener;
    private  List<Map<String, Object>> list;
    private int position=0;
    ImagesAdapter(Context context, List<Map<String, Object>> list) {
    this.context=context;
    this.list=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,final int position) {
        this.position=position;
        holder.mTextView.setText((CharSequence) list.get(position).get(MediaStore.Images.Media.TITLE));
        holder.mImageView.setImageBitmap(SampleSzie.SampleSzie((String) list.get(position).get(MediaStore.Images.Media.DATA)));
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.images);
            mTextView = itemView.findViewById(R.id.textView);
        }
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
