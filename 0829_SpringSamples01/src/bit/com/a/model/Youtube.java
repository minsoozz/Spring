package bit.com.a.model;

import java.io.Serializable;

// 출력해주기 위한 DTO
public class Youtube implements Serializable {
	private String title;
	private String url;
	private String img;
	

	public Youtube() {
		// TODO Auto-generated constructor stub
	}

	public Youtube(String title, String url, String img) {
		super();
		this.title = title;
		this.url = url;
		this.img = img;
	}

	public Youtube(String title, String url, String img, String vname) {
		super();
		this.title = title;
		this.url = url;
		this.img = img;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "Youtube [title=" + title + ", url=" + url + ", img=" + img + "]";
	}
	
	public String getVname() {
		return toUrl(this.url);
	}
	
	/* =를 제거한 유튜브 영상 고유번호 */
		    
		    
   public String toUrl(String msg) {      
      if( msg.indexOf("=") == -1 ) {
         return msg;
      }else {
         return msg.substring(msg.indexOf("=")+1, msg.length() );
      }
   };

}
