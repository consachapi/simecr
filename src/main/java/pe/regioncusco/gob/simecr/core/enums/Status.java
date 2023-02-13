package pe.regioncusco.gob.simecr.core.enums;

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
