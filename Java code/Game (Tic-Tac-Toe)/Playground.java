package LEVEL1.lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Playground extends JPanel {

    public static final int GAME_M_HVH = 1;
    public static final int GAME_M_HVC = 0;

    private static final Random RND = new Random();

    private int[][] map;
    private int X_SIZE;
    private int Y_SIZE;
    private int TO_WIN_SEQ;
    private int gMode;
    private int cellW;
    private int cellH;

    private static final int humanChar = 1;
    private static final int aiChar = 2;
    private static final int emptyChar = 0;

    private boolean gameOver;
    private String winEvent;
    private boolean mapInit;

    Playground() {
        setBackground(new Color(110,90,200));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                update(e);
            }
        });
        mapInit = false;
    }

    void startNewGameSet(int gMode, int X_SIZE, int Y_SIZE, int TO_WIN_SEQ) {
        System.out.println("Starting new game with field: "+X_SIZE+"x"+Y_SIZE+". Win sequence: "+TO_WIN_SEQ+". Game mode: "+gMode);
        this.X_SIZE = X_SIZE;
        this.Y_SIZE = Y_SIZE;
        this.gMode = gMode;
        this.TO_WIN_SEQ = TO_WIN_SEQ;
        this.map = new int[Y_SIZE][X_SIZE];
        gameOver=false;
        mapInit=true;
        repaint();

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    private void render (Graphics g) {
        if (!mapInit) return;

        int height = getHeight();
        int width = getWidth();
        cellW = width/X_SIZE;
        cellH = height/Y_SIZE;
        g.setColor(Color.white);


        for (int i = 0; i < X_SIZE; i++) {
            int x = i*cellW;
            g.drawLine(x,0,x,height);
        }

        for (int i = 0; i < Y_SIZE; i++) {
            int y = i*cellH;
            g.drawLine(0,y,width,y);
        }



        for (int y = 0; y < Y_SIZE; y++) {
            for (int x = 0; x < X_SIZE; x++) {

                if (isEmpty(x, y)) {
                    continue;
                }
                if (map[y][x] == humanChar) {
                    g.setColor(new Color(255, 0, 0));
//                    g.drawLine(x,y,(x*cellW),(y*cellH));
//                    g.drawLine(x*cellW,y,(x),(y*cellH));
                    g.fillOval(x * cellW + 5, y * cellH + 5, cellW - 5 * 2, cellH - 5 * 2);
                } else if (map[y][x] == aiChar) {
                    g.setColor(new Color(38, 255, 0));
                    g.fillOval(x * cellW + 5, y * cellH + 5, cellW - 5 * 2, cellH - 5 * 2);
                } else {
                    throw new RuntimeException("Invalid render at coorditates: x = " + x + " y = " + y);
                }
            }
        }
        if (gameOver) {
            endEvent(g);
        }
    }

    private void endEvent(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 200, getWidth(), 70);
        g.setColor(Color.ORANGE);
        g.setFont(new Font("Times New Roman", Font.BOLD, 40));
        switch (winEvent) {
            case "DRAW!!!":
                g.drawString("DRAW!!!", 180, getHeight() / 2);
                break;
            case "PLAYER WIN!!!":
                g.drawString("PLAYER WIN!!!", 150, getHeight() / 2);
                break;
            case "COMPUTER WIN!!!":
                g.drawString("COMPUTER WIN!!!", 180, getHeight() / 2);
                break;
            default:
                throw new RuntimeException("Invalid winEvent " + winEvent);
        }
    }

    private void gameOverEvent(String valueEvent) {
        gameOver = true;
        winEvent = valueEvent;
        repaint();
    }

    private void update(MouseEvent e){
        if (!mapInit) return;
        if (gameOver) return;


        int cellX = e.getX() / cellW;
        int cellY = e.getY() / cellH;

        if (!isValidTurn(cellX, cellY) || !isEmpty(cellX, cellY)) {
            return;
        }

        map[cellY][cellX] = humanChar;

        if (isWinner(humanChar)) {
            gameOverEvent("PLAYER WIN!!!");
            return;
        }

        if (checkDraw()) {
            gameOverEvent("DRAW!!!");
            return;
        }

        compTurn();
        repaint();

        if (isWinner(aiChar)) {
            gameOverEvent("COMPUTER WIN!!!");
            return;
        }

        if (checkDraw()) {
            gameOverEvent("DRAW!!!");
            return;
        }

    }

    public void compTurn(){
        int x;
        int y;
        if (checkCompWin()){
            return;
        }
        if (checkHumanWinNext()){
            return;
        }
        do {
        x = RND.nextInt(X_SIZE);
        y = RND.nextInt(Y_SIZE);
        } while (!isEmpty(x,y));
        map[y][x] = aiChar;
    }

    public boolean isValidTurn(int x, int y) {
        return x >= 0 && x < X_SIZE && y >= 0 && y < Y_SIZE;
    }

    public boolean isEmpty(int x, int y) {
        return map[y][x] == emptyChar;
    }

    private boolean checkCompWin(){
        for (int i = 0; i < X_SIZE; i++) {
            for (int j = 0; j < Y_SIZE; j++) {
                if (isEmpty(j,i)){
                    map[i][j] = aiChar;
                    if (isWinner(aiChar)) {
                        return true;
                    }
                    map[i][j] = emptyChar;

                }

            }
        }
        return false;
    }

    private boolean checkHumanWinNext(){
        for (int i = 0; i < X_SIZE; i++) {
            for (int j = 0; j < Y_SIZE; j++) {
                if (isEmpty(j,i)){
                    map[i][j] = humanChar;
                    if (isWinner(humanChar)) {
                        map[i][j] = aiChar;
                        return true;
                    }
                    map[i][j] = emptyChar;

                }

            }
        }
        return false;
    }

    private boolean isWinner(int player) {
        for (int i = 0; i < X_SIZE; i++) {
            for (int j = 0; j < Y_SIZE; j++) {
                if (checkLine(i, j, 1, 0, TO_WIN_SEQ, player)) {
                    return true;
                }
                if (checkLine(i, j, 1, 1, TO_WIN_SEQ, player)) {
                    return true;
                }
                if (checkLine(i, j, 0, 1, TO_WIN_SEQ, player)) {
                    return true;
                }
                if (checkLine(i, j, 1, -1, TO_WIN_SEQ, player)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean checkLine(int x, int y, int xVector, int yVector, int len, int player) {
    final int finalX = x + (len - 1) * xVector;
    final int finalY = y + (len - 1) * yVector;
    if (!isValidTurn(finalX, finalY)) {
        return false;
    }
    for (int i = 0; i < len; i++) {
        if (map[y + i * yVector][x + i * xVector] != player) {
            return false;
        }
    }
    return true;
}
    public boolean checkDraw() {
        for (int i = 0; i < Y_SIZE; i++) {
            for (int j = 0; j < X_SIZE; j++) {
                if (map[i][j] == emptyChar) {
                    return false;
                }
            }
        }
        return true;
    }

}
