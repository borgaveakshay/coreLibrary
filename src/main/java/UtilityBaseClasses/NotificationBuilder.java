package UtilityBaseClasses;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Akshay.Borgave on 11-08-2016.
 */
public class NotificationBuilder {

    Context context;
    String contentTitle;
    String contentText;
    int notificationDrawableResource;
    int notificationId;
    NotificationManager notificationManager;
    NotificationCompat.Builder notificationCompatBuilder;
    Intent notificationIntent;
    PendingIntent pendingIntent;
    Bitmap notificationBitmap;

    private NotificationBuilder(Context con, String notificaionContentTitle, String notificationContentText , int drawableResource, int notificationId, Intent intent, PendingIntent pendingIntent, Bitmap bitmap)
    {

        context = con;
        contentTitle = notificaionContentTitle;
        contentText = notificationContentText;
        notificationDrawableResource = drawableResource;
        this.notificationId = notificationId;
        this.pendingIntent = pendingIntent;
        notificationIntent = intent;
        notificationBitmap = bitmap;
        notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public static class Builder {

        Context builderContext;
        NotificationBuilder notificationBuilder;
        String builderContentTitile;
        String builderContentText;
        int builderNotificationDrawableResource;
        int builderNotificationId;
        Intent notificationBuilderIntent;
        PendingIntent builderPendingIntent;
        Bitmap builderNotificationBitmap;

        public Builder(Context context){

            builderContext = context;
        }

        public Builder(Context context, String contentTitle){

            builderContext = context;
            builderContentTitile = contentTitle;

        }
        public Builder(Context context, String contentTitle, String contentText){

            builderContext = context;
            builderContentTitile = contentTitle;
            builderContentText = contentText;

        }

        public Builder(Context context, String contentTitle, String contentText, int drawableResource){

            builderContext = context;
            builderContentTitile = contentTitle;
            builderContentText = contentText;
            builderNotificationDrawableResource = drawableResource;

        }
        public Builder(Context context, String contentTitle, String contentText, int drawableResource, int notificationId){

            builderContext = context;
            builderContentTitile = contentTitle;
            builderContentText = contentText;
            builderNotificationDrawableResource = drawableResource;
            builderNotificationId = notificationId;

        }

        public NotificationBuilder build(){

            notificationBuilder =  new NotificationBuilder(
                    builderContext,
                    builderContentTitile,
                    builderContentText,
                    builderNotificationDrawableResource,
                    builderNotificationId,
                    notificationBuilderIntent,
                    builderPendingIntent,
                    builderNotificationBitmap);

            notificationBuilder.notificationCompatBuilder = new NotificationCompat.Builder(builderContext);

            if(notificationBuilder.contentTitle != null)
                notificationBuilder.notificationCompatBuilder.setContentTitle(notificationBuilder.contentTitle);

            if(notificationBuilder.contentText !=  null)
                notificationBuilder.notificationCompatBuilder.setContentText(notificationBuilder.contentText);

            if(notificationBuilder.notificationDrawableResource != 0)
                notificationBuilder.notificationCompatBuilder.setSmallIcon(notificationBuilder.notificationDrawableResource);

            if(notificationBuilder.notificationBitmap != null)
                notificationBuilder.notificationCompatBuilder.setLargeIcon(notificationBuilder.notificationBitmap);

            return notificationBuilder;
        }


        public String getBuilderContentTitile() {

            return builderContentTitile;
        }

        public NotificationBuilder.Builder setBuilderContentTitile(String builderContentTitile) {
            this.builderContentTitile = builderContentTitile;
            return this;
        }

        public String getBuilderContentText() {
            return builderContentText;
        }

        public NotificationBuilder.Builder setBuilderContentText(String builderContentText) {
            this.builderContentText = builderContentText;
            return this;
        }

        public int getBuilderNotificationDrawableResource() {
            return builderNotificationDrawableResource;
        }

        public NotificationBuilder.Builder setBuilderNotificationDrawableResource(int builderNotificationDrawableResource) {
            this.builderNotificationDrawableResource = builderNotificationDrawableResource;
            return this;
        }

        public int getBuilderNotificationId() {
            return builderNotificationId;
        }

        public NotificationBuilder.Builder setBuilderNotificationId(int builderNotificationId) {
            this.builderNotificationId = builderNotificationId;
            return  this;
        }

        public Intent getNotificationBuilderIntent() {
            return notificationBuilderIntent;
        }

        public  NotificationBuilder.Builder  setNotificationBuilderIntent(Intent notificationBuilderIntent) {
            this.notificationBuilderIntent = notificationBuilderIntent;
            return this;
        }

        public PendingIntent getBuilderPendingIntent() {
            return builderPendingIntent;
        }

        public  NotificationBuilder.Builder  setBuilderPendingIntent() {

            if(notificationBuilderIntent != null) {
                builderPendingIntent = PendingIntent.getActivity(
                        builderContext,
                        0,
                        notificationBuilderIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
            }
            return this;
        }

        public Bitmap getBuilderNotificationBitmap() {
            return builderNotificationBitmap;
        }

        public NotificationBuilder.Builder  setBuilderNotificationBitmap(Bitmap builderNotificationBitmap) {
            this.builderNotificationBitmap = builderNotificationBitmap;
            return this;
        }

    }

    public void sendNotification(){

        notificationManager.notify(notificationId, notificationCompatBuilder.build());
    }

    public NotificationCompat.Builder getNotificationCompatBuilder() {
        return notificationCompatBuilder;
    }
}
