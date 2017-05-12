package com.example.android.fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

public class MainActivity extends FragmentActivity implements HeadlinesFragment.OnHeadlineSelectedListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_articles);
        
        if(findViewById(R.id.fragment_container) != null) {
        	if(savedInstanceState != null) {
        		return;
        	}
        	HeadlinesFragment fisrtFragment = new HeadlinesFragment();
        	fisrtFragment.setArguments(getIntent().getExtras());
        	getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,fisrtFragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

	@Override
	public void onArticleSelected(int position) {
		ArticleFragment articleFrag = (ArticleFragment) getSupportFragmentManager().findFragmentById(R.id.article_fragment);
    	if(articleFrag != null) {
    		articleFrag.updateArticleView(position);
    	} else {
    		ArticleFragment newFragment = new ArticleFragment();
    		Bundle args = new Bundle();
    		args.putInt(ArticleFragment.ARG_POSITION, position);
    		newFragment.setArguments(args);
    		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    		transaction.replace(R.id.fragment_container, newFragment);
    		transaction.addToBackStack(null);
    		transaction.commit();
    	}		
	}

    
}
