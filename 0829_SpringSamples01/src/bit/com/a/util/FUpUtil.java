package bit.com.a.util;

import java.util.Date;

public class FUpUtil {

	// f = myfile.txt = > f.indexOf(.) -> 6
	// f.substring( 6 ) => txt
	// f.substring( 0,6 ) => myfile

	// myfile.txt ---> 2123123.txt (문자열)
	public static String getNewFileName(String f) {
		String filename = "";
		String fpost = "";
		
		if(f == null || f == "") {
			filename = "";
		}
		
		
		else if (f.indexOf('.') >= 0) {
			fpost = f.substring(f.indexOf('.')); // .txt
			filename = new Date().getTime() + fpost; // 4545 + .txt
		} else  { // 확장자명이 없을 때
//			filename = new Date().getTime() + ".back";
		}
		return filename;
	}

}
