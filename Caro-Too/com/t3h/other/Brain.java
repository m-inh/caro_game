/*
 * Decompiled with CFR 0_102.
 */
package com.t3h.other;

import com.t3h.other.Caro;
import com.t3h.other.Point;
import java.util.ArrayList;
import java.util.Random;

public class Brain {
    private int[][] matrixXO;
    private int[][] matrixPoint;
    private int columns;
    private int rows;

    public Brain(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        this.matrixXO = new int[columns][rows];
        this.matrixPoint = new int[columns][rows];
    }

    private void setMatrix(boolean[][] x, boolean[][] o) {
        int j;
        int i;
        this.matrixXO = new int[this.columns][this.rows];
        for (i = 0; i < o.length; ++i) {
            for (j = 0; j < o[0].length; ++j) {
                if (!o[i][j]) continue;
                this.matrixXO[i][j] = 2;
            }
        }
        for (i = 0; i < x.length; ++i) {
            for (j = 0; j < x[0].length; ++j) {
                if (!x[i][j]) continue;
                this.matrixXO[i][j] = 1;
            }
        }
    }

    private void danhGiaBang() {
        int type = 1;
        this.matrixPoint = new int[this.columns][this.rows];
        for (int i = 0; i < this.matrixXO.length; ++i) {
            for (int j = 0; j < this.matrixXO[0].length; ++j) {
                if (this.matrixXO[i][j] == 0) {
                    if (i - 1 >= 0 && j - 1 >= 0 && this.matrixXO[i - 1][j - 1] == type) {
                        int[] arrn = this.matrixPoint[i];
                        int n = j;
                        arrn[n] = arrn[n] + 1;
                    }
                    if (i - 1 >= 0 && this.matrixXO[i - 1][j] == type) {
                        int[] arrn = this.matrixPoint[i];
                        int n = j;
                        arrn[n] = arrn[n] + 1;
                    }
                    if (j - 1 >= 0 && this.matrixXO[i][j - 1] == type) {
                        int[] arrn = this.matrixPoint[i];
                        int n = j;
                        arrn[n] = arrn[n] + 1;
                    }
                    if (i - 1 >= 0 && j + 1 <= this.matrixXO[0].length - 1 && this.matrixXO[i - 1][j + 1] == type) {
                        int[] arrn = this.matrixPoint[i];
                        int n = j;
                        arrn[n] = arrn[n] + 1;
                    }
                    if (j - 1 >= 0 && i + 1 <= this.matrixXO.length - 1 && this.matrixXO[i + 1][j - 1] == type) {
                        int[] arrn = this.matrixPoint[i];
                        int n = j;
                        arrn[n] = arrn[n] + 1;
                    }
                    if (j + 1 <= this.matrixXO[0].length - 1 && this.matrixXO[i][j + 1] == type) {
                        int[] arrn = this.matrixPoint[i];
                        int n = j;
                        arrn[n] = arrn[n] + 1;
                    }
                    if (i + 1 <= this.matrixXO.length - 1 && this.matrixXO[i + 1][j] == type) {
                        int[] arrn = this.matrixPoint[i];
                        int n = j;
                        arrn[n] = arrn[n] + 1;
                    }
                    if (i + 1 <= this.matrixXO.length - 1 && j + 1 <= this.matrixXO[0].length - 1 && this.matrixXO[i + 1][j + 1] == type) {
                        int[] arrn = this.matrixPoint[i];
                        int n = j;
                        arrn[n] = arrn[n] + 1;
                    }
                }
                this.twoCaro(i, j, 1);
                this.twoCaro(i, j, 2);
                this.threeCaro(i, j, 1);
                this.threeCaro(i, j, 2);
                this.fourCaro(i, j, 1);
                this.fourCaro(i, j, 2);
            }
        }
    }

