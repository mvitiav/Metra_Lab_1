package main.andrei;

import java.util.Objects;

public class Class2 {
    public String body;
    public String name;

    public Class2(String name) {
        this(name,"");
    }

    public Class2(String name, String body) {
        this.body = body;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Class2 class2 = (Class2) o;
        return name.equals(class2.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}


