Paintings Collectors is an application designed for art enthusiasts and collectors to explore, acquire, and manage their collections of paintings. It offers a user-friendly
platform where users can browse through various artworks, view details about each painting and add their own art.

1.Database Requirements:
The Database of the Paintings Collectors application needs to support 3 entities:
User
•	Has an Id – "UUID-String" or Long
•	Has a Username (unique, not null)
o	Username length must be between 3 and 20 characters (inclusive of 3 and 20).
•	Has a Password (not null)
o	Password length must be between 3 and 20 characters (inclusive of 3 and 20).
•	Has an Email (unique, not null)
o	Must contain '@'.
•	Has Paintings (paintings added by user)
o	The paintings contain users paintings. One user may have many paintings and one painting can be created by only one user.	
•	Has Favorite Paintings (favorite paintings selected from other user's paintings)
o	Collection with paintings from other users that the user can add to his favorite paintings.
o	One user may have many paintings in Favorite Paintings and one painting can be Favorite to many users.
o	The user can add to his favorites paintings list a single painting only once!
•	Has Rated Paintings (the pictures from other users that the current user has rated)
o	Collection with paintings from other users that the user can rate.
o	One user may have many paintings in Rated Paintings and one painting can be rated by many users.
o	The user can rate a single painting only once!
Painting
•	Has an Id – "UUID-String" or Long
•	Has a Name (not null)
o	Name length must be between 5 and 40 characters (inclusive of 5 and 40).
•	Has an Author (not null)
o	Author length must be between 5 and 30 characters (inclusive of 5 and 30)
•	Has a Style (not null)
o	A painting has one style and one style can have many paintings.
•	Has an Owner (not null)
o	A painting has one Owner, the User who added it.
•	Has an Image URL (not null)
o	Valid image URL containing no more than 150 characters.
•	Has a Is favorite (not null)
o	Checking if the picture has been added to any User's favorites.
•	Has a Votes (not null)
o	The number of votes for the painting, if someone voted for it.
Style
•	Has an Id – "UUID-String" or Long
•	Has a Style name (unique, not null)
o	an option between (IMPRESSIONISM, ABSTRACT, EXPRESSIONISM, SURREALISM, REALISM)
•	Has a Description (not null)
o	For IMPRESSIONISM - "Impressionism is a painting style most commonly associated with the 19th century where small brush strokes are used to build up a larger picture."
o	For ABSTRACT – "Abstract art does not attempt to represent recognizable subjects in a realistic manner. "
o	For EXPRESSIONISM – "Expressionism is a style of art that doesn't concern itself with realism."
o	For SURREALISM– "Surrealism is characterized by dreamlike, fantastical imagery that often defies logical explanation."
o	For REALISM – "Also known as naturalism, this style of art is considered as 'real art' and has been the dominant style of painting since the Renaissance."

Implement the entities with the correct data types and implement repositories for them.

  
2.	Initialize styles
•	Implement a method that checks (when the app started) if the database does not have styles and 
initialize them.
-	You are free to do it in different ways.
-	
3.	Page Requirements
Index Page (logged out user)
Login Page (logged out user)
Login Page validations   
Register Page (logged out user) 
Register Page validations 
Home Page (without having any paintings when a logged in user) 
Add painting (when a logged in user) 
Add painting validation
 
Home Page (with paintings, when a logged in user)
At the top, above all sections in a banner, the username of the logged in user should be displayed
There is a hint in home.html giving guidance on how to proceed with controller models.
•	Note: The left top section (My Paintings) of the page should display all the current logged user paintings from the database which he added.
•	Note: To the left bottom section (My Favorites) of the page should display the currently logged in user's favorite pictures that can be selected from Other Paintings section.
•	Note: The right top section (Other Paintings) of the page should display all other paintings added by the rest users (except the current logged one).
•	Note: The right bottom section (Most Rated Paintings) of the page should display the two most rated paintings
 
The templates have been given to you in the application skeleton, so make sure you implement the pages correctly. 
NOTE: The templates should look as shown above.
NOTE: The templates do NOT require additional CSS for you to write. Only bootstrap and the given CSS are enough.

4.	Functional Requirements
The Functionality Requirements describe the functionality that the application must support.
The application should provide Guest (not logged in) users with the functionality to log in, register and view the Index page.
The application should provide Users (logged in) with the functionality to log out, add a new painting (Add painting page), view all paintings (Home page) and add a painting from Other Paintings to his Favorite Paintings. The logged in Users can rate the paintings of other users (Other Paintings).
Painting Collectors Application in the navbar should redirect to the appropriate URL depending on that if the user is logged in.
The application should provide functionality for adding paintings with following styles - IMPRESSIONISM, ABSTRACT, EXPRESSIONISM, SURREALISM or REALISM.
The paintings should be separated into four different sections according to their ownership and rate.
At the top, above all sections in a banner, the username of the logged in user should be displayed.
My Paintings section
This section displays added paintings by the currently logged in User. When the User adds a new painting, it should appear here. By the "Remove" button in the My Paintings section, the owner of the painting can remove it from Database if none of the other users has favorited it. Otherwise, the owner of the picture should not be able to delete it.
Other Paintings section
This section shows other paintings from all other users who use the app.
When the user clicks on the "Favorite" button of some painting in Other Paintings section, he adds the painting to his Favorite Paintings. You should not delete this painting from DB. The painting should be added to the My Favorites collection with paintings of the currently logged-in User. Added painting should appear in the My Favorites section of the User.
Rate the paintings of other users - NOT REQUIRED IT IS A BONUS!
User can rate the paintings of other users by clicking on the "Vote" button. When the User clicks the button, he rates the given painting by adding 1 vote for it. The User can vote for every painting except his own only once and this step cannot be undone.
My Favorites section
In this section, the favorite paintings of currently logged in User are shown.
Removing a Painting from the My Favorites section - NOT REQUIRED IT IS A BONUS!
The painting can be removed from the My Favorites section by clicking the "X" button.
Most Rated Paintings section - NOT REQUIRED IT IS A BONUS!
Of all the paintings uploaded by all users, the two paintings with the most votes should be displayed in this section, sorted in descending order according to the number of votes. If the count of votes of two paintings is equal, order them by the name of the painting ascending. 
The application should store its data in a MySQL database.

5.	Security Requirements
The Security Requirements are mainly access requirements. Configurations about which users can access specific functionalities and pages.
•	Guest (not logged in) users can access the Index page.
•	Guest (not logged in) users can access the Login page.
•	Guest (not logged in) users can access the Register page.
•	Users (logged in) can access the Home page.
•	Users (logged in) can access Add Painting page.
•	Users (logged in) can access Logout functionality.



