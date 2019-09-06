package bit.com.a.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.AbstractView;

import bit.com.a.service.BitPdsService;

public class DownloadView extends AbstractView {

	@Autowired	// 이거만 붙히면 어디서는 접근이 가능하다 ! ! ! ! ! 
	BitPdsService pdsService;

	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 다운로드가 되면 무조건 여기로 온다
		System.out.println("DownloadView renderMergedOutputModel");

		File file = (File) model.get("downloadFile"); // getAttribute와 동일하다 (Model을 통해서 받을 때)
		response.setContentType(this.getContentType()); // 타입
		response.setContentLength((int) (file.length())); // 길이 값

		// IE , chrome
		String userAgent = request.getHeader("user-Agent");
		boolean ie = userAgent.indexOf("MSIE") > -1;

		String filename = null;

		if (ie) {
			filename = URLEncoder.encode(file.getName(), "utf-8");
		} else {
			filename = new String(file.getName().getBytes("utf-8"),"iso-8859-1");
		}
		
		// window download (다운로드 창)
		response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary;");
		response.setHeader("Content-Length", "" + file.length());
		response.setHeader("Pragma", "no-cache;"); 
		response.setHeader("Expires", "-1;");
		
		// io 
		OutputStream out = response.getOutputStream();
		FileInputStream fi = null;
		
		fi = new FileInputStream(file);
		
		// 파일이 다운로드 되는 부분
		FileCopyUtils.copy(fi, out);
		
		// download count 증가 , pdsService 접근 @Autowired 사용
		
		
		int seq = (int)model.get("seq"); // seq를 받는다

		if(fi != null) {
			fi.close();
		}
		
	}
	

}
