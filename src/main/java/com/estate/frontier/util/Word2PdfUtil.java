/**
 * 
 */
package com.estate.frontier.util;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.aspose.words.Document;
import com.aspose.words.HeaderFooter;
import com.aspose.words.HeaderFooterType;
import com.aspose.words.HorizontalAlignment;
import com.aspose.words.License;
import com.aspose.words.Paragraph;
import com.aspose.words.RelativeHorizontalPosition;
import com.aspose.words.RelativeVerticalPosition;
import com.aspose.words.SaveFormat;
import com.aspose.words.Section;
import com.aspose.words.Shape;
import com.aspose.words.ShapeType;
import com.aspose.words.VerticalAlignment;
import com.aspose.words.WrapType;

/**
 * @author lenovo
 * @data 2019年6月28日 下午4:19:10
 */
public class Word2PdfUtil {

	public static void main(String[] args) {
		doc2pdf("C:\\Users\\lenovo\\Desktop\\mybatis.doc");

	}

	public static boolean doc2pdf(String wordPath) {
		String path = wordPath.substring(0, wordPath.lastIndexOf("."));
		String pdfPath = path + ".pdf";
		return doc2pdf(wordPath, pdfPath);
	}

	public static boolean doc2pdf(String inPath, String outPath) {
		if (!getLicense()) {
			return false;
		}
		FileOutputStream os = null;
		long old = System.currentTimeMillis();
		try {
			File file = new File(outPath); // 新建一个空白pdf文档
			os = new FileOutputStream(file);
			Document doc = new Document(inPath); // Address是将要被转化的word文档
			// insertWatermarkText(doc, "审批专用");
			doc.save(os, SaveFormat.PDF);
			long now = System.currentTimeMillis();
			System.out.println("共耗时：" + ((now - old) / 1000.0) + "s," + "文件保存在:" + file.getPath());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			close(null, os);
		}
		return true;
	}

	/**
	 * 生成水印
	 * 
	 * @param doc
	 * @param watermarkText
	 * @throws Exception
	 * @author lenovo
	 * @date 2019年6月29日 上午10:02:41
	 */
	public static void insertWatermarkText(Document doc, String watermarkText) throws Exception {
		Shape watermark = new Shape(doc, ShapeType.TEXT_PLAIN_TEXT);
		// 水印内容
		watermark.getTextPath().setText(watermarkText);
		// 水印字体
		watermark.getTextPath().setFontFamily("宋体");
		// 水印宽度
		watermark.setWidth(500);
		// 水印高度
		watermark.setHeight(100);
		// 旋转水印
		watermark.setRotation(-40);
		// 水印颜色
		watermark.getFill().setColor(Color.lightGray);
		watermark.setStrokeColor(Color.lightGray);

		watermark.setRelativeHorizontalPosition(RelativeHorizontalPosition.PAGE);
		watermark.setRelativeVerticalPosition(RelativeVerticalPosition.PAGE);
		watermark.setWrapType(WrapType.NONE);
		watermark.setVerticalAlignment(VerticalAlignment.CENTER);
		watermark.setHorizontalAlignment(HorizontalAlignment.CENTER);

		Paragraph watermarkPara = new Paragraph(doc);
		watermarkPara.appendChild(watermark);

		for (Section sect : doc.getSections()) {
			insertWatermarkIntoHeader(watermarkPara, sect, HeaderFooterType.HEADER_PRIMARY);
			insertWatermarkIntoHeader(watermarkPara, sect, HeaderFooterType.HEADER_FIRST);
			insertWatermarkIntoHeader(watermarkPara, sect, HeaderFooterType.HEADER_EVEN);
		}
		System.out.println("Watermark Set");
	}

	public static boolean getLicense() {
		boolean result = false;
		InputStream is = null;
		try {
			is = Word2PdfUtil.class.getResourceAsStream("/license.xml"); // license.xml应放在..\WebRoot\WEB-INF\classes路径下
			License aposeLic = new License();
			aposeLic.setLicense(is);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(is, null);
		}
		return result;
	}

	private static void insertWatermarkIntoHeader(Paragraph watermarkPara, Section sect, int headerType)
			throws Exception {
		HeaderFooter header = sect.getHeadersFooters().getByHeaderFooterType(headerType);

		if (header == null) {
			header = new HeaderFooter(sect.getDocument(), headerType);
			sect.getHeadersFooters().add(header);
		}
		header.appendChild(watermarkPara.deepClone(true));
	}

	private static void close(InputStream inputStream, OutputStream outputStream) {
		try {
			if (null != inputStream) {
				inputStream.close();
			}
			if (null != outputStream) {
				outputStream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
