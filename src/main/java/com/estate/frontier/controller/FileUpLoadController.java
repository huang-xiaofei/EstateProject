/**
 * 
 */
package com.estate.frontier.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;

/**
 * 
 * @author lenovo
 * @data 2019年6月25日 上午9:39:49
 */
@Controller
@RequestMapping("/fileController")
@Api(value = "fileUpLoadController")
public class FileUpLoadController {
	@RequestMapping(value = "/file")
	public String file() {
		return "/file";
	}

	/**
	 * ʵ���ļ��ϴ�
	 */
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	@ResponseBody
	public String fileUpload(@RequestParam("fileName") MultipartFile file, @RequestParam String json) {
		System.out.println(json);
		if (file.isEmpty()) {
			return "false";
		}
		String fileName = file.getOriginalFilename();
		int size = (int) file.getSize();
		System.out.println(fileName + "-->" + size);
		System.getProperty("user.dir");
		String path = System.getProperty("user.dir");
		File dest = new File(path + "/upLoad/" + fileName);
		System.out.println(dest.toString());
		if (!dest.getParentFile().exists()) { // �ж��ļ���Ŀ¼�Ƿ����
			dest.getParentFile().mkdir();
		}
		try {
			file.transferTo(dest); // �����ļ�
			return dest.getPath();
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return "false";
		} catch (IOException e) {
			e.printStackTrace();
			return "false";
		}
	}

	/**
	 * 多文件上传
	 * 
	 * @param files
	 * @param json
	 * @return
	 * @author lenovo
	 * @date 2019年6月25日 上午10:56:16
	 */
	@RequestMapping(value = "/multiUpload", method = RequestMethod.POST)
	@ResponseBody
	public String multiUpload(@RequestParam("fileName") MultipartFile[] files, String json) {
		if (null == files) {
			return "没有文件需要上传";
		}
		String foldPath = System.getProperty("user.dir") + "/upLoad/" + System.currentTimeMillis() + "/";
		for (MultipartFile file : files) {
			String fileName = file.getOriginalFilename();
			File dest = new File(foldPath + fileName);
			if (!dest.getParentFile().exists()) { // �ж��ļ���Ŀ¼�Ƿ����
				dest.getParentFile().mkdirs();
				try {
					file.transferTo(dest);
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
		}
		return foldPath;
	}

	@RequestMapping(value = "/download", method = RequestMethod.POST)
	@ResponseBody
	public String downLoad(HttpServletResponse response, String uri) {
		String path = System.getProperty("user.dir") + "/upLoad/" + uri;
		File file = new File(path);
		String filename = file.getName();
		if (file.exists()) { // 判断文件父目录是否存在
			response.setContentType("application/force-download");
			response.setHeader("Content-Disposition", "attachment;fileName=" + filename);

			byte[] buffer = new byte[1024];
			FileInputStream fis = null; // 文件输入流
			BufferedInputStream bis = null;

			OutputStream os = null; // 输出流
			try {
				os = response.getOutputStream();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				int i = bis.read(buffer);
				while (i != -1) {
					os.write(buffer);
					i = bis.read(buffer);
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("----------file download" + filename);
			try {
				bis.close();
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
//	@RequestMapping(value = "/multifile", method = RequestMethod.POST)
//	public String multifile() {
//		return "multifile";
//	}
//
//	/**
//	 * ʵ�ֶ��ļ��ϴ�
//	 */
//	@RequestMapping(value = "/multifileUpload", method = RequestMethod.POST)
//	public @ResponseBody String multifileUpload(HttpServletRequest request) {
//
//		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("fileName");
//
//		if (files.isEmpty()) {
//			return "false";
//		}
//
//		String path = "F:/test";
//
//		for (MultipartFile file : files) {
//			String fileName = file.getOriginalFilename();
//			int size = (int) file.getSize();
//			System.out.println(fileName + "-->" + size);
//
//			if (file.isEmpty()) {
//				return "false";
//			} else {
//				File dest = new File(path + "/" + fileName);
//				if (!dest.getParentFile().exists()) { // �ж��ļ���Ŀ¼�Ƿ����
//					dest.getParentFile().mkdir();
//				}
//				try {
//					file.transferTo(dest);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//					return "false";
//				}
//			}
//		}
//		return "true";
//	}
}