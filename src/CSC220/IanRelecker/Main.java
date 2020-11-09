package CSC220.IanRelecker;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private final int width = 10;
    private final int height = 600;
    private ControlPanel controlpanel;
    private PlayArea playArea;

    Main(){
        setTitle("Java Swing Tetris");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5,5));
        setLocationRelativeTo(null);
        controlpanel = new ControlPanel();
        playArea = new PlayArea();
        controlpanel.setSize(100,600);
        this.add(controlpanel, BorderLayout.WEST);
        this.add(playArea, BorderLayout.EAST);
        setVisible(true);
        setResizable(false);
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
            this.add(startButton);
            scoreLabel = new JLabel("Score");
            this.add(scoreLabel,0);
            scoreNumberText = new JTextArea();
            scoreNumberText.setText("     0     ");
            this.add(scoreNumberText,0);
            nextPiece = new JLabel("Next Piece");
            this.add(nextPiece,0);
            panelNextPiece = new JPanel();
            panelNextPiece.setSize(20,20);
            this.add(panelNextPiece,0);
            loadButton = new JButton("Load");
            this.add(loadButton,0);
            saveButton = new JButton("Save");
            this.add(saveButton,0);

            //where to add functionality



            setLayout(new FlowLayout());
            setSize(100,600);
            setVisible(true);
        }

    }
    public class PlayArea extends JPanel{


        PlayArea(){
            int rows = 40;
            int columns = 40;
            JPanel[][] gridArrange = new JPanel[rows][columns];
            setLayout(new GridLayout(rows,columns));
            for (int a=0; a<rows; a++){
                for (int b=0; b<columns; b++){
                    gridArrange[a][b] = new JPanel();
                    this.add(gridArrange[a][b]);
                }
            }



            setSize(400,600);
            setVisible(true);

        }
    }


    public static void main(String[] args) {
        new Main();
    }
}
