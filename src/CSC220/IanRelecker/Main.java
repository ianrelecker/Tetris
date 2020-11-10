package CSC220.IanRelecker;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;


public class Main extends JFrame {
    private final int width = 400;
    private final int height = 700;
    private ControlPanel controlpanel;
    private PlayArea playArea;

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
            loadButton = new JButton("Load");
            this.add(loadButton);
            saveButton = new JButton("Save");
            this.add(saveButton);
            setLayout(new FlowLayout());
        }

    }
    public class PlayArea extends JPanel{

        PlayArea(){
            super();
            int rows = 30;
            int columns = 10;

            JPanel[][] gridArrange = new JPanel[rows][columns];
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
        }
    }


    public static void main(String[] args) {
        new Main();
    }
}
