package adapters.jdhau.me;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


import huzykamz.jdhau.huzy.ask_doctor.ImageLoader;
import huzykamz.jdhau.huzy.ask_doctor.R;
import model.DoctorsModel;
import model.NewsModel;

/**
 * Created by HUZY_KAMZ on 2/5/2016.
 */
public class NewsAdapter  extends ArrayAdapter<NewsModel> {
    private Context context;
    private List<NewsModel> flowerList;
    ImageLoader imageLoader;

    public NewsAdapter(Context context, int resource, List<NewsModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.flowerList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_news, parent, false);


        //Display flower name in the TextView widget
        NewsModel flower = flowerList.get(position);
        TextView head = (TextView) view.findViewById(R.id.headlines);
        TextView detail = (TextView) view.findViewById(R.id.details);




        head.setText(flower.getHeadlines());
        detail.setText(flower.getDetails());


        //Display flower photo in ImageView widget
        ImageView image = (ImageView) view.findViewById(R.id.image_news);
        image.setImageBitmap(flower.getBitmap());


        return view;
    }




}




