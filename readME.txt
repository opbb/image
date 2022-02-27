Tom and Oliver's Image Processor :

This was a project for our Object Oriented Design class in which we created a basic image processor from scratch. This project was completed in 4 parts over the course of a month, with each part having us update our IME with new features and functionality. We used the Model-View-Controller design pattern for this project to make maintaining our code and adding new features more streamlined. 

Our image processor can import, edit, and export images in the BPM, PPM, PNG and JPG formats. It can store multiple images at a time, allowing you to swap between them and make modifications to them individually before exporting. The processor can modify the images through basic filters such as grayscaling, extracting a specific color from the image, or brightening/darkening the image. It can also flip the image, as well as apply more advanced filters, such as sepia, sharpen and blur. 

This repository contains the code for the first 3 parts of the assignment. For the 4th part of this assignment, we were given the code from another group and asked to implement a feature to turn the image into a mosaic with a user-specified number of tiles. This code can be found in the "customerIME" repository.

====================================================================================

Note about sample image "when-you-walking": I was given written permission to use this image by both its creator and subject.
Note about new sample image "ella": I took this photograph, and I am giving myself permission to use it. Also, I asked the subject to wag twice for yes, and she did.

====================================================================================

Controller:
For the Controller we used the command design pattern, with ICommands carrying out all of the actual image manipulation. We also used the Interaction class pattern for testing.

ICommand: An interface representing a command that can be used by the controller.
AbstractCommand: An abstract class which allows all ICommands to inherit some shared functionality.

RedValueCommand: Command which gets the red component of a specific Image in the given model.
BlueValueCommand: Command which gets the blue component of a specific Image in the given model.
GreenValueCommand: Command which gets the green component of a specific Image in the given model.
LumaValueCommand : Command which will greyscale the associated image by its pixels luma component.
IntensityValueCommand: Command which will greyscale the associated image by its pixels' intensity component.
ValueCommand: Command which will greyscale the associated image by its pixels' value component.
BrightenCommand: Command which brightens a specific Image in the given model by a given amount.
HoriFlipCommand: Command which will horizontally flip the image.
VertFlipCommand: Command which will vertically flip the image.
LoadCommand: Command which will load a PPM file from the disk into the model.
CloseCommand: Command which will remove an image from the model.
SaveCommand: Command which will save an image in the model as a PPM onto the disk.
BlurCommand: Command which will blur a given image.
SharpenCommand: Command which will sharpen a given image.
SepiaCommand: Command that will apply the sepia tone to a given image.
InputFromFileCommand: Command that will create a readable object of the file with commands to the controller.

IGUICommand: contains two public facing methods and one static utility method, the first, execute simply does the action associated with the command from the given parameters, and the command text is used as its actionCommand.
AbstractGUICommand: Abstract command class in which each GUICommand has a method protected that is used for when the user wants to brighten an image and is prompted asking for a number (double).
RedValueGUICommand: Displays the red component of the displayed image.
BlueValueGUICommand: Displays the blue component of the displayed image.
GreenValueGUICommand: Displays the green component of the displayed image.
LumaValueGUICommand : Command which will greyscale the displayed image by its pixels luma component.
IntensityValueGUICommand: Command which will greyscale the displayed image by its pixels' intensity component.
ValueGUICommand: Command which will greyscale the displayed image by its pixels' value component.
BrightenGUICommand: Command which brightens the displayed Image by a given prompted amount (number) but is double.
HoriFlipGUICommand: Command which will horizontally flip the displayed image.
VertFlipGUICommand: Command which will vertically flip the displayed image.
BlurGUICommand: Command which will blur the displayed image.
SharpenGUICommand: Command which will sharpen the displayed image.
SepiaGUICommand: Command that will apply the sepia tone to the displayed image.

IMEGUIController: A interface used to abide to SOLID principles in which we use this new interface to improve readability within methods, constructors, and tests.
IMEGUIControllerImpl: This class is the main actionlistener, listselectionlistener, and itemlistener for our GUI's buttons and associated actions. ActionListener for the buttons and ItemListener for the choosing multiple loaded images.



IMEController: An interface defining the public methods of the controller.
IMEControllerImpl: The main controller class with all of the implementation.

IMEFileController: The secondary controller class, which offers support in reading commands from a .txt file.

InputUtils: An interface with some static utility methods used reusing code for handling exceptions related to inputs.

Interaction: An interface defining the Interaction type, which is used to streamline testing the controller.
InputInteraction: This class simulates user input for testing.
PrintInteraction: This class simulates printing output for testing.

====================================================================================

Model:
The model is responsible for managing any images given to it, and manipulating them as it is told to. We also have a utility class, ImageUtil and an interface of static methods, Application.

Image: The image contains the 3d array of pixels of the given ppm file, along with knowing the height and width of the image.
ImageImpl: This class acts merely as an object of the ppm file, allowing us to manipulate its pixels' values with ease through the Model.
(new): Two new constructors were added, one simply being a default constructor used for testing purposes, and the other in allowing for the reading and writing of other image formats (png, jpeg, bmp).