    private void twoCaro(int x, int y, int type) {
        int i;
        int count = 0;
        if (type == 1) {
            for (i = 0; i < 2; ++i) {
                if (x + i >= this.matrixXO.length || this.matrixXO[x + i][y] != 1) continue;
                ++count;
            }
            if (count == 2) {
                if (x - 2 >= 0 && this.matrixXO[x - 2][y] == 1 && this.matrixXO[x - 1][y] == 0) {
                    int[] arrn = this.matrixPoint[x - 1];
                    int n = y;
                    arrn[n] = arrn[n] + 16;
                    if (x - 3 >= 0 && this.matrixXO[x - 3][y] == 0) {
                        int[] arrn2 = this.matrixPoint[x - 3];
                        int n2 = y;
                        arrn2[n2] = arrn2[n2] + 16;
                    }
                    if (x + 2 < this.matrixXO.length && this.matrixXO[x + 2][y] == 0) {
                        int[] arrn3 = this.matrixPoint[x + 2];
                        int n3 = y;
                        arrn3[n3] = arrn3[n3] + 16;
                    }
                }
                if (x + 3 < this.matrixXO.length && this.matrixXO[x + 2][y] == 0 && this.matrixXO[x + 3][y] == 1) {
                    int[] arrn = this.matrixPoint[x + 2];
                    int n = y;
                    arrn[n] = arrn[n] + 16;
                    if (x - 1 >= 0) {
                        int[] arrn4 = this.matrixPoint[x - 1];
                        int n4 = y;
                        arrn4[n4] = arrn4[n4] + 16;
                    }
                    if (x + 4 < this.matrixXO.length) {
                        int[] arrn5 = this.matrixPoint[x + 4];
                        int n5 = y;
                        arrn5[n5] = arrn5[n5] + 16;
                    }
                }
            }
            count = 0;
            for (i = 0; i < 2; ++i) {
                if (y + i >= this.matrixXO[0].length || this.matrixXO[x][y + i] != 1) continue;
                ++count;
            }
            if (count == 2) {
                if (y - 2 >= 0 && this.matrixXO[x][y - 2] == 1 && this.matrixXO[x][y - 1] == 0) {
                    int[] arrn = this.matrixPoint[x];
                    int n = y - 1;
                    arrn[n] = arrn[n] + 16;
                    if (y - 3 >= 0 && this.matrixXO[x][y - 3] == 0) {
                        int[] arrn6 = this.matrixPoint[x];
                        int n6 = y - 3;
                        arrn6[n6] = arrn6[n6] + 16;
                    }
                    if (y + 2 < this.matrixXO[0].length && this.matrixXO[x][y + 2] == 0) {
                        int[] arrn7 = this.matrixPoint[x];
                        int n7 = y + 2;
                        arrn7[n7] = arrn7[n7] + 16;
                    }
                }
                if (y + 3 < this.matrixXO.length && this.matrixXO[x][y + 2] == 0 && this.matrixXO[x][y + 3] == 1) {
                    int[] arrn = this.matrixPoint[x];
                    int n = y + 2;
                    arrn[n] = arrn[n] + 16;
                    if (y - 1 >= 0) {
                        int[] arrn8 = this.matrixPoint[x];
                        int n8 = y - 1;
                        arrn8[n8] = arrn8[n8] + 16;
                    }
                    if (y + 4 < this.matrixXO[0].length) {
                        int[] arrn9 = this.matrixPoint[x];
                        int n9 = y + 4;
                        arrn9[n9] = arrn9[n9] + 16;
                    }
                }
            }
            count = 0;
            for (i = 0; i < 2; ++i) {
                if (x + i >= this.matrixXO.length || y + i >= this.matrixXO[0].length || this.matrixXO[x + i][y + i] != 1) continue;
                ++count;
            }
            if (count == 2) {
                if (y - 2 >= 0 && x - 2 >= 0 && this.matrixXO[x - 2][y - 2] == 1 && this.matrixXO[x - 1][y - 1] == 0) {
                    int[] arrn = this.matrixPoint[x - 1];
                    int n = y - 1;
                    arrn[n] = arrn[n] + 16;
                    if (y - 3 >= 0 && x - 3 >= 0 && this.matrixXO[x - 3][y - 3] == 0) {
                        int[] arrn10 = this.matrixPoint[x - 3];
                        int n10 = y - 3;
                        arrn10[n10] = arrn10[n10] + 16;
                    }
                    if (y + 2 < this.matrixXO[0].length && x + 2 < this.matrixXO.length && this.matrixXO[x + 2][y + 2] == 0) {
                        int[] arrn11 = this.matrixPoint[x + 2];
                        int n11 = y + 2;
                        arrn11[n11] = arrn11[n11] + 16;
                    }
                }
                if (y + 3 < this.matrixXO[0].length && x + 3 < this.matrixXO.length && this.matrixXO[x + 2][y + 2] == 0 && this.matrixXO[x + 3][y + 3] == 1) {
                    int[] arrn = this.matrixPoint[x + 2];
                    int n = y + 2;
                    arrn[n] = arrn[n] + 16;
                    if (y - 1 >= 0 && x - 1 >= 0) {
                        int[] arrn12 = this.matrixPoint[x - 1];
                        int n12 = y - 1;
                        arrn12[n12] = arrn12[n12] + 16;
                    }
                    if (y + 4 < this.matrixXO[0].length && x + 4 < this.matrixXO.length) {
                        int[] arrn13 = this.matrixPoint[x + 4];
                        int n13 = y + 4;
                        arrn13[n13] = arrn13[n13] + 16;
                    }
                }
            }
            count = 0;
            for (i = 0; i < 2; ++i) {
                if (x - i < 0 || y + i >= this.matrixXO[0].length || this.matrixXO[x - i][y + i] != 1) continue;
                ++count;
            }
            if (count == 2) {
                if (y - 2 >= 0 && x + 2 < this.matrixXO.length && this.matrixXO[x + 2][y - 2] == 1 && this.matrixXO[x + 1][y - 1] == 0) {
                    int[] arrn = this.matrixPoint[x + 1];
                    int n = y - 1;
                    arrn[n] = arrn[n] + 16;
                    if (y - 3 >= 0 && x + 3 < this.matrixXO.length && this.matrixXO[x + 3][y - 3] == 0) {
                        int[] arrn14 = this.matrixPoint[x + 3];
                        int n14 = y - 3;
                        arrn14[n14] = arrn14[n14] + 16;
                    }
                    if (y + 2 < this.matrixXO[0].length && x - 2 >= 0 && this.matrixXO[x - 2][y + 2] == 0) {
                        int[] arrn15 = this.matrixPoint[x - 2];
                        int n15 = y + 2;
                        arrn15[n15] = arrn15[n15] + 16;
                    }
                }
                if (y + 3 < this.matrixXO[0].length && x - 3 >= 0 && this.matrixXO[x - 2][y + 2] == 0 && this.matrixXO[x - 3][y + 3] == 1) {
                    int[] arrn = this.matrixPoint[x - 2];
                    int n = y + 2;
                    arrn[n] = arrn[n] + 16;
                    if (y - 1 >= 0 && x + 1 < this.matrixXO.length) {
                        int[] arrn16 = this.matrixPoint[x + 1];
                        int n16 = y - 1;
                        arrn16[n16] = arrn16[n16] + 16;
                    }
                    if (y + 4 < this.matrixXO[0].length && x - 4 >= 0) {
                        int[] arrn17 = this.matrixPoint[x - 4];
                        int n17 = y + 4;
                        arrn17[n17] = arrn17[n17] + 16;
                    }
                }
            }
        }
        if (type == 2) {
            for (i = 0; i < 2; ++i) {
                if (x + i >= this.matrixXO.length || this.matrixXO[x + i][y] != 2) continue;
                ++count;
            }
            if (count == 2) {
                if (x - 2 >= 0 && this.matrixXO[x - 2][y] == 2 && this.matrixXO[x - 1][y] == 0) {
                    int[] arrn = this.matrixPoint[x - 1];
                    int n = y;
                    arrn[n] = arrn[n] + 9;
                    if (x - 3 >= 0 && this.matrixXO[x - 3][y] == 0) {
                        int[] arrn18 = this.matrixPoint[x - 3];
                        int n18 = y;
                        arrn18[n18] = arrn18[n18] + 9;
                    }
                    if (x + 2 < this.matrixXO.length && this.matrixXO[x + 2][y] == 0) {
                        int[] arrn19 = this.matrixPoint[x + 2];
                        int n19 = y;
                        arrn19[n19] = arrn19[n19] + 9;
                    }
                }
                if (x + 3 < this.matrixXO.length && this.matrixXO[x + 2][y] == 0 && this.matrixXO[x + 3][y] == 2) {
                    int[] arrn = this.matrixPoint[x + 2];
                    int n = y;
                    arrn[n] = arrn[n] + 9;
                    if (x - 1 >= 0) {
                        int[] arrn20 = this.matrixPoint[x - 1];
                        int n20 = y;
                        arrn20[n20] = arrn20[n20] + 9;
                    }
                    if (x + 4 < this.matrixXO.length) {
                        int[] arrn21 = this.matrixPoint[x + 4];
                        int n21 = y;
                        arrn21[n21] = arrn21[n21] + 9;
                    }
                }
            }
            count = 0;
            for (i = 0; i < 2; ++i) {
                if (y + i >= this.matrixXO[0].length || this.matrixXO[x][y + i] != 2) continue;
                ++count;
            }
            if (count == 2) {
                if (y - 2 >= 0 && this.matrixXO[x][y - 2] == 2 && this.matrixXO[x][y - 1] == 0) {
                    int[] arrn = this.matrixPoint[x];
                    int n = y - 1;
                    arrn[n] = arrn[n] + 9;
                    if (y - 3 >= 0 && this.matrixXO[x][y - 3] == 0) {
                        int[] arrn22 = this.matrixPoint[x];
                        int n22 = y - 3;
                        arrn22[n22] = arrn22[n22] + 9;
                    }
                    if (y + 2 < this.matrixXO[0].length && this.matrixXO[x][y + 2] == 0) {
                        int[] arrn23 = this.matrixPoint[x];
                        int n23 = y + 2;
                        arrn23[n23] = arrn23[n23] + 9;
                    }
                }
                if (y + 3 < this.matrixXO.length && this.matrixXO[x][y + 2] == 0 && this.matrixXO[x][y + 3] == 2) {
                    int[] arrn = this.matrixPoint[x];
                    int n = y + 2;
                    arrn[n] = arrn[n] + 9;
                    if (y - 1 >= 0) {
                        int[] arrn24 = this.matrixPoint[x];
                        int n24 = y - 1;
                        arrn24[n24] = arrn24[n24] + 9;
                    }
                    if (y + 4 < this.matrixXO[0].length) {
                        int[] arrn25 = this.matrixPoint[x];
                        int n25 = y + 4;
                        arrn25[n25] = arrn25[n25] + 9;
                    }
                }
            }
            count = 0;
            for (i = 0; i < 2; ++i) {
                if (x + i >= this.matrixXO.length || y + i >= this.matrixXO[0].length || this.matrixXO[x + i][y + i] != 2) continue;
                ++count;
            }
            if (count == 2) {
                if (y - 2 >= 0 && x - 2 >= 0 && this.matrixXO[x - 2][y - 2] == 2 && this.matrixXO[x - 1][y - 1] == 0) {
                    int[] arrn = this.matrixPoint[x - 1];
                    int n = y - 1;
                    arrn[n] = arrn[n] + 9;
                    if (y - 3 >= 0 && x - 3 >= 0 && this.matrixXO[x - 3][y - 3] == 0) {
                        int[] arrn26 = this.matrixPoint[x - 3];
                        int n26 = y - 3;
                        arrn26[n26] = arrn26[n26] + 9;
                    }
                    if (y + 2 < this.matrixXO[0].length && x + 2 < this.matrixXO.length && this.matrixXO[x + 2][y + 2] == 0) {
                        int[] arrn27 = this.matrixPoint[x + 2];
                        int n27 = y + 2;
                        arrn27[n27] = arrn27[n27] + 9;
                    }
                }
                if (y + 3 < this.matrixXO[0].length && x + 3 < this.matrixXO.length && this.matrixXO[x + 2][y + 2] == 0 && this.matrixXO[x + 3][y + 3] == 2) {
                    int[] arrn = this.matrixPoint[x + 2];
                    int n = y + 2;
                    arrn[n] = arrn[n] + 9;
                    if (y - 1 >= 0 && x - 1 >= 0) {
                        int[] arrn28 = this.matrixPoint[x - 1];
                        int n28 = y - 1;
                        arrn28[n28] = arrn28[n28] + 9;
                    }
                    if (y + 4 < this.matrixXO[0].length && x + 4 < this.matrixXO.length) {
                        int[] arrn29 = this.matrixPoint[x + 4];
                        int n29 = y + 4;
                        arrn29[n29] = arrn29[n29] + 9;
                    }
                }
            }
            count = 0;
            for (i = 0; i < 2; ++i) {
                if (x - i < 0 || y + i >= this.matrixXO[0].length || this.matrixXO[x - i][y + i] != 2) continue;
                ++count;
            }
            if (count == 2) {
                if (y - 2 >= 0 && x + 2 < this.matrixXO.length && this.matrixXO[x + 2][y - 2] == 2 && this.matrixXO[x + 1][y - 1] == 0) {
                    int[] arrn = this.matrixPoint[x + 1];
                    int n = y - 1;
                    arrn[n] = arrn[n] + 9;
                    if (y - 3 >= 0 && x + 3 < this.matrixXO.length && this.matrixXO[x + 3][y - 3] == 0) {
                        int[] arrn30 = this.matrixPoint[x + 3];
                        int n30 = y - 3;
                        arrn30[n30] = arrn30[n30] + 9;
                    }
                    if (y + 2 < this.matrixXO[0].length && x - 2 >= 0 && this.matrixXO[x - 2][y + 2] == 0) {
                        int[] arrn31 = this.matrixPoint[x - 2];
                        int n31 = y + 2;
                        arrn31[n31] = arrn31[n31] + 9;
                    }
                }
                if (y + 3 < this.matrixXO[0].length && x - 3 >= 0 && this.matrixXO[x - 2][y + 2] == 0 && this.matrixXO[x - 3][y + 3] == 2) {
                    int[] arrn = this.matrixPoint[x - 2];
                    int n = y + 2;
                    arrn[n] = arrn[n] + 9;
                    if (y - 1 >= 0 && x + 1 < this.matrixXO.length) {
                        int[] arrn32 = this.matrixPoint[x + 1];
                        int n32 = y - 1;
                        arrn32[n32] = arrn32[n32] + 9;
                    }
                    if (y + 4 < this.matrixXO[0].length && x - 4 >= 0) {
                        int[] arrn33 = this.matrixPoint[x - 4];
                        int n33 = y + 4;
                        arrn33[n33] = arrn33[n33] + 9;
                    }
                }
            }
        }
    }

