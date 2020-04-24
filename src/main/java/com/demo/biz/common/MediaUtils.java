package com.demo.biz.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;

/**
 * @ClassName : MediaUtils.java
 * @Description : 파일의 미디어타입을 매칭하기 위한 클래스
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
public class MediaUtils {
	
	private static Map<String, MediaType> mediaMap;

	/**
	 * 사진 파일 확장자 매핑
	 */
	static{
		mediaMap = new HashMap<String, MediaType>();		
		mediaMap.put("JPG", MediaType.IMAGE_JPEG);
		mediaMap.put("GIF", MediaType.IMAGE_GIF);
		mediaMap.put("PNG", MediaType.IMAGE_PNG);
	}
	
    /**
     * 매핑된 미디어타입을 반환한다.
     *
     * @param type 파일의 확장자
     * @return MediaType - 확장자와 일치하는 미디어타입
     */
	public static MediaType getMediaType(String type){
		
		return mediaMap.get(type.toUpperCase());
		
	}
}