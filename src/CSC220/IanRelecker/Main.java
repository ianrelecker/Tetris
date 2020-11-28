package CSC220.IanRelecker;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


public class Main extends JFrame {
    private final int width = 400;
    private final int height = 700;
    private ControlPanel controlpanel;
    private PlayArea playArea;
    public JPanel[][] gridArrange;
    public int position = 1004;
    public boolean running;
    int rows = 30;
    int columns = 10;



    public int parseY(){
        int temp = position;
        temp /= 10;
        return Math.round(temp);
    }
    public int parseX(){
        return position % 10;
    }


    public boolean moveChecker() {
        boolean blocked;
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
        else if (x==9){
            if ((gridArrange[y-1][x].getBackground() == Color.BLACK) || (gridArrange[y+1][x].getBackground() == Color.BLACK)) {
                blocked = true;
            }else{
                blocked = false;
            }
        }
        else if (y==29) {
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

    public boolean liteMoveChecker() {
        boolean blocked;
        int x = parseX();
        int y = parseY() - 100;
        /*
        System.out.println(x + "x");
        System.out.println(y + "y");
        */

        if (y==0){
            blocked = false;
        }
        else if (y==0){
            if (gridArrange[y+1][x].getBackground() == Color.BLACK) {
                blocked = true;
            }else{
                blocked = false;
            }
        }
        else if (y==29){
            if (gridArrange[y-1][x].getBackground() == Color.BLACK) {
                blocked = true;
            }else{
                blocked = false;
            }
        }

        else if((gridArrange[y+1][x].getBackground() == Color.BLACK)){
            blocked = true;
        }else {
            blocked = false;
        }
        return blocked;
    }

    public boolean blockMoveChecker() {
        boolean blocked;
        int x = parseX();
        int y = parseY() - 100;
        /*
        System.out.println(x + "x");
        System.out.println(y + "y");
        */

        if (y==0){
            blocked = false;
        }
        else if (y==0){
            if (gridArrange[y+1][x].getBackground() == Color.BLACK) {
                blocked = true;
            }else{
                blocked = false;
            }
        }
        else if (y==29){
            if (gridArrange[y-1][x].getBackground() == Color.BLACK) {
                blocked = true;
            }else{
                blocked = false;
            }
        }

        else if((gridArrange[y+1][x].getBackground() == Color.BLACK)){
            blocked = true;
        }else {
            blocked = false;
        }
        return blocked;
    }


    int blocks = 0;
    int nextBlock = 0;
    int score = -1;

    public Timer timer = new Timer(500, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // gamerules can go here bc this is when the block gets moved down
            //
            // adding more blocks

            //dance party mode that checks the coverage of all of the spaces
            /*
            Color [] colors = {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.ORANGE};
            for (int i=0; i<rows; i++){
                    for (int a=0; a<columns; a++){
                        gridArrange[i][a].setBackground(colors[(int) (colors.length*Math.random())]);
                    }
                }

            */


            // locks the block to a single block
            blocks = 0;

            //two on top
            if (!blockMoveChecker() && blocks ==0) {
                System.out.println(position + "p");
                gridArrange[parseY() - 100][parseX()].setBackground(Color.WHITE);
                gridArrange[parseY() - 99][parseX()].setBackground(Color.WHITE);
                position += 10;
                gridArrange[parseY() - 100][parseX()].setBackground(Color.BLACK);
                gridArrange[parseY() - 99][parseX()].setBackground(Color.GRAY);
            }else{
                nextBlock = Math.round((int)(Math.random() * 6));
            }
            //single block
            if (!liteMoveChecker() && blocks == 1) {
                gridArrange[parseY() - 100][parseX()].setBackground(Color.WHITE);
                position += 10;
                gridArrange[parseY() - 100][parseX()].setBackground(Color.BLACK);
            }else{
                nextBlock = Math.round((int)(Math.random() * 6));
            }
            //cube
            if (!blockMoveChecker() && blocks == 2) {
                gridArrange[parseY() - 100][parseX()].setBackground(Color.WHITE);
                gridArrange[parseY() - 101][parseX()].setBackground(Color.WHITE);
                gridArrange[parseY() - 101][parseX()-1].setBackground(Color.WHITE);
                gridArrange[parseY() - 100][parseX()-1].setBackground(Color.WHITE);
                position += 10;
                gridArrange[parseY() - 100][parseX()].setBackground(Color.BLACK);
                gridArrange[parseY() - 101][parseX()].setBackground(Color.BLACK);
                gridArrange[parseY() - 101][parseX()-1].setBackground(Color.BLACK);
                gridArrange[parseY() - 100][parseX()-1].setBackground(Color.BLACK);
            }else{
                nextBlock = Math.round((int)(Math.random() * 6));
            }

            blocks = nextBlock;
            System.out.println(blocks);

            //if a row is filled then remove the row
            boolean scoreOne = false;
            for (int i=0; i<rows; i++){
                for (int a=0; a<columns; a++){
                    if (gridArrange[i][a].getBackground() == Color.WHITE){
                        break;
                    }if (a == 9){
                        for (int b=0; b<columns; b++){
                            gridArrange[i][b].setBackground(Color.WHITE);
                            scoreOne = true;

                        }
                    }
                }
            }
            //changes the score when a row is cleared
            if (scoreOne){
                score++;
            }
            controlpanel.scoreNumberText.setText("     "+ score +"     ");

            //if a row is filled then remove the row and move everything down by 1
            for (int i=0; i<rows; i++){
//                System.out.println(i + "i");
                    for (int a=0; a<columns; a++){
                        if (gridArrange[i][a].getBackground() == Color.WHITE){
                               System.out.println(a);
                               break;
                        }if (a == 9){
                            System.out.println("works");
                            for (int b=0; b<columns; b++){
                                gridArrange[i][b].setBackground(Color.WHITE);
                            }
                            int reverseRow = 29-i;
                            for (int s=0;s<reverseRow;s++){
                                for (int d=9; d>0; d--){
                                    if (gridArrange[s][d].getBackground() == Color.BLACK){
                                        gridArrange[s][d].setBackground(Color.WHITE);
                                        gridArrange[s+1][d].setBackground(Color.WHITE);
                                    }
                                }
                            }
                    }
                }
            }



            //setup for the initial state of the game
            if ((position / 10) - 100 == 29 || gridArrange[parseY() - 99][parseX()].getBackground() == Color.BLACK){
                gridArrange[parseY() - 100][parseX()].setBackground(Color.BLACK);
                position = 1014;
            }
        }
    });

