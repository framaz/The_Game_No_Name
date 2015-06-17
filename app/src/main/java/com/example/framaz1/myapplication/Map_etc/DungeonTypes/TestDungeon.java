package com.example.framaz1.myapplication.Map_etc.DungeonTypes;
import com.example.framaz1.myapplication.Map_etc.Mothermap;
import com.example.framaz1.myapplication.Tiles.StandardDungeon.DungeonFloor;
import com.example.framaz1.myapplication.Tiles.StandardDungeon.DungeonWall;
import com.example.framaz1.myapplication.Tiles.Tile;

/**
 * Created by framaz1 on 22.03.2015.
 */
public class TestDungeon extends Mothermap {
    @Override
    public void generateTheField()
    {
        for(int i=0;i<100;i++)
            for(int j=0;j<100;j++)
                field[i][j]=new DungeonFloor();
        field[3][6]=new DungeonWall();
        generated=true;
    }
    public TestDungeon()
    {
        field=new Tile[100][100];
        generateTheField();
    }
}
