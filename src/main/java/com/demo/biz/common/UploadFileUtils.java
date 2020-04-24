package com.demo.biz.common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

/**
 * @ClassName : UploadFileUtils.java
 * @Description : 파일 업로드 클래스
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
public class UploadFileUtils {

	private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);

    /**
     * 미디어타입에 따라 썸네일 또는 아이콘을 만들어 입력받은 위치에 원래 파일과 만들어진 파일을 업로드한다.
     *
     * @param uploadPath 파일이 저장될 위치
     * @param originalName 파일 이름
     * @param fileData 파일 데이터
     * @return 만들어진 파일 이름
     * @throws Exception
     */
	public static String uploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception {

		UUID uid = UUID.randomUUID();

		String savedName = uid.toString() + "_" + originalName;

		String savedPath = calcPath(uploadPath);

		File target = new File(uploadPath + savedPath, savedName);

		FileCopyUtils.copy(fileData, target);

		String formatName = originalName.substring(originalName.lastIndexOf(".") + 1);

		String uploadedFileName = null;

		if(MediaUtils.getMediaType(formatName) != null)
			uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);
		else
			uploadedFileName = makeIcon(uploadPath, savedPath, savedName);

		return uploadedFileName;

	}

    /**
     * 사진 파일이 아닐 경우 아이콘을 생성한다.
     *
     * @param uploadPath 파일이 저장될 위치
     * @param path 파일의 나머지 경로
     * @param fileName 파일 이름
     * @return 만들어진 아이콘 이름
     * @throws Exception
     */
	private static String makeIcon(String uploadPath, String path, String fileName) throws Exception {

		String iconName = uploadPath + path + File.separator+ fileName;

		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
		
	}

    /**
     * 사진 파일인 경우 썸네일 이미지를 생성한다.
     *
     * @param uploadPath 파일이 저장될 위치
     * @param path 파일의 나머지 경로
     * @param fileName 파일 이름
     * @return 만들어진 썸네일 이름
     * @throws Exception
     */
	private static String makeThumbnail(String uploadPath, String path, String fileName) throws Exception {

		BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));

		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 400);

		String thumbnailName = uploadPath + path + File.separator + "s_" + fileName;

		File newFile = new File(thumbnailName);
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);

		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
		
	} 

    /**
     * 현재 날짜를 통해 '저장위치/월/일'의 저장 경로를 계산한다.
     *
     * @param uploadPath 파일이 저장될 위치
     * @return 저장 경로
     */
	private static String calcPath(String uploadPath){

		Calendar cal = Calendar.getInstance();

		String yearPath = File.separator+cal.get(Calendar.YEAR);

		String monthPath = yearPath + File.separator + 
				new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);

		String datePath = monthPath + File.separator + 
				new DecimalFormat("00").format(cal.get(Calendar.DATE));

		makeDir(uploadPath, yearPath,monthPath,datePath);

		logger.info(datePath);

		return datePath;
		
	}

    /**
     * 저장한 날짜의 디렉토리가 없을 경우 디렉토리를 생성한다.
     *
     * @param uploadPath 파일이 저장될 위치
     * @param paths - 파일의 나머지 경로
     * @return
     */
	private static void makeDir(String uploadPath, String... paths){

		if(new File(paths[paths.length - 1]).exists())
			return;

		for(String path : paths) {

			File dirPath = new File(uploadPath + path);

			if(!dirPath.exists())
				dirPath.mkdir(); 
			
		}
	}
}
