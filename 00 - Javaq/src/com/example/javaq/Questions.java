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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Questions extends Activity {
    ListView listView = null;
    ArrayList<String> aList = null;
    InputStream inputStream = null;
    String customHtml = "";
    LoadHtmlFiles lhf = new LoadHtmlFiles();
    String limit = "";
    int _subLength=0;
    ArrayList<String> _subCheckList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
	    limit = extras.getString("limit");
	     
        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0091EA")));
        setContentView(R.layout.questions);
        listView = (ListView) findViewById(R.id.list);
        AssetManager assetManager = getResources().getAssets();
       try {
    	   aList = new ArrayList<String>();
    	   _subCheckList = new ArrayList<String>();
     	   		inputStream = assetManager.open("jqtest.json");
		        if (inputStream != null){
		        String text = lhf.loadTextFile(inputStream);
		        customHtml = text;
		       if("one".equals(limit)){
		    	   bar.setTitle(Html.fromHtml("<font color='#FFFFFF')> Java  Technologies</font>"));
		    	   setTitle("Java Technologies");
 		    	   JSONObject javaq = (new JSONObject(customHtml)).getJSONObject("javaq");
 			        JSONObject ques =  javaq.getJSONObject("ques");
 			        JSONArray topics =  ques.getJSONArray("topics");
 
			        _subLength = topics.length();
			        for(int i=0;i<=topics.length()-1;i++){
	            		aList.add(topics.getString(i).split("#")[0]);
	            	}
			        for(int i=0;i<=topics.length()-1;i++){
			        	//System.out.println("topics.getString(i).split()[1]"+  topics.getString(i).split("#")[1]  );
		    	    	//_subCheck = ques.getJSONArray(topics.getString(i).split("#")[1]);
			        	_subCheckList.add(topics.getString(i).split("#")[1]);
		    	    }
		    	    
			            } 
		       else  {
		    	    
		    	   String value = extras.getString("ItemValue");
		    	   bar.setTitle(Html.fromHtml("<font color='#FFFFFF')> "+value+"</font>"));
		           		
		    	   setTitle(value);
 		    	    JSONObject javaq = (new JSONObject(customHtml)).getJSONObject("javaq");
			        JSONObject ques =  javaq.getJSONObject("ques");
			        JSONArray topics =  ques.getJSONArray("topics");
		    	    JSONArray subtopics =  ques.getJSONArray(value);
		    	    
		    	    for(int i=0;i<=subtopics.length()-1;i++){
	            		aList.add((subtopics.getString(i)).split("#")[0]);
	            	}
		    	    System.out.println("aList"+aList.size());
		    	    for(int i=0;i<=subtopics.length()-1;i++){
		    	    	//_subCheck.put(ques.getJSONArray((subtopics.getString(i)).split("#")[1]);
			        	_subCheckList.add(subtopics.getString(i).split("#")[1]);

		    	    }
		    	    System.out.println("_subCheckList :"+_subCheckList.size());

 
		       }
		       }
		        
	    } catch (Exception e) {
	        	//((TextView)android.findViewById(R.id.textView)).setText("Exception: File Not Found.");
	    	customHtml = "File/Data Not Found"; 
	        }finally{
	        	lhf = null;
	        }
     ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
             android.R.layout.simple_list_item_1, android.R.id.text1, aList);
           // Assign adapter to ListView
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
                  try{
                	  System.out.println(_subCheckList +"_subCheck.length()" + _subCheckList.size());
                	  System.out.println(aList +"_subCheck.length()" + _subCheckList.size());
                	  if(_subCheckList.size()==0){
                		  System.out.println("-----------!00!-----------");
                		  Intent intent=new Intent(getApplicationContext(), Questions.class);
                    	  intent.putExtra("ItemValue", itemValue);
                    	  intent.putExtra("limit","two");
                    	  startActivity(intent);
                	
                	  }else{
                         	  System.out.println("_subCheckList.get(position)"+_subCheckList.get(position));
                        	  if("0".equals((_subCheckList.get(position)))){
                        		  System.out.println("--in 0 if--");
                        		  Intent intent=new Intent(getApplicationContext(), DisplayTopic.class);
                            	  intent.putExtra("ItemValue", itemValue);
                            	  intent.putExtra("limit","two");
                            	  startActivity(intent);  
                        	  }else{
                        		  System.out.println("--in 1 else--");
                        		  Intent intent=new Intent(getApplicationContext(), Questions.class);
                            	  intent.putExtra("ItemValue", itemValue);
                            	  intent.putExtra("limit","two");
                            	  startActivity(intent);
                        	  }
                         
                	  }
                      
	
                  }catch(Exception e){
                	  e.printStackTrace();
                  }
                   
                  
                 }
            }); 
    }
}  