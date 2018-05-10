package com.example.javaq;

import java.io.InputStream;

import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.webkit.WebView;
import android.widget.TextView;

/*To display Company Questions*/
public class DisplayCompany extends Activity {  
	public String sharedPref = "sai";
	private WebView webView;
	public static final int MENU_CONCEPT = Menu.FIRST;
	public static final int MENU_PROGRAMS = Menu.FIRST + 1;
	public static final int MENU_QUESTIONS = Menu.FIRST + 2;

	
	 /*@Override
	    public boolean onCreateOptionsMenu(Menu menu)
	    {
		 	getMenuInflater().inflate(R.menu.main, menu);
	    	SubMenu concept = menu.addSubMenu("Concept");
	    	SubMenu programs = menu.addSubMenu("Programs");
	    	SubMenu questions = menu.addSubMenu("Questions");
	    	return super.onCreateOptionsMenu(menu);
	    	
	    }*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    super.onCreateOptionsMenu(menu);

	    menu.add(Menu.NONE, MENU_CONCEPT, Menu.NONE, "Concept");
	    menu.add(Menu.NONE, MENU_PROGRAMS, Menu.NONE, "Programs");
	    menu.add(Menu.NONE, MENU_QUESTIONS, Menu.NONE, "Questions");
	    return true;
	}

	   @Override
	    public boolean onOptionsItemSelected(MenuItem item)
	    {
	        switch(item.getItemId())
	        {
	            case MENU_CONCEPT:
	            	WebView webView1 = (WebView) findViewById(R.id.webView);
	        		//WebView.setHorizontalScrollBarEnabled(false);
	        		webView1.setHorizontalScrollBarEnabled(false);
	        		webView1.loadUrl("file:///android_asset/"+sharedPref+"_MODEL_CONCEPT.html");
	            return true;
	        case MENU_PROGRAMS:
	        	WebView webView2 = (WebView) findViewById(R.id.webView);
        		//WebView.setHorizontalScrollBarEnabled(false);
        		webView2.setHorizontalScrollBarEnabled(false);
        		webView2.loadData(sharedPref+"MENU_PROGRAMS", "text/html", "UTF-8");
 	            return true;
	        case MENU_QUESTIONS:
	        	WebView webView3 = (WebView) findViewById(R.id.webView);
        		//WebView.setHorizontalScrollBarEnabled(false);
        		webView3.setHorizontalScrollBarEnabled(false);
        		webView3.loadData(sharedPref+"MENU_QUESTIONS", "text/html", "UTF-8");
 	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	        }
	    }
    @Override  
    protected void onCreate(Bundle savedInstanceState) { 
    	super.onCreate(savedInstanceState);
		
    ActionBar bar = getActionBar();
    bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0091EA")));
    	
    
    
    
		Bundle extras = getIntent().getExtras();
        String value = extras.getString("companyName");
        setTitle(value);
        sharedPref = value;
        setContentView(R.layout.display_company);
        LoadHtmlFiles lhf = new LoadHtmlFiles();
        AssetManager assetManager = getResources().getAssets();
       InputStream inputStream = null;
       String customHtml = "";
       try {
	        inputStream = assetManager.open("jqtest.json");
	        

	            if (inputStream != null){
	            	String text = lhf.loadTextFile(inputStream);
	            	customHtml = text;
	            	JSONObject javaq = (new JSONObject(customHtml)).getJSONObject("javaq");
	        
	            	JSONObject com_ques =  javaq.getJSONObject("com_ques");
	            	customHtml = com_ques.getString(value);
	            	
	            
	            	 
	            }
  } catch (Exception e) {
      	//((TextView)android.findViewById(R.id.textView)).setText("Exception: File Not Found.");
  	customHtml = "Exception: File Not Found"; 
      }finally{
      	lhf = null;
      }
       
		WebView webView = (WebView) findViewById(R.id.webView);
		//WebView.setHorizontalScrollBarEnabled(false);
		webView.setHorizontalScrollBarEnabled(false);
		webView.loadData(customHtml, "text/html", "UTF-8");
    	
    }
    
    
    
}
