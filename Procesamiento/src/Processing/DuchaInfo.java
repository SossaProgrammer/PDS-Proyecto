
package Processing;

import java.util.ArrayList;

public class DuchaInfo {
    public static ArrayList<DuchaInfo> duchas = new ArrayList<>();
    public static int Estrato = 0;
    
    final double GASTO_EST1_SUB=1.94121/1000;
    final double GASTO_EST2_SUB=3.06182/1000;
    final double GASTO_EST3_SUB=4.46515/1000;
    final double GASTO_NOR=5.10303/1000;
    final double GASTO_EST5_NOR=7.65455/1000;
    final double GASTO_EST6_NOR=8.16485/1000;
    
    private String Fecha;
    private double Gasto;
    private int Tiempo;
    private double Costo;
    
    public DuchaInfo(String Fecha, double Gasto, int Tiempo) {
        this.Fecha = Fecha;
        this.Gasto = Gasto;
        this.Tiempo = Tiempo;
        CalcCost();
    }
    public void CalcCost(){
        switch (Estrato) {
            case 1:
                if(Gasto<433){
                    this.Costo = Math.round((Gasto*GASTO_EST1_SUB)*1000);
                }else{
                    double rest=Gasto-433;
                    this.Costo = Math.round(((GASTO_EST1_SUB*433)+(GASTO_NOR*rest))*1000);
                }
                break;
            case 2:
                if(Gasto<433){
                    this.Costo = Math.round((Gasto*GASTO_EST2_SUB)*1000);
                }else{
                    double rest=Gasto-433;
                    this.Costo = Math.round(((GASTO_EST2_SUB*433)+(GASTO_NOR*rest))*1000);
                }
                break;
            case 3:
                if(Gasto<433){
                    this.Costo = Math.round((Gasto*GASTO_EST3_SUB)*1000);
                }else{
                    double rest=Gasto-433;
                    this.Costo = Math.round(((GASTO_EST3_SUB*433)+(GASTO_NOR*rest))*1000);
                }
                break;
            case 4:
                this.Costo = Math.round((Gasto*GASTO_NOR)*1000);
                break;
            case 5:
                this.Costo = Math.round((Gasto*GASTO_EST5_NOR)*1000);
                break;
            case 6:
                this.Costo = Math.round((Gasto*GASTO_EST6_NOR)*1000);
                break;
            default:
                this.Costo = 0;
                break;
        }
    }
    
    public String getFecha() {
        return Fecha;
    }
    
    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public double getGasto() {
        return Gasto;
    }

    public void setGasto(double Gasto) {
        this.Gasto = Gasto;
    }
    
    public String getTiempoFormat() {
        int ss = Tiempo % 60;
        int mm = (Tiempo/60)%60;
        int hh = (int)(Tiempo/60)-mm;
        return String.format("%02d:%02d:%02d", hh,mm,ss);
    }
    
    public int getTiempo() {
        return Tiempo;
    }

    public void setTiempo(int Tiempo) {
        this.Tiempo = Tiempo;
    }
    
    public double getCosto(){
        return this.Costo;
    }
    
    public void setCosto(int Costo) {
        this.Costo = Costo;
    }
}
