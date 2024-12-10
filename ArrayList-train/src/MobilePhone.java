import java.util.ArrayList;

public class MobilePhone {
    private String myNumber;
    private ArrayList<Contacts> myContacts = new ArrayList<>();

    public MobilePhone(String myNumber, ArrayList<Contacts> myContacts) {
        this.myNumber = myNumber;
        this.myContacts = myContacts;
    }

    public boolean addNewContact(Contacts contact){
        if(myContacts.contains(contact)){
            return false;
        }
        myContacts.add(contact);
        return true;
    }
    public boolean updateContact(Contacts old, Contacts latest){
        if(myContacts.contains(old)){
            old = latest;
            return true;
        }
        return false;
    }
    public boolean removeContact(Contacts contact){
        return myContacts.remove(contact);
    }
    public int findContact(Contacts contact){
        return myContacts.indexOf(contact);
    }

    public Contacts queryContact(String contactName){
       for(Contacts c: myContacts){
           if(contactName == c.getName()){
               return c;
           }
       }
       return null;
    }

    public void printContacts(){
        for(int i = 0; i < myContacts.size();i++){
            System.out.println(i+1 + ". " + myContacts.get(i).getName() + " -> " + myContacts.get(i).getNumber());
        }
    }

}
