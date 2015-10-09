package assessment.mycompany.com.emojis.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

import assessment.mycompany.com.emojis.R;
import assessment.mycompany.com.emojis.models.EmojiData;

/**
 * Created by Debasis on 10/07/2015.
 */
public class EmojisListAdapter extends BaseAdapter {
    private Context context;
    private List<EmojiData> emojiDataList;
    private static final String TAG = EmojisListAdapter.class.getCanonicalName();
    private ImageLoader imageLoader;

    public EmojisListAdapter(Context currentContext, List<EmojiData> trendingShowNameList) {
        this.context = currentContext;
        this.emojiDataList = trendingShowNameList;
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(currentContext).build();
        this.imageLoader = ImageLoader.getInstance();
        this.imageLoader.init(config);
    }

    @Override
    public int getCount() {
        return emojiDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return emojiDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return emojiDataList.indexOf(emojiDataList.get(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.layout_list_row_item_emojis, parent, false);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.emojisImage = (ImageView) convertView.findViewById(R.id.emojis_imageview);
        viewHolder.emojisName = (TextView) convertView.findViewById(R.id.emojis_name_textView);

        String emojisImageUrl = emojiDataList.get(position).getEmojisImageUrl();
        String emojisName = emojiDataList.get(position).getEmojisName();

        viewHolder.emojisName.setText(emojisName);
        imageLoader.displayImage(emojisImageUrl, viewHolder.emojisImage);
        convertView.setTag(viewHolder);
        return convertView;
    }

    private static class ViewHolder {
        ImageView emojisImage;
        TextView emojisName;
    }
}
