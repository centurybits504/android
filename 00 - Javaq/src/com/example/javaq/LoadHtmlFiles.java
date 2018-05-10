package com.example.javaq;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class LoadHtmlFiles {
	   public String loadTextFile(InputStream inputStream) throws IOException {
		    ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		    byte[] bytes = new byte[4096];
		    int len = 0;
		    while ((len = inputStream.read(bytes)) > 0)
		      byteStream.write(bytes, 0, len);
		    return new String(byteStream.toByteArray(), "UTF8");
		  }

}
