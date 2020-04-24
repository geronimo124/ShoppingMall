package com.demo.view.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.biz.common.MediaUtils;
import com.demo.biz.common.PageMaker;
import com.demo.biz.common.SearchCriteria;
import com.demo.biz.common.UploadFileUtils;
import com.demo.biz.product.CategoryVO;
import com.demo.biz.product.ProductService;
import com.demo.biz.product.ProductVO;

/**
 * @ClassName : AdminProductController.java
 * @Description : 관리자 상품관리 정보에 대한 컨트롤러 클래스
 * @Modification Information
 *
 *    수정일			수정자		수정내용
 *    -------		-------     -------------------
 *    2020. 4. 23.	전일배		최초생성
 *
 * @author 전일배
 * @since 2020. 4. 23.
 * @version
 * @see
 *
 */
@Controller
@RequestMapping("/admin/product")
public class AdminProductController {

	private static final Logger logger = LoggerFactory.getLogger(AdminProductController.class);

	private final ProductService service;

	@Autowired
	public AdminProductController(ProductService service) {
		this.service = service;
	}

    /**
     * 상품 목록 페이지.
     *
     * @param
     * @return JSP
     */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void listProduct(@ModelAttribute("cri") SearchCriteria cri, Model model) {

		logger.info(cri.toString());

		model.addAttribute("productList", service.getProductList(cri));

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);

		pageMaker.setTotalCount(service.countProductList(cri));

