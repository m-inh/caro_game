package com.t3h.other;

import com.t3h.other.Caro;
import com.t3h.other.CaroManager;
import com.t3h.other.GUI;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class GamePlay
extends JPanel {
    public static final int WIDTH_PANEL = 1000;
    public static final int HEIGHT_PANEL = 600;
    public int column_number = 20;
    public int row_number = 12;
    private Graphics2D g2d;
    private int count;
    private CaroManager caroMGR;
    private GUI gui;
    private int xTimeWin;
    private int oTimeWin;
    private JButton reset;
    private JButton menu;
    private JLabel xPoint;
    private JLabel oPoint;
    private MouseAdapter clickReset;
    private MouseAdapter clickMenu;
    private MouseAdapter click;

    public GamePlay() {
        this.clickReset = new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e) {
                GamePlay.this.newGame();
                GamePlay.this.repaint();
            }
        };
        this.clickMenu = new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e) {
                GamePlay.this.setVisible(false);
                GamePlay.this.gui.setMenu();
            }
        };
        this.click = new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Caro caro = new Caro(e.getX(), e.getY(), GamePlay.this.count % 2);
                if (!caroMGR.stop && e.getX() <= 1000 && e.getY() <= 600 && GamePlay.this.caroMGR.add(caro)) {
                    GamePlay gamePlay = GamePlay.this;
                    GamePlay.access4(gamePlay, gamePlay.count + 1);
                    if (caroMGR.stop) {
                        if (caro.getType() == 1) {
                            GamePlay gamePlay2 = GamePlay.this;
                            GamePlay.access6(gamePlay2, gamePlay2.xTimeWin + 1);
                        } else {
                            GamePlay gamePlay3 = GamePlay.this;
                            GamePlay.access8(gamePlay3, gamePlay3.oTimeWin + 1);
                        }
                    }
                }
                GamePlay.this.repaint();
                if (caroMGR.stop) {
                    int option = JOptionPane.showConfirmDialog(null, "Ch\u01a1i l\u1ea1i? (B\u1ea5m No ho\u1eb7c Cancel l\u00e0 ch\u00f3 -_-)");
                    switch (option) {
                        case 0: {
                            GamePlay.this.newGame();
                            GamePlay.this.repaint();
                            break;
                        }
                        case 1: {
                            break;
                        }
                        case 2: {
                            break;
                        }
                    }
                }
            }
        };
        this.setBackground(Color.BLACK);
        this.setLayout(null);
        this.caroMGR = new CaroManager(this.column_number, this.row_number);
        this.addMouseListener(this.click);
        this.reset = new JButton("Ch\u01a1i l\u1ea1i");
        this.reset.setBounds(1060, 20, 80, 30);
        this.reset.addMouseListener(this.clickReset);
        this.add(this.reset);
        this.menu = new JButton("Menu");
        this.menu.setBounds(1060, 60, 80, 30);
        this.menu.addMouseListener(this.clickMenu);
        this.add(this.menu);
        LineBorder thinBorder = new LineBorder(Color.BLACK, 1);
        this.xPoint = new JLabel("X : " + this.xTimeWin);
        this.xPoint.setForeground(Color.RED);
        this.xPoint.setFont(new Font("Tahoma", 50, 50));
        this.xPoint.setBounds(1030, 60, 140, 150);
        this.add(this.xPoint);
        this.oPoint = new JLabel("O : " + this.oTimeWin);
        this.oPoint.setForeground(Color.BLUE);
        this.oPoint.setFont(new Font("Tahoma", 50, 50));
        this.oPoint.setBounds(1026, 120, 140, 150);
        this.add(this.oPoint);
    }

    public void setGui(GUI gui) {
        this.gui = gui;
    }

    private void newGame() {
        this.caroMGR = new CaroManager(this.column_number, this.row_number);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.g2d = (Graphics2D)g;
        this.g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        this.drawTable(this.column_number, this.row_number);
        this.caroMGR.drawAllCaro(this.g2d);
        if (this.caroMGR.stop) {
            this.caroMGR.drawLine_win(this.g2d);
        }
        this.xPoint.setText("X : " + this.xTimeWin);
        this.oPoint.setText("O : " + this.oTimeWin);
    }

    private void drawTable(int column, int row) {
        int i;
        this.g2d.setColor(Color.LIGHT_GRAY);
        this.g2d.setStroke(new BasicStroke(2.0f));
        for (i = 0; i < column + 1; ++i) {
            this.g2d.drawLine(50 * i, 0, 50 * i, 600);
        }
        for (i = 0; i < row + 1; ++i) {
            this.g2d.drawLine(0, 50 * i, 1000, 50 * i);
        }
    }

    static /* synthetic */ void access4(GamePlay gamePlay, int n) {
        gamePlay.count = n;
    }

    static /* synthetic */ void access6(GamePlay gamePlay, int n) {
        gamePlay.xTimeWin = n;
    }

    static /* synthetic */ void access8(GamePlay gamePlay, int n) {
        gamePlay.oTimeWin = n;
    }

}

