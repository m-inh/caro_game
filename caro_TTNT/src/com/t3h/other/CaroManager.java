/*
 * Decompiled with CFR 0_102.
 */
package com.t3h.other;

import com.t3h.other.Caro;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class CaroManager {
    private ArrayList<Caro> CaroMGR = new ArrayList();
    private boolean[][] matrix_x;
    private boolean[][] matrix_o;
    public boolean stop = false;
    private int winType;
    public Caro LastCaroWin;

    public CaroManager(int column, int row) {
        this.matrix_x = new boolean[column][row];
        this.matrix_o = new boolean[column][row];
    }

    public boolean add(Caro caro) {
        if (!this.exist(caro)) {
            this.CaroMGR.add(caro);
            if (caro.getType() == 1) {
                this.matrix_x[caro.getX() / 50][caro.getY() / 50] = true;
            }
            if (caro.getType() == 0) {
                this.matrix_o[caro.getX() / 50][caro.getY() / 50] = true;
            }
            if (this.checkWin(caro.getX() / 50, caro.getY() / 50, caro.getType())) {
                this.stop = true;
            }
            return true;
        }
        return false;
    }

    private boolean exist(Caro caro) {
        for (int i = 0; i < this.CaroMGR.size(); ++i) {
            if (!this.CaroMGR.get(i).equals(caro)) continue;
            return true;
        }
        return false;
    }

    public void drawAllCaro(Graphics2D g2d) {
        int x = 0;
        int y = 0;
        for (int i = 0; i < this.CaroMGR.size(); ++i) {
            g2d.setFont(new Font("Tahoma", 50, 50));
            x = this.CaroMGR.get(i).getX() / 50;
            y = this.CaroMGR.get(i).getY() / 50 + 1;
            g2d.setColor(this.CaroMGR.get(i).getColor());
            g2d.drawString(this.CaroMGR.get(i).toString(), x * 50 + 8, y * 50 - 6);
        }
        if (this.stop) {
            this.drawLine_win(g2d);
        }
    }

    private boolean checkWin(int x, int y, int type) {
        int i;
        int i2;
        int i3;
        int i4;
        int count_row = 0;
        int location = x - 4;
        if (type == 1) {
            for (i2 = 0; i2 < 9; ++i2) {
                if (location >= 0 && location <= this.matrix_x.length - 1) {
                    count_row = this.matrix_x[location][y] ? ++count_row : 0;
                }
                if (count_row == 5) {
                    this.LastCaroWin = new Caro(location * 50, y * 50, type);
                    break;
                }
                ++location;
            }
        }
        if (type == 0) {
            for (i2 = 0; i2 < 9; ++i2) {
                if (location >= 0 && location <= this.matrix_o.length - 1) {
                    count_row = this.matrix_o[location][y] ? ++count_row : 0;
                }
                if (count_row == 5) {
                    this.LastCaroWin = new Caro(location * 50, y * 50, type);
                    break;
                }
                ++location;
            }
        }
        int count_column = 0;
        location = y - 4;
        if (type == 1) {
            for (i4 = 0; i4 < 9; ++i4) {
                if (location >= 0 && location <= this.matrix_x[0].length - 1) {
                    count_column = this.matrix_x[x][location] ? ++count_column : 0;
                }
                if (count_column == 5) {
                    this.LastCaroWin = new Caro(x * 50, location * 50, type);
                    break;
                }
                ++location;
            }
        }
        if (type == 0) {
            for (i4 = 0; i4 < 9; ++i4) {
                if (location >= 0 && location <= this.matrix_o[0].length - 1) {
                    count_column = this.matrix_o[x][location] ? ++count_column : 0;
                }
                if (count_column == 5) {
                    this.LastCaroWin = new Caro(x * 50, location * 50, type);
                    break;
                }
                ++location;
            }
        }
        int count_duongCheo = 0;
        int location_x = x - 4;
        int location_y = y - 4;
        if (type == 0) {
            for (i = 0; i < 9; ++i) {
                if (location_x >= 0 && location_x <= this.matrix_o.length - 1 && location_y >= 0 && location_y <= this.matrix_o[0].length - 1) {
                    count_duongCheo = this.matrix_o[location_x][location_y] ? ++count_duongCheo : 0;
                }
                if (count_duongCheo == 5) {
                    this.LastCaroWin = new Caro(location_x * 50, location_y * 50, type);
                    break;
                }
                ++location_x;
                ++location_y;
            }
        }
        if (type == 1) {
            for (i = 0; i < 9; ++i) {
                if (location_x >= 0 && location_x <= this.matrix_x.length - 1 && location_y >= 0 && location_y <= this.matrix_x[0].length - 1) {
                    count_duongCheo = this.matrix_x[location_x][location_y] ? ++count_duongCheo : 0;
                }
                if (count_duongCheo == 5) {
                    this.LastCaroWin = new Caro(location_x * 50, location_y * 50, type);
                    break;
                }
                ++location_x;
                ++location_y;
            }
        }
        int count_duongCheo2 = 0;
        location_x = x + 4;
        location_y = y - 4;
        if (type == 0) {
            for (i3 = 0; i3 < 9; ++i3) {
                if (location_x >= 0 && location_x <= this.matrix_o.length - 1 && location_y >= 0 && location_y <= this.matrix_o[0].length - 1) {
                    count_duongCheo2 = this.matrix_o[location_x][location_y] ? ++count_duongCheo2 : 0;
                }
                if (count_duongCheo2 == 5) {
                    this.LastCaroWin = new Caro(location_x * 50, location_y * 50, type);
                    break;
                }
                --location_x;
                ++location_y;
            }
        }
        if (type == 1) {
            for (i3 = 0; i3 < 9; ++i3) {
                if (location_x >= 0 && location_x <= this.matrix_x.length - 1 && location_y >= 0 && location_y <= this.matrix_x[0].length - 1) {
                    count_duongCheo2 = this.matrix_x[location_x][location_y] ? ++count_duongCheo2 : 0;
                }
                if (count_duongCheo2 == 5) {
                    this.LastCaroWin = new Caro(location_x * 50, location_y * 50, type);
                    break;
                }
                --location_x;
                ++location_y;
            }
        }
        if (count_row == 5) {
            this.winType = 1;
        }
        if (count_column == 5) {
            this.winType = 2;
        }
        if (count_duongCheo == 5) {
            this.winType = 3;
        }
        if (count_duongCheo2 == 5) {
            this.winType = 4;
        }
        if (count_row != 5 && count_column != 5 && count_duongCheo != 5 && count_duongCheo2 != 5) {
            return false;
        }
        return true;
    }

    public void drawLine_win(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        if (this.winType == 1) {
            g2d.drawLine(this.LastCaroWin.getX() - 200 + 25, this.LastCaroWin.getY() + 25, this.LastCaroWin.getX() + 25, this.LastCaroWin.getY() + 25);
        }
        if (this.winType == 2) {
            g2d.drawLine(this.LastCaroWin.getX() + 25, this.LastCaroWin.getY() - 200 + 25, this.LastCaroWin.getX() + 25, this.LastCaroWin.getY() + 25);
        }
        if (this.winType == 3) {
            g2d.drawLine(this.LastCaroWin.getX() - 200 + 25, this.LastCaroWin.getY() - 200 + 25, this.LastCaroWin.getX() + 25, this.LastCaroWin.getY() + 25);
        }
        if (this.winType == 4) {
            g2d.drawLine(this.LastCaroWin.getX() + 200 + 25, this.LastCaroWin.getY() - 200 + 25, this.LastCaroWin.getX() + 25, this.LastCaroWin.getY() + 25);
        }
    }

    public Caro getLastCaro() {
        return this.CaroMGR.get(this.CaroMGR.size() - 1);
    }

    public boolean[][] getMatrix_o() {
        return this.matrix_o;
    }

    public boolean[][] getMatrix_x() {
        return this.matrix_x;
    }
}

