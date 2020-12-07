package com.example.umorili.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.umorili.Constants;
import com.example.umorili.adapter.PostAdapter;
import com.example.umorili.model.PostModel;
import com.example.umorili.R;
import com.example.umorili.api.App;

import java.util.List;


import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ObserverableActivity extends AppCompatActivity {

    private static final String TAG = "ObserverableActivity";

    //ui
    private RecyclerView recyclerView;

    // vars
    private CompositeDisposable disposables = new CompositeDisposable();
    private PostAdapter adapter;
    List<PostModel> posts;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.posts_recycle_view);
        
        initRecyclerView();
        
        getPostsObservable()
                .subscribeOn(Schedulers.io())

//                .flatMap(new Function<PostModel, ObservableSource<PostModel>>() {
//                    @Override
//                    public ObservableSource<PostModel> apply(PostModel post) throws Exception {
//                        //в этом месте можно уйти на дополнительный API запрос
//                        return null;//getCommentsObservable(post);
//                    }
//                })
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new Observer<PostModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }

                    @Override
                    public void onNext(PostModel post) {
                        //Log.e(TAG,post.toString());
                        adapter.updatePost(post);
                        //updatePost(post);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    private void initRecyclerView() {

        adapter = new PostAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private Observable<PostModel> getPostsObservable() {
        return App.getRequestApi()

                .getPostModel(Constants.RESOURSENAME,Constants.COINT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<List<PostModel>, ObservableSource<PostModel>>() {
                    @Override
                    public ObservableSource<PostModel> apply(List<PostModel> postModels) throws Exception {
                        adapter.setPosts(postModels); // fill UI - наполняем UI
                        return Observable.fromIterable(postModels)
                                .subscribeOn(Schedulers.io());
                    }
                });



    }

//    private void updatePost(final PostModel p){
//        Observable
//                .fromIterable(adapter.getPosts())
//                .filter(new Predicate<PostModel>() {
//                    @Override
//                    public boolean test(PostModel post) throws Exception {
//                        return true;
//                        //return post.getId() == p.getId();
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<PostModel>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        disposables.add(d);
//                    }
//
//                    @Override
//                    public void onNext(PostModel post) {
//                        Log.d(TAG, "onNext: updating post: " + post.toString() + ", thread: " + Thread.currentThread().getName());
//                        adapter.updatePost(post);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.e(TAG, "onError: ", e);
//                    }
//
//                    @Override
//                    public void onComplete() {
//                    }
//                });
//    }

}
