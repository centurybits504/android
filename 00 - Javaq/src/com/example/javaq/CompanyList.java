package com.example.javaq;

import java.io.InputStream;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
/*Display Company Names*/
public class CompanyList extends Activity  {
    ListView listView = null;
    ArrayList<String> aList = new ArrayList<String>();
    InputStream inputStream = null;
    String customHtml = "";
    LoadHtmlFiles lhf = new LoadHtmlFiles();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0091EA")));
        bar.setTitle(Html.fromHtml("<font color='#FFFFFF')> Companies List</font>"));
		setContentView(R.layout.company_list);
		
		 
		
		 listView = (ListView) findViewById(R.id.company_list);
       AssetManager assetManager = getResources().getAssets();
       try {
	        inputStream = assetManager.open("jqtest.json");
	        

	            if (inputStream != null){
	            	String text = lhf.loadTextFile(inputStream);
	            	customHtml = text;
	            	JSONObject javaq = (new JSONObject(customHtml)).getJSONObject("javaq");
	        
	            	JSONObject com_ques =  javaq.getJSONObject("com_ques");
	        
	            	JSONArray comp =  com_ques.getJSONArray("comp");
	            
	            	for(int i=0;i<=comp.length()-1;i++){
	            		aList.add(comp.getString(i));
	            	}

	    		System.out.println("-----------------"+aList);
	   
	            }
   } catch (Exception e) {
       	//((TextView)android.findViewById(R.id.textView)).setText("Exception: File Not Found.");
   	customHtml = "Exception: File Not Found"; 
       }finally{
       	lhf = null;
       }
       
       
       
       
       ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
         android.R.layout.simple_list_item_1, android.R.id.text1, aList);
       // Assign adapter to ListView
       listView.setAdapter(adapter); 
       
       
       listView.setOnItemClickListener(new OnItemClickListener() {

           

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
               
              // ListView Clicked item index
              int itemPosition     = position;
              
              // ListView Clicked item value
              String  _companyName    = (String) listView.getItemAtPosition(position);
              
           	   Intent intent=new Intent(getApplicationContext(), DisplayCompany.class);
                  intent.putExtra("companyName", _companyName);
   				startActivity(intent);   
                
              
             }

        }); 
       
       
	}

}
