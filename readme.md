# Sony App Assignment

App is developed using MVVM architecture. Where the flow will be as per below.

View <-> ViewModel -> Repository -> DBManager/NetworkManager 

On the application launch, data from CSV file will be read and it will be inserted
into the Room DB Table. Room DB table will be as per below.

Language Name, Key Name, Value


Now whenever user wants to load language specific data and then specific language name
and key will be passed to DB, and Room DB will provide respective value for the same.

In case room DB will not have any specific value than it will return empty value.For
this case, specific value based on the selected language will be retrived from strings.xml
file.

#Advantage of using approach:

1 - Room DB will give the quick access of each value based on the language. In case of
CSV file we have iterate through all the data for just to find single value which will impact
the performance.

2 - Using this approach , if numbers of data grow up still we won't face any error like
Out of Memory as we are not keeping any data into memory at all. 


#Disadvantage:

1 - On the application launch, we have to insert all the data into the Room DB which
delay app little on Splash screen. But this is one time process only so we won't face
delay every time.


#Improvement:
On the application launch , data insertion work is being done Main thread which can be
transferred to background thread. We can show progress dialog during that time.
