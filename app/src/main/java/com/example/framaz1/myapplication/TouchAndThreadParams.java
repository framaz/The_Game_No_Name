package com.example.framaz1.myapplication;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;

import com.example.framaz1.myapplication.Items.EmptyItem;
import com.example.framaz1.myapplication.Items.MotherItem;

public class TouchAndThreadParams {
    public static boolean justClicked, secondFinger;
    public static int flastX, flastY, slastX, slastY;
    public static boolean used;

    public static final Object outStreamSync = new Object();
    public static Object gameSync = new Object();
    public static final double inventoryResize=2;
    public static final Object animationSync = new Object();
    public static long whenClicked;
    public static String getWhatPlayerDesired() {
        int widthOfInv=AllBitmaps.inventoryImage.getWidth();
        if(Params.gameField) {
            if (Game.whereToGoX > Params.displaySettings.heightPixels - AllBitmaps.standartIconSize - 38 && Game.whereToGoY > Params.displaySettings.widthPixels - 2 * AllBitmaps.standartIconSize)
                return "Inventory";
            if (Game.whereToGoX > Params.displaySettings.heightPixels - AllBitmaps.standartIconSize - 38 && Game.whereToGoY > Params.displaySettings.widthPixels / 2 - AllBitmaps.standartIconSize && Game.whereToGoY < Params.displaySettings.widthPixels / 2)
                return "Wait";
            if(Game.whereToGoX>(100 - 38)*widthOfInv/288&&Game.whereToGoX<(100-38)*widthOfInv/288+AllBitmaps.getItemHere.getHeight()&&Game.whereToGoY>Params.displaySettings.widthPixels -AllBitmaps.getItemHere.getWidth()&&(Game.gamedepths[Game.layer].field[Game.player.yCoordinates][Game.player.xCoordinates].goldHere>0||Game.gamedepths[Game.layer].field[Game.player.yCoordinates][Game.player.xCoordinates].itemsHere.size()>0) )
                return "TakeFromFloor";
            if(Game.whereToGoX>(100 - 38+50)*widthOfInv/288&&Game.whereToGoX<(100-38+50)*widthOfInv/288+AllBitmaps.getItemHere.getHeight()&&Game.whereToGoY>Params.displaySettings.widthPixels -AllBitmaps.getItemHere.getWidth()&&Game.gamedepths[Game.layer].field[Game.player.yCoordinates][Game.player.xCoordinates].interractable)
                return "Interact";
            return "Move";
        }
        if(Params.inventory) {
            if(Game.whereToGoX>Params.displaySettings.heightPixels/2-AllBitmaps.inventoryImage.getHeight()/2 &&
                    Game.whereToGoX<Params.displaySettings.heightPixels-(Params.displaySettings.heightPixels/2-AllBitmaps.inventoryImage.getHeight()/2) &&
                    Game.whereToGoY>Params.displaySettings.widthPixels/2-AllBitmaps.inventoryImage.getWidth()/2 &&
                    Game.whereToGoY<Params.displaySettings.widthPixels-(Params.displaySettings.widthPixels/2-AllBitmaps.inventoryImage.getHeight()/2))
                return "ChooseItem";
            else
                return "Back";
        }
        if(Params.item) {
            if(Game.whereToGoX>Params.displaySettings.heightPixels/2-AllBitmaps.inventoryImage.getHeight()/2 &&
                    Game.whereToGoX<Params.displaySettings.heightPixels-(Params.displaySettings.heightPixels/2-AllBitmaps.inventoryImage.getHeight()/2) &&
                    Game.whereToGoY>Params.displaySettings.widthPixels/2-AllBitmaps.inventoryImage.getWidth()/2 &&
                    Game.whereToGoY<Params.displaySettings.widthPixels-(Params.displaySettings.widthPixels/2-AllBitmaps.inventoryImage.getHeight()/2)) {
                if(Game.whereToGoX>Params.displaySettings.heightPixels/2-AllBitmaps.inventoryImage.getHeight()/2-19+(int)(408*widthOfInv/288))
                {
                    return "PushInventoryButton";
                }
                else return "Nothing";

            }
            else return "BackToInventory";
        }
        return "";
    }
    public static Canvas drawMenu(Canvas canvas){
        Bitmap picture;
        picture= AllBitmaps.waitIcon;
        int widthOfInv=AllBitmaps.inventoryImage.getWidth();
        canvas.drawBitmap(picture,Params.displaySettings.widthPixels/2-AllBitmaps.standartIconSize,Params.displaySettings.heightPixels-AllBitmaps.standartIconSize-38,null);
      // canvas.drawBitmap(picture,0,Params.displaySettings.heightPixels-AllBitmaps.standartIconSize-38,null);
        picture= AllBitmaps.magicIcon;
   //     canvas.drawBitmap(picture,Params.displaySettings.widthPixels/2,Params.displaySettings.heightPixels-AllBitmaps.standartIconSize-38,null);
        picture = AllBitmaps.inventoryIcon;
        canvas.drawBitmap(picture, Params.displaySettings.widthPixels - 2 * AllBitmaps.standartIconSize, Params.displaySettings.heightPixels - AllBitmaps.standartIconSize - 38, null);
        if(Game.gamedepths[Game.layer].field[Game.player.yCoordinates][Game.player.xCoordinates].goldHere>0||Game.gamedepths[Game.layer].field[Game.player.yCoordinates][Game.player.xCoordinates].itemsHere.size()>0) {
            picture = AllBitmaps.getItemHere;
            canvas.drawBitmap(picture, Params.displaySettings.widthPixels - picture.getWidth(), (100 - 38)*widthOfInv/288, null);
            if(Game.gamedepths[Game.layer].field[Game.player.yCoordinates][Game.player.xCoordinates].goldHere>0)
                canvas.drawBitmap(AllBitmaps.coins, Params.displaySettings.widthPixels - picture.getWidth()+4*widthOfInv/288, (100 - 38 + 4)*widthOfInv/288, null);
            else
                canvas.drawBitmap(Game.gamedepths[Game.layer].field[Game.player.yCoordinates][Game.player.xCoordinates].itemsHere.peek().picture, Params.displaySettings.widthPixels - picture.getWidth() + 4*widthOfInv/288, (100 - 38+4)*widthOfInv/288, null);

        }
        if(Game.gamedepths[Game.layer].field[Game.player.yCoordinates][Game.player.xCoordinates].interractable)
        {
            picture = AllBitmaps.getItemHere;
            canvas.drawBitmap(picture, Params.displaySettings.widthPixels - picture.getWidth(), (100 - 38+50)*widthOfInv/288, null);
            picture=Bitmap.createScaledBitmap(AllBitmaps.getPictureById(Game.gamedepths[Game.layer].field[Game.player.yCoordinates][Game.player.xCoordinates].drawId),AllBitmaps.coins.getWidth(),AllBitmaps.coins.getHeight(),false);
            canvas.drawBitmap(picture, Params.displaySettings.widthPixels - AllBitmaps.getItemHere.getWidth()+4*widthOfInv/288, (100 - 38 + 4+50)*widthOfInv/288, null);
        }
        return canvas;
    }
    public static void drawMultiLineEllipsizedText(final Canvas _canvas, final TextPaint _textPaint, final float _left,
                                                   final float _top, final float _right, final float _bottom, final String _text) {
        //Thanks to androidseb on http://stackoverflow.com/questions/6756975/draw-multi-line-text-to-canvas
        final float height = _bottom - _top;

        final StaticLayout measuringTextLayout = new StaticLayout(_text, _textPaint, (int) Math.abs(_right - _left),
                Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);

        int line = 0;
        final int totalLineCount = measuringTextLayout.getLineCount();
        for (line = 0; line < totalLineCount; line++) {
            final int lineBottom = measuringTextLayout.getLineBottom(line);
            if (lineBottom > height) {
                break;
            }
        }
        line--;

        if (line < 0) {
            return;
        }

        int lineEnd;
        try {
            lineEnd = measuringTextLayout.getLineEnd(line);
        } catch (Throwable t) {
            lineEnd = _text.length();
        }
        String truncatedText = _text.substring(0, Math.max(0, lineEnd));

        if (truncatedText.length() < 3) {
            return;
        }

        if (truncatedText.length() < _text.length()) {
            truncatedText = truncatedText.substring(0, Math.max(0, truncatedText.length() - 3));
            truncatedText += "...";
        }
        final StaticLayout drawingTextLayout = new StaticLayout(truncatedText, _textPaint, (int) Math.abs(_right
                - _left), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);

        _canvas.save();
        _canvas.translate(_left, _top);
        drawingTextLayout.draw(_canvas);
        _canvas.restore();
    }
    public static Canvas drawInventory(Canvas canvas) {
        Bitmap picture;
        picture=AllBitmaps.inventoryImage;
    //    picture=Bitmap.createScaledBitmap(picture,(int)(picture.getWidth()*inventoryResize),(int)(picture.getHeight()*inventoryResize),false);
        int widthToOff=Params.displaySettings.widthPixels/2-picture.getWidth()/2;
        int heightToOff=Params.displaySettings.heightPixels/2-picture.getHeight()/2-19;
        int widthOfInv=AllBitmaps.inventoryImage.getWidth();
        int heightOfInv=AllBitmaps.inventoryImage.getHeight();
        canvas.drawBitmap(picture,widthToOff,heightToOff,null);
        if(!Game.player.bodyWear.name.equals("")) {

            picture=Game.player.bodyWear.picture;
     //       picture=Bitmap.createScaledBitmap(picture,(int)(picture.getWidth()*inventoryResize),(int)(picture.getHeight()*inventoryResize),false);
            canvas.drawBitmap(picture,widthToOff+(int)(16*widthOfInv/288),heightToOff+(int)(32*heightOfInv/464),null);
        }
        if(!Game.player.weapon.name.equals("")) {

            picture=Game.player.weapon.picture;
      //      picture=Bitmap.createScaledBitmap(picture,(int)(picture.getWidth()*inventoryResize),(int)(picture.getHeight()*inventoryResize),false);
            canvas.drawBitmap(picture,widthToOff+(int)(72*widthOfInv/288),heightToOff+(int)(32*heightOfInv/464),null);
        }
        if(!Game.player.jewel.name.equals("")) {

            picture=Game.player.jewel.picture;
     //       picture=Bitmap.createScaledBitmap(picture,(int)(picture.getWidth()*inventoryResize),(int)(picture.getHeight()*inventoryResize),false);
            canvas.drawBitmap(picture,widthToOff+(int)((16+56*2)*widthOfInv/288),heightToOff+(int)(32*heightOfInv/464),null);
        }
        if(!Game.player.ring1.name.equals("")) {

            picture=Game.player.ring1.picture;
     //       picture=Bitmap.createScaledBitmap(picture,(int)(picture.getWidth()*inventoryResize),(int)(picture.getHeight()*inventoryResize),false);
            canvas.drawBitmap(picture,widthToOff+(int)((16+56*3)*widthOfInv/288),heightToOff+(int)(32*heightOfInv/464),null);
        }
        if(!Game.player.ring2.name.equals("")) {

            picture=Game.player.ring2.picture;
       //     picture=Bitmap.createScaledBitmap(picture,(int)(picture.getWidth()*inventoryResize),(int)(picture.getHeight()*inventoryResize),false);
            canvas.drawBitmap(picture,widthToOff+(int)((16+56*4)*widthOfInv/288),heightToOff+(int)(32*heightOfInv/464),null);
        }
        for(int i=0;i<4;i++)
            for(int j=0;j<5;j++)
            {
                if(!Game.player.items[j + i * 5].name.equals(""))
                {
                    picture=Game.player.items[j+i*5].picture;
        //            picture=Bitmap.createScaledBitmap(picture,(int)(picture.getWidth()*inventoryResize),(int)(picture.getHeight()*inventoryResize),false);
                    canvas.drawBitmap(picture,widthToOff+(int)((16+56*j)*widthOfInv/288),heightToOff+(int)((136+90*i)*heightOfInv/464),null);
                }
            }

        return canvas;
    }
    public static Canvas drawItem(Canvas canvas) {
        Bitmap picture;
        picture=AllBitmaps.itemView;
       // picture=Bitmap.createScaledBitmap(picture,(int)(picture.getWidth()*inventoryResize),(int)(picture.getHeight()*inventoryResize),false);
        int widthToOff=Params.displaySettings.widthPixels/2-picture.getWidth()/2;
        int heightToOff=Params.displaySettings.heightPixels/2-picture.getHeight()/2-19;
        int widthOfInv=AllBitmaps.inventoryImage.getWidth();
        int heightOfInv=AllBitmaps.inventoryImage.getHeight();
        canvas.drawBitmap(picture,widthToOff,heightToOff,null);
        MotherItem item=null;
        if(Params.itemToShow>=0)
            item=Game.player.items[Params.itemToShow];
        else
        switch (Params.itemToShow) {
            case -5:
                item=Game.player.bodyWear;
                break;
            case -4:
                item=Game.player.weapon;
                break;
            case -3:
                item=Game.player.jewel;
                break;
            case -2:
                item=Game.player.ring1;
                break;
            case -1:
                item=Game.player.ring2;
                break;
        }
        picture=item.picture;
     //   picture=Bitmap.createScaledBitmap(picture,(int)(picture.getWidth()*inventoryResize),(int)(picture.getHeight()*inventoryResize),false);
        canvas.drawBitmap(picture,widthToOff+(int)(8*widthOfInv/288),heightToOff+(int)(8*heightOfInv/464),null);
        TextPaint tp=new TextPaint();
        tp.setTextSize((float)(8*widthOfInv/288));
        drawMultiLineEllipsizedText(canvas,tp,widthToOff+54*widthOfInv/288,heightToOff+(int)(6*widthOfInv/288),600,600,item.name);
        drawMultiLineEllipsizedText(canvas,tp,widthToOff+(int)(6*widthOfInv/288),heightToOff+(int)(56*widthOfInv/288),widthToOff+(int)(280*widthOfInv/288),heightToOff+(int)(400*widthOfInv/288),item.description);

        //To unEquip

        if(item.equipable&&Params.itemToShow<0)
        {
            picture=AllBitmaps.unequip;
            canvas.drawBitmap(picture,widthToOff,heightToOff+(int)(408*widthOfInv/288),null);
        }

        //To Equip

        if(item.equipable&&Params.itemToShow>=0)
        {
            picture=AllBitmaps.equip;
            canvas.drawBitmap(picture,widthToOff,heightToOff+(int)(408*widthOfInv/288),null);
        }

        //To use

        if(item.usable)
        {
            picture=AllBitmaps.use;
            canvas.drawBitmap(picture,widthToOff+(int)(picture.getWidth()),heightToOff+(int)(408*widthOfInv/288),null);
        }

        //To Drop

        if(Params.itemToShow>=0)
        {
            picture = AllBitmaps.drop;
            canvas.drawBitmap(picture, widthToOff + (int) (picture.getWidth())*2, heightToOff + (int) (408*widthOfInv/288), null);
        }
        return canvas;
    }
    public static Canvas drawAllTilesOnTheField(Canvas canvas){
        int startX, startY, endX, endY;
        startX = Params.displayX / Params.size - 1;
        startY = Params.displayY / Params.size - 1;
        endX = startX + Params.displaySettings.heightPixels / Params.size + 1;
        Bitmap picture;
        endY = startY + Params.displaySettings.widthPixels / Params.size + 1;
        for (int i = startX; i <= endX+2; i++)
            for (int j = startY; j <= endY+2; j++) {
                if (i >= 0 && j >= 0 && i < 100 && j < 100) {
                    picture = AllBitmaps.getPictureById(Game.gamedepths[Game.layer].field[i][j].drawId);
                    canvas.drawBitmap(picture, j * Params.size - Params.displayY, i * Params.size - Params.displayX, null);
                    if(Game.gamedepths[Game.layer].field[i][j].goldHere>0)
                        canvas.drawBitmap(Bitmap.createScaledBitmap(AllBitmaps.coins,Params.size,Params.size,false),j * Params.size - Params.displayY, i * Params.size - Params.displayX, null);
                    else
                        if(Game.gamedepths[Game.layer].field[i][j].itemsHere.size()>0)
                            canvas.drawBitmap(Bitmap.createScaledBitmap(Game.gamedepths[Game.layer].field[i][j].itemsHere.peek().picture,Params.size,Params.size,false),j * Params.size - Params.displayY, i * Params.size - Params.displayX, null);
                }
            }
        return canvas;
    }
}