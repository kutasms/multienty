
package com.chia.multienty.core.util;

import cn.hutool.extra.qrcode.BufferedImageLuminanceSource;
import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Hashtable;

@Component
@Slf4j
public class QRCodeUtil {

	private static final String CHARSET = "utf-8";
	private static final String FORMAT_NAME = "JPG";
	// 二维码尺寸
	private static final int QRCODE_SIZE = 300;
	// LOGO宽度
	private static final int WIDTH = 60;
	// LOGO高度
	private static final int HEIGHT = 60;

	public static BufferedImage createImage(String content, String imgPath, boolean needCompress) throws Exception {
		Hashtable hints = new Hashtable();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
		hints.put(EncodeHintType.MARGIN, 1);
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE,
				hints);
		int width = bitMatrix.getWidth();
		int height = bitMatrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
			}
		}
		if (imgPath == null || "".equals(imgPath)) {
			return image;
		}
		// 插入图片
		QRCodeUtil.insertImage(image, imgPath, needCompress);
		return image;
	}

	private static void insertImage(BufferedImage source, String imgPath, boolean needCompress) throws Exception {
		File file = new File(imgPath);
		if (!file.exists()) {
			System.err.println("" + imgPath + "   该文件不存在！");
			return;
		}
		Image src = ImageIO.read(new File(imgPath));
		int width = src.getWidth(null);
		int height = src.getHeight(null);
		if (needCompress) { // 压缩LOGO
			if (width > WIDTH) {
				width = WIDTH;
			}
			if (height > HEIGHT) {
				height = HEIGHT;
			}
			Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // 绘制缩小后的图
			g.dispose();
			src = image;
		}
		// 插入LOGO
		Graphics2D graph = source.createGraphics();
		int x = (QRCODE_SIZE - width) / 2;
		int y = (QRCODE_SIZE - height) / 2;
		graph.drawImage(src, x, y, width, height, null);
		Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
		graph.setStroke(new BasicStroke(3f));
		graph.draw(shape);
		graph.dispose();
	}

	/**
	 * 二维码生成
	 * @param content  编码到二维码的内容
	 * @param imgPath	嵌入二维码图片地址
	 * @param savePath	生成二维码存放路径
	 * @param needCompress	是否将嵌入图片压缩
	 * @throws Exception
	 */
	public static void encode(String content, String imgPath, String savePath, boolean needCompress) throws Exception {
		BufferedImage image = QRCodeUtil.createImage(content, imgPath, needCompress);
		mkdirs(savePath);
		ImageIO.write(image, FORMAT_NAME, new File(savePath));
	}

	public static void main(String[] args) throws Exception{
		encode("172.18.1.155:8080/xyemergentserver/emergent/AlipayProgram/getShop.do?s=20191010200255215006087504721718&o=910032&source=alipay","",
				"F:\\test.jpg",false);
	}

	public static BufferedImage encode(String content, String imgPath, boolean needCompress) throws Exception {
		BufferedImage image = QRCodeUtil.createImage(content, imgPath, needCompress);
		return image;
	}

	public static void mkdirs(String saveDir) {
		File file = new File(saveDir);
		// 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
		if (!file.exists() && !file.isDirectory()) {
			file.mkdirs();
		}
	}

	public static void encode(String content, String imgPath, String savePath) throws Exception {
		QRCodeUtil.encode(content, imgPath, savePath, false);
	}
	// 被注释的方法
	/*
	 * public static void encode(String content, String destPath, Integer
	 * needCompress) throws Exception { QRCodeUtil.encode(content, null, destPath,
	 * needCompress); }
	 */

	/**
	 * 生成二维码
	 * @param content
	 * @param savePath
	 * @throws Exception
	 */
	public static void encode(String content, String savePath) throws Exception {
		QRCodeUtil.encode(content, null, savePath, false);
	}

	public static void encode(String content, String imgPath, OutputStream output, boolean needCompress)
			throws Exception {
		BufferedImage image = QRCodeUtil.createImage(content, imgPath, needCompress);
		ImageIO.write(image, FORMAT_NAME, output);
	}

	public static void encode(String content, OutputStream output) throws Exception {
		QRCodeUtil.encode(content, null, output, false);
	}

	public static String decode(File file) throws Exception {
		BufferedImage image;
		image = ImageIO.read(file);
		if (image == null) {
			return null;
		}
		BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		Result result;
		Hashtable hints = new Hashtable();
		hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
		result = new MultiFormatReader().decode(bitmap, hints);
		String resultStr = result.getText();
		return resultStr;
	}

	/**
	 * 解码二维码
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static String decode(String path) throws Exception {
		return QRCodeUtil.decode(new File(path));
	}

	public static byte[] toByteArray(InputStream input) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		int n = 0;
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
		}
		return output.toByteArray();
	}
}
