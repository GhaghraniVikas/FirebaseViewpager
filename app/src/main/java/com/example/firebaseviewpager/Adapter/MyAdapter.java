package com.example.firebaseviewpager.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firebaseviewpager.Model.Wall;
import com.example.firebaseviewpager.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class MyAdapter extends PagerAdapter {


    Context context;
    List<Wall> wallList;
    LayoutInflater inflater;

    public MyAdapter(Context context, List<Wall> wallList, LayoutInflater inflater) {
        this.context = context;
        this.wallList = wallList;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return wallList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.view_page_item, container, false);
        //view
        ImageView wall_img = view.findViewById(R.id.wallp_img);
        TextView wall_title = view.findViewById(R.id.wall_title);
        TextView wall_description = view.findViewById(R.id.wall_description);

        FloatingActionButton btn_fav = view.findViewById(R.id.btn_fav);
        Picasso.get().load(wallList.get(position).getImage()).into(wall_img);
        wall_title.setText(wallList.get(position).getName());
        wall_description.setText(wallList.get(position).getDescription());

btn_fav.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
Toast.makeText(context,"fv clicked",Toast.LENGTH_SHORT).show();

    }
});
container.addView(view);
return  view;

    }
}

