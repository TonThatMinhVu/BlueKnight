package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity
{
    public int worldX,worldY;
    public int speed;
//cai nay de gan/ hinh
    public BufferedImage up1,up2,down1,down2,right1,right2,left1,left2;
    public String direction;
//cai nay de lam hoat anh
    public int spriteCounter=0;
    public int spriteNum=1;
    public Rectangle solidArea;
    public boolean collisionOn = false;
}
