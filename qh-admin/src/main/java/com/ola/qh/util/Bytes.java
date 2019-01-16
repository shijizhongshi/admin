package com.ola.qh.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Hashtable;
import java.util.Objects;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public final class Bytes
{

    public final static byte[] qrcode(String content, int w, int h, byte[] logo, int logow, int logoh) throws IOException
    {
	String format = "jpg";
	Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
	// 内容所使用编码
	hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
	hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
	BitMatrix bitMatrix;
	try
	{
	    bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, w, h, hints);
	}
	catch (WriterException e)
	{
	    throw new IOException("contents cannot be encoded legally in a format->" + e);
	}
	// 生成二维码
	BufferedImage image = toBufferedImage(bitMatrix);

	if (Objects.nonNull(logo) && logo.length > 0)
	{
	    addlogo(logo, image, logow, logoh);
	}

	try (ByteArrayOutputStream output = new ByteArrayOutputStream())
	{
	    ImageIO.write(image, format, output);
	    return output.toByteArray();
	}
    }

    public final static String base64qrcode(String content, int w, int h, byte[] logo, int logow, int logoh) throws IOException
    {
	try
	{
	    return Base64.getEncoder().encodeToString(qrcode(content, w, h, logo, logow, logoh));
	}
	catch (UnsupportedEncodingException e)
	{
	    e.printStackTrace();
	}
	return "";
    }

    public final static byte[] qrcode(String content, int w, int h, byte[] logo) throws IOException
    {
	return qrcode(content, w, h, logo, w / 6, h / 6);
    }

    public final static String base64qrcode(String content, int w, int h, byte[] logo) throws IOException
    {
	return base64qrcode(content, w, h, logo, w / 6, h / 6);
    }

    public final static byte[] qrcode(String content, int w, int h) throws IOException
    {
	return qrcode(content, w, h, null);
    }

    public final static String base64qrcode(String content, int w, int h) throws IOException
    {
	return base64qrcode(content, w, h, null);
    }

    /**
     * read file content to a byte array.
     * 
     * @param file
     * @return
     * @throws IOException
     */
    public final static byte[] from(File file) throws IOException
    {
	Path path = Paths.get(file.getAbsolutePath());
	return Files.readAllBytes(path);
	// try(InputStream is = new FileInputStream(file)){
	// ByteArrayOutputStream baos = new ByteArrayOutputStream();
	// byte[] result = new byte[2048];
	// int read = 0;
	// while ((read = is.read(result, 0, result.length)) > 0){
	// baos.write(result, 0, read);
	// }
	// return baos.toByteArray();
	// }
    }

    public final static String hex(byte[] bytes)
    {
	StringBuilder builder = new StringBuilder();
	// 把数组每一字节换成16进制连成md5字符串
	int digital;
	for (int i = 0; i < bytes.length; i++)
	{
	    digital = bytes[i];

	    if (digital < 0)
	    {
		digital += 256;
	    }
	    if (digital < 16)
	    {
		builder.append("0");
	    }
	    builder.append(Integer.toHexString(digital));
	}
	return builder.toString();
    }

    private static void addlogo(byte[] logo, BufferedImage image, int w, int h) throws IOException
    {
	Graphics2D g = image.createGraphics();

	BufferedImage logobi = ImageIO.read(new ByteArrayInputStream(logo));

	int x = (image.getWidth() - w) / 2;
	int y = (image.getHeight() - h) / 2;

	g.drawImage(logobi, x, y, w, h, null);
	g.drawRoundRect(x, y, w, h, 15, 15);
	g.setStroke(new BasicStroke(0.1f));
	g.setColor(new Color(WHITE));
	g.drawRect(x, y, w, h);

	g.dispose();
    }

    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    private static BufferedImage toBufferedImage(BitMatrix matrix)
    {
	int width = matrix.getWidth();
	int height = matrix.getHeight();
	BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	for (int x = 0; x < width; x++)
	{
	    for (int y = 0; y < height; y++)
	    {
		image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
	    }
	}
	return image;
    }

}
