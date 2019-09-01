/**
 * 
 */
package com.estate.frontier.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.estate.frontier.model.base.BaseEstate;

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
	// public static final String OPEN_URL = "http://fcpgpre.jstspg.com//rpt/open/";

//  http://bgsp.jstspg.com//rpt/open/1566743938913/1566743938913.pdf
	public static final String OPEN_URL = "http://bgsp.jstspg.com//rpt/open/";// 正式环境

	public static String getUriNoSuffix(String uri) {
		if (StringUtils.isEmpty(uri)) {
			return null;
		}
		return uri.substring(0, uri.lastIndexOf("."));
	}

	public static List<FileResult> upLoad(List<MultipartFile> files) throws Exception {
		List<FileResult> results = new ArrayList<FileUtil.FileResult>();
		for (MultipartFile multipartFile : files) {
			FileResult fileResult = upLoad(multipartFile);
			results.add(fileResult);
		}

		return results;
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
	public static FileResult upLoad(MultipartFile file) throws Exception {
		if (null == file) {
			log.debug("传入的文件为空，返回前端的uri:{}", "null");
			return null;
		}
		String fileName = file.getOriginalFilename();

		fileName = FileUtil.getFileName(fileName);
		log.info("保存文件的原始文件名:{},处理后的文件名为:{}", file.getOriginalFilename() == null ? "null" : file.getOriginalFilename(),
				fileName);
		// 拼接uri
		// String upURI = getUri(base, fileName);
		long random = System.currentTimeMillis();
		String upURI = getUri(fileName, random);
		log.info("保存文件拼接的uri为:{}", upURI);

		String abPath = "";
		try {
			abPath = FileUtil.upLoadFile(file, upURI);// 服务器上的真实路径
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		log.info("保存文件后返回的路径为:{}", abPath);
		return new FileResult(upURI, abPath, random);
	}

	/**
	 * @param base
	 * @param fileName
	 * @return
	 * @author lenovo
	 * @date 2019年8月11日 上午10:30:43
	 */
	private static String getUri(BaseEstate base, String fileName) {
		StringBuilder uri = new StringBuilder();
		String branchOffice = base.getBranchOffice();
		if (StringUtils.isNotEmpty(branchOffice)) {
			// uri.append(branchOffice).append(FileUtil.FILE_SEPARATOR);
			uri.append(base.getId()).append(FileUtil.FILE_SEPARATOR);
			uri.append(fileName);
		} else {
			uri.append(base.getId()).append(FileUtil.FILE_SEPARATOR);
			uri.append(fileName);
		}
		String upURI = uri.toString();
		return upURI;
	}

	private static String getUri(String fileName, long random) {

		StringBuilder uri = new StringBuilder();
		uri.append(random).append(FileUtil.FILE_SEPARATOR);
		if (!fileName.contains(".") ||fileName.endsWith(".pdf")) {//临时文件和pdf文件，文件都重命名
			uri.append(random);
			uri.append(".pdf");
		} else {
			uri.append(fileName);
		}
		return uri.toString();
	}

	public static class FileResult {
		private String uri;// 文件的后缀路径
		private String wordPath;// 可以浏览器直接下载的路径(http路径)
		private String pdfPath;
		private String realPath;// 在服务器上的物理地址
		private long id;

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
				if (!isZip(uri)) {// 压缩包不生成pdf路径
					this.pdfPath = OPEN_URL + FileUtil.getUriNoSuffix(uri) + ".pdf";
				}
			}
		}

		// 以id作为pdf文件名
		public FileResult(String uri, String realPath, long id) {
			super();
			this.uri = uri;
			this.realPath = realPath;// 文件在服务器上的真实地址
			this.id = id;
			if (!StringUtils.isEmpty(uri)) {
				this.wordPath = OPEN_URL + uri;
				if (!isZip(uri)) {// 压缩包不生成pdf路径
					this.pdfPath = OPEN_URL + id + "/" + id + ".pdf";
				}
			}
		}

		public FileResult(String uri, String realPath, String zipPath, int id) {
			super();
			this.uri = uri;
			this.realPath = realPath;
			this.id = id;
			if (!StringUtils.isEmpty(uri)) {
				this.wordPath = OPEN_URL + uri;
				if (!isZip(uri)) {// 压缩包不生成pdf路径
					this.pdfPath = OPEN_URL + id + "/" + id + ".pdf";
				}
			}
		}

		public long getId() {
			return id;
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

		public void setWordPath(String wordPath) {
			this.wordPath = wordPath;
		}

	}

	public static boolean isZip(String path) {
		if (StringUtils.isEmpty(path)) {
			return true;
		}
		if (path.endsWith(".zip") || path.endsWith(".rar")) {
			return true;
		}
		return false;
	}

	public static String upLoadFile(MultipartFile file, String upURI) throws Exception {
//		int size = (int) file.getSize();
//		System.out.println(upURI + "-->" + size);

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

	public static boolean deleteFold(BaseEstate baseEstate) {
		if (!StringUtils.isEmpty(baseEstate.getWordUri())) {
			deleteFold(getRandomFold(baseEstate.getWordUri()));// 删除word和pdf
		}
		if (!StringUtils.isEmpty(baseEstate.getUpFileURI())) {// 删除zip
			deleteFold(getRandomFold(baseEstate.getUpFileURI()));
		}
		return true;
	}

	public static boolean deleteFold(String randomFold) {
		if (StringUtils.isEmpty(randomFold)) {
			return true;
		}
		File filePath = new File(ROOT_PATH + randomFold + FILE_SEPARATOR);
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

//	public static boolean deleteFold(String id) {
//		if (null == id || id.isEmpty()) {
//			return true;
//		}
//		File filePath = new File(ROOT_PATH + id + FILE_SEPARATOR);
//		if (!filePath.exists()) {
//			return true;
//		}
//		File[] files = filePath.listFiles();
//		for (File file : files) {
//			file.delete();
//		}
//		if (filePath.isDirectory() && filePath.list().length == 0) {
//			filePath.delete();
//		}
//		return true;
//	}

	public static String getRandomFold(String path) {// 根据路径获取随机数文件夹
		if (StringUtils.isEmpty(path)) {
			return null;
		}
		String result = path.substring(path.indexOf("open") + 5, path.lastIndexOf("/"));
		return result;
	}

	public static String getFileName(String path) throws IOException {
		String p = normalizer(path);
		String fileName = new File(p).getName();
		return fileName;
	}

	public static void main(String[] args) throws IOException {
		System.out.println(getRandomFold("http://fcpgpre.jstspg.com//rpt/open/1565783986359/顾研-材料.rar"));
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
