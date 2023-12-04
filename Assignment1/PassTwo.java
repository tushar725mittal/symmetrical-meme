import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class PassTwo {
    ArrayList<String> symtab = new ArrayList<>();
    ArrayList<Integer> symaddr = new ArrayList<>();

    public void extractSymtab() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("sym.txt"));
        String line;
        line = br.readLine();
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\s+");
            symtab.add(parts[1]);
            symaddr.add(Integer.parseInt(parts[2], 16));
        }
    }

    public void generateMC() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("ic.txt"));
        BufferedWriter wr = new BufferedWriter(new FileWriter("mc.txt"));

        String line;
        line = br.readLine();
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\s+");
            if (parts[0] != "") {
                String partOne = parts[0];
                String partTwo = parts[1];
                partTwo = (partTwo.split(",")[1]).replace(")", "");
                if (parts.length == 4) {
                    String partThree = parts[2].replace("(", "").replace(")", "");
                    String partFour = parts[3];
                    partFour = (partFour.split(",")[1]).replace(")", "");
                    // System.out.println(partFour);
                    int addr = symaddr.get(Integer.parseInt(partFour) - 1);
                    wr.write(partOne + " " + partTwo + " " + partThree + " " + addr + "\n");
                } else {
                    String partThree = " ";
                    String partFour = parts[2];
                    partFour = (partFour.split(",")[1]).replace(")", "");
                    wr.write(partOne + " " + partTwo + " " + partThree + " " + partFour + "\n");
                }
            } else {
                wr.write("\n");
            }
        }

        br.close();
        wr.close();
    }

    public static void main(String[] args) throws Exception {
        PassTwo p = new PassTwo();
        p.extractSymtab();
        p.generateMC();
    }
}