    private void threeCaro(int x, int y, int type) {
        int i;
        int count = 0;
        if (type == 1) {
            for (i = 0; i < 3; ++i) {
                if (x + i >= this.matrixXO.length || this.matrixXO[x + i][y] != 1) continue;
                ++count;
            }
            if (count == 3 && x - 1 >= 0 && x + 3 < this.matrixXO.length && this.matrixXO[x - 1][y] == 0 && this.matrixXO[x + 3][y] == 0) {
                int[] arrn = this.matrixPoint[x - 1];
                int n = y;
                arrn[n] = arrn[n] + 16;
                int[] arrn2 = this.matrixPoint[x + 3];
                int n2 = y;
                arrn2[n2] = arrn2[n2] + 16;
            }
            count = 0;
            for (i = 0; i < 3; ++i) {
                if (y + i >= this.matrixXO[0].length || this.matrixXO[x][y + i] != 1) continue;
                ++count;
            }
            if (count == 3 && y - 1 >= 0 && y + 3 < this.matrixXO[0].length && this.matrixXO[x][y - 1] == 0 && this.matrixXO[x][y + 3] == 0) {
                int[] arrn = this.matrixPoint[x];
                int n = y - 1;
                arrn[n] = arrn[n] + 16;
                int[] arrn3 = this.matrixPoint[x];
                int n3 = y + 3;
                arrn3[n3] = arrn3[n3] + 16;
            }
            count = 0;
            for (i = 0; i < 3; ++i) {
                if (x + i >= this.matrixXO.length || y + i >= this.matrixXO[0].length || this.matrixXO[x + i][y + i] != 1) continue;
                ++count;
            }
            if (count == 3 && x - 1 >= 0 && y - 1 >= 0 && x + 3 < this.matrixXO.length && y + 3 < this.matrixXO[0].length && this.matrixXO[x - 1][y - 1] == 0 && this.matrixXO[x + 3][y + 3] == 0) {
                int[] arrn = this.matrixPoint[x - 1];
                int n = y - 1;
                arrn[n] = arrn[n] + 16;
                int[] arrn4 = this.matrixPoint[x + 3];
                int n4 = y + 3;
                arrn4[n4] = arrn4[n4] + 16;
            }
            count = 0;
            for (i = 0; i < 3; ++i) {
                if (x - i < 0 || y + i >= this.matrixXO[0].length || this.matrixXO[x - i][y + i] != 1) continue;
                ++count;
            }
            if (count == 3 && x + 1 >= 0 && y - 1 >= 0 && x - 3 < this.matrixXO.length && y + 3 < this.matrixXO[0].length && this.matrixXO[x + 1][y - 1] == 0 && this.matrixXO[x - 3][y + 3] == 0) {
                int[] arrn = this.matrixPoint[x + 1];
                int n = y - 1;
                arrn[n] = arrn[n] + 16;
                int[] arrn5 = this.matrixPoint[x - 3];
                int n5 = y + 3;
                arrn5[n5] = arrn5[n5] + 16;
            }
        }
        if (type == 2) {
            for (i = 0; i < 3; ++i) {
                if (x + i >= this.matrixXO.length || this.matrixXO[x + i][y] != 2) continue;
                ++count;
            }
            if (count == 3 && x - 1 >= 0 && x + 3 < this.matrixXO.length && this.matrixXO[x - 1][y] == 0 && this.matrixXO[x + 3][y] == 0) {
                int[] arrn = this.matrixPoint[x - 1];
                int n = y;
                arrn[n] = arrn[n] + 9;
                int[] arrn6 = this.matrixPoint[x + 3];
                int n6 = y;
                arrn6[n6] = arrn6[n6] + 9;
            }
            count = 0;
            for (i = 0; i < 3; ++i) {
                if (y + i >= this.matrixXO[0].length || this.matrixXO[x][y + i] != 2) continue;
                ++count;
            }
            if (count == 3 && y - 1 >= 0 && y + 3 < this.matrixXO[0].length && this.matrixXO[x][y - 1] == 0 && this.matrixXO[x][y + 3] == 0) {
                int[] arrn = this.matrixPoint[x];
                int n = y - 1;
                arrn[n] = arrn[n] + 9;
                int[] arrn7 = this.matrixPoint[x];
                int n7 = y + 3;
                arrn7[n7] = arrn7[n7] + 9;
            }
            count = 0;
            for (i = 0; i < 3; ++i) {
                if (x + i >= this.matrixXO.length || y + i >= this.matrixXO[0].length || this.matrixXO[x + i][y + i] != 2) continue;
                ++count;
            }
            if (count == 3 && x - 1 >= 0 && y - 1 >= 0 && x + 3 < this.matrixXO.length && y + 3 < this.matrixXO[0].length && this.matrixXO[x - 1][y - 1] == 0 && this.matrixXO[x + 3][y + 3] == 0) {
                int[] arrn = this.matrixPoint[x - 1];
                int n = y - 1;
                arrn[n] = arrn[n] + 9;
                int[] arrn8 = this.matrixPoint[x + 3];
                int n8 = y + 3;
                arrn8[n8] = arrn8[n8] + 9;
            }
            count = 0;
            for (i = 0; i < 3; ++i) {
                if (x - i < 0 || y + i >= this.matrixXO[0].length || this.matrixXO[x - i][y + i] != 2) continue;
                ++count;
            }
            if (count == 3 && x + 1 >= 0 && y - 1 >= 0 && x - 3 < this.matrixXO.length && y + 3 < this.matrixXO[0].length && this.matrixXO[x + 1][y - 1] == 0 && this.matrixXO[x - 3][y + 3] == 0) {
                int[] arrn = this.matrixPoint[x + 1];
                int n = y - 1;
                arrn[n] = arrn[n] + 9;
                int[] arrn9 = this.matrixPoint[x - 3];
                int n9 = y + 3;
                arrn9[n9] = arrn9[n9] + 9;
            }
        }
    }

