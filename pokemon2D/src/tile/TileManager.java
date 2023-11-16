package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];
    private int countMap = 0;

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[20];
        mapTileNum = new int[gp.MAXSCREENCOL][gp.MAXSCREENROW];

        getTileImage();
        //loadMap();
    }


    public void getTileImage(){

        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass1.png")));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass2.png")));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass3.png")));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass4.png")));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water1.png")));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water2.png")));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water3.png")));

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water4.png")));

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water5.png")));

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water6.png")));

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water7.png")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(){

        try{
               InputStream is = null;
               if(countMap > 300) {
                   is = getClass().getResourceAsStream("/maps/map1.txt");
                   countMap = 0;
               }
               else{
                   is = getClass().getResourceAsStream("/maps/map2.txt");
                   countMap++;
               }



            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            int col = 0, row = 0;

            while (col < gp.MAXSCREENCOL && row < gp.MAXSCREENROW) {
                line = br.readLine();

                while(col < gp.MAXSCREENCOL) {

                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.MAXSCREENCOL){
                    col = 0;
                    row++;
                }


            }

            br.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        loadMap();
        //g2.drawImage(tile[0].image, 0, 0, gp.TILESIZE, gp.TILESIZE, null); See if draw correctly

        int col = 0, row = 0, x = 0, y = 0;

        while(col < gp.MAXSCREENCOL && row < gp.MAXSCREENROW){
            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].image, x, y, gp.TILESIZE, gp.TILESIZE, null);
            col++;
            x += gp.TILESIZE;

            if(col == gp.MAXSCREENCOL) {
                col = 0;
                x = 0;
                row++;
                y += gp.TILESIZE;
            }

        }
    }

}
