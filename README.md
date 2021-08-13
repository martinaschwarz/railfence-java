# railfence-java
Encryption Application, using a Rail Fence Cipher, in Java

* This application takes in a file or URL, processes the strings found and returns them in encrypted form, by placing each character into a matrix in a zig-zag (ie. a rail fence) pattern, and putting them back together row by row. The matrix is defined by the char array generated from the strings found (-> number of columns) and the encryption key, which is also input by the user (-> number of rows). Additionally, an offset value can be passed in, which changes the starting row of the encryption.

* To start the application, run the class Runner.

* A menu will be initialised and options are presented to the user:

  - Option 1 asks for the input of a file or URL
  - Option 2 asks for the input of an encryption key and an offset
  - Option 3 will encrypt the input source, using the key and offset given
  - Option 4 will decrypt the encrypted file
  - Option 5 will display the Rail Fence matrix using the last encrypted line
  - Option 6 will quit the programme
