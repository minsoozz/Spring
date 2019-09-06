package bit.com.a.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import bit.com.a.model.Youtube;

@Component 	// 싱글톤 처럼 사용한다..?
public class YoutubeParser {
	String urls = "https://www.youtube.com/results?search_query=";
	ArrayList<String> htmls = new ArrayList<String>();

	public ArrayList<String> search(String s) {

		htmls.clear();

		BufferedReader br = null;

		try {
			String ss = URLEncoder.encode(s, "utf-8"); // 지금 들어온 것을 utf-8로 바꾸어 주겠다
		
			URL url = new URL(urls + ss); // 주소 + 검색어 url이 완성된다
			br = new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));
			
			String msg = "";
			while((msg = br.readLine()) != null) {	// 데이터의 끝까지 읽어와라..?
				if(msg.trim().contains("class=\"yt-uix-tile-link yt-ui-ellipsis yt-ui-ellipsis-2 yt-uix-sessionlink")) {
					htmls.add(msg.trim());
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return htmls;
	}
	
	// $를 제거 /watch?v=백종원&12308
	public String toUrl(String msg) {
		String tt = "";
		if(msg.indexOf("&") == -1) {
			tt = msg;
		} else { // 있으면
			msg.substring(0, msg.indexOf("&"));
		}
		
		return tt;
	};
	
	
	// 제목
	public ArrayList<Youtube> getTitles(String key) {

		ArrayList<Youtube> af = new ArrayList<Youtube>();
		int i = 0;
		
		
		
		ArrayList<String> asd = search(key);

		for (int j = 0; j < asd.size(); j++) {

			try {	

				String fu[] = asd.get(i).split("\"");
				String url = URLDecoder.decode(fu[5], "EUC-KR");
				String title = URLDecoder.decode(fu[11], "EUC-KR");
				Youtube f = new Youtube(title, toUrl(url), "");
				af.add(f);
				
			} catch (Exception e) {

				e.printStackTrace();
			}
			i++;
		}
		return af;
	}
}
