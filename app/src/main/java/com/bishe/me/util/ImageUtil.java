package com.bishe.me.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;


public class ImageUtil {

	/**
	 * 以取样方式加载Bitmap 从byte[]中
	 * 
	 * @param data
	 * @param resId
	 * @param reqWidth
	 * @param reqHeight
	 * @return 取样后的Bitmap
	 */
	public static Bitmap decodeSampledBitmapFromBytes(byte[] data,
			int reqWidth, int reqHeight) {
		// step1，将inJustDecodeBounds置为true，以解析Bitmap真实尺寸
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeByteArray(data, 0, data.length, options);

		// step2，计算Bitmap取样比例
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);

		// step3，将inJustDecodeBounds置为false，以取样比列解析Bitmap
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeByteArray(data, 0, data.length, options);
	}

	/**
	 * @function 以取样方式加载Bitmap 从Resources中
	 * @param res
	 * @param resId
	 * @param reqWidth
	 * @param reqHeight
	 * @return 取样后的Bitmap
	 */
	public static Bitmap decodeSampledBitmapFromResource(Resources res,
			int resId, int reqWidth, int reqHeight) {
		// step1，将inJustDecodeBounds置为true，以解析Bitmap真实尺寸
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, resId, options);

		// step2，计算Bitmap取样比例
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);

		// step3，将inJustDecodeBounds置为false，以取样比列解析Bitmap
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeResource(res, resId, options);
	}

	/**
	 * 以取样方式加载Bitmap 从File中
	 * 
	 * @param pathName
	 * @param reqWidth
	 * @param reqHeight
	 * @return 取样后的Bitmap
	 */
	public static Bitmap decodeSampledBitmapFromFile(String pathName,
			int reqWidth, int reqHeight) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(pathName, options);
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);

		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(pathName, options);
	}

	/**
	 * 以取样方式加载Bitmap 从File中
	 * 
	 * @param pathName
	 * @param reqWidth
	 * @param reqHeight
	 * @return 取样后的Bitmap
	 */
	public static Bitmap decodeSampledBitmapFromFile(String pathName) {
		return BitmapFactory.decodeFile(pathName);
	}

	/**
	 * 以取样方式加载Bitmap 从Stream中
	 * 
	 * @param inputStream
	 * @param reqWidth
	 * @param reqHeight
	 * @return 取样后的Bitmap
	 */
	public static Bitmap decodeSampledBitmapFromStream(InputStream inputStream,
			int reqWidth, int reqHeight) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeStream(inputStream, null, options);

		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);

		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeStream(inputStream, null, options);
	}

	/**
	 * @function 计算Bitmap取样比例
	 * @param options
	 * @param reqWidth
	 * @param reqHeight
	 * @return 取样比例
	 */
	private static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// 默认取样比例为1:1
		int inSampleSize = 1;
		// Bitmap原始尺寸
		final int width = options.outWidth;
		final int height = options.outHeight;
		// 取最大取样比例
		if (height > reqHeight || width > reqWidth) {
			final int widthRatio = Math.round((float) width / (float) reqWidth);
			final int heightRatio = Math.round((float) height
					/ (float) reqHeight);

			// 取样比例为X:1，其中X>=1
			inSampleSize = Math.max(widthRatio, heightRatio);
		}

		return inSampleSize;
	}

	public static float calculateInSampleSize(int imgWidth, int imgHeight,
			int reqWidth, int reqHeight) {
		// 默认取样比例为1:1
		float inSampleSize = 1;
		final float widthRatio = (float) imgWidth / (float) reqWidth;
		final float heightRatio = (float) imgHeight / (float) reqHeight;
		// 缩小
		if (widthRatio > 1 || heightRatio > 1) {
			inSampleSize = widthRatio > heightRatio ? widthRatio : heightRatio;
			// 放大
		} else if (widthRatio < 1 && heightRatio < 1) {
			inSampleSize = widthRatio > heightRatio ? widthRatio : heightRatio;
		}
		return inSampleSize;
	}

	/**
	 * 缩放为指定大小
	 * 
	 * @param bitmap
	 * @param width
	 * @param height
	 * @return
	 */
	public static Bitmap decodeBitmapToSpecifySize(Bitmap bitmap, int width,
			int height) {
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		Matrix matrix = new Matrix();
		float scaleWidth = ((float) width / w);
		float scaleHeight = ((float) height / h);
		matrix.postScale(scaleWidth, scaleHeight);
		return Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
	}

	/**
	 * 将图片转换成圆角图片
	 */
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
		final int color = 0xff424242;
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		canvas.drawColor(Color.TRANSPARENT, Mode.CLEAR);
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = 6;

		paint.setAntiAlias(true);
		paint.setColor(color);
		canvas.drawARGB(0, 0, 0, 0);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		// canvas.drawCircle(cx, cy, radius, paint);

		paint.setXfermode(new android.graphics.PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, 0, 0, paint);
		return output;
	}

	/**
	 * 回收不用的bitmap
	 */
	public static void recycleBitmap(Bitmap b) {
		if (b != null && !b.isRecycled()) {
			b.recycle();
			b = null;
		}
	}

	/**
	 * 画点
	 * 
	 * @param currentIndex
	 * @param count
	 * @param radius
	 * @param spacing
	 * @return
	 */
	public static Bitmap drawPoints(int currentIndex, int count, int radius,
			int spacing) {
		Bitmap localBitmap = Bitmap.createBitmap(radius * 2 + spacing
				* (count - 1), radius * 2, Config.ARGB_8888);
		Canvas localCanvas = new Canvas();
		localCanvas.setBitmap(localBitmap);
		Paint localPaint = new Paint();
		localPaint.setAntiAlias(true);
		localPaint.setStyle(Paint.Style.FILL);
		for (int i = 0; i < count; ++i) {
			localPaint.setColor(Color.GRAY);
			if (currentIndex == i)
				localPaint.setColor(Color.RED);
			localCanvas.drawCircle(radius + spacing * i, radius, radius,
					localPaint);
		}

		return localBitmap;
	}
	
	/**
	 * bitmapToBase64
	 */
	public static String bitmapToBase64(String imgPath, Bitmap bitmap) {
		if (imgPath != null && imgPath.length() > 0) {
			bitmap = BitmapFactory.decodeFile(imgPath);
		}
		if (bitmap == null) {
			// bitmap not found!!
		}
		ByteArrayOutputStream out = null;
		try {
			out = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);

			out.flush();
			out.close();

			byte[] imgBytes = out.toByteArray();
			return Base64.encodeToString(imgBytes, Base64.DEFAULT);
		} catch (Exception e) {
			return null;
		} finally {
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * base64ToBitmap
	 */
	public static Bitmap base64ToBitmap(String base64Data) {
		byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
		return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
	}

	/**
	 * Save Bitmap to a file.保存图片到SD卡。
	 * 
	 * @param bitmap
	 * @param file
	 * @return error message if the saving is failed. null if the saving is
	 *         successful.
	 * @throws IOException
	 */
	public static void saveBitmapToFile(Bitmap bitmap, String _file)
			throws IOException {
		BufferedOutputStream os = null;
		try {
			File file = new File(_file);
			// String _filePath_file.replace(File.separatorChar +
			// file.getName(), "");
			int end = _file.lastIndexOf(File.separator);
			String _filePath = _file.substring(0, end);
			File filePath = new File(_filePath);
			if (!filePath.exists()) {
				filePath.mkdirs();
			}
			file.createNewFile();
			os = new BufferedOutputStream(new FileOutputStream(file));
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


	public static Bitmap zoomBitmap(Bitmap bitmap, int w, int h) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		Matrix matrix = new Matrix();
		float scaleWidht = ((float) w / width);
		float scaleHeight = ((float) h / height);
		matrix.postScale(scaleWidht, scaleHeight);
		Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, width, height,
				matrix, true);
		return newbmp;
	}

	/**
	 * 图片按比例大小压缩方法
	 * 
	 * @param image
	 * @return
	 */
	public static Bitmap comp(Bitmap image,int size,int width,int height) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		if (baos.toByteArray().length / 1024 > 1024) {// 判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
			baos.reset();// 重置baos即清空baos
			image.compress(Bitmap.CompressFormat.JPEG, 50, baos);// 这里压缩50%，把压缩后的数据存放到baos中
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		// 开始读入图片，此时把options.inJustDecodeBounds 设回true了
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		// 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
		int hh = width;// 这里设置高度为800f
		int ww = height;// 这里设置宽度为480f
		// 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = 1;// be=1表示不缩放
		if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;// 设置缩放比例
		newOpts.inPreferredConfig = Config.RGB_565;// 降低图片从ARGB888到RGB565
		// 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
		isBm = new ByteArrayInputStream(baos.toByteArray());
		bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		return compressImage(bitmap,size);// 压缩好比例大小后再进行质量压缩
	}

	/**
	 * 质量压缩法 size(kb)
	 */
	public static Bitmap compressImage(Bitmap image,int size) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		int options = 100;
		while (baos.toByteArray().length / 1024 > size) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
			baos.reset();// 重置baos即清空baos
			options -= 10;// 每次都减少10
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中

		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
		return bitmap;
	}

	/**
	 * 图片按比例大小压缩方法
	 * 
	 * @param srcPath
	 * @return
	 */
	public static Bitmap getimage(String srcPath,int size,int width,int height) {
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		// 开始读入图片，此时把options.inJustDecodeBounds 设回true了
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空

		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		// 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
		int hh = height;// 这里设置高度为800f
		int ww = width;// 这里设置宽度为480f
		// 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = 1;// be=1表示不缩放
		if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;// 设置缩放比例
		// 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
		bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
		return compressImage(bitmap,size);// 压缩好比例大小后再进行质量压缩
	}

	public static synchronized Drawable byteToDrawable(String icon) {

		byte[] img = Base64.decode(icon.getBytes(), Base64.DEFAULT);
		Bitmap bitmap;
		if (img != null) {

			bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
			@SuppressWarnings("deprecation")
			Drawable drawable = new BitmapDrawable(bitmap);

			return drawable;
		}
		return null;

	}

	public static synchronized String drawableToByte(Drawable drawable) {

		if (drawable != null) {
			Bitmap bitmap = Bitmap
					.createBitmap(
							drawable.getIntrinsicWidth(),
							drawable.getIntrinsicHeight(),
							drawable.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888
									: Config.RGB_565);
			Canvas canvas = new Canvas(bitmap);
			drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
					drawable.getIntrinsicHeight());
			drawable.draw(canvas);
			int size = bitmap.getWidth() * bitmap.getHeight() * 4;

			// 创建一个字节数组输出流,流的大小为size
			ByteArrayOutputStream baos = new ByteArrayOutputStream(size);
			// 设置位图的压缩格式，质量为100%，并放入字节数组输出流中
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
			// 将字节数组输出流转化为字节数组byte[]
			byte[] imagedata = baos.toByteArray();

			String icon = Base64.encodeToString(imagedata, Base64.DEFAULT);
			return icon;
		}
		return null;
	}

	/**
	 * 旋转图片
	 */
	public static Bitmap adjustBitmapRotation(Bitmap resource,
			final int orientationDegree) {
		Matrix m = new Matrix();
		m.setRotate(orientationDegree, (float) resource.getWidth() / 2,
				(float) resource.getHeight() / 2);
		try {
			Bitmap bm1 = Bitmap.createBitmap(resource, 0, 0,
					resource.getWidth(), resource.getHeight(), m, true);
			return bm1;
		} catch (OutOfMemoryError ex) {
		}
		return null;
	}
}

