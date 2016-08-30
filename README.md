#CipherText v2.4
<i>Cipher and Decipher information, created using JavaFX.</i>

With suitable and in good circumstances it is significant that the information you send and receive is protected. Cases such as sending someone your password or personal information - you wouldn't want someone to steal this information and use it against you right? CipherText allows you to encrypt and decrypt information using Symmetric-key algorithms; meaning that a key and the encrypted message is required to view your information. Depending on the encryption type and the key strength, your information wouldn't be worthwhile for hackers to obtain. 

<h2><b>Requirements and Notes</b></h2>

The initial purpose of this project was to allow the user to cipher and decipher information. This Application 'CipherText' was created in JavaFX using 'IntelliJ IDEA', therefore when editing these files, you could use 'IntelliJ IDEA' to import the project folder or simply use your favorite text editor - the java files can be found in <b>/src/</b>. All code is open-source, but a reference to 'Milan Conhye' would be much appreciated. This program does not use the backbone of ciphering and deciphering, instead, uses the "javax.crypto" library to generate keys and securely encrypt and decrypt information. This program also includes a style sheet in order to further customize the program. All code in this program has been thoroughly commented in order to be understood and further extended. 

It is recommended to install the "Java Cryptography Extension (JCE) unlimited strength jurisdiction policy files" - instructions can be found [here](http://suhothayan.blogspot.co.uk/2012/05/how-to-install-java-cryptography.html). This would allow you to use the stronger security to encrypt and decrypt files. However, if you are unable to install this, an enabler of this extension has been provided within the main method of this program.

Basic Requirements: Any Desktop Operating System which has the support of JVM (Java Virtual Machine) and JDK (Java Development Kit) when editing these files.

<h2><b>General Knowledge</b></h2>

There are various encryption and decryption algorithms provided in this program. Each algorithm provides various key strengths in order to correctly cipher the required information. The table below introduces the minimum and maximum bit sizes for the key strengths. Although this program limits the bit size, due to the complexity and confusion to the user, the minimum and maximum bit strength, should you have to change the bit sizes, has been organized within the Encryption() method of the program and of course within this table. 

Algorithm Type                                  | Allowances (bits)
-------------                                   | -------------
AES                                             | 128, 192 or 256 exactly
BLOWFISH                                        | Between 32 - 448 in multiples of 8
DES                                             | 56 exactly
TRIPLEDES                                       | 112 or 168 exactly
RC2/RC4                                         | Between 40 - 1024 exactly
DESEDE                                          | 122 or 168 exactly

<h2>Operation</h2>

<h4>Encrypt Text - Tab</h4>

As seen in Figure 1a, this 'CipherText' program features Choice boxes in order to choose the algorithm type and key strength, prevailing against any human error. The key strength sizes would depend on the chosen algorithm type and can vary between 56 and 1024 bits. An error prevention technique is also contained in the encryption process: 'Encrypt Message' text area cannot be null or empty. 

<i>Figure 1a - Encrypt Text</i>

![Encrypt Text - Tab](/Screenshots/1.png?raw=true "Encrypt Text - Tab")

<h4>Encrypt Text - Output</h4>

Once the user has chosen their algorithm type, key strength, entered the message or text that needs to be encrypted and have pressed the cipher button, they will be promoted a dialog which contains the information: original message, algorithm type, key strength, decryption key and encrypted message. This dialog also contains an 'Export Info' feature, whereby two separate files will be created on the desktop; one of them containing the algorithm type and decryption key, and the other containing the encrypted message. Figure 1b displays the output of the encryption process. 

<i>Figure 1b - Encrypted Text Output</i>

![Encrypt Text - Output](/Screenshots/2.png?raw=true "Encrypt Text - Output")

<h4>Decrypt Text - Tab</h4>

As seen in Figure 2, the decryption process requires the algorithm type which was used to encrypt the information - this is a first step security procedure. The next step is entering the encryption key and encrypted message; if one of these hex keys are incorrect, then the program would display an error. However, if all fields are substantially correct and the decipher button has been pressed, there deciphered message would be displayed on the encrypted message text area. 

<i>Figure 2 - Decrypt Text</i>

![Decrypt Text - Tab](/Screenshots/3.png?raw=true "Decrypt Text - Tab")

<h2>Errors, Bugs and Feedback </h2>

If you come across any of those nasty little things, would like to contribute some ideas towards this project or even if you need some guidance - please do leave a comment and I will try my best to respond as fast as possible. 

<h2>Licence and Agreement</h2>

The software is provided "as is" and the author disclaims all warranties with regard to this software including all implied warranties of merchantability and fitness. This software in no way claims to fully protect the integrity of your information, instead it protects the naked information from the human eye and makes it harder for hackers to decrypt. In no event shall the author be liable for any special, direct, indirect, or consequential damages or any damages whatsoever resulting from loss of use, data or profits, whether in an action of contract, negligence or other tortious action, arising out of or in connection with the use or performance of this software. Please acknowledge and agree to this agreement before downloading and using this software. 

<a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-sa/4.0/88x31.png" /></a><br />This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/">Creative Commons Attribution-ShareAlike 4.0 International License</a>.