    private void fourCaro(int x, int y, int type) {
        int i;
        int count = 0;
        if (type == 1) {
            for (i = 0; i < 4; ++i) {
                if (x + i >= this.matrixXO.length || this.matrixXO[x + i][y] != 1) continue;
                ++count;
            }
            if (count == 4) {
                if (x - 1 >= 0 && this.matrixXO[x - 1][y] == 0) {
                    int[] arrn = this.matrixPoint[x - 1];
                    int n = y;
                    arrn[n] = arrn[n] + 30;
                }
                if (x + 4 < this.matrixXO.length && this.matrixXO[x + 4][y] == 0) {
                    int[] arrn = this.matrixPoint[x + 4];
                    int n = y;
                    arrn[n] = arrn[n] + 30;
                }
            }
            count = 0;
            for (i = 0; i < 4; ++i) {
                if (y + i >= this.matrixXO[0].length || this.matrixXO[x][y + i] != 1) continue;
                ++count;
            }
            if (count == 4) {
                if (y - 1 >= 0 && this.matrixXO[x][y - 1] == 0) {
                    int[] arrn = this.matrixPoint[x];
                    int n = y - 1;
                    arrn[n] = arrn[n] + 30;
                }
                if (y + 4 < this.matrixXO[0].length && this.matrixXO[x][y + 4] == 0) {
                    int[] arrn = this.matrixPoint[x];
                    int n = y + 4;
                    arrn[n] = arrn[n] + 30;
                }
            }
            count = 0;
            for (i = 0; i < 4; ++i) {
                if (x + i >= this.matrixXO.length || y + i >= this.matrixXO[0].length || this.matrixXO[x + i][y + i] != 1) continue;
                ++count;
            }
            if (count == 4) {
                if (y - 1 >= 0 && x - 1 >= 0 && this.matrixXO[x - 1][y - 1] == 0) {
                    int[] arrn = this.matrixPoint[x - 1];
                    int n = y - 1;
                    arrn[n] = arrn[n] + 30;
                }
                if (y + 4 < this.matrixXO[0].length && x + 4 < this.matrixXO.length && this.matrixXO[x + 4][y + 4] == 0) {
                    int[] arrn = this.matrixPoint[x + 4];
                    int n = y + 4;
                    arrn[n] = arrn[n] + 30;
                }
            }
            count = 0;
            for (i = 0; i < 4; ++i) {
                if (x - i < 0 || y + i >= this.matrixXO[0].length || this.matrixXO[x - i][y + i] != 1) continue;
                ++count;
            }
            if (count == 4) {
                if (y - 1 >= 0 && x + 1 >= 0 && this.matrixXO[x + 1][y - 1] == 0) {
                    int[] arrn = this.matrixPoint[x + 1];
                    int n = y - 1;
                    arrn[n] = arrn[n] + 30;
                }
                if (y + 4 < this.matrixXO[0].length && x - 4 < this.matrixXO.length && this.matrixXO[x - 4][y + 4] == 0) {
                    int[] arrn = this.matrixPoint[x - 4];
                    int n = y + 4;
                    arrn[n] = arrn[n] + 30;
                }
            }
        }
        if (type == 2) {
            for (i = 0; i < 4; ++i) {
                if (x + i >= this.matrixXO.length || this.matrixXO[x + i][y] != 2) continue;
                ++count;
            }
            if (count == 4) {
                if (x - 1 >= 0 && this.matrixXO[x - 1][y] == 0) {
                    int[] arrn = this.matrixPoint[x - 1];
                    int n = y;
                    arrn[n] = arrn[n] + 24;
                }
                if (x + 4 < this.matrixXO.length && this.matrixXO[x + 4][y] == 0) {
                    int[] arrn = this.matrixPoint[x + 4];
                    int n = y;
                    arrn[n] = arrn[n] + 24;
                }
            }
            count = 0;
            for (i = 0; i < 4; ++i) {
                if (y + i >= this.matrixXO[0].length || this.matrixXO[x][y + i] != 2) continue;
                ++count;
            }
            if (count == 4) {
                if (y - 1 >= 0 && this.matrixXO[x][y - 1] == 0) {
                    int[] arrn = this.matrixPoint[x];
                    int n = y - 1;
                    arrn[n] = arrn[n] + 24;
                }
                if (y + 4 < this.matrixXO.length && this.matrixXO[x][y + 4] == 0) {
                    int[] arrn = this.matrixPoint[x];
                    int n = y + 4;
                    arrn[n] = arrn[n] + 24;
                }
            }
            count = 0;
            for (i = 0; i < 4; ++i) {
                if (x + i >= this.matrixXO.length || y + i >= this.matrixXO[0].length || this.matrixXO[x + i][y + i] != 2) continue;
                ++count;
            }
            if (count == 4) {
                if (y - 1 >= 0 && x - 1 >= 0 && this.matrixXO[x - 1][y - 1] == 0) {
                    int[] arrn = this.matrixPoint[x - 1];
                    int n = y - 1;
                    arrn[n] = arrn[n] + 24;
                }
                if (y + 4 < this.matrixXO[0].length && x + 4 < this.matrixXO.length && this.matrixXO[x + 4][y + 4] == 0) {
                    int[] arrn = this.matrixPoint[x + 4];
                    int n = y + 4;
                    arrn[n] = arrn[n] + 24;
                }
            }
            count = 0;
            for (i = 0; i < 4; ++i) {
                if (x - i < 0 || y + i >= this.matrixXO[0].length || this.matrixXO[x - i][y + i] != 2) continue;
                ++count;
            }
            if (count == 4) {
                if (y - 1 >= 0 && x + 1 >= 0 && this.matrixXO[x + 1][y - 1] == 0) {
                    int[] arrn = this.matrixPoint[x + 1];
                    int n = y - 1;
                    arrn[n] = arrn[n] + 24;
                }
                if (y + 4 < this.matrixXO[0].length && x - 4 < this.matrixXO.length && this.matrixXO[x - 4][y + 4] == 0) {
                    int[] arrn = this.matrixPoint[x - 4];
                    int n = y + 4;
                    arrn[n] = arrn[n] + 24;
                }
            }
        }
    }

