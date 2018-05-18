package com.zhenhui.demo.falcon.core.domain;

import java.io.Serializable;
import java.util.Arrays;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EqualsAndHashCode
public class UniqueID implements Serializable {

    private static final long serialVersionUID = -12312335569703L;

    @NonNull
    private Type type;
    @NonNull
    private String value;

    @Override
    public String toString() {
        return String.format("%s-%s", type.name(), value);
    }

    public enum Type {
        IMEI(1, "IMEI");
        public final int code;
        public final String comment;

        Type(int code, String comment) {
            this.code = code;
            this.comment = comment;
        }
    }

    public static UniqueID fromString(String unique) {

        int idx = unique.indexOf("-");
        if (idx > 0 && idx < unique.length()) {
            final String[] components = new String[]{
                unique.substring(0, idx),
                unique.substring(idx + 1, unique.length())
            };

            if (Arrays.stream(components).noneMatch(String::isEmpty)) {
                return new UniqueID(Type.valueOf(components[0]), components[1]);
            }
        }

        throw new IllegalArgumentException(String.format("invalid unique-id string: %s", unique));
    }

}
