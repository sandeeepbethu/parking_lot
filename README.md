Overview :

This is a sample application to create a parking lot with n spaces and allot 
customers with token numbers of available ones, reclaimed when they leave.   

Allotment is done on nearest slot first approach.

Features :
- Empty & nearest slot allotment
- Fetch car vehicle number & color when queried over registration number
- Fetch all cars details when queried over color
- Free empty slots & make them available for next customer
- Show a FULL board when we have reached max capacity.

Tech Stack :
- Java 8 
- Springboot
- Gradle
- mockito, lombok dependencies

How to run it :

- Clone the repo and download and required bins (this will be automatic if you 
already have springboot for gradle)
- Java 8 or more already installed
- Just click on application run and the command window will open, start 
giving in command either singular or together as a file path
eg : bin/file_inputs.txt (this is already available in root)
     OR
     create_parking_slot 4
     OR
     exit (to terminate the program)
- Have also added drone. docker files so in case the fat jar is deployed 
on to a k8s pod / any cloud platform using Docker, it will run smooth by 
downloading all required dependencies (assuming we have our artifact in binrepo)
- ./gradlew clean build -x test (for building only code)
- ./gradlew clean test (only junits)
- ./gradlew clean build (both code & junits)

Additional Info:
- You cannot create more than 1 active parking slot
- You cannot run any command other than the ones defined in enum (Operations)
- Model vehicle is named as such so that we can use that to expand further 
when parking has non car objects
- Added a profile called local but was not needed so left it blank , even it's 
supporting config file (ApplicationConfiguration) can be used when we expand
this program.
- All test input files are located under src/test/resources (can be used
to add more use cases and test files when we expand)
- Bookkeeper is the main object which holds all info of parking when it is active.
- Did not use autowiring as constructor injection is recommended in service
classes

     



