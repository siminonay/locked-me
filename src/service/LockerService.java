package service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LockerService {

    public static String getFilePath() {
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }

    static String filePath = getFilePath();

    public static void menu() {
        if (!filePath.isEmpty()) {
            print("Please Select One Of The Options:");
            print("1- List Files");
            print("2- File Operations Menu");
            print("3- Exit");

            Scanner sc = new Scanner(System.in);
            checkOperation(sc.nextInt());
        } else {
            print("File Path is Empty!Please Enter File Path To Continue:");
            getFilePath();
        }
    }
    public static void checkOperation(Integer options) {
        if (options == 1) {
            listFilesAsc();
            fileListOperations();
        } else if (options == 2) {
            fileOperationsMenu();
        } else if (options == 3) {
            System.exit(0);
        }
    }

    public static void listFilesAsc() {
        File file = new File(filePath);
        File[] listOfFiles = file.listFiles();
        if (listOfFiles != null && listOfFiles.length != 0) {
            print("******FILE LIST******");
            for (File listOfFile : listOfFiles) {
                if (listOfFile.isFile()) {
                    print(listOfFile.getName());
                }
            }
            print("*********************");
        } else {
            print("File List Is Empty!");
            menu();
        }
    }

    public static void fileListOperations() {
        print("Select One Of The Options:");
        print("A - List Files Again");
        print("B - Go Back");

        Scanner sc = new Scanner(System.in);
        switch (sc.next()) {
            case "A":
                listFilesAsc();
                fileListOperations();
                break;
            case "B":
                menu();
                break;
            default:
                fileListOperations();
                break;
        }
    }

    public static void fileOperationsMenu() {
        print("Select One Of The File Operations:");
        print("A - Add File By Name");
        print("B - Delete File By Name");
        print("C - Search File By Name");
        print("D - Go Back");

        Scanner sc = new Scanner(System.in);
        switch (sc.next()) {
            case "A":
                print("Please Enter File Name To Create:");
                Scanner addName = new Scanner(System.in);
                try {
                    addFile(addName.next());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "B":
                print("Please Enter File Name To Delete:");
                Scanner deleteName = new Scanner(System.in);
                try {
                    deleteFile(deleteName.next());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "C":
                print("Please Enter File Name To Search:");
                Scanner searchName = new Scanner(System.in);
                try {
                    searchFile(searchName.next());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "D":
                menu();
                break;
            default:
                fileOperationsMenu();
                break;
        }
    }

    public static void addFile(String fileName) throws IOException {
        File file = new File(filePath + "\\" + fileName);
        if (file.exists()) {
            print("File Already Exists!");
            menu();
        } else {
            new FileWriter(file);
            print("File Successfully Created!");
            menu();
        }
    }

    public static void deleteFile(String fileName) throws IOException {
        File file = new File(filePath + "\\" + fileName);
        if (file.getAbsoluteFile().exists()) {
            file.delete();
            print("File Successfully Deleted!");
            menu();
        } else {
            print("File Not Found To Delete!");
            menu();
        }
    }

    public static void searchFile(String fileName) throws IOException {
        File file = new File(filePath + "\\" + fileName);
        if (file.getAbsoluteFile().exists()) {
            print("File Exists!");
            menu();
        } else {
            print("File Not Found!");
            menu();
        }
    }

    public static void print(String s) {
        System.out.println(s);
    }

}