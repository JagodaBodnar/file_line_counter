import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileLineCounter {
    private String baseDir;
    private int totalCounter;

    public static void main(String[] args) throws IOException {
        new FileLineCounter().run();
    }

    private void run() throws IOException{
        System.out.println("Enter file to folder that should be scanned.");
        Scanner scanner = new Scanner(System.in);
        String directoryLocation = scanner.nextLine();

        File dir = new File(directoryLocation);
        if(dir.exists()){
            baseDir = directoryLocation;
            processDirectory(dir);
            System.out.printf("In total there is %s lines", totalCounter);
        }
        else{
            System.out.println("Folder does not exist.");
        }
    }

    private void processDirectory(File dir) throws IOException{
        for(File file : dir.listFiles()){
            if(file.isDirectory()){
                processDirectory(file);
            } else{
                int lines = countLinesForFile(file);
                totalCounter +=lines;
                String relativeName =  file.getAbsolutePath().replace(baseDir, "");
                System.out.printf("%s ---> %d%n", relativeName, lines);
            }
        }
    }
    private int countLinesForFile(File file) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int counter = 0;
        while(reader.readLine() != null){
            counter++;
        }
        return counter;
    }
}
