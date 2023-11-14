package com.example.appkotlin.features.recognize.presentation.presenter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore

class InputImagePresenter(private val context: Context) {

        fun getBitmapFromUri(uri: Uri): Bitmap{
            val bitmap = if (Build.VERSION.SDK_INT < 28) {
                MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
            } else {
                val source = ImageDecoder.createSource(context.contentResolver, uri)
                ImageDecoder.decodeBitmap(source).copy(Bitmap.Config.ARGB_8888, true)
            }
            return bitmap;
        }

        /*
        static void convertBitmapToTensorBuffer(Bitmap bitmap, TensorBuffer buffer) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        int[] intValues = new int[w * h];
        bitmap.getPixels(intValues, 0, w, 0, 0, w, h);
        int[] shape = new int[]{h, w, 3};
        switch (buffer.getDataType()) {
            case UINT8:
                byte[] byteArr = new byte[w * h * 3];
                int i = 0;

                for(int j = 0; i < intValues.length; ++i) {
                    byteArr[j++] = (byte)(intValues[i] >> 16 & 255);
                    byteArr[j++] = (byte)(intValues[i] >> 8 & 255);
                    byteArr[j++] = (byte)(intValues[i] & 255);
                }

                ByteBuffer byteBuffer = ByteBuffer.wrap(byteArr);
                byteBuffer.order(ByteOrder.nativeOrder());
                buffer.loadBuffer(byteBuffer, shape);
                break;
            case FLOAT32:
                float[] floatArr = new float[w * h * 3];
                int i = 0;

                for(int j = 0; i < intValues.length; ++i) {
                    floatArr[j++] = (float)(intValues[i] >> 16 & 255);
                    floatArr[j++] = (float)(intValues[i] >> 8 & 255);
                    floatArr[j++] = (float)(intValues[i] & 255);
                }

                buffer.loadArray(floatArr, shape);
                break;
            default:
                throw new IllegalStateException("The type of TensorBuffer, " + buffer.getBuffer() + ", is unsupported.");
        }

    }
         */
}