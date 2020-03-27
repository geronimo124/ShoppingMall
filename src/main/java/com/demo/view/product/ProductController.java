package com.demo.view.product;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.biz.common.Criteria;
import com.demo.biz.common.MediaUtils;
import com.demo.biz.common.PageMaker;
import com.demo.biz.product.CategoryVO;
import com.demo.biz.product.MProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	private final MProductService service;
	
	@Autowired
	public ProductController(MProductService service) {
		this.service = service;
	}
	
	@ResponseBody
	@RequestMapping("/displayFile")
	public ResponseEntity<byte[]> displayFile(HttpServletRequest request, String fileName) {

		InputStream in = null; 
		ResponseEntity<byte[]> entity = null;
		String uploadPath = request.getSession().getServletContext().getRealPath("/");
		uploadPath += "resources\\upload";

		logger.info("FILE NAME: " + fileName);

		try{

			String formatName = fileName.substring(fileName.lastIndexOf(".")+1);

			MediaType mType = MediaUtils.getMediaType(formatName);

			HttpHeaders headers = new HttpHeaders();

			in = new FileInputStream(uploadPath+fileName);

			if(mType != null)
				headers.setContentType(mType);
			else {
				fileName = fileName.substring(fileName.indexOf("_") + 1);       
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("Content-Disposition", "attachment; filename=\""+ 
						new String(fileName.getBytes("UTF-8"), "ISO-8859-1")+"\"");
			}

			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, 
					HttpStatus.CREATED);

		} catch(Exception e) {

			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);

		} finally {
			if(in != null)
				try { in.close(); } catch(Exception e) { e.printStackTrace(); }
		}

		return entity;    
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void listProduct(@ModelAttribute("cri") Criteria cri, String ctgyCd, Model model) {

		model.addAttribute("productList", service.getProductList(ctgyCd, cri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);

		pageMaker.setTotalCount(service.countProductList(ctgyCd));

		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("ctgyCd", ctgyCd);
		
	}
	
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
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void readProduct(@RequestParam("pdNo") Integer pdNo, Criteria cri, Model model) {

		logger.info(service.getProduct(pdNo).toString());
		
		model.addAttribute("productVO", service.getProduct(pdNo));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		model.addAttribute("pageMaker", pageMaker);
		
	}
}
