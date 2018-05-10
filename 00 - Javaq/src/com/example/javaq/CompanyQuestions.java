package com.example.javaq;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.SubMenu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class CompanyQuestions extends Activity {  
    @Override  
    protected void onCreate(Bundle savedInstanceState) { 
		 
        super.onCreate(savedInstanceState);
        
        /*Bundle extras = getIntent().getExtras();
        String value = extras.getString("ItemValue");*/
        
        String value = "sai";
        TextView textview=new TextView(this);  
        textview.setText("Position CompanyQuestions: ");  
        setContentView(textview);  
        
   
    }  
 /*   @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
	//	getMenuInflater().inflate(R.menu.main, menu);
    	SubMenu f1=menu.addSubMenu("f1");
    	SubMenu f2=menu.addSubMenu("f2");
    	return super.onCreateOptionsMenu(menu);
    	
    }*/
    
   /* @Override  
    public boolean onCreateOptionsMenu(Menu menu) {  
        // Inflate the menu; this adds items to the action bar if it is present.  
        getMenuInflater().inflate(R.menu.activity_main, menu);  
        return true;  
    }*/  
}  