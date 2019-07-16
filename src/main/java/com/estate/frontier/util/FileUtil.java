/**
 * 
 */
package com.estate.frontier.util;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author lenovo
 * @data 2019年6月26日 上午8:48:03
 */
public final class FileUtil {
	private static final Logger log = LogManager.getLogger(FileUtil.class);
	public static final String FILE_SEPARATOR = System.getProperty("file.separator");
	private static final String ROOT_PATH = System.getProperty("user.dir") + FileUtil.FILE_SEPARATOR + "upLoad"
			+ FileUtil.FILE_SEPARATOR;
	/**
	 * 用于打开上传文件的路径
	 */
	public static final String OPEN_URL = "http://fcpgpre.jstspg.com//rpt/open/";

	public static String getUriNoSuffix(String uri) {
		if (StringUtils.isEmpty(uri)) {
			return null;
		}
		return uri.substring(0, uri.lastIndexOf("."));
	}

	/**
	 * 上传文件 返回文件的保存路径
	 * 
	 * @param file
	 * @param id
	 * @return
	 * @throws Exception
	 * @author lenovo
	 * @date 2019年7月12日 上午11:00:18
	 */
	public static FileResult upLoad(MultipartFile file, int id) throws Exception {
		if (null == file || file.isEmpty()) {
			log.debug("传入的文件为空，返回前端的uri:{}", "null");
			return null;
		}
		log.debug("执行upLoad,文件名:{}", file.getOriginalFilename() == null ? "null" : file.getOriginalFilename());
		String fileName = file.getOriginalFilename();
		fileName = FileUtil.getFileName(fileName);
		System.out.println("文件名字为：：：" + fileName);

		String upURI = id + FileUtil.FILE_SEPARATOR + fileName;
		String abPath = "";
		try {
			abPath = FileUtil.upLoadFile(file, upURI);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		log.debug("返回前端的文件路径:{}", abPath);
		return new FileResult(upURI, abPath);
	}

	public static class FileResult {
		private String uri;
		private String wordPath;
		private String pdfPath;
		private String realPath;

		/**
		 * @param uri
		 * @param wordPath
		 * @param pdfPath
		 */
		public FileResult(String uri, String realPath) {
			super();
			this.uri = uri;
			this.realPath = realPath;
			if (!StringUtils.isEmpty(uri)) {
				this.wordPath = OPEN_URL + uri;
				this.pdfPath = OPEN_URL + FileUtil.getUriNoSuffix(uri) + ".pdf";
			}
		}

		public String getRealPath() {
			return realPath;
		}

		public String getUri() {
			return uri;
		}

		public String getWordPath() {
			return wordPath;
		}

		public String getPdfPath() {
			return pdfPath;
		}

	}

	public static String upLoadFile(MultipartFile file, String upURI) throws Exception {
		int size = (int) file.getSize();
		System.out.println(upURI + "-->" + size);
		File dest = new File(ROOT_PATH + upURI);
		log.info("保存文件的目的地为:{}", dest.toString());
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		file.transferTo(dest);
		return dest.getCanonicalPath();
	}

	public static boolean deleteFile(String upURI) {
		if (null == upURI || upURI.isEmpty()) {
			return true;
		}
		File filePath = new File(ROOT_PATH + upURI);
		if (!filePath.exists()) {
			return true;
		}
		filePath.delete();
		// String fold = filePath.getParentFile();
		File path = filePath.getParentFile();
		if (path.isDirectory() && path.list().length == 0) {
			path.delete();
		}
		return true;
	}

	public static boolean deleteFold(String id) {
		if (null == id || id.isEmpty()) {
			return true;
		}
		File filePath = new File(ROOT_PATH + id + FILE_SEPARATOR);
		if (!filePath.exists()) {
			return true;
		}
		File[] files = filePath.listFiles();
		for (File file : files) {
			file.delete();
		}
		if (filePath.isDirectory() && filePath.list().length == 0) {
			filePath.delete();
		}
		return true;
	}

	public static String getFileName(String path) {
		String p = normalizer(path);
		return new File(p).getName();
	}

	public static void main(String[] args) {
		System.out.println(getUriNoSuffix("F:\\1//33/4test.pdf"));
		;
	}

	public static String normalizer(String path) {
		if (StringUtils.isEmpty(path)) {
			return path;
		}
		String formatPath = "";
		try {
			formatPath = path.replace("/", FILE_SEPARATOR).replace("\\", FILE_SEPARATOR);
		} catch (Exception e) {
			e.printStackTrace();
			return new File(formatPath).getName();
		}
		return formatPath;
	}
}
