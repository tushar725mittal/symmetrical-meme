public class MnemonicTable {
    private String mnemonic;
    private String opcode;
    private int format;

    public MnemonicTable(String mnemonic, String opcode, int format) {
        this.mnemonic = mnemonic;
        this.opcode = opcode;
        this.format = format;
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    public String getOpcode() {
        return opcode;
    }

    public void setOpcode(String opcode) {
        this.opcode = opcode;
    }

    public int getFormat() {
        return format;
    }

    public void setFormat(int format) {
        this.format = format;
    }

    @Override
    public String toString() {
        return "MnemonicTable{" +
                "mnemonic='" + mnemonic + '\'' +
                ", opcode='" + opcode + '\'' +
                ", format=" + format +
                '}';
    }
}
