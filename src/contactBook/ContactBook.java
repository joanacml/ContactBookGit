package contactBook;

import contactBook.Contact;

public class ContactBook {
    static final int DEFAULT_SIZE = 100;

    private int counter;
    private Contact[] contacts;
    private int currentContact;

    public ContactBook() {
        counter = 0;
        contacts = new Contact[DEFAULT_SIZE];
        currentContact = -1;
    }

    //Pre: name != null
    public boolean hasContact(String name) {
        return searchIndex(name) >= 0;
    }

    /**
     * @param number a phone number
     * @return true if number exists, false otherwise
     */
    public boolean hasNumber(int number) {
        return searchIndexNumber(number) >= 0;
    }

    public int getNumberOfContacts() {
        return counter;
    }

    //Pre: name!= null && !hasContact(name)
    public void addContact(String name, int phone, String email) {
        if (counter == contacts.length)
            resize();
        contacts[counter] = new Contact(name, phone, email);
        counter++;
    }

    //Pre: name != null && hasContact(name)
    public void deleteContact(String name) {
        int index = searchIndex(name);
        for (int i = index; i < counter; i++)
            contacts[i] = contacts[i + 1];
        counter--;
    }

    //Pre: name != null && hasContact(name)
    public int getPhone(String name) {
        return contacts[searchIndex(name)].getPhone();
    }

    //Pre: name != null && hasContact(name)
    public String getEmail(String name) {
        return contacts[searchIndex(name)].getEmail();
    }

    //Pre: name != null && hasContact(name)
    public void setPhone(String name, int phone) {
        contacts[searchIndex(name)].setPhone(phone);
    }

    //Pre: name != null && hasContact(name)
    public void setEmail(String name, String email) {
        contacts[searchIndex(name)].setEmail(email);
    }

    /**
     * new
     *
     * @param number a phone number
     * @return the name of the contact given a phone number
     */
    public String getName(int number) {
        return contacts[searchIndexNumber(number)].getName();
    }

    /**
     * @param number searches by phone number
     * @return the index of a contact if it exists, given a phone number
     */
    private int searchIndexNumber(int number) {
        int i = 0;
        int result = -1;
        boolean found = false;
        while (i < counter && !found)
            if (contacts[i].getPhone() == number)
                found = true;
            else
                i++;
        if (found) result = i;
        return result;
    }

    private int searchIndex(String name) {
        int i = 0;
        int result = -1;
        boolean found = false;
        while (i < counter && !found)
            if (contacts[i].getName().equals(name))
                found = true;
            else
                i++;
        if (found) result = i;
        return result;
    }

    private void resize() {
        Contact tmp[] = new Contact[2 * contacts.length];
        for (int i = 0; i < counter; i++)
            tmp[i] = contacts[i];
        contacts = tmp;
    }

    public void initializeIterator() {
        currentContact = 0;
    }

    public boolean hasNext() {
        return (currentContact >= 0) && (currentContact < counter);
    }
    //new

    /**
     * @return true if there are contacts that share phone numbers
     */
    public boolean hasRepeatedPhone() {
        if (getNumberOfContacts() == 0)
            return false;
        for (int i = 0; i < counter; i++) {
            for (int j = i + 1; j < counter; j++) {
                if (contacts[i].getPhone() == contacts[j].getPhone())
                    return true;
            }
        }
        return false;
    }

    //Pre: hasNext()
    public Contact next() {
        return contacts[currentContact++];
    }


}
