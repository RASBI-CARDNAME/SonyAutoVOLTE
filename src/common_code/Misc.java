package common_code;

import javax.swing.*;
import java.io.File;
import java.util.Scanner;

public class Misc {

    //any key
    public static void enter() {
        //instance
        Scanner input = new Scanner(System.in);

        //variable
        String temp = "";

        //code
        System.out.println("(계속하려면 엔터를 눌러주세요.)");
        try {
            temp = input.nextLine();
            temp = "";

        } catch (Exception e) {
            temp = "";
        }
    }

    //clear screen
    public static void cls() {
        for (int i = 0; i <100; i++) {
            System.out.println("");
        }
    }

    //get file PATH
    public static String selectFilePath() {
        JFileChooser file_path = new JFileChooser();

        // open dialog
        int result = file_path.showOpenDialog(null);

        // return if click open button
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file_path.getSelectedFile();
            return selectedFile.getAbsolutePath();
        } else {
            // cancel
            return null;
        }
    }

    //get folder PATH
    public static String selectFolderPath() {
        JFileChooser folder_path = new JFileChooser();

        //set folder path mode
        folder_path.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        // open dialog
        int result = folder_path.showOpenDialog(null);

        // return if click open button
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFolder = folder_path.getSelectedFile();
            return selectedFolder.getAbsolutePath();
        } else {
            // cancel
            return null;
        }
    }
}
