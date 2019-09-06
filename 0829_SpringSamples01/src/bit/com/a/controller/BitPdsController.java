package bit.com.a.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import bit.com.a.model.BbsParam;
import bit.com.a.model.PdsDto;
import bit.com.a.service.BitPdsService;
import bit.com.a.util.FUpUtil;
import bit.com.a.util.FileDelete;

@Controller
public class BitPdsController {
	private static final Logger logger = LoggerFactory.getLogger(BitBbsController.class);

	@Autowired
	BitPdsService pdsService;

	@RequestMapping(value = "pdslist.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String pdslist(Model model,BbsParam param) {

		model.addAttribute("doc_title", "자료실 목록");

		// 글의 총 수 
		int totalRecordCount = pdsService.getPdsCount(param);
				
		
		
		// pageNumber 취득
		int sn = param.getPageNumber(); // 0 , 1, 2
		int start = sn * param.getRecordCountPerPage() + 1; // 0 -> 1 , 1 - > 11		1   11
		int end = (sn + 1) * param.getRecordCountPerPage(); // 0 - > 10, 1 - > 20		10  20
			
		param.setStart(start);
		param.setEnd(end);
		
		System.out.println("param : " +  param.toString());
		
		List<PdsDto> list = pdsService.getPdsList(param);

		model.addAttribute("pdslist", list);
		model.addAttribute("s_category",param.getS_category());
		model.addAttribute("s_keyword",param.getS_keyword());
		
		model.addAttribute("pageNumber",sn); // 현재 페이지 넘버
		model.addAttribute("pageCountPerScreen",10);
		model.addAttribute("recordCountPerPage",param.getRecordCountPerPage());
		model.addAttribute("totalRecordCount", totalRecordCount);
		
		return "pdslist.tiles";
	}

	@RequestMapping(value = "pdswrite.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String pdswrite(Model model) {

		model.addAttribute("doc_title", "자료 올리기");

		return "pdswrite.tiles";
	}

	@RequestMapping(value = "pdsupload.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String pdsupload(PdsDto pdsdto, @RequestParam(value = "fileload", required = false) MultipartFile fileload,
			HttpServletRequest req) {
		
		
		// filename을 취득
		String filename = fileload.getOriginalFilename(); // mydata
		
		if(filename.length() > 0) {
			
			System.out.println("filename : " + filename + filename.length());
			pdsdto.setFilename(filename); // 실제 filename 을 넣어준다

		// upload 경로 설정
		// tomcat
		String fupload = req.getServletContext().getRealPath("/upload");

		// 폴더
		// String fupload = "d\\tmp";

		logger.info("fupload : " + fupload + " " + new Date());

		// 파일명 변경, DB <- 파일명을 변경 abc.txt -> 123456.txt -> abc, 123456
		String f = pdsdto.getFilename();
		String newfilename = FUpUtil.getNewFileName(f);

		// 업로드
		pdsdto.setFilename(newfilename);

		File file = new File(fupload + "/" + newfilename);

		try {
			// 실제 파일이 업로드 되는 부분
			FileUtils.writeByteArrayToFile(file, fileload.getBytes());
			pdsService.uploadPds(pdsdto);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		} else {
			System.out.println(pdsdto.toString());
			pdsdto.setFilename("왜 낫널이냐고");
			pdsService.uploadPds(pdsdto);

		}

		return "redirect:/pdslist.do";
	}

	@RequestMapping(value = "fileDownload.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String fileDownload(String filename, int seq, HttpServletRequest req, Model model) { // 자동으로 넘어온다

		logger.info("fileDownload.do filename : " + filename + " seq : " + seq);

		// download 경로

		// tomcat 경로
		String fupload = req.getServletContext().getRealPath("/upload");

		// 폴더 경로
		// String fupload = "d:\\tmp";

		File downloadFile = new File(fupload + "/" + filename);

		model.addAttribute("downloadFile", downloadFile);
		model.addAttribute("seq", seq);

		return "downloadView";
	}

	@RequestMapping(value = "pdsdetail.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String pdsdetail(int seq, Model model) {
					pdsService.readcount(seq);
		
		PdsDto dto = pdsService.pdsdetail(seq);
		
		model.addAttribute("dto", dto);

		return "pdsdetail.tiles";
	}

	
	@RequestMapping(value = "pdsupdate.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String pdsupdate(int seq, Model model) {
		
		PdsDto dto = pdsService.pdsdetail(seq);
		
		model.addAttribute("dto", dto);
		
		return "pdsupdate.tiles";
	}
	
	@RequestMapping(value = "pdsupdateAf.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String pdsupdateAf(PdsDto dto,Model model, HttpServletRequest req,
			@RequestParam(value = "file2", required = false) MultipartFile newfile) {	// 어려워어어어
		
		String file2 = newfile.getOriginalFilename();	// 형 변환~
		System.err.println("file2 : " + file2);
		String filename = ""; // 초기화 ~ 
		
		if(file2 == "" || file2 == null){
			filename = dto.getFilename() ;
		} else {
			filename = file2 ;
		}
		
		System.out.println("filename : " + filename);
		
		Map<String,Object> map = new HashMap<>();
		
		map.put("filename", filename);
		map.put("title", dto.getTitle());
		map.put("content", dto.getContent());
		map.put("seq", dto.getSeq());
		

				// filename을 취득
//				String filename = fileload.getOriginalFilename(); // mydata
//				pdsdto.setFilename(filename); // 실제 filename 을 넣어준다

				// upload 경로 설정
				// tomcat
				String fupload = req.getServletContext().getRealPath("/upload");
				System.err.println("fupload :" + fupload );
				
				// 폴더
				// String fupload = "d\\tmp";

				
				// 파일명 변경, DB <- 파일명을 변경 abc.txt -> 123456.txt -> abc, 123456
				String f = filename;
				System.err.println("f : " + f);
				
				String newfilename = FUpUtil.getNewFileName(f);
				System.err.println("newfilename : " + newfilename);

				// 업로드
				
				dto.setFilename(newfilename);

				File file = new File(fupload + "/" + newfilename);
				
				System.err.println("file : " + file);
				try {
					// 실제 파일이 업로드 되는 부분
					FileUtils.writeByteArrayToFile(file, newfile.getBytes());
					pdsService.pdsupdate(dto);

				} catch (IOException e) {
					e.printStackTrace();
				}

				return "redirect:/pdslist.do";
	}
		@RequestMapping(value = "pdsdelete.do", method = { RequestMethod.GET, RequestMethod.POST })
		public String pdsdelete(int seq, Model model,HttpServletRequest req) {
			
			String filename = pdsService.getfilename(seq);
			
			// tomcat 경로
			String fupload = req.getServletContext().getRealPath("/upload");

			
			FileDelete.main(fupload + "/" + filename);
			
			
			pdsService.pdsdelete(seq);

			return "redirect:/pdslist.do";

		}
		
		@RequestMapping(value="downcount.do", produces="applecation/String; charset=UTF-8",
				method= {RequestMethod.GET,RequestMethod.POST})
		@ResponseBody
		public String downcount(int seq) {
			String n = "";
			System.out.println("seq : "  + seq  );
			
			boolean b =  pdsService.downcount(seq); // ajax로 downcount 가 증가한다
			
			if(b) {
				n = "1";
			}
			else { 
				n = "0";
			}
			System.out.println("n : "  + n);
			return n;
		}
}
