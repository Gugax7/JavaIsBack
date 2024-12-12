public enum Toppings {
    CHEDDAR,
    BACON,
    SALAD,
    MUSTARD,
    KETCHUP,
    PICKLES,
    CHEESE,
    TOMATO;

    public Double getPrice(){
        return switch (this){
            case BACON -> 1.0;
            case SALAD -> 0.5;
            case MUSTARD -> 0.3;
            default -> 0.0;
        };
    }
}
