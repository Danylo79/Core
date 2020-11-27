package dev.dankom.util.typeEnum;

import com.google.common.collect.ComparisonChain;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class TypeEnumEntry implements Serializable, Cloneable, Comparable<TypeEnumEntry> {

    protected String name;
    protected boolean deprecated;

    @Override
    public int compareTo(@NotNull TypeEnumEntry o) {
        return ComparisonChain.start().
                compare(getName(), o.getName()).
                compare(isDeprecated(), o.isDeprecated()).
                result();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDeprecated() {
        return deprecated;
    }

    public void setDeprecated(boolean deprecated) {
        this.deprecated = deprecated;
    }

    public void deprecate() {
        setDeprecated(true);
    }
}
