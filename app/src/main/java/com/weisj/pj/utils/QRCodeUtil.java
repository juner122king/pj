package com.weisj.pj.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.weisj.pj.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/11 0011.
 */
public class QRCodeUtil {
    public static int scale = 2;

    /**
     * 生成二维码Bitmap
     *
     * @param content   内容
     * @param widthPix  图片宽度
     * @param heightPix 图片高度
     * @param logoBm    二维码中心的Logo图标（可以为null）
     * @param filePath  用于存储二维码图片的文件路径
     * @return 生成二维码及保存文件是否成功
     */
    public static boolean createQRImage(String content, int widthPix, int heightPix, Bitmap logoBm, String filePath) {
        try {
            if (content == null || "".equals(content)) {
                return false;
            }

            //配置参数
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            //容错级别
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            //设置空白边距的宽度
//            hints.put(EncodeHintType.MARGIN, 2); //default is 4

            // 图像数据转换，使用了矩阵转换
            BitMatrix bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, widthPix, heightPix, hints);
            int[] pixels = new int[widthPix * heightPix];
            // 下面这里按照二维码的算法，逐个生成二维码的图片，
            // 两个for循环是图片横列扫描的结果
            for (int y = 0; y < heightPix; y++) {
                for (int x = 0; x < widthPix; x++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * widthPix + x] = 0xff000000;
                    } else {
                        pixels[y * widthPix + x] = 0xffffffff;
                    }
                }
            }

            // 生成二维码图片的格式，使用ARGB_8888
            Bitmap bitmap = Bitmap.createBitmap(widthPix, heightPix, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, widthPix, 0, 0, widthPix, heightPix);

            if (logoBm != null) {
                bitmap = addLogo(bitmap, logoBm);
            }

            //必须使用compress方法将bitmap保存到文件中再进行读取。直接返回的bitmap是没有任何压缩的，内存消耗巨大！
            return bitmap != null && bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(filePath));
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 生成二维码Bitmap
     *
     * @param content   内容
