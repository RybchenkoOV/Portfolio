package LEVEL1.lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {

    private Settings settings;
    private Playground playground;
    private static final int SET_X_MAP = 300;
    private static final int SET_Y_MAP = 300;
    /** Заберем текущие значения экрана для мультиэкранного режима */
    GraphicsDevice dimensionCaster = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    int WWW = dimensionCaster.getDisplayMode().getWidth()/2-SET_X_MAP/2; // Центруем по X
    int HHH = dimensionCaster.getDisplayMode().getHeight()/2-SET_Y_MAP/2; // Центруем по Y

    MainMenu() {
        settings = new Settings(this);
        playground = new Playground();
        setTitle("Tic-Tac-Toe Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(SET_X_MAP,SET_Y_MAP);
        setLocation(WWW, HHH);
        JButton newGame = new JButton("NEW GAME");
        JButton exit = new JButton("EXIT");
        JPanel main_Panel = new JPanel();
        main_Panel.setLayout(new GridLayout(1,2));
        main_Panel.add(newGame);
        main_Panel.add(exit);
        add(main_Panel, BorderLayout.SOUTH);
        add(playground);

        setResizable(false);

        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings.setVisible(true);
            }

        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            System.exit(0);
            }
        });




        setVisible(true);
    }

    void getSetToStart(int gMode, int X_SIZE, int Y_SIZE, int TO_WIN_SEQ){
        playground.startNewGameSet(gMode, X_SIZE, Y_SIZE, TO_WIN_SEQ);
    }



}
