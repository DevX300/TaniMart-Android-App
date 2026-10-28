package com.washeed.tanimart.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.washeed.tanimart.R;
import com.washeed.tanimart.data.entities.Article;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    private List<Article> articleList;

    private int displayLimit = Integer.MAX_VALUE;
    public void setDisplayLimit(int displayLimit) {
        this.displayLimit = displayLimit;
        notifyDataSetChanged();
    }
    public ArticleAdapter(List<Article> articleList) {
        this.articleList = articleList;
    }
    public void setArticles(List<Article> articleList) {
        this.articleList = articleList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ArticleAdapter.ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_list_view, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleAdapter.ArticleViewHolder holder, int position) {
        Article article = articleList.get(position);
        holder.articleCategory.setText(article.getArticleCategory());
        holder.articleTitle.setText(article.getArticleName());
        holder.articleDate.setText(article.getArticlePublishDate());
        holder.articleView.setText("👁️ "+article.getArticleViews()+"k Read");
        //set article image
        int imageResId = holder.itemView.getContext()
                .getResources().getIdentifier(article.getArticlePictureID(), "drawable", holder.itemView.getContext().getPackageName());
        holder.articleImage.setImageResource(imageResId);
    }
    @Override
    public int getItemCount() {
        return  articleList != null?Math.min(articleList.size(), displayLimit):0;
    }

    static class ArticleViewHolder extends RecyclerView.ViewHolder{
        ImageView articleImage;
        TextView articleCategory,articleTitle, articleDate, articleView;
        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            articleImage=itemView.findViewById(R.id.articleImage);
            articleCategory=itemView.findViewById(R.id.articleCategory);
            articleTitle=itemView.findViewById(R.id.articleTitle);
            articleDate=itemView.findViewById(R.id.articleDate);
            articleView=itemView.findViewById(R.id.articleViews);
        }
    }
}
