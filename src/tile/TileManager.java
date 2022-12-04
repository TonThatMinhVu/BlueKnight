package tile;
import main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.Objects;
public class TileManager
{
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp)
    {
        this.gp = gp;
        tile = new Tile[100];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/world01.txt");
    }
    public void getTileImage()
    {
        try
        {
            tile[0]=new Tile();
            tile[0].image = ImageIO.read(tile.getClass().getResourceAsStream("/tiles/earth.png"));
            tile[1]=new Tile();
            tile[1].image = ImageIO.read(tile.getClass().getResourceAsStream("/tiles/floor01.png"));
            tile[2]=new Tile();
            tile[2].image = ImageIO.read(tile.getClass().getResourceAsStream("/tiles/water01.png"));
            tile[2].collision = true;
            tile[3]=new Tile();
            tile[3].image = ImageIO.read(tile.getClass().getResourceAsStream("/tiles/hut.png"));
            tile[3].collision = true;
            tile[4]=new Tile();
            tile[4].image = ImageIO.read(tile.getClass().getResourceAsStream("/tiles/tree.png"));
            tile[4].collision = true;
            tile[5]=new Tile();
            tile[5].image = ImageIO.read(tile.getClass().getResourceAsStream("/tiles/road00.png"));


        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath)
    {
        try
        {
            InputStream is = getClass().getResourceAsStream(filePath);
            //assert is != null;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            int col=0;
            int row=0;
            while(col < gp.maxWorldRow && row <gp.maxWorldCol)
            {
                String line = bufferedReader.readLine();
                while(col< gp.maxWorldCol)
                {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row]=num;
                    col++;
                }
                if(col == gp.maxWorldCol)
                    {
                        col = 0;
                        row++;
                    }
            }

            bufferedReader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D t2)
    {
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol<gp.maxWorldCol && worldRow < gp.maxWorldRow)
        {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.playerT.worldX + gp.playerT.screenX;
            int screenY = worldY - gp.playerT.worldY + gp.playerT.screenY;
            if (       worldX + gp.tileSize> gp.playerT.worldX - gp.playerT.screenX
                    && worldX - gp.tileSize< gp.playerT.worldX+ gp.playerT.screenX
                    && worldY + gp.tileSize> gp.playerT.worldY - gp.playerT.screenY
                    && worldY - gp.tileSize< gp.playerT.worldY + gp.playerT.screenY)
            {
                t2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            worldCol++;

            if(worldCol == gp.maxWorldCol)
            {
                worldCol=0;
                worldRow++;
            }
        }
    }
}
