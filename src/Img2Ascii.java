import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Img2Ascii {
	
    private BufferedImage img;
    private double pixval;
    private PrintWriter prntwrt;
    private FileWriter filewrt;
    private static Scanner input = new Scanner(System.in);
    private static String fileName;

    public Img2Ascii() {
        try {
            prntwrt = new PrintWriter(filewrt = new FileWriter(fileName,
                    true));
        } catch (IOException ex) {
        }
    }

    public void convertToAscii(String imgname) {
        try {
            img = ImageIO.read(new File(imgname));
        } catch (IOException e) {
        }

        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                Color pixcol = new Color(img.getRGB(j, i));
                pixval = (((pixcol.getRed() * 0.30) + (pixcol.getBlue() * 0.59) + (pixcol
                        .getGreen() * 0.11)));
                print(strChar(pixval));
            }
            try {
                prntwrt.println("");
                prntwrt.flush();
                filewrt.flush();
            } catch (Exception ex) {
            }
        }
    }

    public String strChar(double g) {
        String str = " ";
        if (g >= 240) {
            str = " ";
        } else if (g >= 237) {
            str = ".";
        } else if (g >= 234) {
            str = "'";
        } else if (g >= 231) {
            str = "`";
        } else if (g >= 228) {
            str = "^";
        } else if (g >= 225) {
            str = "\"";
        } else if (g >= 222) {
            str = ",";
        } else if (g >= 219) {
            str = ":";	
        } else if (g >= 216) {
            str = ";";
        } else if (g >= 213) {
            str = "I";
        } else if (g >= 210) {
            str = "l";
        } else if (g >= 207) {
            str = "!";
        } else if (g >= 204) {
            str = "i";
        } else if (g >= 201) {
            str = ">";
        } else if (g >= 198) {
            str = "<";
        } else if (g >= 195) {
           str = "~";
        } else if (g >= 192) {
            str = "+";
        } else if (g >= 189) {
            str = "_";
        } else if (g >= 186) {
            str = "-";
        } else if (g >= 183) {
            str = "?";
        } else if (g >= 180) {
            str = "]";
        } else if (g >= 177) {
            str = "[" ;
        } else if (g >= 174) {
            str = "}";
        } else if (g >= 171) {
            str = "{";
        } else if (g >= 168) {
            str = "1";
        } else if (g >= 165) {
            str = ")";
        } else if (g >= 162) {
            str = "(";
        } else if (g >= 159) {
            str = "|";
        } else if (g >= 156) {
            str = "\\";
        } else if (g >= 153) {
            str = "/";
        } else if (g >= 150) {
            str = "t";
        } else if (g >= 147) {
            str = "f";
        } else if (g >= 144) {
            str = "j";
        } else if (g >= 141) {
            str = "r";
        } else if (g >= 138) {
            str = "x";
        } else if (g >= 135) {
            str = "n";
        } else if (g >= 132) {
            str = "u";
        } else if (g >= 129) {
            str = "v";
        } else if (g >= 126) {
            str = "c";
        } else if (g >= 123) {
            str = "z";
        } else if (g >= 120) {
            str = "X";
        } else if (g >= 117) {
            str = "Y";
        } else if (g >= 114) {
            str = "U";
        } else if (g >= 111) {
            str = "J";
        } else if (g >= 108) {
            str = "C";
        } else if (g >= 105) {
            str = "L";
        } else if (g >= 102) {
            str = "Q";
        } else if (g >= 99) {
            str = "0";
        } else if (g >= 96) {
            str = "Z";
        } else if (g >= 93) {
            str = "m";
        } else if (g >= 90) {
            str = "w";
        } else if (g >= 87) {
            str = "q";
        } else if (g >= 84) {
            str = "p";
        } else if (g >= 81) {
            str = "d";
        } else if (g >= 78) {
            str = "b";
        } else if (g >= 75) {
            str = "k";
        } else if (g >= 72) {
            str = "h";
        } else if (g >= 69) {
            str = "a";
        } else if (g >= 66) {
            str = "o";
        } else if (g >= 63) {
            str = "*";
        } else if (g >= 60) {
            str = "#";
        } else if (g >= 57) {
            str = "M";
        } else if (g >= 54) {
            str = "W";
        } else if (g >= 51) {
            str = "&";
        } else if (g >= 48) {
            str = "8";
        } else if (g >= 45) {
            str = "%";
        } else if (g >= 42) {
            str = "B";
        } else if (g >= 39) {
            str = "@";
        }else {
            str = "$";
        }
        return str;
    }

    public void print(String str) {
        try {
            prntwrt.print(str);
            prntwrt.flush();
            filewrt.flush();
        } catch (Exception ex) {
        }
    }

    public static void main(String[] args) {
        System.out.print("Enter the path : ");
        String path = input.nextLine();
        System.out.print("Enter file output name : ");
        fileName = input.nextLine();
        Img2Ascii obj = new Img2Ascii();
        obj.convertToAscii(path);
    }
}