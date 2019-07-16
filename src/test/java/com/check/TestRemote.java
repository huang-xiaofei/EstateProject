/**
 * 
 */
package com.check;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * @author lenovo
 * @data 2019年6月29日 下午9:36:38
 */
public class TestRemote {

	/**
	 * @param args
	 * @author lenovo
	 * @throws Exception
	 * @date 2019年6月29日 下午9:36:39
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		testDownloadImage();
	}

	public static void testDownloadImage() throws Exception {
		// 构造URL
		// URL url = new
		// URL("http://www.pptbz.com/pptpic/UploadFiles_6909/201211/2012111719294197.jpg");
		URL url = new URL("http://fcpgpre.jstspg.com/rpt/upload/mybatis.doc");
		InputStream is = url.openStream();

		// 1K的数据缓冲
		byte[] bs = new byte[1024];
		// 读取到的数据长度
		int len;
		// 输出的文件流
		File sf = new File("D:\\");
		if (!sf.exists()) {
			sf.mkdirs();
		}
		OutputStream os = new FileOutputStream(sf.getPath() + "\\test.jpg");
		// 开始读取
		while ((len = is.read(bs)) != -1) {
			os.write(bs, 0, len);
		}
		// 完毕，关闭所有链接
		os.close();
		is.close();

	}
}
