package dev.lpa;

public enum Generation {
    GEN_Z{
        {
            System.out.println("Only for GenZ");
        }
    },
    MILLENIALS(1900,1999),
    BABY_BOOMERS(1800,1899),
    SILENT_GENERATION(1700,1799),
    GREATEST_GENERATION(1600,1699);

    private final int beginDate;
    private final int finalDate;

    Generation(int beginDate, int finalDate){
        this.beginDate = beginDate;
        this.finalDate = finalDate;
        System.out.println(this);
    }
    Generation(){
        this.beginDate = 10;
        this.finalDate = 20;
    }
}
