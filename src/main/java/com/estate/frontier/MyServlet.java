/**
 * 
 */
package com.estate.frontier;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lenovo
 * @data 2019年6月26日 上午11:28:34
 */
@WebServlet(name = "myServlet", urlPatterns = "/servlet/ServerAction")
public class MyServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// 获取上传的文件id信息
			String fileid = request.getParameter("fileid");
			System.out.println(fileid);
			String tempFileName = UUID.randomUUID().toString();

			// create the temp file.

			File temp = new File("C:/", tempFileName);

			FileOutputStream o = new FileOutputStream(temp);

			if (request.getContentLength() > 297) {

				// write the upload content to the temp file.

				InputStream in = request.getInputStream();

				byte b[] = new byte[1024];

				int n;

				while ((n = in.read(b)) != -1) {

					o.write(b, 0, n);

				}

				o.close();

				in.close();
				String FilePath = "C:/" + tempFileName;
				File fp = new File(FilePath);

				long fileLen = fp.length();

				if (fileLen > 147) {
					fileLen -= 147;
				}

				long nTimes = fileLen / 1024;
				long nDelta = fileLen % 1024;

				String desFilePath = FilePath + "_FAQ";
				FileInputStream in2 = new FileInputStream(FilePath);
				File file = new File(desFilePath);
				if (!file.exists())
					file.createNewFile();
				FileOutputStream out = new FileOutputStream(file);
				int c;
				byte buffer[] = new byte[1024];
//		         while((c=in2.read(buffer))!=-1){
//		             for(int i=0;i<c;i++)
//		                 out.write(buffer[i]);        
//		         }
				for (int i = 0; i < nTimes; i++) {
					if ((c = in2.read(buffer)) != -1) {
						for (int j = 0; j < c; j++) {
							out.write(buffer[j]);
						}
					}
				}
				byte[] bufferDelata = new byte[(int) nDelta];
				if ((c = in2.read(bufferDelata)) != -1) {
					for (int j = 0; j < c; j++) {
						out.write(bufferDelata[j]);
					}
				}
				in2.close();
				out.close();

				fp.delete();
				File fp2 = new File(desFilePath);

				File fp3 = new File(FilePath);
				if (!fp3.exists())
					fp3.createNewFile();
				FileOutputStream out3 = new FileOutputStream(fp3);

				RandomAccessFile random = new RandomAccessFile(fp2, "r");
				for (int i = 0; i < 12; i++)
					random.readLine();

				while ((c = random.read(buffer)) != -1) {
					for (int i = 0; i < c; i++)
						out3.write(buffer[i]);
				}

				random.close();
				out3.close();

				fp2.delete();
			}
		}

		catch (IOException e) {

			System.out.print("upload error.");
			System.out.print("No file!");
			response.setContentType("text/html,gb2312");
			PrintWriter out = response.getWriter();
			out.print("postfaild");
			out.flush();
			out.close();

			e.printStackTrace();

		}
	}
}