    private int maxPoint() {
        this.danhGiaBang();
        int maxPoint = 0;
        for (int i = 0; i < this.matrixPoint.length; ++i) {
            for (int j = 0; j < this.matrixPoint[0].length; ++j) {
                if (this.matrixPoint[i][j] <= maxPoint) continue;
                maxPoint = this.matrixPoint[i][j];
            }
        }
        return maxPoint;
    }

    public Caro nextCaro(boolean[][] matrisX, boolean[][] matrisO) {
        int x = 0;
        int y = 0;
        int type = 1;
        ArrayList<Point> pointMgr = new ArrayList<Point>();
        this.setMatrix(matrisX, matrisO);
        int maxPoint = this.maxPoint();
        if (maxPoint == 0) {
            this.firstTurn();
            ++maxPoint;
        }
        for (int i = 0; i < this.matrixPoint.length; ++i) {
            for (int j = 0; j < this.matrixPoint[0].length; ++j) {
                if (this.matrixPoint[i][j] != maxPoint) continue;
                Point point = new Point(i, j);
                pointMgr.add(point);
            }
        }
        Random random = new Random();
        int indexCaroRandom = random.nextInt(pointMgr.size());
        x = ((Point)pointMgr.get(indexCaroRandom)).getX();
        y = ((Point)pointMgr.get(indexCaroRandom)).getY();
        return new Caro(x * 50, y * 50, type);
    }

