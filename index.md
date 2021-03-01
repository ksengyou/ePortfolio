# Hello, and welcome to my ePortfolio.

This ePortfolio will outline my process of converting an older project for a zoo authentication system and the enhancements that follow. 

This will include the following:

- Professional Self-Assessment
- Initial code review with incoming enhancements
- Artifacts and Enhancements:
    - Software Design and Engineering: Update of GUI
    - Algorithm and Data Structures: Authenticating Users Through MD5 With Database
    - Database: Implementation of PostgreSQL database
- Narritive of each enhancement

## Profesional Self-Assessment

This ePortfolio was created as part of my Computer Science capstone at Southern New Hampshire University. 

Prior to this capstone I did not have much experience with any of the elements included with the enhancements. The Computer Science program at Southern New Hampshire University has provided me with the tools and confidence to tackle the enhancements that I have chosen to implement. With this confidence I was happy to take on these new challenges and become acquainted with the new technologies such as implementing a GUI by way of JFrames, authentication of user credentials against MD5 hashes available from my database, and implementing a PostgreSQL database to read from. 

As demonstrated through this ePortfolio, the application was morphed from one that made use of console input/output and credentials saved to a text file to one that is more aesthetically appealing and makes use of a database to more securely store user credentials and information. 

## Initial Code Review and Incoming Enhancements

<iframe width="560" height="315" src="https://www.youtube.com/embed/GF4T9qjylZo" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>

## Artifacts and Enhancements:

#### Software Design and Engineering: Update of GUI

I have created a JFrame that will act as the interface for the user. This will include a header which will display the message “Zoo Authentication System”. The GUI has a status area that will display login status as well as display roles for the user once correctly logged in. The JFrame also includes text areas for username and password as well as buttons for enter and cancel as a part of a panel attached to the JFrame. An example of this can be found below:
 
<img src="GUI.png" alt="hi" class="inline"/>

#### Algorithm and Data Structures: Authenticating Users Through MD5 With Database

The algorithm for referencing the username and password have been implemented through the enter button through the use of an actionListener. Once the actionListener has been called, it will call a method to check the MD5 hash value of the user inputted value against the stored credentials’ hash. There are two methods that are created to generate the hashes, usernameHash() and passwordHash() that take in a string and return the hash value. If the user inputted credentials match the credentials’ hash the statusLabel will be updated to confirm the same. Otherwise, the statusLabel will update to advise that username and/or password do not match. An example of this can be found below:

###### Matching username/password:

<img src="LoginSuccess.png" alt="hi" class="inline"/>

###### Invalid username/password:

<img src="LoginFail.png" alt="hi" class="inline"/>

#### Database: Implementation of PostgreSQL database

The database for my application has been done through PostgreSQL. I have created my table, credentials, and connected the application through JDBC. 

###### The table can be seen below:

<img src="PSQLTable.png" alt="hi" class="inline"/>

###### The application is able to read from the database successfully and check against the user and password hashes to verify the user and print the corresponding role:

<img src="PSQLRead.png" alt="hi" class="inline"/>
