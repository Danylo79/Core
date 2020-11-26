package dev.dankom.util.typeEnum;

import com.google.common.collect.Sets;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TypeEnum implements Iterable<TypeEnumEntry> {
    // Used to convert between IDs and names
    protected Set<TypeEnumEntry> members = Sets.newHashSet();

    /**
     * Registers every declared PacketType field.
     */
    public TypeEnum() {
        registerAll(true);
    }

    public TypeEnum(boolean onlyValid) {
        registerAll(onlyValid);
    }

    /**
     * Registers every public assignable static field as a member.
     */
    @SuppressWarnings("unchecked")
    protected void registerAllValid() {
        try {
            // Register every valid field
            for (Field entry : this.getClass().getFields()) {
                if (Modifier.isStatic(entry.getModifiers()) && TypeEnumEntry.class.isAssignableFrom(entry.getType())) {
                    TypeEnumEntry value = (TypeEnumEntry) entry.get(null);
                    if (value == null) {
                        throw new IllegalArgumentException("Field " + entry.getName() + " was null!");
                    }

                    value.setName(entry.getName());
                    boolean deprecated = entry.getAnnotation(Deprecated.class) == null;
                    value.setDeprecated(deprecated);

                    if (members.contains(value)) {
                        // Replace potentially deprecated values with non-deprecated ones
                        if (deprecated) {
                            members.remove(value);
                            members.add(value);
                        }
                    } else {
                        if (!deprecated) {
                            members.add(value);
                        } else {
                            continue;
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    protected void registerAll() {
        try {
            // Register every field
            for (Field entry : this.getClass().getFields()) {
                TypeEnumEntry value = (TypeEnumEntry) entry.get(null);
                if (value == null) {
                    throw new IllegalArgumentException("Field " + entry.getName() + " was null!");
                }

                value.setName(entry.getName());
                boolean deprecated = entry.getAnnotation(Deprecated.class) == null;
                value.setDeprecated(deprecated);

                if (members.contains(value)) {
                    // Replace potentially deprecated values with non-deprecated ones
                    if (deprecated) {
                        members.remove(value);
                        members.add(value);
                    }
                } else {
                    members.add(value);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected void registerAll(boolean validOnly) {
        if (validOnly) {
            registerAllValid();
        } else {
            registerAll();
        }
    }

    /**
     * Registers a member if its not present.
     * @param instance - member instance.
     * @param name - name of member.
     * @return TRUE if the member was registered, FALSE otherwise.
     */
    public boolean registerMember(TypeEnumEntry instance, String name) {
        instance.setName(name);

        if (!members.contains(instance)) {
            members.add(instance);
            return true;
        }

        return false;
    }

    /**
     * Determines whether or not the given member has been registered to this enum.
     * @param member - the member to check.
     * @return TRUE if the given member has been registered, FALSE otherwise.
     */
    public boolean hasMember(TypeEnumEntry member) {
        return members.contains(member);
    }

    /**
     * Retrieve a member by name,
     * @param name - name of member to retrieve.
     * @return The member, or NULL if not found.
     * @deprecated Don't use this
     */
    @Deprecated
    public TypeEnumEntry valueOf(String name) {
        for (TypeEnumEntry member : members) {
            if (member.getName().equals(name))
                return member;
        }

        return null;
    }

    /**
     * Retrieve every registered member.
     * @return Enumeration of every value.
     */
    public Set<TypeEnumEntry> values() {
        return new HashSet<>(members);
    }

    @Override
    public Iterator<TypeEnumEntry> iterator() {
        return members.iterator();
    }
}
