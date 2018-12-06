package com.dragger2.mahjong;

/**
 * Description :麻将bean
 * <p>
 * Author:yang
 * <p>
 * Email:1318392199@qq.com
 * <p>
 * Date: 2018/11/29
 */

public class Mahjong {
    public int suit;    //花色
    public int rank;    //点数

    public Mahjong(int suit, int rank) {
        this.suit = suit;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Mahjong{" +
            "suit=" + suit +
            ", rank=" + rank +
            '}';
    }
}
