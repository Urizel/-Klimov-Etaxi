package com.spb.kbv.etest.infrastructure;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageDownLoader extends BroadcastReceiver {

    private static final int DELETE_OFFSET = 5 * 60 * 1000;

    public void download(final ImageView image, final String photo, final Context context) {
        final File file = new File(context.getCacheDir(), photo);
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        if (bitmap != null) {
            image.setImageBitmap(bitmap);
        } else {
            EtestApplication application = (EtestApplication)context.getApplicationContext();
            application.getApi().getImage(photo).enqueue(new Callback<ResponseBody>() {

                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Bitmap bm = BitmapFactory.decodeStream(response.body().byteStream());
                    image.setImageBitmap(bm);
                    try {
                        FileOutputStream outputStream = null;
                        try {
                            outputStream = new FileOutputStream(file);
                            bm.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                        } finally {
                            if (outputStream !=  null) {
                                outputStream.close();
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    notifyAlarmManager(context, photo, DELETE_OFFSET);
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(context, "Error while downloading picture", Toast.LENGTH_SHORT)
                            .show();
                }
            });
        }
    }

    //Set AlarmManager delete file action
    private void notifyAlarmManager(Context context, String fileName, long deleteOffset) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, ImageDownLoader.class);
        intent.setAction(fileName);
        PendingIntent pIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + deleteOffset, pIntent);
    }

    public static void deleteFile (Context context, String fileName) {
        File file = new File(context.getCacheDir(), fileName);
        file.delete();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
            //Restart AlarmManager queue after system reboot event
            for (File file: context.getCacheDir().listFiles()) {
                long fileLifeTime = System.currentTimeMillis() - file.lastModified();
                if ((fileLifeTime) > DELETE_OFFSET) {
                    file.delete();
                } else {
                    notifyAlarmManager(context, file.getName(), (DELETE_OFFSET - fileLifeTime));
                }
            }
        } else {
            //Delete file with long life
            String file = intent.getAction();
            deleteFile(context, file);
        }
    }
}
