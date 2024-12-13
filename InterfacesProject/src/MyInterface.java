public interface MyInterface extends MySecondInterface {
    double KM_TO_MILES = 0.6032;
    double MILES_TO_KILOMETERS = 1.6203;

    void track();
    void find();
    void myFunction();
}

interface MySecondInterface{

    void anotherFunction();
}
