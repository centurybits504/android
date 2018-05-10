package com.example.javaq;

import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ActionBar;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class Windows extends Fragment {
	private WebView webview ;   
	View mView;
	 @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
			
			/*new code for webview*/	
				 mView = inflater.inflate(R.layout.ios_frag, container, false);
				  //webview use to call own site
			    webview =(WebView)mView.findViewById(R.id.webView);
			    /*new code for webview*/
			
				ActionBar bar = this.getActivity().getActionBar();
		        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0091EA")));
		        
				Bundle b = getActivity().getIntent().getExtras();
			    String value = (String)b.get("ItemValue");
			
		        bar.setTitle(Html.fromHtml("<font color='#FFFFFF')>"+value+"</font>"));
		        
		        
		        
		        
		        

			
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
			            	JSONObject ques =  javaq.getJSONObject("ques");
			            	//customHtml = ques.getString(value);
			            	JSONArray _tabbs =  ques.getJSONArray(value);

			            	customHtml = _tabbs.getString(1);

			            
			            	 
			            }
		} catch (Exception e) {
		   	//((TextView)android.findViewById(R.id.textView)).setText("Exception: File Not Found.");
			customHtml = "Oops! No Data"; 
		   }finally{
		   	lhf = null;
		   }
		     
			
			
			
			
			
			
			
					
		    		/*View android = inflater.inflate(R.layout.android_frag, container, false);
			        TextView tv = ((TextView)android.findViewById(R.id.textView));
			        tv.setMovementMethod(new ScrollingMovementMethod());
			        tv.setText(Html.fromHtml(customHtml));
			        return android;*/
		    
				    /*new code for webview*/  
				    webview.setWebViewClient(new WebViewClient());          
				   // String header = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";
		 		   // webview.loadData("<html>"+customHtml+"</html>", "text/html; charset=utf-8", "UTF-8");
				    String htmlData = "" ;
				    webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
				    //webview.loadDataWithBaseURL("file:///android_asset/", htmlData+customHtml, "text/html", "UTF-8", null);
				    System.out.println("value------3-----------"+value);
				    webview.loadUrl("file:///android_asset/"+value+"_QUESTIONS.html");
				    webview.getSettings().setBuiltInZoomControls(true);
				   
				    //webview.setInitialScale(50);
				    return webview;
				    /*new code for webview*/
		 
				    //htmlData = "<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\" />" + htmlData;
				 // lets assume we have /assets/style.css file
				 //webView.loadDataWithBaseURL("file:///android_asset/", htmlData, "text/html", "UTF-8", null);
			        
}}
