package com.t3h.other;

import com.t3h.other.Brain;
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
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class AutoPlay
extends JPanel
implements Runnable {
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
    private volatile int luot;
    private Brain brain;
    private Random random;
    private Thread th;
    private MouseAdapter clickReset;
    private MouseAdapter clickMenu;
    private MouseAdapter click;

    public AutoPlay() {
        this.clickReset = new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e) {
                AutoPlay.this.newGame();
                AutoPlay.this.repaint();
            }
        };
        this.clickMenu = new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e) {
                AutoPlay.this.setVisible(false);
                AutoPlay.this.gui.setMenu();
            }
        };
        
        this.click = new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                Caro caro = new Caro(e.getX(), e.getY(), 0);
                if (!caroMGR.stop && e.getX() <= 1000 && e.getY() <= 600 && AutoPlay.this.luot == 1 && AutoPlay.this.caroMGR.add(caro)) {
                    AutoPlay.this.repaint();
                    if (!caroMGR.stop) {
                        AutoPlay autoPlay = AutoPlay.this;
                        AutoPlay.access4(autoPlay, autoPlay.luot * -1);
                    } else {
                        AutoPlay autoPlay = AutoPlay.this;
                        AutoPlay.access6(autoPlay, autoPlay.oTimeWin + 1);
                        AutoPlay.this.thongBao();
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
        this.brain = new Brain(this.column_number, this.row_number);
        this.random = new Random();
        this.luot = 1;
        this.th = new Thread(this);
        this.th.start();
    }

    public void setGui(GUI gui) {
        this.gui = gui;
    }

    private void newGame() {
        this.caroMGR = new CaroManager(this.column_number, this.row_number);
        this.count = 0;
        this.luot*=-1;
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

    private void thongBao() {
        int option = JOptionPane.showConfirmDialog(null, "Ch\u01a1i l\u1ea1i? (B\u1ea5m No ho\u1eb7c Cancel l\u00e0 ch\u00f3 -_-)");
        switch (option) {
            case 0: {
                this.newGame();
                this.repaint();
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

    @Override
    public void run() {
        do {
            if (!(this.luot != -1 || this.caroMGR.stop)) {
                Caro caro;
                while (!this.caroMGR.add(caro = this.brain.nextCaro(this.caroMGR.getMatrix_x(), this.caroMGR.getMatrix_o()))) {
                }
                this.repaint();
                if (!this.caroMGR.stop) {
                    this.luot*=-1;
                } else {
                    ++this.xTimeWin;
                    this.thongBao();
                }
            }
            try {
                Thread.sleep(100);
                continue;
            }
            catch (InterruptedException e) {
                e.printStackTrace();
                continue;
            }
        } while (true);
    }

    static void access4(AutoPlay autoPlay, int n) {
        autoPlay.luot = n;
    }

    static void access6(AutoPlay autoPlay, int n) {
        autoPlay.oTimeWin = n;
    }

}

