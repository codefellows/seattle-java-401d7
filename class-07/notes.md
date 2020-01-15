Restaraunt
  Review
    properties about reviews
    a property of next: that points to the next review

Restaurant
  LinkedList : Reviews

Restaurant
  ArrayList : Reviews

Properties of restaurant
  String name
  int, float, String price 3, 4.2, "$$$"
  float, int num of stars

Properties of Reviews
  body
  author
  stars

public float stars;
Adding a new review
  iterate through the linked list, and resum, then redevide by the total
  iterate through the arraylist, sum, divide by length

public float getStars(){}
On restaurant don't track stars. Only track it when someone asks about stars.
  When someone asks, perform the average method and find the star count

Nicholas' Preferences

- storing reviews: I really like the LinkedList
  - Really fast insertion, O(n) space
  - the drawbacks of the arraylist are space related. Insertion is still not efficient.
  - An arraylist behind the scenes is an array. Its just a really big array with room to grow
  - [ ____ _____ ] track that I have 0 values
  - I can perform up to 10 insertions with O(1) efficiency.
  - when it gets to the end, I double the size of the contained array
- Tracking star count: I really like doing it on creating a new review, storing an instance variable that updates
  - I can see each restaurant getting 100 reviews
  - I can see each restaurant being accessed many times a day, maybe thousands
- Tracking price category: Strings scare me
  - the flavor of float vs int is relative
  - Because british people don't want to see $$$$ they want to see lblblblb
  - I can turn the int into a count of the local symbol either on back end or front end
  - If I have $ vs lb in a string library, they can be referenced by local language type
  - use 4 * StringLibrary.moneySymbol
- How does a Review know which Restaurant it is about
  - Using a linked list to hold Reviews lets you know they belong to the Restaurant
  - There could be a pointer inside of Review towards Restaurant
  - Review review = new Review();
  - review.toString() =>
    - :( Which? restaurant was 5 stars because it was awesome-
    - Olive garden was 5 stars because I paid for 4 dollars of soup and they brought me 20 refills of breadsticks
  - We need to not rely on the user to type in the name of the Restaurant in the body
  - I want to load all the reviews on the website on a new page
    - each one should have a button that takes you to the restaurant

Gradle Stuff

- If my gradle isnt working I can re-sync gradle with the refresh button in the gradle side tab in intelliJ

General notes

- When we call System.out.println() on anything, it by default calls toString()
- other methods use the same logic
- Once you Overrwrite the toString method the old one is gone. The memoryLocation is still property

Linked List Notes

- Inserts
  - Error handling
  - What type of exception did you throw if insert before/after did not contain the target value
    - IllegalArgumentException
    - Why choose IllegalArgumentException
    - java docs seemed to say if the user gives bad input, throw this type of exception
    - not illegalArgument because as long as they ask for a reasonable int, its on ok argument
    - Best one is NoSuchElementException
  - Why Exception
    - Java throws exceptions: all over the place
    - `throw new Exception()`
    - other devs will write code around your library expecting exceptions when things go wrong
    - SO THAT they can handle them, and handle them specifically
  - Edge case: before first, after last, and something doesnt exist
- Daily Systems
  - ENV and the PATH
  - Env lives purely from the terminal
  - we use a file called .env to emulate the env
  - dotenv treats the .env file as your environment
  - export VARNAME=potato sets an environment variable
  - we can reference those vars from our terminal with the syntax $VARNAME
  - echo can be used as the console.log/System.out.println() of the ternimal
  - echo $PATH will print the path to the command line
  - We can reference vars from java using System.getenv("VARNAME")
  - System.getenv("PATH"); will get the path
  - process.env.POTATO
  - has become System.getenv("POTATO");

- Warmup question
  - What is the difference between an interface and a parent class
  - Interface has no constructor
  - you can only have one parent class, you can implement infinite interfaces
  - a parent and interface can both have infinite children
  - keyword for inheritence is different: interface implements, parent class extends
  - Interfaces have no defined variables, and no defined methods, only method signatures
  - parent classes can have both methods and variables that are passed to the child
  - Interfaces are used when we would like for something to have behaviour but we want that class to use custom behaviour "under the hood"
  - Iterable interface: iterable is the interface that means we can iterate over a collection of things
  - Array, String, LinkedList, ArrayList - all extend iterable
  - iterate(){ }
    - some might use a for loop and increment a counter by one
    - How does a linked list iterate
    - store a pointer as CurrentNode, set CurrentNode to CurrentNode.next til we get to the end
    - An interface is used to define generic behavior
    - the entire signature must match

- Interfaces
  - implementing a interface makes the methods required
  @Override is syntax that comes as a helper to devs, it does nothing for java
  - its optional but very helpful

  - Another example would be a Vet that treats Cats, Bobcats, Dinosaurs, and Llamas
  - I don't want conditionals to check if i am looking at a specific one, that would suck
  - so I define an interface TreatableAnimal with a method checkVitals();
  - now I can make Car implement TreatableAnimal
  - and has method checkVitals(){
    check the heartbeat and fang length
  }
  - Dinosaurs implements TreatableAnimal
  - so must have method of checkVitals too(){
    check body temp, scaliness, their height, heartrate
  }
