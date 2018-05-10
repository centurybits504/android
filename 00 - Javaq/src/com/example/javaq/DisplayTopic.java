package com.example.javaq;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;


import android.view.Menu;
import android.view.SubMenu;
import android.view.Window;

public class DisplayTopic extends  FragmentActivity {
	ViewPager Tab;
    TabPagerAdapter TabAdapter;
	ActionBar actionBar;

    
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        
        
        
        setContentView(R.layout.display_topic);
        
        
         
        
        
        
        TabAdapter = new TabPagerAdapter(getSupportFragmentManager());
        
        Tab = (ViewPager)findViewById(R.id.pager);
        
        Tab.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    
                    public void onPageSelected(int position) {
                       
                    	actionBar = getActionBar();
                    	actionBar.setSelectedNavigationItem(position);                    }
                });
        Tab.setAdapter(TabAdapter);
        
        actionBar = getActionBar();
        //Enable Tabs on Action Bar
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.TabListener tabListener = new ActionBar.TabListener(){

			@Override
			public void onTabReselected(android.app.ActionBar.Tab tab,
					FragmentTransaction ft) {
				// TODO Auto-generated method stub
				
			}

			@Override
			 public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
	          
	            Tab.setCurrentItem(tab.getPosition());
	        }

			@Override
			public void onTabUnselected(android.app.ActionBar.Tab tab,
					FragmentTransaction ft) {
				// TODO Auto-generated method stub
				
			}};
			//Add New Tab
			actionBar.addTab(actionBar.newTab().setText("Concept").setTabListener(tabListener));
			actionBar.addTab(actionBar.newTab().setText("Programs").setTabListener(tabListener));
			actionBar.addTab(actionBar.newTab().setText("Questions").setTabListener(tabListener));

    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
	//	getMenuInflater().inflate(R.menu.main, menu);
    	
    	SubMenu f1=menu.addSubMenu("f1");
    	SubMenu f2=menu.addSubMenu("f2");
    	
		
    	return super.onCreateOptionsMenu(menu);
    	
    }


    
}
