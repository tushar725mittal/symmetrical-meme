import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Hashtable;

public class Macro {
    ArrayList<String> mdt = new ArrayList<>();
    ArrayList<String> alatab = new ArrayList<>();
    ArrayList<String> alaac = new ArrayList<>();
    Hashtable<String, MNTable> mnt = new Hashtable<>();

    public void passOne(BufferedReader br) throws Exception {
        Integer mntc = 0;
        Integer mdtc = 0;
        String line;
        boolean isMacro = false;
        BufferedWriter wrO = new BufferedWriter(new FileWriter("output.txt"));
        BufferedWriter wrE = new BufferedWriter(new FileWriter("expansion.txt"));

        while ((line = br.readLine()) != null) {
            if (isMacro) {
                if (line.contains("MEND")) {
                    isMacro = false;
                    mdt.add(mdtc, line);
                    BufferedWriter wrMdt = new BufferedWriter(new FileWriter("pass1mdt.txt"));
                    wrMdt.write("Index  Instruction\n");
                    for (int i = 0; i < mdt.size(); i++) {
                        wrMdt.write(String.format("%-6d %s%n", (i + 1), mdt.get(i)));
                        wrMdt.newLine();
                    }
                    wrMdt.close();
                    break;
                }
                mdt.add(mdtc, line);
                mdtc++;
            } else {
                if (line.contains("MACRO")) {
                    isMacro = true;
                    line = br.readLine();
                    mdt.add(mdtc, line);
                    String[] words = line.split(" ");
                    String macroName = words[0];
                    MNTable mnTable = new MNTable(mntc, macroName, mdtc);
                    mnt.put(macroName, mnTable);
                    for (int i = 1; i < words.length; i++) {
                        alatab.add(words[i].replace(",", ""));
                        alaac.add("?");
                    }
                    mntc++;
                    mdtc++;
                    continue;
                } else {
                    wrO.write(line);
                    wrO.newLine();
                    wrE.write(line);
                    wrE.newLine();
                }
            }
        }
        wrE.close();
        wrO.close();
    }

    public void passTwo(BufferedReader br) throws Exception {
        BufferedWriter wrO = new BufferedWriter(new FileWriter("output.txt", true));
        BufferedWriter wrE = new BufferedWriter(new FileWriter("expansion.txt", true));
        String line;
        while ((line = br.readLine()) != null) {
            String[] words = line.split(" ");
            if (mnt.containsKey(words[0])) {
                wrO.write(line);
                wrO.newLine();
                ArrayList<String> actualArguments = new ArrayList<>();
                for (int i = 1; i < words.length; i++) {
                    actualArguments.add(words[i].replace(",", ""));
                }
                MNTable mnTable = mnt.get(words[0]);
                Integer mdtIndex = mnTable.getMdtIndex();
                String mdtLine = mdt.get(mdtIndex);
                String[] mdtWords = mdtLine.split(" ");
                ArrayList<String> formalArguments = new ArrayList<>();
                for (int i = 1; i < mdtWords.length; i++) {
                    formalArguments.add(mdtWords[i].replace(",", ""));
                }
                for (int i = 0; i < formalArguments.size(); i++) {
                    String formalArgument = formalArguments.get(i);
                    String actualArgument = actualArguments.get(i);
                    Integer index = alatab.indexOf(formalArgument);
                    alaac.set(index, actualArgument);
                }
                for (int i = mdtIndex + 1; i < mdt.size() && !(mdt.get(i)).equals("MEND"); i++) {
                    mdtLine = mdt.get(i);
                    String expansionFileLine = mdtLine;
                    for (int j = 0; j < alatab.size(); j++) {
                        mdtLine = mdtLine.replace(alatab.get(j), "#" + (j + 1));
                        expansionFileLine = expansionFileLine.replace(alatab.get(j), alaac.get(j));
                    }
                    mdt.set(i, mdtLine);
                    wrE.write(expansionFileLine);
                    wrE.newLine();
                }
            } else {
                wrO.write(line);
                wrO.newLine();
                wrE.write(line);
                wrE.newLine();
            }
        }
        wrE.close();
        wrO.close();
        BufferedWriter wrMdt = new BufferedWriter(new FileWriter("pass2mdt.txt"));
        wrMdt.write("Index  Instruction\n");
        for (int i = 0; i < mdt.size(); i++) {
            wrMdt.write(String.format("%-6d %s%n", (i + 1), mdt.get(i)));
            wrMdt.newLine();
        }
        wrMdt.close();
        BufferedWriter wrAlaTab = new BufferedWriter(new FileWriter("alatab.txt"));
        wrAlaTab.write("Index       Formal-Argument       Actual-Argument\n");
        for (int i = 0; i < alatab.size(); i++) {
            wrAlaTab.write(String.format("#%-10d %-21s %-20s%n", (i + 1), alatab.get(i), alaac.get(i)));
            wrAlaTab.newLine();
        }
        wrAlaTab.close();
        BufferedWriter wrMnt = new BufferedWriter(new FileWriter("mnt.txt"));
        wrMnt.write("Index       Macro-Name       MDT-Index\n");
        for (String key : mnt.keySet()) {
            MNTable mnTable = mnt.get(key);
            wrMnt.write(String.format("%-11d %-16s %-10d%n", (mnTable.getMntIndex() + 1), mnTable.getMacroName(),
                    (mnTable.getMdtIndex() + 1)));
            wrMnt.newLine();
        }
        wrMnt.close();
    }

    public static void main(String[] args) throws Exception {
        Macro macro = new Macro();
        BufferedReader br = new BufferedReader(new FileReader("input.asm"));
        macro.passOne(br);
        macro.passTwo(br);
        br.close();
    }
}