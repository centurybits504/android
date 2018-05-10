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

public class QuestionsSubTopics extends Activity {
	 
	ListView listView  = null;
	 ArrayList<String> _subTopics = new ArrayList<String>();
	 InputStream inputStream = null;
	 String customHtml = "";
	 LoadHtmlFiles lhf = new LoadHtmlFiles();
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();
	    String value = extras.getString("ItemValue");
	    
		ActionBar bar = getActionBar();
	    bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0091EA")));
	    bar.setTitle(Html.fromHtml("<font color='#FFFFFF')>"+value+" Topics</font>"));
		setContentView(R.layout.questions_topics);
		
		
		listView = (ListView) findViewById(R.id.list);
       AssetManager assetManager = getResources().getAssets();
      try {
	     
		        inputStream = assetManager.open("jqtest.json");
		            if (inputStream != null){
		            	String text = lhf.loadTextFile(inputStream);
		            	customHtml = text;
		            	JSONObject javaq = (new JSONObject(customHtml)).getJSONObject("javaq");
		            	JSONObject ques =  javaq.getJSONObject("ques");
		            	JSONArray subtopics =  ques.getJSONArray(value);
		            	
		            	for(int i=0;i<=subtopics.length()-1;i++){
		            		_subTopics.add(subtopics.getString(i));
		            	}

		    		System.out.println("------value-----"+value);
		    		System.out.println("------_subTopics-----"+_subTopics);
		            }
	    } catch (Exception e) {
	        	//((TextView)android.findViewById(R.id.textView)).setText("Exception: File Not Found.");
	    	customHtml = " File/Data Not Found "; 
	        }finally{
	        	lhf = null;
	        }

		
		 
			 listView = (ListView) findViewById(R.id.questionssubtopics_list);
			    
			    
			    ArrayAdapter<String> adapter = null;
			  
			    		adapter = new ArrayAdapter<String>(this,
			    	           android.R.layout.simple_list_item_1, android.R.id.text1, _subTopics);
			    	          listView.setAdapter(adapter); 
			
			    	          
			    	          // ListView Item Click Listener
			    	          listView.setOnItemClickListener(new OnItemClickListener() {
			    	  			@Override
			    	  			public void onItemClick(AdapterView<?> parent, View view,
			    	  					int position, long id) {
			    	                 // ListView Clicked item index
			    	                 int itemPosition     = position;
			    	                 // ListView Clicked item value
			    	                 String  itemValue    = (String) listView.getItemAtPosition(position);
			    	                  // Show Alert 
			    	                 //Toast.makeText(getApplicationContext(),"Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG).show();
			    	                 Intent intent=new Intent(getApplicationContext(), DisplayTopic.class);
			    	                 intent.putExtra("ItemValue", itemValue);
			    	      			startActivity(intent);
			    	                 
			    	  			
			    	  			}

			    	           }); 
		 
		
	   
	    	          
	}

}
