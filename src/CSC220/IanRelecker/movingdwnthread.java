package CSC220.IanRelecker;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

//DONT NEED

public class movingdwnthread extends Thread{
    public boolean running;
    public int position;
    public int[] block;
    public JPanel[][] gridArrange;
    public boolean blocked;
    public int keypressed;


    movingdwnthread(int position, int[] block, boolean running, JPanel[][] gridArrange, int keypressed){
        this.running = running;
        this.position = position;
        this.block = block;
        this.gridArrange = gridArrange;
        this.keypressed = keypressed;
    }
    public void run(){
        keyactionsthread apple = new keyactionsthread(position, block, running, gridArrange);
        apple.start();
        while(running && !moveChecker()){
            gridArrange[parseY()-100][parseX()].setBackground(Color.WHITE);
            position += 10;
            gridArrange[parseY()-100][parseX()].setBackground(Color.BLACK);
//            System.out.println('a');
            //going to do the timer instead of this
//            try {
//                //speed that the blocks fall at
//                TimeUnit.MILLISECONDS.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }


    }

    public boolean moveChecker() {
        int x = parseX();
        int y = parseY() - 100;
        if (x==0 && y==0){
            blocked = false;
        }
        else if (x==0){
            if ((gridArrange[y-1][x].getBackground() == Color.BLACK) || (gridArrange[y+1][x].getBackground() == Color.BLACK)) {
                blocked = true;
            }else{
                blocked = false;
            }
        }
        else if (y==0) {
            if ((gridArrange[y][x + 1].getBackground() == Color.BLACK) || (gridArrange[y][x - 1].getBackground() == Color.BLACK)) {
                blocked = true;
            }else{
                blocked = false;
            }
        }
        else if (x==30){
            if ((gridArrange[y-1][x].getBackground() == Color.BLACK) || (gridArrange[y+1][x].getBackground() == Color.BLACK)) {
                blocked = true;
            }else{
                blocked = false;
            }
        }
        else if (y==30) {
            if ((gridArrange[y][x + 1].getBackground() == Color.BLACK) || (gridArrange[y][x - 1].getBackground() == Color.BLACK)) {
                blocked = true;
            }else{
                blocked = false;
            }
        }
        else if((gridArrange[y][x+1].getBackground() == Color.BLACK) || (gridArrange[y][x-1].getBackground() == Color.BLACK) ||
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
