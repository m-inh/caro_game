/*
 * Decompiled with CFR 0_102.
 */
package com.t3h.other;

import com.t3h.other.GUI;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Menu
extends JPanel {
    private JButton twoPlayer;
    private JButton Fa;
    private GUI gui;
    private MouseAdapter clickTwoPlayer;
    private MouseAdapter clickFa;

    public Menu() {
        this.clickTwoPlayer = new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent arg0) {
                Menu.this.setVisible(false);
                Menu.this.gui.setTwoPlayer();
            }
        };
        this.clickFa = new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent arg0) {
                Menu.this.setVisible(false);
                Menu.this.gui.setFa();
            }
        };
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        LineBorder thinBorder = new LineBorder(Color.WHITE, 1);
        this.twoPlayer = new JButton("2 Players");
        this.twoPlayer.setBorder(thinBorder);
        this.twoPlayer.setFont(new Font("Tahoma", 20, 20));
        this.twoPlayer.setBounds(550, 10, 100, 50);
        this.add(this.twoPlayer);
        this.Fa = new JButton("FA");
        this.Fa.setFont(new Font("Tahoma", 20, 20));
        this.Fa.setBounds(550, 70, 100, 50);
        this.add(this.Fa);
        this.twoPlayer.addMouseListener(this.clickTwoPlayer);
        this.Fa.addMouseListener(this.clickFa);
    }

    public void setGui(GUI gui) {
        this.gui = gui;
    }

}

