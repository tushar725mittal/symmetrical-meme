public class MneumonicTable {
    private String mnuemonic;
    private String opcode;
    private int format;

    public MneumonicTable(String mnuemonic, String opcode, int format) {
        this.mnuemonic = mnuemonic;
        this.opcode = opcode;
        this.format = format;
    }

    public void setMnuemonic(String mnuemonic) {
        this.mnuemonic = mnuemonic;
    }

    public String getMnuemonic() {
        return mnuemonic;
    }

    public void setOpcode(String opcode) {
        this.opcode = opcode;
    }

    public String getOpcode() {
        return opcode;
    }

    public void setFormat(int format) {
        this.format = format;
    }

    public int getFormat() {
        return format;
    }

}
