# CipherText v2.4
Cipher and Decipher Text using JavaFX 

Requirements: Any Desktop Operating System which has the support of JVM (Java Virtual Machine) and JDK (Java Development Kit) when editing these files. 

<h1>Requirements and Notes</h1>

The initial purpose of this project was to allow the user to cipher and decipher text.This Application 'CipherText' was created in JavaFX using 'IntelliJ IDEA' therefore when editing these files, you could use 'IntelliJ IDEA' to import the project folder or simply use your faviourte text editor - the java files can be found in the <b>/src/</b> folder. All code is open-source, but a reference to 'Milan Conhye' would be much appreciated. This program does not use the back bone of ciphering and deciphering text, instead uses the "javax.crypto" library to generate keys and securly encrypt and decrypt information. This program also includes a style sheets in order to further customise the program 
All code in this program has been thoroughly commented in order to be understood and further extended. 

It is recommended to install the "Java Cryptography Extension (JCE) unlimited strength jurisdiction policy files" - instructions can be found [here](http://suhothayan.blogspot.co.uk/2012/05/how-to-install-java-cryptography.html). This would allow you to use the stronger security to encrypt and decrypt files. However, if you are unable to install this, an enabler of this extention has been enabled within the main method of this program.

<h2><b>General Knowledge</b></h2>

There are various encryption and decryption algorithms provided in this program. Each alogrithms provides various key strengths in order to correctly cipher the required informaion. The table below introduces the minimum and maximum bit sizes for the key strengths. Although this program limits the bit size, due to the complexity and confusion to the user, the minimum and maximum bit strength, should you have to change the bit sizes, has been organised within the Encryption() method of the program and within this table. 

Algorithm Type                                  | Allowances (bits)
-------------                                   | -------------
AES                                             | 128, 192 or 256 exactly
BLOWFISH                                        | Between 32 - 448 in multiples of 8
DES                                             | 56 exactly
TRIPLEDES                                       | 112 or 168 exactly
RC2/RC4                                         | Between 40 - 1024 exactly
DESEDE                                          | 122 or 168 exactly

<h2>Operation</h2>

<h4>Encrypt Text Tab</h4>

As seen in Figure 1, this 'CipherText' program features Choice boxes in order to choose the algorithm type and key strength, prevailing against any human error. The key strength sizes would depend on the chosen algorithm type and can vary between 56 and 1024 bits. Error prevention techniques are also contained in the encryption process, such as the 'Encrypt Message' text area cannot be null or empty. 

<h5>Encrypt Text Output</h5>

Once the user has successfully 


