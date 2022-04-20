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

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import in.calibrage.easyfarm.R;
import in.calibrage.easyfarm.Views.EncyclopediaActivity;
import in.calibrage.easyfarm.model.GetActiveEncyclopediaCategoryDetails;

public class EncycloAdapter extends RecyclerView.Adapter<EncycloAdapter.ViewHolder> {

    public Context mContext;
    private List<GetActiveEncyclopediaCategoryDetails.ListResult> learning_Set;

    public EncycloAdapter(Context context, List<GetActiveEncyclopediaCategoryDetails.ListResult>catagoriesList) {

        this.mContext = context;
        this.learning_Set =catagoriesList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.adapter_kz_home, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        ((ViewHolder) holder).text_title.setText(learning_Set.get(position).getNAME());

        if(learning_Set.get(position).getId() == 1017 ){
            Picasso.with(mContext )
                    .load(R.drawable.fertilizer_new)
                    .error(R.drawable.easy_farm_logo )
                    .placeholder( R.drawable.progress_animation)
                    .into(holder.img);

        }else if(learning_Set.get(position).getId() == 1018 )
        {
            Picasso.with(mContext )
                    .load(R.drawable.pestesa)
                    .error(R.drawable.easy_farm_logo )
                    .placeholder( R.drawable.progress_animation)
                    .into(holder.img);

        }
        else if(learning_Set.get(position).getId() == 1019)
        {
            Picasso.with(mContext )
                    .load(R.drawable.growing_new)
                    .error(R.drawable.easy_farm_logo )
                    .placeholder( R.drawable.progress_animation)
                    .into(holder.img);

        }

        else
        {
            Picasso.with(mContext )
                    .load(R.drawable.fertilizer_new)
                    .error(R.drawable.easy_farm_logo )
                    .placeholder( R.drawable.progress_animation)
                    .into(holder.img);

        }


        holder.learn_relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, EncyclopediaActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("postTypeId", learning_Set.get(position).getId());
                intent.putExtra("name", learning_Set.get(position).getNAME());
              //  intent.putExtra("teluguname", learning_Set.get(position).getTeluguName());




                    String[] tabnames = {mContext.getString(R.string.str_pdf),mContext.getString(R.string.str_videos)};
                    intent.putExtra("tabslist", tabnames);


                mContext.startActivity(intent);


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

    public  void  clearList()
    {
        learning_Set.clear();
        notifyDataSetChanged();
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

