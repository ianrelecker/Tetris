package CSC220.IanRelecker;

import javax.swing.*;

public class keyactionsthread extends Thread{
    public boolean running;
    public int position;
    public int[] block;
    public JPanel[][] gridArrange;
    public boolean blocked;



    keyactionsthread(int position, int[] block, boolean running, JPanel[][] gridArrange){
        this.running = running;
        this.position = position;
        this.block = block;
        this.gridArrange = gridArrange;
    }


    public void run(int keypressed) {
        int y = parseY() -100;
        int x = parseX();

        if (keypressed == 37){

        }
        if (keypressed == 38){

        }
        if (keypressed == 39){

        }
        if (keypressed == 40){

        }


    }
    public int parseY(){
        int temp = position;
        temp /= 10;
        return Math.round(temp);
    }
    public int parseX(){
        return position % 10;
    }

    public void left(){
        System.out.println('a');
    }
}
