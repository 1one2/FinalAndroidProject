package com.example.lightsout.workers;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.lightsout.ApplicationController;
import com.example.lightsout.R;
import com.example.lightsout.activities.Authentication;

public class ShowWorker extends Worker {
    public ShowWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Intent intent = new Intent(ApplicationController.getInstance(), Authentication.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(ApplicationController.getInstance(),
                0, intent, 0);

        NotificationCompat.Builder builder  = new NotificationCompat.Builder(ApplicationController.getInstance(), ApplicationController.CHANNEL_ID)
                .setSmallIcon(R.raw.lightoff)
                .setContentTitle("Come back")
                .setContentText("The lights are waiting for you to turn them off")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(ApplicationController.getInstance());

        notificationManager.notify(1,builder.build());
        return Result.success();
    }
}
