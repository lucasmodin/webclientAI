package webclient.webclientai.raiderio_dto;

public class MythicPlusScoreDTO {
    private double all;
    private double dps;
    private double healer;
    private double tank;

    public MythicPlusScoreDTO() {}

    public double getAll() {
        return all;
    }

    public void setAll(double all) {
        this.all = all;
    }

    public double getDps() {
        return dps;
    }

    public void setDps(double dps) {
        this.dps = dps;
    }

    public double getHealer() {
        return healer;
    }

    public void setHealer(double healer) {
        this.healer = healer;
    }

    public double getTank() {
        return tank;
    }

    public void setTank(double tank) {
        this.tank = tank;
    }
}
