package in.calibrage.easyfarm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import in.calibrage.easyfarm.R;

public class SlideAdapter  extends SliderViewAdapter<SlideAdapter.SliderAdapterVH> {

private Context context;
private int mCount;
//private List<BannerresponseModel.ListResult> Banner_set;
List<String>imagelist = new ArrayList<>();
public SlideAdapter(Context context, List<String> imaelist) {
        this.context = context;
        this.imagelist = imaelist;
        }

public void setCount(int count) {
        this.mCount = count;
        }

@Override
public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
        return new SliderAdapterVH(inflate);
        }

@Override
public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {

    Glide.with(context).load(imagelist.get(position)).error(R.drawable.ic_user).into(viewHolder.imageViewBackground);



        }

@Override
public int getCount() {

        return mCount;
        }

class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

    View itemView;
    ImageView imageViewBackground;
    ImageView imageGifContainer;
    TextView textViewDescription;

    public SliderAdapterVH(View itemView) {
        super(itemView);
        imageViewBackground = itemView.findViewById(R.id.image);
//        imageGifContainer = itemView.findViewById(R.id.iv_gif_container);
//        textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
        this.itemView = itemView;
    }
}



}
