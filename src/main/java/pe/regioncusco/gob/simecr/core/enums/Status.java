package pe.regioncusco.gob.simecr.commons.enums;

public enum Status {
    ENABLED(true),
    DISABLED(false);

    private final boolean value;

    private Status(boolean status){
        value = status;
    }

    public boolean value(){
        return value;
    }
}
