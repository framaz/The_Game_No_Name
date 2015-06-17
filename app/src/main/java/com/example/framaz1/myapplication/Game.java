package com.example.framaz1.myapplication;

import com.example.framaz1.myapplication.Creatures.Goblin;
import com.example.framaz1.myapplication.Creatures.MotherCreature;
import com.example.framaz1.myapplication.Creatures.PlayCreature;
import com.example.framaz1.myapplication.Helpers.FindingHelper;
import com.example.framaz1.myapplication.Items.Weapons.Meeles.WoodenSword;
import com.example.framaz1.myapplication.Map_etc.DungeonTypes.ShallowDungeon;
import com.example.framaz1.myapplication.Map_etc.DungeonTypes.TestDungeon;
import com.example.framaz1.myapplication.Map_etc.Mothermap;
import com.example.framaz1.myapplication.Tiles.StandardDungeon.DungeonDownStairway;
import com.example.framaz1.myapplication.Tiles.StandardDungeon.DungeonWall;
import com.example.framaz1.myapplication.Tiles.Wall;

import java.util.LinkedList;

/**
 * Created by framaz1 on 22.03.2015.
 */
public class Game {
    public static Mothermap gamedepths[] = new Mothermap[100];
    public static int layer = 0;
    public static int timeFromStart = 0;
    public static boolean doneMyTurn=false;
    public static int whereToGoX = -1, whereToGoY = -1;
    public static int pathFindingHelpArray[][] = new int[100][100];
    public static PlayCreature player = new PlayCreature();

    public static void gaming() {
        Object waitingThing = new Object();
        synchronized (waitingThing) {
            synchronized (TouchAndThreadParams.outStreamSync) {
                try {
                    layer = 0;
                    AllBitmaps.initialize();
                    AllBitmaps.changeSize();
                    gamedepths[layer] = new TestDungeon();
                    gamedepths[layer].creatures.add(new Goblin());
                    gamedepths[layer].field[99][99] = new Wall();
                    int i;
                    int j=0;
                    tototo: for(i=0;i<100;i++)
                        for(j=0;j<100;j++)
                            if(gamedepths[layer].field[i][j].iswall==false)
                                break tototo;
                    player.yCoordinates=i;
                    player.xCoordinates=j;
                    gamedepths[layer].creatures.peek().xCoordinates=3;
                    gamedepths[layer].creatures.peek().yCoordinates=3;
                    gamedepths[layer].field[5][0].goldHere=30;
                    gamedepths[layer].field[5][1]=new DungeonDownStairway();
                    gamedepths[layer].field[5][1].linker=1;
                    for(int ddd=0;ddd<7;ddd++) gamedepths[layer].field[4][ddd]=new DungeonWall();
                    gamedepths[layer].field[98][3]=new DungeonWall();
                    gamedepths[layer].field[3][98]=new DungeonWall();
                    gamedepths[layer].field[5][2].itemsHere.add(new WoodenSword());
                    TouchAndThreadParams.outStreamSync.notifyAll();
                    for(i=1;i<100;i++)
                        gamedepths[i]=new Mothermap();
                } catch (Exception e) {
                }
            }
            while (true) {
                if (player.toWait == 0) {
                    player.behavior();

                } else
                    player.toWait--;
                for (int i = 0; i < gamedepths[layer].creatures.size(); i++) {
                    if (gamedepths[layer].creatures.get(i).toWait == 0) {
                        gamedepths[layer].creatures.get(i).behavior();

                    } else gamedepths[layer].creatures.get(i).toWait--;
                }
                timeFromStart++;
                whereToGoX = -1;
            }
        }
    }

    public static LinkedList<String> pathFinding(MotherCreature from, MotherCreature to) {
        return pathFinding(from.xCoordinates, from.yCoordinates, to.xCoordinates, to.yCoordinates, from.maxVision);
    }

    public static LinkedList<String> pathFinding(MotherCreature from, int tx, int ty) {
        return pathFinding(from.xCoordinates, from.yCoordinates, tx, ty, from.maxVision);
    }

