package cmru.siriratanapaisalkul.pichate.cmrurun;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by PICHATE on 1/7/2559.
 */
public class FriendAdapter extends BaseAdapter{
    //Explicit
    private Context context;
    private int[] avataInts, goldInts;
    private String[] nameStrings;

    public FriendAdapter(Context context,
                         int[] avataInts,
                         int[] goldInts,
                         String[] nameStrings) {
        this.context = context;
        this.avataInts = avataInts;
        this.goldInts = goldInts;
        this.nameStrings = nameStrings;
    }

    @Override
    public int getCount() {
        return nameStrings.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.friend_listview, viewGroup, false);

        ImageView avataImageView = (ImageView) view1.findViewById(R.id.imageView8);
        ImageView goldImageView = (ImageView) view1.findViewById(R.id.imageView9);
        TextView textView = (TextView) view1.findViewById(R.id.textView10);

        avataImageView.setImageResource(avataInts[i]);
        goldImageView.setImageResource(goldInts[i]);
        textView.setText(nameStrings[i]);

        return view1;
    }
}   //Main Class
