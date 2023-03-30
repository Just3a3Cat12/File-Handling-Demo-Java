import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class FileHandling {
    public static void main(String[] args) {
        System.out.println("Enter directory to work with or type \"0\" to work with current directory.\n" +
                "You can give absolute path also.\n" +
                "If directory is not present at given path it will create new one.");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine().replaceAll("[\\\\/]", "\\\\\\\\");
        String actPath = null;
//        Creating/ Locating directory
        try {
            if (!path.equals("0")) {
                new File(path).mkdirs();
                File file = new File(path);
                actPath = file.getAbsolutePath() + "\\\\";
            } else {
                File file = new File(System.getProperty("user.dir"));
                actPath = file.getAbsolutePath() + "\\\\";
            }
            System.out.println("Path is set to : " + actPath);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        boolean shutdown = true;
        do {
            try {//Printing main menu
                File file = new File(actPath);
                System.out.println("""
                    Choose option :
                    1. Create a new file
                    2. Delete a file
                    3. List the files
                    4. Search the file
                    5. Exit""");

                int option = scanner.nextInt();
                scanner.nextLine();
                String[] fileList = file.list();
                switch (option) {
                    case 1 -> {
                        do {//creating new file
                            System.out.println("Enter File name to create : ");
                            String newFileName = scanner.nextLine();
                            File file1 = new File(actPath + newFileName);
                            if (file1.exists()) {
                                System.out.println("File already exists");
                            } else {
                                file1.createNewFile();
                                System.out.println("File created : " + file1.exists());
                            }
                        } while (msg() != 2);
                    }
                    case 2 -> {//Deleting desired file
                        do {
                            System.out.println("Enter file name to delete : ");
                            String fileDelete = scanner.nextLine();
                            File file1 = new File(fileDelete);
                            if (!file1.exists()) {
                                System.out.println("File not found in directory");
                            } else {
                                file1.delete();
                                System.out.println("File is deleted : " + !file1.exists());
                            }
                        } while (msg() != 2);
                    }
                    case 3 -> {//File listing
                        System.out.println("List of files in ascending order : ");
                        if (fileList.length == 0) {
                            System.out.println("Directory is empty");
                            break;
                        }
                        Arrays.sort(fileList);
                        for (String s : fileList) {
                            System.out.println(s);
                        }
                    }
                    case 4 -> {//searching desired file
                        do {
                            System.out.println("Enter file name to search : ");
                            String searchName = scanner.nextLine();
                            if (fileList.length == 0) {
                                break;
                            }
                            for (String s : fileList) {
                                if (s.matches(searchName)) {
                                    System.out.println("File named \"" + searchName + "\" exists in a directory");
                                } else {
                                    System.out.println("File does not exists");
                                }
                            }
                        } while (msg() != 2);
                    }
                    case 5 -> shutdown = false;
                    default -> System.out.println("Invalid input\n");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println();
        } while (shutdown);
    }

    public static int msg() {
        do {//Printing sub-menu
            System.out.println("""
                                                        
                    Options :
                    1. repeat the operation
                    2. Go back to main menu""");
            Scanner scanner = new Scanner(System.in);
            int opt = scanner.nextInt();
            if (opt == 1 || opt == 2) {
                return opt;
            } else {
                System.out.println("Invalid option");
            }
        } while (true);
    }
}
