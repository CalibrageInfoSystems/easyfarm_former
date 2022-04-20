package in.calibrage.easyfarm.Adapter;


import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.List;

import in.calibrage.easyfarm.Common.AnimationUtil;
import in.calibrage.easyfarm.R;
import in.calibrage.easyfarm.model.GetUserInfoByUserCode;

public class IDDetailsAdapter extends RecyclerView.Adapter<IDDetailsAdapter.ViewHolder> {

    List<GetUserInfoByUserCode.IdentityProofDetail> settings;
    public Context mContext;

    LayoutInflater mInflater;

    public IDDetailsAdapter(List<GetUserInfoByUserCode.IdentityProofDetail> recomm_Set, Context context) {
        this.settings = recomm_Set;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.ids_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.name.setText(settings.get(position).getIdProofName());
        holder.number.setText(settings.get(position).getIdProofNumber());


        //Picasso.with(mContext).load(settings.get(position).getIdentityProof()).error(R.drawable.ic_user).transform(new CircleTransform()).into(holder.id_image);

        Glide.with(mContext).load(settings.get(position).getIdentityProof()+"").into(holder.id_image);
        if (position % 2 == 0) {
            holder.card_view.setCardBackgroundColor(mContext.getColor(R.color.white));
        } else {
            holder.card_view.setCardBackgroundColor(mContext.getColor(R.color.white2));

        }
        holder.card_view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.e("===========","================clicked");
                Context context = mContext.getApplicationContext();
                mInflater = LayoutInflater.from(context);
               AlertDialog.Builder mBuilder = new AlertDialog.Builder(mContext);
                View mView = mInflater.inflate(R.layout.dialog_custom_layout, null);
                TextView cancel =mView.findViewById(R.id.cancel);
                TextView text = mView.findViewById(R.id.text);
                text.setText(settings.get(position).getIdProofName());
                //  Picasso.with(mContext).load(getCollectionInfoById.getResult().getReceiptImg()).error(R.drawable.ic_user).into(photoView);
                PhotoView photoView = mView.findViewById(R.id.imageView);
                Glide.with(mContext).load(settings.get(position).getIdentityProof()+"").into(photoView);
               // Picasso.with(mContext).load(img.get(finalJ)).error(R.drawable.ic_user).into(photoView);
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



        AnimationUtil.animate(holder, true);
    }

    @Override
    public int getItemCount() {
        return settings.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
TextView name, number;
ImageView id_image;
CardView card_view;

        public ViewHolder(View itemView) {
            super(itemView);

            this.card_view = (CardView) itemView.findViewById(R.id.card_view);
            this.name =(TextView)itemView.findViewById(R.id.name);
            this.number =(TextView)itemView.findViewById(R.id.num);
            this.id_image =(ImageView)itemView.findViewById(R.id.imageView);






        }


    }
}


