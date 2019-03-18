package com.bkstudios.marvelapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class SearchHolder extends RecyclerView.ViewHolder {
    View view = null;
    Activity activity=null;
    public SearchHolder(@NonNull View itemView,Activity activity) {
        super(itemView);
        view=itemView;
        this.activity=activity;
    }
    public void setData(Search search){
        final TextView text = view.findViewById(R.id.search_name);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usertext=text.getText().toString();
                Intent intent=new Intent(activity,UserActivity.class);
                intent.putExtra("userclick",usertext);
                activity.startActivity(intent);
                //activity.finish();
            }
        });
        ImageView img = view.findViewById(R.id.search_image);
        text.setText(search.getLogin());

        Glide.with(activity)
                .load(search.getAvatar())
                .apply(RequestOptions.circleCropTransform())
                .into(img);


//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            img.setTransitionName("test");
//        }
    }
}