//     * @param widthPix  图片宽度
//     * @param heightPix 图片高度
//     * @param logoBm    二维码中心的Logo图标（可以为null）
//     * @param filePath  用于存储二维码图片的文件路径
     * @return 生成二维码及保存文件是否成功
     */
    public static Bitmap createQRImage(String content, int QR_WIDTH, int QR_HEIGHT, Bitmap logoBm) {
        try {

            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            //图像数据转换，使用了矩阵转换
            BitMatrix bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);
            int[] pixels = new int[QR_WIDTH * QR_HEIGHT];
            //下面这里按照二维码的算法，逐个生成二维码的图片，
            //两个for循环是图片横列扫描的结果
            for (int y = 0; y < QR_HEIGHT; y++) {
                for (int x = 0; x < QR_WIDTH; x++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * QR_WIDTH + x] = 0xff000000;
                    } else {
                        pixels[y * QR_WIDTH + x] = 0xffffffff;
                    }
                }
            }
            //生成二维码图片的格式，使用ARGB_8888
            Bitmap bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 在二维码中间添加Logo图案
     */
    private static Bitmap addLogo(Bitmap src, Bitmap logo) {
        if (src == null) {
            return null;
        }

        if (logo == null) {
            return src;
        }

        //获取图片的宽高
        int srcWidth = src.getWidth();
        int srcHeight = src.getHeight();
        int logoWidth = logo.getWidth();
        int logoHeight = logo.getHeight();

        if (srcWidth == 0 || srcHeight == 0) {
            return null;
        }

        if (logoWidth == 0 || logoHeight == 0) {
            return src;
        }

        //logo大小为二维码整体大小的1/5
        float scaleFactor = srcWidth * 1.0f / 5 / logoWidth;
        Bitmap bitmap = Bitmap.createBitmap(srcWidth, srcHeight, Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(bitmap);
            canvas.drawBitmap(src, 0, 0, null);
            canvas.scale(scaleFactor, scaleFactor, srcWidth / 2, srcHeight / 2);
            canvas.drawBitmap(logo, (srcWidth - logoWidth) / 2, (srcHeight - logoHeight) / 2, null);

            canvas.save(Canvas.ALL_SAVE_FLAG);
            canvas.restore();
        } catch (Exception e) {
            bitmap = null;
            e.getStackTrace();
        }

        return bitmap;
    }

    public static Bitmap getShareImage(Bitmap imageBitmap, String url, String title, String price , String delMone) {
        try {
            if (url == null) {
                url = Urls.IP;
            }
            Bitmap bitmap = Bitmap.createBitmap(375 * scale, 460 * scale, Bitmap.Config.ARGB_4444);
            // 新建一个画布
            Canvas canvas = new Canvas(bitmap);
            // 新建画笔
            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            canvas.drawRect(new Rect(0, 0, 375 * scale, 460 * scale), paint);

            canvas.drawBitmap(imageBitmap, null, new Rect(15 * scale, 15 * scale, 360 * scale, 360 * scale), paint);
            canvas.drawBitmap(createQRImage(url, 150 * scale, 150 * scale, null), null, new Rect(5 * scale, 365 * scale, 100 * scale, 460 * scale), paint);
            paint.setColor(Color.parseColor("#666666"));
            paint.setAntiAlias(true);
            paint.setFilterBitmap(true);
            paint.setTextSize(20 * scale);
            if (title.length()>8){
                canvas.drawText(title.substring(0,8)+"...", 110 * scale, 390 * scale, paint);
            }else {
                canvas.drawText(title, 110 * scale, 390 * scale, paint);
            }

            paint.setTextSize(17 * scale);

            System.out.println("delMon ==" +TextViewUtils.sprStringMoney(delMone)+"元");
            if (TextViewUtils.sprStringMoney(delMone).contains("0")&&TextViewUtils.sprStringMoney(delMone).length()<2){
                canvas.drawText("原价￥"+price+"元", 110 * scale, 420 * scale, paint);
            }else{
                canvas.drawText("原价￥"+price+"元 ", 110 * scale, 415 * scale, paint);
                canvas.drawText("立减￥"+TextViewUtils.sprStringMoney(delMone)+"元", 110 * scale, 430 * scale, paint);
            }
            canvas.drawText("长按扫描二维码查看详情", 110 * scale, 455 * scale, paint);

            canvas.drawBitmap(BitmapFactory.decodeResource(SystemConfig.getContext().getResources(), R.mipmap.icon_head), null, new Rect(300 * scale, 380 * scale, 365 * scale, 445 * scale), paint);
            canvas.save();
            canvas.restore();
            return compressImage(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Bitmap getShareImage(String url, String title) {
        try {
            if (url == null) {
                url = Urls.IP;
            }
            Bitmap bitmap = Bitmap.createBitmap(375 * scale, 460 * scale, Bitmap.Config.ARGB_4444);
            // 新建一个画布
            Canvas canvas = new Canvas(bitmap);
            // 新建画笔
            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            canvas.drawRect(new Rect(0, 0, 375 * scale, 460 * scale), paint);
            canvas.drawBitmap(createQRImage(url, 150 * scale, 150 * scale, null), null, new Rect(15 * scale, 15 * scale, 360 * scale, 360 * scale), paint);
            paint.setColor(Color.parseColor("#666666"));
            paint.setAntiAlias(true);
            paint.setFilterBitmap(true);
            paint.setTextSize(20 * scale);
            canvas.drawText(title, 20 * scale, 400 * scale, paint);
            canvas.drawText("查看详情", 20 * scale, 425 * scale, paint);
            canvas.drawText("长按扫描二维码查看详情", 20 * scale, 450 * scale, paint);
            canvas.drawBitmap(BitmapFactory.decodeResource(SystemConfig.getContext().getResources(), R.mipmap.icon_head), null, new Rect(300 * scale, 380 * scale, 365 * scale, 445 * scale), paint);
            canvas.save();
            canvas.restore();
            return compressImage(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Bitmap compressionBit(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postScale(0.5f, 0.5f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }
}
