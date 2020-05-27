package RouteInformation;

import java.io.IOException;
import java.io.Serializable;

public class LocationFrom implements Serializable {
    private long xx;
    private Float yy; //Поле не может быть null
    private int zz;

    public LocationFrom(long xx, Float yy, int zz) throws MyException, IOException {
        this.xx = xx;
        this.yy = yy;
        if (yy == null) throw new MyException("Поле не может быть null");
        this.zz = zz;
    }

    public double getXx() {
        return xx;
    }

    public Float getYy() {
        return yy;
    }

    public double getZz() {
        return zz;
    }

    public void setX(long xx) {
        this.xx = xx;
    }

    public void setY(Float yy) {
        this.yy = yy;
    }

    public void setZ(int zz) {
        this.zz = zz;
    }


    public String toString() {
        if (yy == null) {
            return null;
        } else {
            return (xx + "," + yy + "," + zz);
        }
    }

    LocationFrom() throws IOException {
    }


    public void ParseFromCSV(Check с) {
        try {
            if (Parse.IsValue(с)) {
                xx = Long.parseLong(с.val);
                if (Parse.IsValue(с)) {
                    yy = Float.parseFloat(с.val);
                    if (Parse.IsValue(с)) {
                        zz = Integer.parseInt(с.val);
                    }
                }
            }
        } catch (RuntimeException e) {
            if (toString() != null) {
                System.out.println("Ошибка, неверный формат введенных данных, коллеция заполнена некорректно");
            }
        }
    }
}