    public static LinkedList<String> pathFinding(int fromX, int fromY, int toX, int toY, int max) {
        boolean washere[][] = new boolean[100][100];
        for (int i = 0; i < 100; i++)
            for (int j = 0; j < 100; j++) {
                washere[i][j] = false;
                pathFindingHelpArray[i][j]=100000;
            }
        LinkedList<FindingHelper> list = new LinkedList<FindingHelper>();
        list.add(new FindingHelper(toX, toY));
        //washere[toY][toX]=true;
        pathFindingHelpArray[toY][toX]=0;
        while (list.size() > 0&&!washere[fromY][fromX]) {
            FindingHelper obj = list.poll();

            //Left

            if(obj.x!=0&&!gamedepths[layer].field[obj.y][obj.x-1].iswall&&(!gamedepths[layer].field[obj.y][obj.x-1].isMobHere||(obj.x-1==fromX&&obj.y==fromY))&&pathFindingHelpArray[obj.y][obj.x-1]==100000&&pathFindingHelpArray[obj.y][obj.x]<max)
            {
                list.add(new FindingHelper(obj.x-1,obj.y));
                washere[obj.y][obj.x-1]=true;
                pathFindingHelpArray[obj.y][obj.x-1]=pathFindingHelpArray[obj.y][obj.x]+1;
            }

            //Right

            if(obj.x<99&&!gamedepths[layer].field[obj.y][obj.x+1].iswall&&(!gamedepths[layer].field[obj.y][obj.x+1].isMobHere||(obj.x+1==fromX&&obj.y==fromY))&&!washere[obj.y][obj.x+1]&pathFindingHelpArray[obj.y][obj.x+1]==100000&pathFindingHelpArray[obj.y][obj.x]<max)
            {
                list.add(new FindingHelper(obj.x+1,obj.y));
                washere[obj.y][obj.x+1]=true;
                pathFindingHelpArray[obj.y][obj.x+1]=pathFindingHelpArray[obj.y][obj.x]+1;
            }

            //Up

            if(obj.y!=0&&!gamedepths[layer].field[obj.y-1][obj.x].iswall&&(!gamedepths[layer].field[obj.y-1][obj.x].isMobHere||(obj.x==fromX&&obj.y-1==fromY))&&pathFindingHelpArray[obj.y-1][obj.x]==100000&&pathFindingHelpArray[obj.y][obj.x]<max)
            {
                list.add(new FindingHelper(obj.x,obj.y-1));
                washere[obj.y-1][obj.x]=true;
                pathFindingHelpArray[obj.y-1][obj.x]=pathFindingHelpArray[obj.y][obj.x]+1;
            }

            //Down

            if(obj.y<99&&!gamedepths[layer].field[obj.y+1][obj.x].iswall&&(!gamedepths[layer].field[obj.y+1][obj.x].isMobHere||(obj.x==fromX&&obj.y+1==fromY))&&pathFindingHelpArray[obj.y+1][obj.x]==100000&&pathFindingHelpArray[obj.y][obj.x]<max)
            {
                list.add(new FindingHelper(obj.x,obj.y+1));
                washere[obj.y+1][obj.x]=true;
                pathFindingHelpArray[obj.y+1][obj.x]=pathFindingHelpArray[obj.y][obj.x]+1;
            }

            //UpLeft

            if(obj.x!=0&&obj.y!=0&&!gamedepths[layer].field[obj.y-1][obj.x-1].iswall&&(!gamedepths[layer].field[obj.y-1][obj.x-1].isMobHere||(obj.x-1==fromX&&obj.y-1==fromY))&&pathFindingHelpArray[obj.y-1][obj.x-1]==100000&&pathFindingHelpArray[obj.y][obj.x]<max)
            {
                list.add(new FindingHelper(obj.x-1,obj.y-1));
                washere[obj.y-1][obj.x-1]=true;
                pathFindingHelpArray[obj.y-1][obj.x-1]=pathFindingHelpArray[obj.y][obj.x]+1;
            }

            //UpRight
            if(obj.x<99&&obj.y!=0&&!gamedepths[layer].field[obj.y-1][obj.x+1].iswall&&(!gamedepths[layer].field[obj.y-1][obj.x+1].isMobHere||(obj.x+1==fromX&&obj.y-1==fromY))&&pathFindingHelpArray[obj.y-1][obj.x+1]==100000&&pathFindingHelpArray[obj.y][obj.x]<max)
            {
                list.add(new FindingHelper(obj.x+1,obj.y-1));
                washere[obj.y-1][obj.x+1]=true;
                pathFindingHelpArray[obj.y-1][obj.x+1]=pathFindingHelpArray[obj.y][obj.x]+1;
            }

            //DownRight

            if(obj.x<99&&obj.y<99&&!gamedepths[layer].field[obj.y+1][obj.x+1].iswall&&(!gamedepths[layer].field[obj.y+1][obj.x+1].isMobHere||(obj.x+1==fromX&&obj.y+1==fromY))&&pathFindingHelpArray[obj.y+1][obj.x+1]==100000&&pathFindingHelpArray[obj.y][obj.x]<max)
            {
                list.add(new FindingHelper(obj.x+1,obj.y+1));
                washere[obj.y+1][obj.x+1]=true;
                pathFindingHelpArray[obj.y+1][obj.x+1]=pathFindingHelpArray[obj.y][obj.x]+1;
            }

            //DownLeft

            if(obj.x!=0&&obj.y<99&&!gamedepths[layer].field[obj.y+1][obj.x-1].iswall&&(!gamedepths[layer].field[obj.y+1][obj.x-1].isMobHere||(obj.x-1==fromX&&obj.y+1==fromY))&&pathFindingHelpArray[obj.y+1][obj.x-1]==100000&&pathFindingHelpArray[obj.y][obj.x]<max)
            {
                list.add(new FindingHelper(obj.x-1,obj.y+1));
                washere[obj.y+1][obj.x-1]=true;
                pathFindingHelpArray[obj.y+1][obj.x-1]=pathFindingHelpArray[obj.y][obj.x]+1;
            }
        }

        LinkedList<String> path=new LinkedList<String>();
        if(pathFindingHelpArray[fromY][fromX]<100000)
        {
            int x=fromX,y=fromY;
            while(x!=toX||y!=toY) {

                //Вверх

                if (y>0&&pathFindingHelpArray[y][x] > pathFindingHelpArray[y - 1][x]) {
                    y--;
                    path.add("Up");
                    continue;
                }

                //Вниз

                if (y<99&&pathFindingHelpArray[y][x] > pathFindingHelpArray[y + 1][x]) {
                    y++;
                    path.add("Down");
                    continue;
                }

                //Влево

                if (x>0&&pathFindingHelpArray[y][x] > pathFindingHelpArray[y][x-1]) {
                    x--;
                    path.add("Left");
                    continue;
                }

                //Вправо

                if (x<99&&pathFindingHelpArray[y][x] > pathFindingHelpArray[y][x+1]) {
                    x++;
                    path.add("Right");
                    continue;
                }

                //Вверх-Влево

                if (y>0&&x>0&&pathFindingHelpArray[y][x] > pathFindingHelpArray[y-1][x-1]) {
                    x--;
                    y--;
                    path.add("UpLeft");
                    continue;
                }

                //Вверх-Вправо

                if (y>0&&x<99&&pathFindingHelpArray[y][x] > pathFindingHelpArray[y-1][x+1]) {
                    x++;
                    y--;
                    path.add("UpRight");
                    continue;
                }

                //Вниз-Вправо

                if (y<99&&x<99&&pathFindingHelpArray[y][x] > pathFindingHelpArray[y+1][x+1]) {
                    x++;
                    y++;
                    path.add("DownRight");
                    continue;
                }

                //Вниз-Влево

                if (y<99&&x>0&&pathFindingHelpArray[y][x] > pathFindingHelpArray[y+1][x-1]) {
                    x--;
                    y++;
                    path.add("DownLeft");
                    continue;
                }

            }
        }
        return path;
    }

    public static int findCreatureByCoordinater(int x,int y) {
        int result=-3;
        if(player.xCoordinates==x&&player.yCoordinates==y)
            return -1;
        for(int i=0;i<gamedepths[layer].creatures.size();i++)
        {
            if(gamedepths[layer].creatures.get(i).xCoordinates==x&&gamedepths[layer].creatures.get(i).yCoordinates==y)
            {
                result=i;
                break;
            }
        }

        return result;
    }
    public static void changeLevel(int fromWhat, int toWhere)
    {
        if(toWhere>=0) {
            if (gamedepths[toWhere].generated == false) {
                if (toWhere < 5)
                    gamedepths[toWhere] = new ShallowDungeon();
                //TODO more levels
            }
            boolean doing=true;
            int j=0;
            int i=0;
            for (i = 0; i < 99 && doing; i++)
                for (j = 0; j < 99 &&doing; j++) {
                    if(gamedepths[toWhere].field[i][j].linker == fromWhat)
                        doing=false;
                }
            player.xCoordinates = j-1;
            player.yCoordinates = i-1;
            Params.displayY=player.xCoordinates*Params.size;
            Params.displayX=player.yCoordinates*Params.size;
            layer = toWhere;
        }
    }
}