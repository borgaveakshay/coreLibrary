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
    Bitmap bigStyleNotificationBitmap;
    String bigStyleNotificationText;
    String subTextString;

    private NotificationBuilder(Context con,
                                String notificaionContentTitle,
                                String notificationContentText ,
                                int drawableResource,
                                int notificationId,
                                Intent intent,
                                PendingIntent pendingIntent,
                                Bitmap bitmap,
                                String bigStyleText,
                                String subTextString){

        this.context = con;
        this.contentTitle = notificaionContentTitle;
        this.contentText = notificationContentText;
        this.notificationDrawableResource = drawableResource;
        this.notificationId = notificationId;
        this.pendingIntent = pendingIntent;
        this.notificationIntent = intent;
        this.bigStyleNotificationBitmap = bitmap;
        this.bigStyleNotificationText = bigStyleText;
        this.subTextString = subTextString;
        this.notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
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
        Bitmap builderBigStyleNotificationBitmap;
        String builderBigStyleText;
        String builderSubText;

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
                    builderBigStyleNotificationBitmap,
                    builderBigStyleText,
                    builderSubText);

            notificationBuilder.notificationCompatBuilder = new NotificationCompat.Builder(builderContext);

            if(notificationBuilder.contentTitle != null)
                notificationBuilder.notificationCompatBuilder.setContentTitle(notificationBuilder.contentTitle);

            if(notificationBuilder.contentText !=  null)
                notificationBuilder.notificationCompatBuilder.setContentText(notificationBuilder.contentText);

            if(notificationBuilder.notificationDrawableResource != 0)
                notificationBuilder.notificationCompatBuilder.setSmallIcon(notificationBuilder.notificationDrawableResource);

            if(notificationBuilder.bigStyleNotificationBitmap != null){
                notificationBuilder.notificationCompatBuilder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(notificationBuilder.bigStyleNotificationBitmap));
            }
            if(notificationBuilder.bigStyleNotificationText != null)
                notificationBuilder.notificationCompatBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText(notificationBuilder.bigStyleNotificationText));

            if(notificationBuilder.pendingIntent != null)
                notificationBuilder.notificationCompatBuilder.setContentIntent(notificationBuilder.pendingIntent);

            if(notificationBuilder.subTextString != null && !notificationBuilder.subTextString.equals(""))
                notificationBuilder.notificationCompatBuilder.setSubText(notificationBuilder.subTextString);

                notificationBuilder.notificationCompatBuilder.setAutoCancel(true);

            return notificationBuilder;
        }


        public String getBuilderContentTitile() {

            return builderContentTitile;
        }

        public NotificationBuilder.Builder setContentTitile(String builderContentTitile) {
            this.builderContentTitile = builderContentTitile;
            return this;
        }

        public String getContentText() {
            return builderContentText;
        }

        public NotificationBuilder.Builder setContentText(String builderContentText) {
            this.builderContentText = builderContentText;
            return this;
        }

        public int getNotificationDrawableResource() {
            return builderNotificationDrawableResource;
        }

        public NotificationBuilder.Builder setNotificationDrawableResource(int builderNotificationDrawableResource) {
            this.builderNotificationDrawableResource = builderNotificationDrawableResource;
            return this;
        }

        public int getNotificationId() {
            return builderNotificationId;
        }

        public NotificationBuilder.Builder setNotificationId(int builderNotificationId) {
            this.builderNotificationId = builderNotificationId;
            return  this;
        }

        public Intent getNotificationIntent() {
            return notificationBuilderIntent;
        }

        public  NotificationBuilder.Builder  setNotificationIntent(Intent notificationBuilderIntent) {
            this.notificationBuilderIntent = notificationBuilderIntent;
            return this;
        }

        public PendingIntent getPendingIntent() {
            return builderPendingIntent;
        }

        public  NotificationBuilder.Builder  setPendingIntentForActivity() {

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

        public  NotificationBuilder.Builder  setPendingIntentForBroadcastReceiver() {

            if(notificationBuilderIntent != null) {
                builderPendingIntent = PendingIntent.getBroadcast(
                        builderContext,
                        0,
                        notificationBuilderIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
            }
            return this;
        }

        public Bitmap getBuilderBigStytleNotificationBitmap() {
            return builderBigStyleNotificationBitmap;
        }

        public NotificationBuilder.Builder  setBigStyleNotificationBitmap(Bitmap builderNotificationBitmap) {
            this.builderBigStyleNotificationBitmap = builderNotificationBitmap;

            return this;
        }

        public String getBigStyleText() {
            return builderBigStyleText;
        }

        public NotificationBuilder.Builder setBigStyleText(String bigStyleText) {
            this.builderBigStyleText = bigStyleText;

            return this;
        }
        public String getSubText() {
            return builderSubText;
        }

        public Builder setSubText(String builderSubText) {
            this.builderSubText = builderSubText;
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
