package com.example;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;

public class Link2D {
    public static void main(String[] args) throws Exception {
        getLink2D("http://img4.jiecaojingxuan.com/2016/11/25/24/d2701880-645a-4608-8407-fb08112ad050.gif");
    }

    public static byte[] getLink2D(String link) throws Exception {
        if (link == null || "".equals(link))
            return null;
        String text = link.trim(); // 二维码内容
        int width = 300; // 二维码图片宽度
        int height = 300; // 二维码图片高度
        String format = "png";// 二维码的图片格式

        Hashtable<EncodeHintType, String> hints = new Hashtable<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.MARGIN, "0");//设置没有边界

        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, format, baos);
        byte[] bytes = baos.toByteArray();

        //本地生成图片看效果
//        FileOutputStream fos = new FileOutputStream(new File("2D.png"));
//        fos.write(bytes);
//        fos.close();

        return bytes;
    }
}
