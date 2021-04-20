package com.bishe.me;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UploadFileListener;
import com.bishe.me.util.ImageUtil;
import java.io.File;

@SuppressLint("NewApi") 

public class ActivityChooseBitmap extends BaseActivity{
	
	LinearLayout linearLayout;
	 EditText editText;
	 
	 ImageView imageView;
	 
	 Button button;
	 
	 private static final int IMAGE_REQUEST_CODE = 3;
	    private static final int SELECT_PIC_KITKAT = 4;
	    private static final int CAMERA_REQUEST_CODE = 5;
	    private static final int RESULT_REQUEST_CODE = 6;
	    private static final int RESULT_GESTUREE_CODE = 7;
	    private String imgPath;
	    private  Bitmap photo;
	    private boolean has;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choosebitmap);
		Bmob.initialize(this, getResources().getString(R.string.bomb));
		
		
		imageView=(ImageView)findViewById(R.id.imageView1);
		button=(Button)findViewById(R.id.button3);
	
		String SDPath = Environment.getExternalStorageDirectory().getAbsolutePath();
		 makeRootDirectory(SDPath + "/" + "111111football");
		 imgPath=SDPath+"/111111football/"+"test.jpg";

		
		
		
	}
	
	
	
	public void chuan(View v) {
		if (true){
			
			//showTost(imgPath);
			
			
//			String filePath_mp3 = imgPath;
//			String filePath_lrc = imgPath;
//			final String[] filePaths = new String[2];
//			filePaths[0] = filePath_mp3;
//			filePaths[1] = filePath_lrc;
//			BmobFile.uploadBatch(filePaths, 
//					new UploadBatchListener() {
//
//			    @Override
//			    public void onSuccess(List<BmobFile> files,List<String> urls) {
//			    	
//			    	showTost("111");
//			        //1、files-上传完成后的BmobFile集合，是为了方便大家对其上传后的数据进行操作，例如你可以将该文件保存到表中
//			        //2、urls-上传文件的完整url地址
//			        if(urls.size()==filePaths.length){//如果数量相等，则代表文件全部上传完成
//			            //do something
//			        }
//			    }
//
//			    @Override
//			    public void onError(int statuscode, String errormsg) {
//			    	showTost("错误码"+statuscode +",错误描述："+errormsg);
//			    }
//
//			    @Override
//			    public void onProgress(int curIndex, int curPercent, int total,int totalPercent) {
//			        //1、curIndex--表示当前第几个文件正在上传
//			        //2、curPercent--表示当前上传文件的进度值（百分比）
//			        //3、total--表示总的上传文件数
//			        //4、totalPercent--表示总的上传进度（百分比）
//			    }
//			});
			
			
		String picPath = imgPath;
		final BmobFile bmobFile = new BmobFile(new File(picPath));
		showProgressDialog("", "");
		
		try {
			
		
			bmobFile.uploadblock(new UploadFileListener() {

			    @Override
			    public void done(BmobException e) {
			        if(e==null){
			            //bmobFile.getFileUrl()--返回的上传文件的完整地址
			            showTost("上传成功");
			            Activity3.imageView.setImageBitmap(photo);
			            Activity3.url=bmobFile.getFileUrl();
			            dissProgressDialog();
			            finish();
			        }else{
			        	showTost("上传文件失败：" + e.getMessage());
			        	dissProgressDialog();
			        }
			    }
			    @Override
			    public void onProgress(Integer value) {
			        // 返回的上传进度（百分比）
			    	button.setText("上传百分比： "+value);
			    	
			    }
			});
			
		} catch (Exception e) {
			// TODO: handle exception
			showTost("你的网络无法连接云服务器");
		}
			
			
			
		}
		
		
		
	}
	
	
	 public void pai(View v) {
		 Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
         // 判断存储卡是否可以用，可用进行存储
       
           //  imgPath=Environment.getExternalStorageDirectory()
             //        +File.separator+ System.currentTimeMillis() +"_"
               //      + "123"+".jpg";
             
             intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT,
                     Uri.fromFile(new File(imgPath)));
         
         startActivityForResult(intentFromCapture,
                 CAMERA_REQUEST_CODE);


	    }
	 public void xuan(View v) {
		// imgPath=Environment.getExternalStorageDirectory()
          //       +File.separator+ System.currentTimeMillis() +"_"
            //     + "123"+".jpg";
         Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
         intent.addCategory(Intent.CATEGORY_OPENABLE);
         intent.setType("image/*");
         if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
             startActivityForResult(intent,SELECT_PIC_KITKAT);
         } else {
             startActivityForResult(intent,IMAGE_REQUEST_CODE);
         }


	    }
	 
	 
	 public static void into(Context context){
	        Intent intent =new Intent(context,ActivityChooseBitmap.class);
	       
	        context.startActivity(intent);
	    }
	 
	 
	 
	 
	 
	
	    @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);

	        if(resultCode==RESULT_OK){
	           
	        }

	        if (resultCode != RESULT_CANCELED) {
	            switch (requestCode) {
	                case IMAGE_REQUEST_CODE:
	                    startPhotoZoom(data.getData());
	                    break;
	                case SELECT_PIC_KITKAT:
	                    startPhotoZoom(data.getData());
	                    break;
	                case CAMERA_REQUEST_CODE:
	                    if (true) {
	                        File tempFile = new File(imgPath);
	                        startPhotoZoom(Uri.fromFile(tempFile));
	                    } else {
	                        //showTost(R.string.no_sdcard);
	                    }
	                    break;
	                case RESULT_REQUEST_CODE:
	                    if (data != null) {
	                        setImageToView(data,imageView
	                        		);
	                    }
	                    break;
	            }
	        }
	    }
	 
	 
	 
	 
	 
	 private void setImageToView(Intent data,ImageView imageView) {
	        Bundle extras = data.getExtras();
	        if (extras != null) {
	            photo = extras.getParcelable("data");
	            try {
	                imageView.setImageBitmap(photo);
	                has=true;
	                ImageUtil.saveBitmapToFile
	                (photo, imgPath);
	             //   String path=PreferenceHelper.readString(mContext,Constants.sp_final_file_name, loginName);
	             //  if(!TextUtils.isEmpty(path)){
	               //     FileUtil.deleteFile
	                 //   (new File(path));
	                //}
	              //  PreferenceHelper.write(mContext,Constants.sp_final_file_name,loginName, imgPath);
	               // headChanged=true;
	            }catch (Exception e){
	                e.printStackTrace();
	            }
	        }
	    }

	    public void startPhotoZoom(Uri uri) {
	        if (uri == null) {
	            return;
	        }
	        Intent intent = new Intent("com.android.camera.action.CROP");
	        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
	            String url=getPath(this,uri);
	            intent.setDataAndType(Uri.fromFile(new File(url)),"image/*");
	        }else{
	            intent.setDataAndType(uri,"image/*");
	        }
	        // 设置裁剪
	        intent.putExtra("crop","true");
	        // aspectX aspectY 是宽高的比例
	        intent.putExtra("aspectX", 4);
	        intent.putExtra("aspectY", 3);
	        // outputX outputY 是裁剪图片宽高
	        intent.putExtra("outputX", 400);
	        intent.putExtra("outputY", 300);
	        intent.putExtra("return-data", true);
	        startActivityForResult(intent, RESULT_REQUEST_CODE);
	    }

	    //以下是关键，原本uri返回的是file:///...来着的，android4.4返回的是content:///...
	   
	    public static String getPath(final Context context, final Uri uri) {

	        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
	        // DocumentProvider
	        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
	            // ExternalStorageProvider
	            if (isExternalStorageDocument(uri)) {
	                final String docId = DocumentsContract.getDocumentId(uri);
	                final String[] split = docId.split(":");
	                final String type = split[0];
	                if ("primary".equalsIgnoreCase(type)) {
	                    return Environment.getExternalStorageDirectory() +"/"+ split[1];
	                }
	            }
	            // DownloadsProvider
	            else if (isDownloadsDocument(uri)) {
	                final String id = DocumentsContract.getDocumentId(uri);
	                final Uri contentUri = ContentUris.withAppendedId(
	                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
	                return getDataColumn(context, contentUri, null, null);
	            }
	            // MediaProvider
	            else if (isMediaDocument(uri)) {
	                final String docId = DocumentsContract.getDocumentId(uri);
	                final String[] split = docId.split(":");
	                final String type = split[0];
	                Uri contentUri = null;
	                if ("image".equals(type)) {
	                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
	                } else if ("video".equals(type)) {
	                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
	                } else if ("audio".equals(type)) {
	                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
	                }
	                final String selection ="_id=?";
	                final String[] selectionArgs = new String[] {
	                        split[1]
	                };

	                return getDataColumn(context, contentUri, selection, selectionArgs);
	            }
	        }
	        // MediaStore (and general)
	        else if ("content".equalsIgnoreCase(uri.getScheme())) {
	            // Return the remote address
	            if (isGooglePhotosUri(uri))
	                return uri.getLastPathSegment();
	            return getDataColumn(context, uri, null, null);
	        }
	        // File
	        else if ("file".equalsIgnoreCase(uri.getScheme())) {
	            return uri.getPath();
	        }
	        return null;
	    }

	    /**
	     * Get the value of the data column for this Uri. This is useful for
	     * MediaStore Uris, and other file-based ContentProviders.
	     *
	     * @param context The context.
	     * @param uri The Uri to query.
	     * @param selection (Optional) Filter used in the query.
	     * @param selectionArgs (Optional) Selection arguments used in the query.
	     * @return The value of the _data column, which is typically a file path.
	     */
	    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
	        Cursor cursor = null;
	        final String column ="_data";
	        final String[] projection = {column};

	        try {
	            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
	            if (cursor != null && cursor.moveToFirst()) {
	                final int index = cursor.getColumnIndexOrThrow(column);
	                return cursor.getString(index);
	            }
	        } finally {
	            if (cursor != null)
	                cursor.close();
	        }
	        return null;
	    }

	    /**
	     * @param uri The Uri to check.
	     * @return Whether the Uri authority is ExternalStorageProvider.
	     */

	    public static boolean isExternalStorageDocument(Uri uri) {
	        return"com.android.externalstorage.documents".equals(uri.getAuthority());
	    }

	    /**
	     * @param uri The Uri to check.
	     * @return Whether the Uri authority is DownloadsProvider.
	     */
	    public static boolean isDownloadsDocument(Uri uri) {
	        return"com.android.providers.downloads.documents".equals(uri.getAuthority());
	    }

	    /**
	     * @param uri The Uri to check.
	     * @return Whether the Uri authority is MediaProvider.
	     */
	    public static boolean isMediaDocument(Uri uri) {
	        return"com.android.providers.media.documents".equals(uri.getAuthority());
	    }

	    /**
	     * @param uri The Uri to check.
	     * @return Whether the Uri authority is Google Photos.
	     */
	    public static boolean isGooglePhotosUri(Uri uri) {
	        return"com.google.android.apps.photos.content".equals(uri.getAuthority());
	    }
	    
	    
	    
	    public static void makeRootDirectory(String filePath) {
	        File file = null;
	        try {
	            file = new File(filePath);
	            if (!file.exists()) {
	                file.mkdir();
	            }
	        } catch (Exception e) {
	            Log.i("error:", e + "");
	        }
	    }


}
