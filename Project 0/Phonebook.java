public class Phonebook
{
    // Storage of contacts.
    private Person[] contacts;
    // Number of contacts present in the phonebook.
    private int size;

    /**
     * Create a phonebook of size 50.
     */
    public Phonebook()
    {
        contacts = new Person[50];
    }

    /**
     * @return Number of contacts stored in this phonebook.
     */
    public int getSize()
    {
        // Complete this method
         int size = 0;
        for (Person contact : contacts) {
            if (contact != null) {
                size++;
            }
        }
        return size;
        }

    /**
     * Get the contact at index.
     * 
     * @param index Index to get contact.
     * @return Person object from index. Null if index is not valid or out of range.
     */
    public Person getContactAtIndex(int index)
    {
        // Complete this method

        // checks if index is valid and returns contacts
        if (index >= 0 && index < getSize()) {
            return contacts[index];
        } else {
            // Invalid or out of range
            return null;
        }
    }

    /**
     * Get the person object based on a given id.
     * 
     * @param id Target id.
     * @return Person object that has this id. Null if it does not exist.
     */
    public Person getContact(String id)
    {
        // Complete this method

        // loops through contacts and returns the contact with the same id from given parameter
        for (int i = 0; i < size; i++) {
            if (contacts[i].getId().equals(id)) {
                return contacts[i];
            }
        }
        // If the contact with the specified id does not exist, return null
        return null;
    }

    /**
     * Checks if this phonebook has contacts or not.
     * 
     * @return True or False.
     */
    public boolean isEmpty()
    {
        return this.getSize() == 0;
    }

    /**
     * Increase number of contacts present in this phonebook.
     */
    public void incrSize()
    {
        this.size++;
    }

    /**
     * Decrease number of contacts present in this phonebook.
     */
    public void decrSize()
    {
        this.size--;
    }

    /**
     * Increases the size of the phonebook whenever it is full.
     */
    private void increasePhonebookMaxSize()
    {
        // Complete this method

        // Create a new array with increased size
        Person[] newContacts = new Person[contacts.length * 2];
        // Copy existing contacts to the new array
        for (int i = 0; i < size; i++) {
            newContacts[i] = contacts[i];
        }
        // Update the contacts reference to point to the new array
        contacts = newContacts;
    }

    /**
     * Inserts a new person object at its appropriate lexicographic location in the phonebook.
     * 
     * @param p Person to be addded to the Phonebook.
     */
    public void insert(Person p)
    {
        // Complete this method

        // Check if the phonebook is full and increase its size if necessary
        if (size == contacts.length) {
            increasePhonebookMaxSize();
        }
        // Find the appropriate index to insert the new person
        int indexToInsert = findIndexInsertion(p);
        // Shift elements to the right to make space for the new person
        for (int i = size - 1; i >= indexToInsert; i--) {
            contacts[i + 1] = contacts[i];
        }
        // Insert the new person at the appropriate index
        contacts[indexToInsert] = p;
        // Increment the size of the phonebook
        size++;
    }

    /**
     * Searches in what index should this person object with the given be inserted.
     * 
     * @param p Person object to be inserted into the phonebook.
     * @return Appropriate index (position).
     */
    private int findIndexInsertion(Person p)
    {
        // Complete this method
        
        int indexToInsert = 0;
        while (indexToInsert < size && contacts[indexToInsert].compareTo(p) < 0) {
            indexToInsert++;
        }
        return indexToInsert;
    }

    /**
     * Delete a person based on their contact id.
     * 
     * @param id Contact ID of that contact.
     * @return Deleted contact.
     */
    public Person deleteContact(String id)
    {
        // Complete this method...

        // Search for the contact with the specified ID
        int indexToDelete = -1;
        for (int i = 0; i < size; i++) {
            if (contacts[i].getId().equals(id)) {
                indexToDelete = i;
                break;
            }
        }
        // If contact with the specified ID is found
        if (indexToDelete != -1) {
            Person deletedContact = contacts[indexToDelete];
            // Shift elements to the left to remove the deleted contact
            adjustPhonebook(indexToDelete, size, "f");
            // Set the last element to null and decrement the size
            contacts[size - 1] = null;
            size--;
            return deletedContact;
        } else {
            // If contact with the specified ID is not found
            return null;
        }
    }

    /**
     * Adjusts the existing contacts in a phonebook from a given starting index to where it ends,
     * following a particular direction.
     * 
     * @param start Index to start adjustment from.
     * @param end Index to end adjustment into.
     * @param direction Direction in which the adjustment must be made. direction = "f" if element
     *        at index 0 takes the value of the element next to it (e.g. index 1). direction = "b"
     *        if element at index 1 takes the value of the element behind it (e.g. index 0).
     */
    private void adjustPhonebook(int start, int end, String direction)
    {
        // Complete this method...

        // Check the direction of adjustment
    if (direction.equals("f")) {
        // Forward direction: element at index i takes the value of the element next to it
        for (int i = start; i < end; i++) {
            contacts[i] = contacts[i + 1];
        }
    } else if (direction.equals("b")) {
        // Backward direction: element at index i takes the value of the element behind it
        for (int i = end; i > start; i--) {
            contacts[i] = contacts[i - 1];
        }
    }
    }

    /**
     * Uses ellipsis to ambiguously accept as many country codes as possible. <br>
     * <br>
     * For example: <br>
     * <br>
     * If we have: printContactsFromCountryCodes(1, 2, 3) <br>
     * <br>
     * Then we get: countryCodes = { 1, 2, 3 };
     * 
     * @param countryCodes Area codes to be used as a filter.
     * @return Contacts on this phonebook under a particular area code set by the user.
     */
    public String printContactsFromCountryCodes(int... countryCodes) {
        // Complete this method.
    
        String result = "Contacts from the country/countries ";
        int size = countryCodes.length;
        
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                switch (countryCodes[i]) {
                    case 95:
                        result += "Burma";
                        break;
                    case 855:
                        result += "Cambodia";
                        break;
                    case 66:
                        result += "Thailand";
                        break;
                    case 84:
                        result += "Vietnam";
                        break;
                    case 60:
                        result += "Malaysia";
                        break;
                    case 63:
                        result += "Philippines";
                        break;
                    case 62:
                        result += "Indonesia";
                        break;
                    case 670:
                        result += "Timor Leste";
                        break;
                    case 856:
                        result += "Laos";
                        break;
                    case 673:
                        result += "Brunei";
                        break;
                    case 65:
                        result += "Singapore";
                        break;
                    default:
                        result += "Invalid country code";
                        break;
                }
                if (i < size - 1) {
                    result += ", ";
                } else if (i == size - 1 && size > 1) {
                    result += ": ";
                } else if (size == 1) {
                    result += ": ";
                }
            }
        } else {
            result += "\nNo country codes provided";
        }
    
        // Iterate through contacts and filter based on country codes
        if (contacts.length > 0) {
            int contactCount = 0;
            for (Person contact : contacts) {
                if (contact != null) {
                    for (int countryCode : countryCodes) {
                        if (contact.getCountryCode() == countryCode) {
                            result += "\n" + contact.toString();
                            contactCount++;
                            break;
                        }
                    }
                }
            }
            if (contactCount == 0) {
                result += "\nNo contacts found for provided country codes.";
            }
        } else {
            result += "\nNo contacts available.";
        }
    
        // Check if there are only two countries
        if (countryCodes.length == 2) {
            result = result.replace(", ", " and ");
        } else if (countryCodes.length > 2) {
            result = result.replace(", ", ", ");
            int lastCommaIndex = result.lastIndexOf(",");
            if (lastCommaIndex != -1) {
                result = result.substring(0, lastCommaIndex) + ", and" + result.substring(lastCommaIndex + 1);
            }
        }
        return result;
    }
    
    
    /**
     * Print the entire phonebook without any filter or so...
     * 
     * @return The entire list of contacts present in this phonebook.
     */
    public String toString()
    {
        // Complete this method.

        String pbString = "Phonebook Contacts:\n";
        for (int i = 0; i < size; i++) {
            pbString += contacts[i] + "\n";
            }
        return pbString;
        }
    }
}
