public enum Type {
    BASIC("basic"),
    ADMIN("admin");

    private String typeInString;

    Type(String type) {
        typeInString = type;
    }

    /**
     * Converter a String para o equivalente tipo no Enum
     * @param typeInString
     * @return
     */
    public static Type stringToType(String typeInString) {
        for (Type type : Type.values()) {
            if (type.typeInString.equalsIgnoreCase(typeInString)) {
                return type;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return typeInString;
    }
}
