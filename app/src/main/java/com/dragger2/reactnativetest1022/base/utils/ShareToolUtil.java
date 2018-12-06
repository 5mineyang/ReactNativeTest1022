package com.dragger2.reactnativetest1022.base.utils;

/**
 * 作者：咕咚股东
 * 链接：https://www.jianshu.com/p/9522e24713e1
 * 來源：简书
 */

public class ShareToolUtil {
//    private static String sharePicName = "share_pic.jpg";
//    private static String sharePicPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "UmeBrowser" + File.separator + "sharepic" + File.separator;
//
//    /**
//     * 保存图片，并返回一个File类型的文件
//     */
//    public static File saveSharePic(Context context, Bitmap bitmap) {
//        if (FileUtil.isSDcardExist()) {
//            File file = new File(sharePicPath);
//            if (!file.exists()) {
//                file.mkdirs();
//            }
//            File filePic = new File(sharePicPath, sharePicName);
//            if (filePic.exists()) {
//                filePic.delete();
//            }
//            try {
//                FileOutputStream out = new FileOutputStream(filePic);
//                if (bitmap == null) {
//                    bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.share_homepage);
//                }
//                bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
//                try {
//                    out.flush();
//                    out.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return filePic;
//        }
//        return null;
//    }
}
