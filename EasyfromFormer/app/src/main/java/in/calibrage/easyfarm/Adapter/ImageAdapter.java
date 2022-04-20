package in.calibrage.easyfarm.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import java.util.List;

import in.calibrage.easyfarm.R;
import in.calibrage.easyfarm.model.GetVendorServices;

class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    LayoutInflater mInflater;
    public Context mContext;
    List<GetVendorServices.ListResult.VendorServiceFile> learning_Set;
    public ImageAdapter(Context context, List<GetVendorServices.ListResult.VendorServiceFile>imagelist) {

        this.mContext = context;
        this.learning_Set =imagelist;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.imagesdata, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Glide.with(mContext).load(learning_Set.get(position).getImage()).error(R.drawable.ic_user).into(holder.img);
        Log.e("============", learning_Set.get(position).getImage());
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = mContext.getApplicationContext();
                mInflater = LayoutInflater.from(context);
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(mContext);
                View mView = mInflater.inflate(R.layout.dialog_custom_layout, null);
                TextView cancel = mView.findViewById(R.id.cancel);
                //  Picasso.with(mContext).load(getCollectionInfoById.getResult().getReceiptImg()).error(R.drawable.ic_user).into(photoView);
                PhotoView photoView = mView.findViewById(R.id.imageView);

                Glide.with(mContext).load(learning_Set.get(position).getImage()).error(R.drawable.ic_user).into(photoView);
                //photoView.setImageResource(Integer.parseInt(getCollectionInfoById.getResult().getReceiptImg()));
                mBuilder.setView(mView);

                final AlertDialog mDialog = mBuilder.create();
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mDialog.dismiss();
                    }
                });
                mDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (learning_Set != null)
            return learning_Set.size();
        else
            return 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView text_title;
        private ImageView img;
        private RelativeLayout learn_relative;

        public ViewHolder(View itemView) {
            super(itemView);
            text_title = itemView.findViewById(R.id.text_title);
            img= itemView.findViewById(R.id.imageView);
            learn_relative =itemView.findViewById(R.id.learn_relative);

        }


    }

}


