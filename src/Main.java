import java.io.*;

import static java.lang.System.out;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.CREATE;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> myArrList = new ArrayList<>();

        boolean done = false;
        String userChoice;
        String trash;
        String arrayAdd = "";
        String arrayInsert = "";
        int indexToMove = 0;
        int arrayIndex = 0;
        boolean userArray = false;
        System.out.println("~File ListMaker 2024~");
        do {
            System.out.println("\n[ A - Add item | D - Delete item | I - Insert item | V - View list | M - Move item | O - Open list file | S - Save current list file | C - Clear elements | Q - Quit ]");
            userChoice = in.nextLine();
            switch (userChoice)
            {
                case "A":
                case "a":
                    addItem();
                    myArrList.add(in.nextLine());
                    break;
                case "D":
                case "d":
                    deleteItem();
                    myArrList.remove(in.nextInt());
                    break;
                case "I":
                case "i":
                    insertItem();
                    myArrList.add(arrayIndex, arrayInsert);
                    break;
                case "V":
                case "v":
                    System.out.println(myArrList);
                    break;
                case "M":
                case "m":
                    moveItem();
                    myArrList.remove(indexToMove);
                    myArrList.add(arrayInsert);

                    break;
                case "O":
                case "o":
                    open();
                    break;
                case "S":
                case "s":
                    saveFile();
                    break;
                case "C":
                case "c":
                    myArrList.clear();
                    System.out.println("Array cleared.");
                    break;
                case "Q":
                case "q":
                    done = SafeInput.getYNConfirm(in,"Are you sure you'd like to quit? Enter Y or N");
                    break;
                default:
                    System.out.println("Your input is invalid. Please provide a valid input.");

            }
        } while(!done);
    }
    public static void addItem()
    {
        Scanner in = new Scanner(System.in);
        String arrayAdd;
        arrayAdd=SafeInput.getNonZeroLength(in, "Enter a string to be added to the array");

    }
    public static void deleteItem()
    {
        Scanner in = new Scanner(System.in);
        int arrayIndex;
        arrayIndex=SafeInput.getInt(in, "Enter the index integer at which you'd like to delete a string");

    }
    public static void insertItem()
    {
        Scanner in = new Scanner(System.in);
        String arrayInsert;
        int arrayIndex;
        arrayInsert=SafeInput.getNonZeroLength(in, "Enter a string to be inserted into the array");
        arrayIndex=SafeInput.getInt(in, "Enter the index integer at which you'd like to insert the string");
        arrayIndex=in.nextInt();
    }
    public static void moveItem()
    {
        Scanner in = new Scanner(System.in);
        String arrayInsert;
        int arrayIndex;
        arrayInsert=SafeInput.getNonZeroLength(in, "Enter a string to be inserted into the array");
        arrayIndex=SafeInput.getInt(in, "Enter the index integer at which you'd like to insert the string");
        arrayIndex=in.nextInt();
    }
    public static void open()
    {
        String rec = "";
        ArrayList<String> myArrList2 = new ArrayList<>();
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("src"));
        int result = chooser.showOpenDialog(chooser);

        try {
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));

                int line = 0;
                int wordCount = 0;
                int charCount = 0;
                while (reader.ready()) {
                    rec = reader.readLine();
                    myArrList2.add(rec);
                    line++;

                    System.out.printf("\nLine %4d %-60s ", line, rec);


                }
                String fields[] = myArrList2.get(0).split("\\W");

                reader.close();
                System.out.println("\n\nNew File Loaded.");
            } else {
                System.out.println("No file was chosen.");
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File could not be located.");
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        catch(IndexOutOfBoundsException e)
        {
            e.printStackTrace();
        }
    }
    public static void saveFile()
    {
        Scanner in = new Scanner(System.in);
        String firstName = "";
        String lastName = "";
        String ID = "";
        String email = "";
        String birthYear = "";
        String filePath = "/Users/ianmarr/Documents/CECH/ComputerProgrammingI/FileInspector.java/src/TestTXT";


        rec(firstName,lastName,ID,email,birthYear,filePath);
    }
    public static void rec(String firstName, String lastName, String ID, String email, String birthYear, String filePath)
    {
        try
        {
            FileWriter writer = new FileWriter(filePath, true);
            BufferedWriter bWrite = new BufferedWriter(writer);
            PrintWriter pWrite = new PrintWriter(bWrite);

            pWrite.println(firstName+","+lastName+","+ID+","+email+","+birthYear);
            pWrite.flush();
            pWrite.close();

            JOptionPane.showMessageDialog(null,"Record Saved.");
        }
        catch (Exception E)
        {
            JOptionPane.showMessageDialog(null,"An error occurred. Your record was not saved.");
        }
    }

}