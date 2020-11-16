package CSC220.IanRelecker;

import javax.swing.*;
import java.awt.*;

public class movingdwnthread extends Thread{
    public boolean running;
    public int position;
    public int[] block;
    public JPanel[][] gridArrange;
    public boolean blocked;


    movingdwnthread(int position, int[] block, boolean running, JPanel[][] gridArrange){
        this.running = running;
        this.position = position;
        this.block = block;
        this.gridArrange = gridArrange;
        System.out.println('b');
    }
    public void run(){
        while(running && moveChecker()){
            gridArrange[parseY()][parseX()].setBackground(Color.WHITE);
            position += 10;
            gridArrange[parseY()][parseX()].setBackground(Color.BLACK);
            System.out.println('a');
            try {
                this.wait(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    public boolean moveChecker() {
        int x = parseX();
        int y = parseY();
        if((gridArrange[y][x+1].getBackground() == Color.BLACK) || (gridArrange[y][x-1].getBackground() == Color.BLACK) ||
                (gridArrange[y-1][x].getBackground() == Color.BLACK) || (gridArrange[y+1][x].getBackground() == Color.BLACK)){
            blocked = true;
        }else {
            blocked = false;
        }
        return blocked;
    }

    public int parseY(){
        int temp = position;
        temp /= 10;
        return Math.round(temp);
    }
    public int parseX(){
        return position % 10;
    }
}
