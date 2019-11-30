# EntryManagementApp
Entry management App summergeeks innovaccer task for SDE Intern
## CONTRIBUTING
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.
## LANGUAGE USED
```bash
JAVA
XML
FIREBASE (backend)
```
## BASIC IDEA

App is installed in the device of the person who manages entry and exit of the person in the specific premises.
#### DIFFERENT OPERATION ON THE APP
- Make entry of the host
    1. get Name
    2. get Phone
    3. get addressto to be visited
    4.  select the host from list of host present from recycler view.
 - Add Host
    1. get Name
    2. get Designation
    3. get Phone
    4. get OFFICE ID
  - View the visitor Who are in system till time.
      Every detail of all Visitor  in card view with the visitor detail having phone number host of the visitor and arrival timestamp.

  - View  all the log based entry of Visitor with arrival and departure time stamp from system.
  

 ##### App functionality
```bash
Visitor come in system fill it detail and select the host available.

### VISITOR DATA IS ADDED TO THE FIREBASE with Phone no. as Key.

The selected HOST Receive the msg.for arrival of user with NAME  timestamp it come in system AND Phone number of visitor

Current timestamp is the network time with date.

Vistor is added to recycler view of pending section.

On departure from system select the Visitor from list of visitor in pending section and make the departure timestamp on database.

### VISITOR ->  DEPARTURE TIMESTAMP updated in FIREBASE at the key assigned to the user.

The visitor Receive msg after departure timetamp succesfully created with details like
- Name
- Phone
- Arrival Time
- Departure Time
- Address Visited
- Host Name

Ater all the above process completeion entry of the Visitor made on log Fragment to get all details of person visiting premises in future.

Entry of visitor removed from Pending Fragment ---> Entry made in Log section and database status changes
```
***

## WORKING EXAMPLE SCREENSHOT


      
