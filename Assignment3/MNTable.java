public class MNTable {
    private Integer mntIndex;
    private String macroName;
    private Integer mdtIndex;

    public MNTable(Integer mntIndex, String macroName, Integer mdtIndex) {
        this.mntIndex = mntIndex;
        this.macroName = macroName;
        this.mdtIndex = mdtIndex;
    }

    public Integer getMntIndex() {
        return mntIndex;
    }

    public void setMntIndex(Integer mntIndex) {
        this.mntIndex = mntIndex;
    }

    public String getMacroName() {
        return macroName;
    }

    public void setMacroName(String macroName) {
        this.macroName = macroName;
    }

    public Integer getMdtIndex() {
        return mdtIndex;
    }

    public void setMdtIndex(Integer mdtIndex) {
        this.mdtIndex = mdtIndex;
    }
}
