package com.kosmo.board;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kosmo.common.PagingUtil;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


@Controller
public class BoardController { //extends MultiActionController {
	private static final long serialVersionUID = 1L;

	@Autowired
	private BoardService service;   //BoardServiceImpl service 로 하면안됨. 프록시가 가로채서

	@Value("${upload_file_max_size}")
	int upload_file_max_size;
	@Value("${upload_file_dir}")
	String upload_file_dir;
	@Value("${upload_file_format}")
	String upload_file_format;

	@RequestMapping(value = "/list.spring", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response)
	{
		int currentPage = 1;

			if(request.getParameter("currentPage") != null) {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}

			int totalCount = service.boardCount();

			//------------페이징
			PagingUtil pu
			= new PagingUtil("/list.spring?"
					, currentPage
					, totalCount  //------------
					, 5	//선택한 2번 블럭에 나타날 게시물 갯수
					, 5 // 1 2 [다음]
					);
			String  html = pu.getPagingHtml();

			ArrayList<BoardVO> list = service.boardList(pu.getStartSeq(), pu.getEndSeq());

			ModelAndView mav = new ModelAndView();

			mav.addObject("LVL_COUNT", totalCount);
			mav.addObject("LVL_LIST", list);
			mav.addObject("LVL_PAGING", html);

			mav.setViewName("board/board_list");
			return mav;
		}

	@RequestMapping(value = "/detail.spring", method = RequestMethod.GET)
	public ModelAndView detail(HttpServletRequest request, HttpServletResponse response)
	{
			String bseq = request.getParameter("bseq");
			BoardVO vo = new BoardVO();
			vo = service.boardDetail(Integer.parseInt(bseq));

			ModelAndView mav = new ModelAndView();
			mav.addObject("LVL_VO", vo);
			mav.setViewName("board/board_detail");
			return mav;
		}

	@RequestMapping(value = "/edit.spring", method = RequestMethod.GET)
	public ModelAndView edit(
			HttpServletRequest request,
			HttpServletResponse response
			)
	{
			String bseq = request.getParameter("bseq");
			//BoardDAO impl = new BoardDAO();
			BoardVO vo = new BoardVO();
			vo = service.boardDetail(Integer.parseInt(bseq));

			ModelAndView mav = new ModelAndView();
			mav.addObject("LVL_VO", vo);
			mav.setViewName("board/board_edit");
			return mav;
		}

	@RequestMapping(value = "/insert.spring", method = RequestMethod.POST)
	public String insert(BoardVO vo) throws IOException{
		
//			HttpSession session = request.getSession();

//			vo.setMseq(1); //Integer.parseInt(mseq));

//			String ufile = mRequest.getFilesystemName("ufile");
			//신규첨부파일
			MultipartFile ufile = vo.getUfile();
			if(ufile != null) {
				String fullPath = upload_file_dir+"\\"+ufile.getOriginalFilename();
				File newFile = new File(fullPath); //파일생성
				//byte[] bytes = ufile.getBytes();		//바이트에 담아서 쓰는방법
				//FileCopyUtils.copy(bytes, newFile);	//copy는 static 이라서 new안하고도 쓸 수 있음!(2줄타이핑)
				ufile.transferTo(newFile);	//이런방법도 있음!
				vo.setBfile_size(ufile.getSize());
				vo.setBfile_path(upload_file_dir);
				vo.setBfile_name(ufile.getOriginalFilename());
			}

			int res = service.boardInsert(vo);
			System.out.println(res + "건 입력");
			return "redirect:/list.spring";

		}

	@RequestMapping(value = "/update.spring", method = RequestMethod.POST)
	public String update(HttpServletRequest request, HttpServletResponse response)
	throws IOException
	{

			MultipartRequest mRequest = new MultipartRequest
					(request, upload_file_dir, upload_file_max_size,
							upload_file_format,
							new DefaultFileRenamePolicy());


			//BoardDAO impl = new BoardDAO();
			BoardVO vo = new BoardVO();

			String bseq = mRequest.getParameter("bseq");
			String btype = mRequest.getParameter("btype");
			String btitle = mRequest.getParameter("btitle");
			String bcon = mRequest.getParameter("bcon");
			String mseq = mRequest.getParameter("mseq");

			String bfile_path = mRequest.getParameter("bfile_path");
			String bfile_name = mRequest.getParameter("bfile_name");
			String ufile = mRequest.getFilesystemName("ufile");

			
			vo.setBseq(Integer.parseInt(bseq));
			vo.setBtitle(btitle);
			vo.setBcon(bcon);

			int res = 0;
			if(ufile == null) {		//기존 첨부파일 유지
				res = service.boardUpdate(vo);
			} else {				//신규 첨부파일 등록
				//시스템에 기존 첨부파일 삭제
				File oldFile = new File(bfile_path+"/"+bfile_name); //파일생성
				if(oldFile.exists())
					oldFile.delete();

				//시스템에 신규 첨부파일 생성
				String fullPath = upload_file_dir+"\\"+ufile;
				File newFile = new File(fullPath); //파일생성
				vo.setBfile_size(newFile.length());
				vo.setBfile_path(upload_file_dir);
				vo.setBfile_name(ufile);

				//DB에 게시물 등록
				res = service.boardUpload(vo);
			}
			return "redirect:/detail.spring?bseq="+bseq;

		}

	@RequestMapping(value = "/delete.spring", method = RequestMethod.POST)
	public String delete(HttpServletRequest request, HttpServletResponse response)
	{
			//BoardDAO impl = new BoardDAO();
			String bseq = request.getParameter("bseq");

			//기존 첨부파일 삭제
			String bfile_path = request.getParameter("bfile_path");
			String bfile_name = request.getParameter("bfile_name");
			File oldFile = new File(bfile_path+"/"+bfile_name); //파일생성
			if(oldFile.exists())
				oldFile.delete();

			//DB에 게시물 지우기
			int res = service.boardDelete(Integer.parseInt(bseq));
			if(res > 0) {
				return "redirect:/list.spring";

			}else {
				return "redirect:/detail.spring?bseq="+bseq;
			}
		}

	
	
	
	
	
}