ImageModelImpl: This is the implemented class of the ImageModel interface and has multiple methods that deal with manipulating the given image within its map of images.
ImageUtil: With the given starter code, we have manipulated it to our advantage in allowing for info of the PPM file given, such as height, width, and the ability to write a PPM.
Application: This class contains helper static methods designed to actually do the dirty work of manipulating pixels' values of an image. We had decided on making this interface have static methods
just so it has the ability to be reached across other implementations of the model, while still having security through its private helper methods that perform the main manipulation.
(new)We have changed the type of this object to a class as informed by Prof. Shesh that there is no difference between having it be a class or an interface, so to keep with formality, we decided to make it a class much like ImageUtil.
Added methods are a new version of the previous applyMultipliedEffects, in which matrix manipulation is supported in multiplying the desired effects of either blur or sharpen to an image through its kernels.
Along with a reimplemented multiplyEffect, while still retaining the original as its purpose was still needed within our revamped applyTransformation method. Also, we now have clampPixel,
which allows for the clamping of an entire kernel rather than one pixel at a time.

(new)
ExtraFilters: This is a new interface, through the use of composition, that extends the ImageModel Interface, in adhering to SOLID principles. It contains 3 new methods, which are 2 image filters and 1 image transformation (blur, sharpen, sepia respectively).
ExtraFiltersImpl: Once again through the use of code reuse, in this case composition, we have adhered to SOLID principles through the use of a delegate object of type ImageModel, allowing us to keep old implementation that has been tried and tested, while adding new implementation.
This class also holds the implementation for the three new methods from the ExtraFilters interface, in which matrix manipulation along with the reuse of an original method in the Application class are utilized.
Formats: This class contains static utility methods that help to read, write, get height, and get width of other image formats (png, jpeg, bmp). This utility class also extends the original ImageUtil class, in case for future need of that class' methods.

(new 2.0)
Added a static helper method to the Formats class in which we can make a buffered image out of any given image path.

(new 2.0)
Histogram: Extends the original model interface and has one new public method which receives the histogram data of a given image's pixels.
HistogramImpl: has the one public facing method of getting histogram data, and delegates the rest of the methods to the delegate object when constructed.
====================================================================================

View:
Our view contains the model, and is able to represent it with a toString(). It also renders messages.

IMEView: The interface defining the public methods of the view.
IMEViewImpl: The implementaion of the view. Contain a model it can represent.

IMEGUIView: This interface contains multiple public facing methods used to generate and affect the GUI when actions are done.
IMEGUIViewImpl: This class contains all public facing methods from the IMEGUIView interface, as well as some helper private methods, and a private JPanel class for the histogram.



====================================================================================

Main: This class simply holds the main method to our application, this is the class by which the user should run to start the application,
within the main method, we initialize a map of commands as the values and their
name as the key in a String, declare an image, model, and controller and call the controller's run method.
(new) Added the new commands for this assignment (revamped load and save, blur, sharpen, sepia, inputting commands from file).
Along with the ability to designate between two controllers if given files or command scripts.

ApplicationTest: Is the tester class for the Application interface's static methods.
ImageImplTest: The tester class for the ImageImpl class, testing all of its public facing methods.
ImageModelImplTest: The tester class for the ImageModelImplTest which tests all of its public facing methods.
ImageUtilTest: The tester class for the ImageUtil class, this has no tests within it as the tests would not pass if not given
the same images we would test on our machines.
ExtraFiltersImplTest: A tester class for the ExtraFiltersImpl class, which also uses a mock to show that inputs are given and processed correctly.


Script of commands:

1. First run the main method within the main class of the src/ folder.

2. It will prompt you to input a new command. To load an image type "load filepath name", load being
the command to load an image, filepath being the file path of the image, and name being what you
would like to call this image within the application.

3. It will now show the given image under the loaded images border. Now we can use any command on this image.

4. To brighten the image type "brighten num-to-brigthen-by name new-name". Brighten is the name of the command,
num-to-brighten-by is any double positive or negative by which to alter the pixels of the given image, which is the name.
New name is what this image will be called as from now on, you can either rewrite the same name you have been using
or give it a new name, but remember this new name will be what this image is associated with from now on.

5. To save this newly brightened or darkened image, please type "save name filepath", by which save is the command
to save the image, name is the name you have been addressing this image by, and filepath being the filepath by which you
want this image to be found.

Other info:

You can receive the greyscale of any image in multiple different ways, either by its luma, value,
intensity, red-component, green-component, or blue-component.
You can also vertically-flip an image however many times you want, along with horizontally flipping it

If you no longer wish to modify an image, you can use the close command, simply type "close name" and
this will close the given image, meaning you can no longer modify this image if you use its name.
(This does not save any alterations made, but rather leaves the original image back where/how it was)

If at any moment you would like to be reminded by what commands you can use and how to use them just
type "help" when prompted for a new command.


Other commands:
red-value [image to get red-value of] [new image name]
brighten [amount to brighten] [image to brighten] [new image name]
intensity-value [image to get intensity-value of] [new image name]
luma-value [image to get luma-value of] [new image name]
load [file name] [new image name]
vertical-flip [image to flip] [new image name]
green-value [image to get green-value of] [new image name]
save [image to save] [file name to save as]
horizontal-flip [image to flip] [new image name]
value [image to get value of] [new image name]
close [image to close]
blue-value [image to get blue-value of] [new image name]
sepia [image-to-give-sepia-effect] [name-to-call-image-by]
sharpen [image-to-get-sharpened] [name-to-call-image-by]
blur [image-to-get-blurred] [name-to-call-image-by]
input-from-file [file-path]
q or quit to quit the program


