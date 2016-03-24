/*
 * Decompiled with CFR 0_102.
 */
package com.t3h.other;

import com.t3h.other.AutoPlay;
import com.t3h.other.GamePlay;
import com.t3h.other.Menu;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class GUI
extends JFrame {
    public static final int WIDTH_FRAME = 1200;
    public static final int HEIGHT_FRAME = 630;
    private Menu menu;
    private GamePlay gamePlay;
    private AutoPlay autoPlay;

    public GUI() {
        this.setBounds((int)(this.getToolkit().getScreenSize().getWidth() - 1200.0) / 2, (int)(this.getToolkit().getScreenSize().getHeight() - 630.0) / 2, 1200, 630);
        this.setDefaultCloseOperation(3);
        this.setLayout(new CardLayout());
        this.setResizable(false);
        this.menu = new Menu();
        this.menu.setGui(this);
        this.add(this.menu);
        this.gamePlay = new GamePlay();
        this.gamePlay.setGui(this);
        this.gamePlay.setVisible(false);
        this.add(this.gamePlay);
        this.autoPlay = new AutoPlay();
        this.autoPlay.setGui(this);
        this.autoPlay.setVisible(false);
        this.add(this.autoPlay);
    }

    public void setTwoPlayer() {
        this.gamePlay.setVisible(true);
    }

    public void setFa() {
        this.autoPlay.setVisible(true);
    }

    public void setMenu() {
        this.menu.setVisible(true);
    }
}