    Main(){
        super();
        setTitle("Java Swing Tetris");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5,5));
        setLocationRelativeTo(null);
        controlpanel = new ControlPanel();
        playArea = new PlayArea();
        this.add(controlpanel, BorderLayout.CENTER);
        this.add(playArea, BorderLayout.EAST);
        playArea.repaint();
        setVisible(true);
        playArea.requestFocus();
    }

    public class ControlPanel extends JPanel{
        private JButton startButton;
        private JLabel scoreLabel;
        public JTextArea scoreNumberText;
        private JLabel nextPiece;
        private JPanel panelNextPiece;
        private JButton loadButton;
        private JButton saveButton;


        ControlPanel(){


            startButton = new JButton("Start");
            loadButton = new JButton("Load");
            saveButton = new JButton("Save");
            prepButtons();

            this.add(startButton);
            scoreLabel = new JLabel("Score");
            this.add(scoreLabel);
            scoreNumberText = new JTextArea();
            scoreNumberText.setText("     0     ");
            scoreNumberText.setEditable(false);
            this.add(scoreNumberText);
            nextPiece = new JLabel("Next Piece");
            this.add(nextPiece);
            panelNextPiece = new JPanel();
            panelNextPiece.add(new TextArea());
            this.add(panelNextPiece);
            setLayout(new FlowLayout());
            this.add(loadButton);
            this.add(saveButton);
        }

        public void prepButtons(){
            startButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (running){
                        running = false;
//                        playArea.endmovingdown();
                        playArea.stoptimer();
                    }else{
                        running = true;
//                        playArea.startmovingdown();
                        playArea.starttimer();
                    }
                    playArea.requestFocus();
                }
            });
            loadButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFileChooser loader = new JFileChooser();
                    if (loader.showDialog(null, "Open") == JFileChooser.APPROVE_OPTION){
                        System.out.println(loader.getSelectedFile().getName());
                    }
                }
            });
            saveButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFileChooser saver = new JFileChooser();
                    if (saver.showDialog(null, "Save") == JFileChooser.APPROVE_OPTION){
                        System.out.println(saver.getSelectedFile().getName());
                    }
                }
            });
        }

    }
    public class PlayArea extends JPanel{
        //going to arrange the grid so that it is (1yyx)
        //example top left 1000
        //example bottom right 1299
        public int keypressed;

        PlayArea(){
            super();
            int rows = 30;
            int columns = 10;

            gridArrange = new JPanel[rows][columns];
            setLayout(new GridLayout(rows,columns));
            for (int a=0; a<rows; a++){
                    for (int b=0; b<columns; b++){
                    JPanel holdingPanel = new JPanel();
                    holdingPanel.setPreferredSize(new Dimension(30,20));
                    holdingPanel.setBorder(new BasicBorders.FieldBorder(Color.BLACK, null, null, null));
                    gridArrange[a][b] = holdingPanel;
                    this.add(gridArrange[a][b]);
                }
            }
            keyactions();

        }


        public void keyactions(){
            this.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    keypressed = e.getKeyCode();
//                    System.out.println(keypressed);
                    if (keypressed == 37 && !moveChecker()) {
                        if (parseX() == 0) {
//                            System.out.println("fail");
                        } else {
                            if (blocks == 1) {
                                gridArrange[parseY() - 100][parseX()].setBackground(Color.WHITE);
                            }
                            if (blocks == 0){
                                gridArrange[parseY() - 100][parseX()].setBackground(Color.WHITE);
                                gridArrange[parseY() - 99][parseX()].setBackground(Color.WHITE);

                            }
                            position -= 1;
                        }
                    }
                    if (keypressed == 39 && !moveChecker()){
                        if (parseX() == 9) {
//                            System.out.println("fail");
                        }else{
                            if (blocks == 1) {
                                gridArrange[parseY() - 100][parseX()].setBackground(Color.WHITE);
                            }
                            if (blocks == 0){
                                gridArrange[parseY() - 100][parseX()].setBackground(Color.WHITE);
                                gridArrange[parseY() - 99][parseX()].setBackground(Color.WHITE);

                            }
                            position += 1;
                        }
                    }
                    //issues with skipping blocks when moving down
                    /*
                    if (keypressed == 40 && !moveChecker()){
                        gridArrange[parseY()-100][parseX()].setBackground(Color.WHITE);
                        position += 10;
                    }
                    */
                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });
        }
        public void starttimer() {
            timer.start();
        }
        public void stoptimer(){
            timer.stop();
        }

//        public void startmovingdown(){
//            //starting the tread for moving it down
//            //testing with a block
//            position = 1004;
//            mvndwnthread = new movingdwnthread(position, block, running, gridArrange, keypressed);
//            mvndwnthread.start();
//        }
//        public void endmovingdown() {
//            mvndwnthread.interrupt();
//        }



    }


    public static void main(String[] args) {
        new Main();
    }
}
