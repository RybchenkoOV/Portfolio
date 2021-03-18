package LEVEL1.lesson8;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings extends JFrame{
    private Playground playground;
    private MainMenu mainMenu;
    private JRadioButton HvH;
    private JRadioButton HvC;
    private JSlider pgSize;
    private JSlider winSeqLength;
    private final int minWinSeq = 3;
    private final int minPgSize = 3;
    private final int maxPgSize = 10;
    private JButton starterBtn;

    private static final int SET_X_SET = 600;
    private static final int SET_Y_SET = 400;
    /** Заберем текущие значения экрана для мультиэкранного режима */
    GraphicsDevice dimensionCaster = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    int WWW = dimensionCaster.getDisplayMode().getWidth()/2-SET_X_SET/2; // Центруем по X
    int HHH = dimensionCaster.getDisplayMode().getHeight()/2-SET_Y_SET/2; // Центруем по Y

    Settings(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
        setTitle("Settings");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(SET_X_SET, SET_Y_SET);
        setLocation(WWW, HHH);
        getContentPane().setBackground(Color.gray);

        setModeMethod();
        setPlaygroundMethod();
        endSetGotoStart();

        setResizable(false);
        setVisible(false);

    }

    private void startNewGame(){
        int selectedMode;
        int finPgSize = pgSize.getValue();
        int finWinSeq = winSeqLength.getValue();
        if (HvH.isSelected()){
            selectedMode = Playground.GAME_M_HVH;
        } else if (HvC.isSelected()) {
            selectedMode = Playground.GAME_M_HVC;
        } else {
            throw new RuntimeException("Unknown Game mode");
        }
        mainMenu.getSetToStart(selectedMode,finPgSize,finPgSize, finWinSeq);
        setVisible(false);
    }
    private void setModeMethod(){
        /** Game options menu */
        JPanel mode = new JPanel();
        JLabel gameOptions = new JLabel("Game Options:");
        HvH = new JRadioButton("Human versus Human mode");
        HvC = new JRadioButton("Human versus Computer mode", true);

        ButtonGroup gameModeGroup = new ButtonGroup();
        gameModeGroup.add(HvH);
        gameModeGroup.add(HvC);

        mode.setLayout(new GridLayout(10, 1));
        add(mode, BorderLayout.WEST);
        mode.add(gameOptions);
        mode.add(HvH);
        mode.add(HvC);


    }
    private void setPlaygroundMethod(){
        JPanel dimensions = new JPanel();
        JLabel dimName = new JLabel("Field settings: ");
        JLabel SIZE_LABEL = new JLabel("Playground size: "+minPgSize);
        JLabel WIN_SEQ_LABEL = new JLabel("Winner sequence: "+minWinSeq);

        pgSize = new JSlider(minPgSize,maxPgSize,minPgSize);
        pgSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
            int curPos = pgSize.getValue();
            SIZE_LABEL.setText("Playground size: "+curPos);
            winSeqLength.setMaximum(curPos);
            }
        });
        winSeqLength = new JSlider(minWinSeq, minPgSize, minPgSize);
        winSeqLength.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                WIN_SEQ_LABEL.setText("Winner sequence: " + winSeqLength.getValue());
            }
        });

        dimensions.setLayout(new GridLayout(10,1));
        add(dimensions, BorderLayout.EAST);
        dimensions.add(dimName);
        dimensions.add(new JLabel("Choose your playground size:"));
        dimensions.add(SIZE_LABEL);
        dimensions.add(pgSize);
        dimensions.add(WIN_SEQ_LABEL);
        dimensions.add(winSeqLength);


    }
    private void endSetGotoStart() {
        JPanel start = new JPanel();
        starterBtn = new JButton("START GAME");
        start.setLayout(new GridLayout(1, 1));
        add(start, BorderLayout.CENTER);


        starterBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewGame();
            }
        });
        start.add(starterBtn);
    }
}
