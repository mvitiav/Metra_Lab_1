package main.andrei;

import java.util.Objects;

public class Method2 {
    public String body;
    public String name;
    public int usedCount =0;
    public String interFace;

    public Method2(String body, String name) {
        this.body = body;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Method2 method2 = (Method2) o;
        return Objects.equals(name, method2.name) &&
                Objects.equals(interFace, method2.interFace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, interFace);
    }
}
