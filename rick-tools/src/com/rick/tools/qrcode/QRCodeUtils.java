package com.rick.tools.qrcode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * @category 【二维码】工具类
 * @description [创建二维码]、[读取二维码]
 * @author bxw
 * @date 2016年6月29日
 * @version v1.0
 * @source [core-2.2.jar]，【javase-2.2.jar】
 */
public class QRCodeUtils {

	/**
	 * @category 创建二维码
	 * @param content 内容(可以为链接，文字)
	 * @param filePath 文件路径
	 * @param fileName 文件名称
	 * @param hintMap 样式(可为空)
	 * @param height 高度
	 * @param width 宽度
	 * @throws WriterException
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String createQRCode(String content, String filePath,String fileName, Map hintMap, int height,
			int width) throws WriterException, IOException {
		try {
			String charset = "UTF-8";
			if(hintMap==null||hintMap.isEmpty()){
				hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
				hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.Q);
			}
			File file = new File(filePath+fileName);
			if(!file.exists()){
				file.mkdirs();
			}
			
			BitMatrix matrix = new MultiFormatWriter().encode(new String(content.getBytes(charset), charset),
					BarcodeFormat.QR_CODE, width, height, hintMap);
			MatrixToImageWriter.writeToFile(matrix, fileName.substring(fileName
					.lastIndexOf('.') + 1), file);
			return filePath+fileName;
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	/**
	 * @category 读取二维码内容
	 * @param filePath
	 * @param fileName
	 * @param hintMap
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws NotFoundException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String readQRCode(String filePath,String fileName, Map hintMap)
			throws FileNotFoundException, IOException, NotFoundException {
		try {
			if(hintMap==null||hintMap.isEmpty()){
				hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
				hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.Q);
			}
			BinaryBitmap binaryBitmap = new BinaryBitmap(
					new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new FileInputStream(filePath+fileName)))));
			com.google.zxing.Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap, hintMap);
			return qrCodeResult.getText();
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}
	
	/**
	 * 主方法测试类
	 * @param args
	 * @throws WriterException
	 * @throws IOException
	 * @throws NotFoundException
	 */
	public static void main(String[] args) throws WriterException, IOException, NotFoundException {
		
		String content = "";//"http://192.168.1.103:8088/shop/";
		content = "https://www.yiface.com/agent";
		String filePath = "D:/softs/";
		/*String charset = "UTF-8"; // or "ISO-8859-1"
		Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.Q);*/

		String path = createQRCode(content, filePath,"QRCode.png", null, 200, 200);
		System.out.println("QR Code image created successfully! " + path);

		System.out.println("Data read from QR Code: " + readQRCode(filePath, "QRCode.png", null));
	}
}
