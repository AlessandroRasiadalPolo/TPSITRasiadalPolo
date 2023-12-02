package Entities;

public class Stats {

    private int ps;
    private int spa;
    private int spd;
    private int def;
    private int att;
    private int spe;

    public Stats()
    {
        ps = 0;
        spa = 0;
        spd = 0;
        def = 0;
        att = 0;
        spe = 0;
    }

    public Stats(int ps, int spa, int spd, int def, int att, int spe) {
        this.ps = ps;
        this.spa = spa;
        this.spd = spd;
        this.def = def;
        this.att = att;
        this.spe = spe;

    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public int getSpa() {
        return spa;
    }

    public void setSpa(int spa) {
        this.spa = spa;
    }

    public int getSpd() {
        return spd;
    }

    public void setSpd(int spd) {
        this.spd = spd;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getAtt() {
        return att;
    }

    public void setAtt(int att) {
        this.att = att;
    }

    public int getSpe() {
        return spe;
    }

    public void setSpe(int spe) {
        this.spe = spe;
    }
}
