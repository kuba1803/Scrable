/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

import Controller.Coordinates;
import Controller.Pair;
import Model.Board;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Dictionary {

    Map<String, Integer> map = new HashMap<String, Integer>();
    int size;
    public char[] set;
    public int[] value;

    public Dictionary(String location, char[] letter, int[] value) throws IOException {
        Scanner scr = new Scanner(Paths.get(location));
        set = letter;
        this.value = value;
        int i = 0;
        while (scr.hasNext()) {
            map.put(scr.next(), i);
            i++;
        }
        
        System.out.print(map.size());
        System.out.print(map.size());
        System.out.print(map.size());
        size = i;
    }

    public boolean cheack(List<Pair> list, Board board) throws Exception {
        /*if (!board.get(7, 7).isFull) {
            return false;
        }*/
       // return true;
        List<Coordinates> start = new ArrayList<Coordinates>();
        for (Pair e : list) {

            if ((e.x > 0 && board.get(e.x - 1, e.y).isFull) || (e.x < 14 && board.get(e.x + 1, e.y).isFull)) {
                start.add(new Coordinates(e.x, e.y, board, 1));
            }
            if ((e.y > 0 && board.get(e.x, e.y - 1).isFull) || (e.y < 14 && board.get(e.x, e.y + 1).isFull)) {
                start.add(new Coordinates(e.x, e.y, board, 2));
            }
            if ((e.x == 0 || !board.get(e.x - 1, e.y).isFull) && (e.x == 14 || !board.get(e.x + 1, e.y).isFull) && (e.y == 0 || !board.get(e.x, e.y - 1).isFull) && (e.y == 14 || !board.get(e.x, e.y + 1).isFull)) {
                start.add(new Coordinates(e.x, e.y, board, 0));
            }
        }
        List<String> words = new ArrayList<String>();
        StringBuilder build = new StringBuilder();
        for (Coordinates e : start) {
            if (e.wymiar == 0) {
                build.append(board.get(e.startx, e.starty).current.Char);
            } else if (e.wymiar == 1) {
                int x = e.startx;
                do {
                    build.append(board.get(x, e.starty).current.Char);
                    x++;
                } while (x <= e.stopx);
            } else {
                int y = e.starty;
                do {
                    build.append(board.get(e.startx, y).current.Char);
                    y++;
                } while (y <= e.stopy);
            }
            words.add(build.toString().toLowerCase());
            build = new StringBuilder();
        }
        boolean corect = true;
        for (String e : words) {
            System.out.println(e);
            if (!map.containsKey(e)) {
                corect = false;
                break;
            }
        }
        return corect;
    }

    public static Dictionary Polish() throws IOException {
        final char[] set = {' ', ' ',
            'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A',
            'E', 'E', 'E', 'E', 'E', 'E', 'E',
            'I', 'I', 'I', 'I', 'I', 'I', 'I', 'I',
            'N', 'N', 'N', 'N', 'N',
            'O', 'O', 'O', 'O', 'O', 'O',
            'R', 'R', 'R', 'R',
            'S', 'S', 'S', 'S',
            'W', 'W', 'W', 'W',
            'Z', 'Z', 'Z', 'Z', 'Z',
            'C', 'C', 'C',
            'D', 'D', 'D',
            'K', 'K', 'K',
            'L', 'L', 'L',
            'M', 'M', 'M',
            'P', 'P', 'P',
            'T', 'T', 'T',
            'Y', 'Y', 'Y', 'Y',
            'B', 'B', 'G', 'G', 'H', 'H', 'J', 'J', 'Ł', 'Ł', 'U', 'U', 'Ą', 'Ę', 'F', 'Ó', 'Ś', 'Ż', 'Ć', 'Ń', 'Ź'};
        final int[] swartosc = {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 5, 5, 5, 5, 5, 6, 7, 9};
        int size = swartosc.length;
        return new Dictionary("src/dictionary/slowa-win.txt", set, swartosc);
    }

    public static Dictionary English() throws IOException {
        char[] set = {'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'I', 'I', 'I', 'I', 'I', 'I', 'I', 'I', 'I',
            'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'N', 'N', 'N', 'N', 'N', 'N', 'R', 'R', 'R', 'R', 'R', 'R', 'T', 'T', 'T', 'T', 'T', 'T', 'L', 'L', 'L', 'L', 'S', 'S',
            'S', 'S', 'U', 'U', 'U', 'U', 'D', 'D', 'D', 'D', 'G', 'G', 'G', 'B', 'B', 'C', 'C', 'M', 'M', 'P', 'P', 'F', 'F', 'H', 'H', 'V', 'V', 'W', 'W', 'Y', 'T', 'K',
            'J', 'X', 'Q', 'Z'};
        int[] value = new int[set.length];
        for (int i = 0; i < set.length; i++) {
            if (i < 68) {
                value[i] = 1;
            } else if (i < 75) {
                value[i] = 2;
            } else if (i < 83) {
                value[i] = 3;
            } else if (i < 93) {
                value[i] = 4;
            } else if (i < 94) {
                value[i] = 5;
            } else if (i < 96) {
                value[i] = 8;
            } else if (i < 98) {
                value[i] = 10;
            }
        }
        return new Dictionary("src/dictionary/English.txt", set, value);
    }
}

