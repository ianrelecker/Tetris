package CSC220.IanRelecker;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Main extends JFrame {
    private final int width = 400;
    private final int height = 700;
    private ControlPanel controlpanel;
    private PlayArea playArea;
    public JPanel[][] gridArrange;
    public int position;
    public int[] block;
    public boolean running;
    movingdwnthread mvndwnthread;

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
        private JTextArea scoreNumberText;
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
                        playArea.endmovingdown();
                    }else{
                        running = true;
                        playArea.startmovingdown();
                    }
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
                    keyactionsthread apple = new keyactionsthread(position, block, running, gridArrange);
                    apple.run(keypressed);
                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });
        }
        public void testingGrid() {
            gridArrange[5][5].setBackground(Color.BLACK);
        }

        public void startmovingdown(){
            //starting the tread for moving it down
            //testing with a block
            position = 1004;
            mvndwnthread = new movingdwnthread(position, block, running, gridArrange, keypressed);
            mvndwnthread.start();
        }
        public void endmovingdown() {
            mvndwnthread.interrupt();
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


    public static void main(String[] args) {
        new Main();
    }
}
