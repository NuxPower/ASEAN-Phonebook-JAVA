import java.util.Scanner;

public class Main
{

    private static final String[][] MENUS = { {
            // Main Menu
            "Store to ASEAN phonebook", "Edit entry in ASEAN phonebook", "Delete entry from ASEAN phonebook", "View/search ASEAN phonebook", "Exit" },
            {
                    // Edit Contact Menu
                    "Student Number", "Surname", "Gender", "Occupation", "Country Code",
                    "Area Code", "Phone Number", "None - Go back to Main Menu" },
            {
                    // Menu for View Phonebook
                    "Search by country", "Search by ID",
                    "Go back to Main Menu" },
            {
                    //Menu for Country
                    "Burma", "Cambodia", "Thailand", "Vietnam", "Malaysia", "Philippines",
                    "Indonesia", "Timor Leste", "Laos", "Brunei", "Singapore", 
                    "All", "No more"}};
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args)
    {
        Phonebook pb = new Phonebook();
        boolean exit = false;
        while (true)
        {
            showMenu(1, 1);
            // System.out.print("Select an option: ");
            // int opt = input.nextInt();
            int opt;
            try {
                opt = Integer.parseInt(prompt("Select an option: "));
            } catch (Exception e) {
                opt = 0;
            }
            switch (opt)
            {
                case 1:
                    pb.insert(createNewPerson());
                    while (true) {
                        String again = prompt("Do you want to enter another entry [Y/N]?");
                        if (again.equals("Y") || again.equals("y")) {
                            pb.insert(createNewPerson());
                            continue;
                        } else if (again.equals("N") || again.equals("n")) {
                            break;
                        }
                    }
                    break;
                case 2:
                    String contactID = prompt("\nEnter contact ID: ");
                    Person contact = pb.getContact(contactID);
                    pb.deleteContact(contactID);

                    while(true) {
                        if (contact != null) {
                            System.out.println("Here is the existing information about " + contactID + ":\n" + contact.toString());
                            System.out.println("\nWhich of the information do you wish to change?");
                            showMenu(2, 4);
                            int editOpt = Integer.parseInt(prompt("Enter choice: "));

                            switch (editOpt) {
                                case 1:
                                    String newID = prompt("Enter new ID: ");
                                    contact.setId(newID);
                                    continue;
                                case 2:
                                    String newSurname = prompt("Enter new surname: ");
                                    contact.setLName(newSurname);
                                    continue;
                                case 3:
                                    String newGender = prompt("Enter new gender (M for male, F for female): ");
                                    contact.setSex(newGender);
                                    continue;
                                case 4:
                                    String newOccupation = prompt("Enter new occupation: ");
                                    contact.setOccupation(newOccupation);
                                    continue;
                                case 5:
                                    int newCountryCode = Integer.parseInt(prompt("Enter new country code: "));
                                    contact.setCountryCode(newCountryCode);
                                    continue;
                                case 6:
                                    int newAreaCode = Integer.parseInt(prompt("Enter new area code: "));
                                    contact.setAreaCode(newAreaCode);
                                    continue;
                                case 7:
                                    String newPhoneNumber = prompt("Enter new phone number: ");
                                    contact.setContactNum(newPhoneNumber);
                                    continue;
                                case 8:
                                    pb.insert(contact);
                                    break;
                                default:
                                    System.out.println("Invalid option!");
                                    continue;
                                }
                                break;
                        } else {
                            System.out.println("No contact found with inputted id!");
                            break;
                        }
                    }
                    break;
                case 3:
                    String id = prompt("Enter student number: ");
                    Person p = pb.getContact(id);
                    if (p != null)
                    {
                        String suresure = prompt("Are you sure you want to delete it [Y/N]? ");
                        if (suresure.equalsIgnoreCase("Y")) {
                            Person deletedContact = pb.deleteContact(id);
                            if (deletedContact != null) {
                                System.out.println("Deletion successful");
                            } continue;
                        } else if(suresure.equalsIgnoreCase("N")) {
                            System.out.println("Deletion did not proceed");
                            break;
                        } 
                    }
                    else
                    {
                        System.out.println("This contact does not exist!");
                    }
                    break;
                case 4:
                    while (true)
                    {
                        showMenu(3, 1);
                        int showOpt = Integer.parseInt(prompt("Enter option:"));

                        if (showOpt == 1)
                        {   
                            showMenu(4,5);
			                int[] array = new int[0];	
                            while(true) {
                                int choices = Integer.parseInt(prompt("From which country: "));
                                if (choices != 0 && choices != 12) {
                                    int[] newArray = new int[array.length + 1];
                                    for (int i = 0; i < array.length; i++) {
                                        newArray[i] = array[i];
                                    }
                                    newArray[array.length] = convertChoices(choices);
                                    array = newArray;
                                    continue;
                                }
                                else if (choices == 12) {
                                    System.out.println(pb.toString());
                                    break;
                                } else if (choices == 0) {
                                    System.out.println(pb.printContactsFromCountryCodes(array));
                                    break;
                                }
                            }
                        }
                        else if (showOpt == 2)
                        {
                            String targetId = prompt("Enter id to search: ");
                            Person target = pb.getContact(targetId);
                            if (target != null)
                            {
                                System.out.println(target);
                            }
                            else
                            {
                                System.out.println("No contact exists with that surname!");
                            }
                        }

                        else if (showOpt == 3)
                        {
                            break;
                        }
                    }
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option!");
            }
            if (exit)
                break;
        }
    }

    /**
     * Show menu based on given index. <br>
     * <br>
     * 1 for Main Menu. <br>
     * <br>
     * 2 for Edit Contact Menu. <br>
     * <br>
     * 3 for View Phonebook Menu. <br>
     * <br>
     * 4 for Country Code Menu.
     * 
     * @param menuIdx Index of the menu to be shown.
     * @param inlineTexts Number of menu options to be printed in a single line. Set to 1 if you
     *        want every line to only have one menu option.
     */
    private static void showMenu(int menuIdx, int inlineTexts)
    {
        String[] menu = MENUS[menuIdx - 1];
        int count = 0;
        String space = inlineTexts == 0 ? "" : "%-12s";
        String fmt = "[%d] " + space;
        for (int i = 0; i < menu.length; i++)
        {
            System.out.printf(fmt, i + 1, menu[i]);
            if (inlineTexts != 0)
            {
                count += 1;
            }
            if (count % inlineTexts == 0)
            {
                System.out.print("\n");
            }
        }
    }

    /**
     * Convert choices from the menu into their appropriate country code values.
     * 
     * @return Country code value of the menu choice.
     */
    private static int convertChoices(int choice)
    {
        // Complete this method.

        int countryCode = 0;
        switch (choice) {
            case 1:
                countryCode = 95;
                break;
            case 2:
                countryCode = 855;
                break;
            case 3:
                countryCode = 66;
                break;
            case 4:
                countryCode = 84;
                break;
            case 5:
                countryCode = 60;
                break;
            case 6:
                countryCode = 63;
                break;
            case 7:
                countryCode = 62;
                break;
            case 8:
                countryCode = 670;
                break;
            case 9:
                countryCode = 856;
                break;
            case 10:
                countryCode = 673;
                break;
            case 11:
                countryCode = 65;
                break;
            case 0:
                System.out.println("Exiting...");
                System.exit(0);
            default:
                System.out.println("Invalid choice");
                System.exit(0);
        }
        return countryCode;
    }

    /**
     * Create a new person object using a slightly complicated setup.
     * 
     * @return Newly created person object.
     */
    private static Person createNewPerson()
    {
        String id, fname, lname, sex, occupation, contactNum;
        int countryCode, areaCode;
        id = prompt("Enter Contact ID: ");
        fname = prompt("Enter First Name: ");
        lname = prompt("Enter Last Name: ");
        occupation = prompt("Enter Occupation: ");
        sex = prompt("Enter sex/gender (M for male, F for female): ");
        countryCode = Integer.parseInt(prompt("Enter Country Code: "));
        areaCode = Integer.parseInt(prompt("Enter Area Code: "));
        contactNum = prompt("Enter Contact Number: ");
        return new Person(id, fname, lname, sex, occupation, contactNum, countryCode, areaCode);
    }

    /**
     * Receive prompt and return the inputted value back to the variable or process that requires
     * it. Data type is String. Do not forget to type cast if possible.
     * 
     * @param phrase Phrase to be given to user when requiring input.
     * @return Returns the data needed.
     */
    private static String prompt(String phrase)
    {
        System.out.print(phrase);
        return input.nextLine();
    }
}
