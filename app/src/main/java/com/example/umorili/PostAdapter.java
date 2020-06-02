package com.example.umorili;


import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private List<PostModel> posts;

    public PostAdapter(List<PostModel> posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    //  возвращает объект ViewHolder, который будет хранить данные по одному объекту
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    // выполняет привязку объекта ViewHolder к объекту  по определенной позиции
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PostModel postModel = posts.get(position);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.postModel.setText(Html.fromHtml(postModel.getElementPureHtml(), Html.FROM_HTML_MODE_LEGACY));
        } else {
            holder.postModel.setText(Html.fromHtml(postModel.getElementPureHtml()));
        }
    }

    @Override
    // возвращает количество объектов в списке
    public int getItemCount() {
        if (posts == null) return 0;
        return posts.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView postModel;
        TextView site;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            postModel = (TextView) itemView.findViewById(R.id.postitem_post);
            site = (TextView) itemView.findViewById(R.id.postitem_site);

        }
    }
}