		model.addAttribute("pageMaker", pageMaker);

	}

    /**
     * 상품 등록 페이지.
     *
     * @param
     * @return JSP
     */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerProduct(Model model) {

		logger.info("product register");

		model.addAttribute("mainCateList", service.getCategoryList(1));
	}

    /**
     * 관리자가 새로운 상품을 등록한다.
     *
     * @param ProductVO 상품 정보
     * @return redirect URL
     */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerProduct(HttpServletRequest request, ProductVO vo) throws Exception {

		logger.info(vo.toString());
		
		MultipartFile file = vo.getFile();

		// 파일 저장 경로
		String uploadPath = request.getSession().getServletContext().getRealPath("/");
		uploadPath += "resources\\upload";
		
		// 등록된 파일이 있을 경우 업로드 한다.
		if(file.getSize() != 0) 		
			vo.setPdImg(UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()));
		else
			vo.setPdImg("");

		service.registerProduct(vo);

		return "redirect:list";
	}

    /**
     * 특정 카테고리의 하위 카테고리 목록을 가져온다.
     *
     * @param ctgyParent 부모 카테고리 고유코드
     * @return ResponseEntity - 카테고리 목록
     */
	@ResponseBody
	@RequestMapping(value = "/getSubCateList/{ctgyParent}", method = RequestMethod.GET)
	public ResponseEntity<List<CategoryVO>> getSubCateList(@PathVariable("ctgyParent") Integer ctgyParent) {

		ResponseEntity<List<CategoryVO>> entity = null;

		try {
			entity = new ResponseEntity<List<CategoryVO>>(service.getCategoryList(ctgyParent), HttpStatus.OK);
		} catch(Exception e) {
			entity = new ResponseEntity<List<CategoryVO>>(HttpStatus.BAD_REQUEST);
		}

		return entity;
	}

    /**
     * 파일을 웹페이지에 보여준다.
     *
     * @param fileName 파일 이름
     * @return ResponseEntity - 파일 정보
     */
	@ResponseBody
	@RequestMapping("/displayFile")
	public ResponseEntity<byte[]> displayFile(HttpServletRequest request, String fileName) {

		InputStream in = null; 
		ResponseEntity<byte[]> entity = null;
		
		// 업로드 파일 경로
		String uploadPath = request.getSession().getServletContext().getRealPath("/");
		uploadPath += "resources\\upload";

		logger.info("FILE NAME: " + fileName);

		try{

			String formatName = fileName.substring(fileName.lastIndexOf(".")+1);

			MediaType mType = MediaUtils.getMediaType(formatName);

			HttpHeaders headers = new HttpHeaders();

			in = new FileInputStream(uploadPath + fileName);

			if(mType != null)
				headers.setContentType(mType);
			else {
				fileName = fileName.substring(fileName.indexOf("_") + 1);       
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("Content-Disposition", "attachment; filename=\""+ 
						new String(fileName.getBytes("UTF-8"), "ISO-8859-1")+"\"");
			}

			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);

		} catch(Exception e) {

			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);

		} finally {
			if(in != null)
				try { in.close(); } catch(Exception e) { e.printStackTrace(); }
		}

		return entity;    
	}

    /**
     * 파일을 서버에 업로드한다.
     *
     * @param MultipartFile 업로드 파일
     * @return URL
     */
	@RequestMapping(value = "editor/imageUpload", method = RequestMethod.POST)
	public void contentImageUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile upload) {
		
		OutputStream out = null;
		PrintWriter printWriter = null;
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		try {
			
			String fileName = UUID.randomUUID().toString() + "_" + upload.getOriginalFilename();
			byte[] bytes = upload.getBytes();
			
			String uploadPath = request.getSession().getServletContext().getRealPath("/");
			uploadPath = uploadPath + "resources\\upload\\" + fileName;
			out = new FileOutputStream(new File(uploadPath));
			
			out.write(bytes);
			
			printWriter = response.getWriter(); // PrintWriter : �������� Ŭ���̾�Ʈ�� ���� �� ���
			String fileUrl = "/upload/" + fileName;
            		
            printWriter.println("{\"filename\" : \"" + fileName + "\", \"uploaded\" : 1, \"url\":\"" + fileUrl + "\"}");
            printWriter.flush();
            
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(printWriter != null) try { printWriter.close(); } catch(Exception e) { e.printStackTrace(); }
		}
	}
	
    /**
     * 특정 상품을 삭제한다.
     *
     * @param pdNo 상품 고유번호
     * @return ResponseEntity - 성공 여부
     */
	@ResponseBody
	@RequestMapping(value = "delete/{pdNo}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteProduct(HttpServletRequest request, @PathVariable("pdNo") Integer pdNo) {
		
		ResponseEntity<String> entity = null;
		
		ProductVO vo = service.getProduct(pdNo);
		String uploadPath = request.getSession().getServletContext().getRealPath("/") + "resources\\upload\\";
		
		try {
		
			// 상품 대표 이미지 삭제
			String fileName = vo.getPdImg();
			logger.info("delete pdImg : " + fileName);
			
			String front = fileName.substring(0,12);
			String end = fileName.substring(14);
			new File(uploadPath + (front+end).replace('/', File.separatorChar)).delete();
			new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
			
			// 상품 상세 이미지 삭제
			String content = vo.getPdDetl();
			int index = content.indexOf("src=");
			List<String> imgList = new ArrayList<>();
			
			while(index > -1) {
				String img = content.substring(index + 13).split("\"")[0];
				imgList.add(img);
				content = content.substring(index + 13 + img.length());
				index = content.indexOf("src=");
			}
			
			for(String img : imgList)
				new File(uploadPath + img).delete();
			
			service.deleteProduct(pdNo);
			
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			
		} catch(Exception e) {
			
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
    /**
     * 선택한 상품들을 삭제한다.
     *
     * @param List 상품 고유번호 목록
     * @return ResponseEntity - 성공 여부
     */
	@ResponseBody
	@RequestMapping(value = "deleteChecked", method = RequestMethod.POST)
	public ResponseEntity<String> deleteCheckedProduct(HttpServletRequest request, @RequestParam("productList[]") List<Integer> productList) {
		
		ResponseEntity<String> entity = null;
		
		String uploadPath = request.getSession().getServletContext().getRealPath("/") + "resources\\upload\\";
		
		for(int pdNo : productList) {
			
			ProductVO vo = service.getProduct(pdNo);
			
			// 상품 대표 이미지 삭제
			String fileName = vo.getPdImg();
			logger.info("delete pdImg : " + fileName);
						
			String front = fileName.substring(0,12);
			String end = fileName.substring(14);
			new File(uploadPath + (front+end).replace('/', File.separatorChar)).delete();
			new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
			
			// 상품 상세 이미지 삭제
			String content = vo.getPdDetl();
			int index = content.indexOf("src=");
			List<String> imgList = new ArrayList<>();
						
			while(index > -1) {
				String img = content.substring(index + 13).split("\"")[0];
				imgList.add(img);
				content = content.substring(index + 13 + img.length());
				index = content.indexOf("src=");
			}
				
			for(String img : imgList)
				new File(uploadPath + img).delete();
		}
		
		try {
			service.deleteProducts(productList);
			entity = new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	/**
     * 상품 상세보기 페이지.
     *
     * @param pdNo 상품 고유번호
     * @return URL
     */
	@RequestMapping(value = "read", method = RequestMethod.GET)
	public void readProduct(@RequestParam("pdNo") Integer pdNo, @ModelAttribute("cri") SearchCriteria cri, Model model) {

		logger.info(service.getProduct(pdNo).toString());
		
		model.addAttribute("productVO", service.getProduct(pdNo));
		
	}

	/**
     * 상품 수정 페이지.
     *
     * @param pdNo 상품 고유번호
     * @return URL
     */
	@RequestMapping(value = "modify", method = RequestMethod.GET)
	public void modifyProduct(@RequestParam("pdNo") Integer pdNo, @ModelAttribute("cri") SearchCriteria cri, Model model) {
		
		ProductVO vo = service.getProduct(pdNo);
		logger.info(vo.toString());
		
		model.addAttribute("mainCateList", service.getCategoryList(1));
		
		model.addAttribute("productVO", vo);
		model.addAttribute("subCateList", service.getCategoryList(vo.getCtgyPtcd()));
	}
	
	/**
     * 특정 상품을 수정한다.
     *
     * @param ProductVO 상품 정보
     * @param image 상품 이미지
     * @return redirect URL
     */
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public String modifyProduct(HttpServletRequest request, ProductVO vo, SearchCriteria cri, RedirectAttributes rttr, @RequestParam("image") String image) throws Exception {
		
		logger.info(vo.toString());
		
		ProductVO preVO = service.getProduct(vo.getPdNo());
		
		String uploadPath = request.getSession().getServletContext().getRealPath("/") + "resources\\upload\\";
		
		MultipartFile file = vo.getFile();
		
		// 기존 상품 대표 이미지 삭제
		if(file.getSize() == 0) {
			if(image.equals(""))
				vo.setPdImg("");
			else
				vo.setPdImg(image);
		} else {
			String fileName = preVO.getPdImg();
			logger.info("delete previous VO pdImg : " + fileName);
			
			if(fileName != null) {
				String front = fileName.substring(0,12);
				String end = fileName.substring(14);
				new File(uploadPath + (front+end).replace('/', File.separatorChar)).delete();
				new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
			}
			vo.setPdImg(UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()));
		}
			
		// 기존 상품 상세 이미지와 수정된 상품 상세 이미지 가져오기
		String content = preVO.getPdDetl();
		int index = content.indexOf("src=");
		List<String> preImgList = new ArrayList<>();
		
		while(index > -1) {
			String img = content.substring(index + 13).split("\"")[0];
			preImgList.add(img);
			content = content.substring(index + 13 + img.length());
			index = content.indexOf("src=");
		}
		
		content = vo.getPdDetl();
		index = content.indexOf("src=");
		List<String> imgList = new ArrayList<>();
		
		while(index > -1) {
			String img = content.substring(index + 13).split("\"")[0];
			imgList.add(img);
			content = content.substring(index + 13 + img.length());
			index = content.indexOf("src=");
		}
		
		// 수정 이후 없어진 이미지 삭제
		for(String img : preImgList) {
			if(!imgList.contains(img)) {
				new File(uploadPath + img).delete();
			}
		}
		service.modifyProduct(vo);
		
		rttr.addAttribute("page", cri.getPage());
	    rttr.addAttribute("perPageNum", cri.getPerPageNum());
	    rttr.addAttribute("searchType", cri.getSearchType());
	    rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:list";
	}
	
    /**
     * 선택한 상품들을 수정한다.
     *
     * @param List 상품 정보 목록
     * @return ResponseEntity - 성공 여부
     */
	@ResponseBody
	@RequestMapping(value = "modifyChecked", method = RequestMethod.POST)
	public ResponseEntity<String> modifyCheckedProduct(@RequestBody List<ProductVO> productList) {
		
		logger.info(productList.toString());
		
		ResponseEntity<String> entity = null;
		
		try {
			service.modifyCheckedProducts(productList);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch(Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
}