    private void firstTurn() {
        int i = 9;
        int j = 5;
        for (int q = 0; q < this.matrixXO.length; ++q) {
            for (int k = 0; k < this.matrixXO[0].length; ++k) {
                if (this.matrixXO[q][k] != 2) continue;
                i = q;
                j = k;
            }
        }
        if (i - 1 >= 0 && j - 1 >= 0) {
            int[] arrn = this.matrixPoint[i - 1];
            int n = j - 1;
            arrn[n] = arrn[n] + 1;
        }
        if (i - 1 >= 0) {
            int[] arrn = this.matrixPoint[i - 1];
            int n = j;
            arrn[n] = arrn[n] + 1;
        }
        if (j - 1 >= 0) {
            int[] arrn = this.matrixPoint[i];
            int n = j - 1;
            arrn[n] = arrn[n] + 1;
        }
        if (i - 1 >= 0 && j + 1 <= this.matrixXO[0].length - 1) {
            int[] arrn = this.matrixPoint[i - 1];
            int n = j + 1;
            arrn[n] = arrn[n] + 1;
        }
        if (j - 1 >= 0 && i + 1 <= this.matrixXO.length - 1) {
            int[] arrn = this.matrixPoint[i + 1];
            int n = j - 1;
            arrn[n] = arrn[n] + 1;
        }
        if (j + 1 <= this.matrixXO[0].length - 1) {
            int[] arrn = this.matrixPoint[i];
            int n = j + 1;
            arrn[n] = arrn[n] + 1;
        }
        if (i + 1 <= this.matrixXO.length - 1) {
            int[] arrn = this.matrixPoint[i + 1];
            int n = j;
            arrn[n] = arrn[n] + 1;
        }
        if (i + 1 <= this.matrixXO.length - 1 && j + 1 <= this.matrixXO[0].length - 1) {
            int[] arrn = this.matrixPoint[i + 1];
            int n = j + 1;
            arrn[n] = arrn[n] + 1;
        }
    }
}

