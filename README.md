# middle_oop

# Here is the application on <a href="https://secret-bastion-97588.herokuapp.com/">heroku</a>

## Used Design Patterns:

In our realization we used Chain of Responsibility when requiring the data from a DataBase.

The checking class is the mid step between request and getting data. It performs the processing and then decides whether to pass the request further down the chain. It contains just a single method for handling requests.

We decided to use this pattern because our program is expected to process different kinds of requests, but the exact types of requests and their sequences are unknown beforehand.




## Usage Examples:

<img width="477" alt="Screenshot 2022-12-03 at 16 33 05" src="https://user-images.githubusercontent.com/92575094/205446125-e7556bf7-1f19-4425-bdfd-859b53a5e183.png">


<img width="516" alt="Screenshot 2022-12-03 at 16 33 36" src="https://user-images.githubusercontent.com/92575094/205446153-da17035c-9c38-4027-9acc-cb8a11fa831d.png">


As can be seen, the aplication is reusable  :)

The UML Diagrams:

<img width="928" alt="Screenshot 2022-12-03 at 16 35 21" src="https://user-images.githubusercontent.com/92575094/205446241-c10a0700-b4c9-441c-b443-a63b7431861d.png">

Use Case Diagram:


<img width="1025" alt="Screenshot 2022-12-03 at 16 36 14" src="https://user-images.githubusercontent.com/92575094/205446275-a4162854-8687-44c5-9df3-2bc688c3a85c.png">


