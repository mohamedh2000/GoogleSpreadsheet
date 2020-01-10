# GoogleSpreadsheet
Implemented features such as saving and opening spreadsheet, parsing through input information and displaying it. Adding in functionality of functions such as SUM, LESS THAN, etc. Allowed to create multiple worksheets and switch in between the information on each different page. 

To Run this: 
1- Go to file and project structure, artifacts and create a new jar file.
    Steps: press plus sign, hover over Jar, and click "from modules with dependencies" 
           Main Class should be BeyondGood 
           Press construct on run after continuing
           Press build and the jar file will appear in the OutPut Folder under Artificats. 
           
2- Move the jar file into a folder/directory of your choice. 

3- Move the InputTest1.txt or create your own test file
   In the Text file make sure it is: ColumnxRow = (Either boolean, number, String, or function)
                                    Ex: A2 = true 
                                        A1 = (SUM A1 A4
                                        ...
                                        
4-cd into the directory 

5- type into terminal: java -jar (jar file name).jar -in (textfilename).txt -provider(or other modes refer to BeyondGood class)

6 - Enjoy! 
                        
