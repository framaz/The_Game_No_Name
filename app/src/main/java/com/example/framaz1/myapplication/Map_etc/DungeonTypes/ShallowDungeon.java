package com.example.framaz1.myapplication.Map_etc.DungeonTypes;

import com.example.framaz1.myapplication.Game;
import com.example.framaz1.myapplication.Map_etc.Mothermap;
import com.example.framaz1.myapplication.Map_etc.RoomHelper;
import com.example.framaz1.myapplication.Tiles.StandardDungeon.DungeonDownStairway;
import com.example.framaz1.myapplication.Tiles.StandardDungeon.DungeonFloor;
import com.example.framaz1.myapplication.Tiles.StandardDungeon.DungeonUpStairway;
import com.example.framaz1.myapplication.Tiles.StandardDungeon.DungeonWall;


public class ShallowDungeon extends Mothermap {
    public ShallowDungeon()
    {
        super();
    }
    public ShallowDungeon(int where)
    {
        super(where);
    }
    @Override
    public void generateTheField()
    {
        generated=true;
        int n=(int)(Math.random()*20+29);
        for(int i=0;i<100;i++)
            for(int j=0;j<100;j++)
                field[i][j]=new DungeonWall();

        //Генерация первой комнаты

        RoomHelper room = new RoomHelper(4+(int)(Math.random()*80),(int)(4+Math.random()*80),(int)(Math.random()*7)+3,(int)(Math.random()*7)+3);
        for(int i=room.y;i<room.y+room.heigth;i++)
            for(int j=room.x;j<room.x+room.width;j++)
                field[i][j]=new DungeonFloor();
        RoomHelper prev=room;

        //Генерация комнат

        for(int k=0;k<n;k++)
        {
            prev=room;
            room=new RoomHelper((int)(Math.random()*84),(int)(Math.random()*84),(int)(Math.random()*7)+3,(int)(Math.random()*7)+3);
            for(int i=room.y;i<room.y+room.heigth;i++)
                for(int j=room.x;j<room.x+room.width;j++)
                    field[i][j]=new DungeonFloor();

            //Генерацая путей (от новой комнаты к предыдущей)

            int fx,fy,tx,ty;
            fx=room.x+(int)(Math.random()*room.width);
            fy=room.y+(int)(Math.random()*room.heigth);
            tx=prev.x+(int)(Math.random()*prev.width);
            ty=prev.y+(int)(Math.random()*prev.heigth);
            int i=fx;
            while(i!=tx)
            {
                field[fy][i]=new DungeonFloor();
                if(i>tx)
                    i--;
                else i++;
            }
            field[fy][i]=new DungeonFloor();

            i=fy;
            while (i!=ty)
            {
                field[i][tx]=new DungeonFloor();
                if(i>ty)
                    i--;
                else i++;
            }
            field[fy][i]=new DungeonFloor();
        }
        int i=1;
        int x,y;
        while(i<=3)
        {
            x=(int)(Math.random()*100);
            y=(int)(Math.random()*100);
            if(!field[x][y].iswall&&!field[x][y].interractable)
            {

                field[x][y]=new DungeonDownStairway();
                field[x][y].linker=i;
                i++;
            }
        }
        i=1;
        while(i<=3)
        {
            x=(int)(Math.random()*100);
            y=(int)(Math.random()*100);
            if(!field[x][y].iswall&&!field[x][y].interractable)
            {

                field[x][y]=new DungeonUpStairway();
                field[x][y].linker=i+5;
                i++;
            }
        }
        for(i=0;i<50;i=i) {
            x=(int)(Math.random()*100);
            y=(int)(Math.random()*100);
            if(!field[y][x].iswall) {
                Game.placeCreature(102,depth,x, y);
                i++;
            }
        }
    }
}
