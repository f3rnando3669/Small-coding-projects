for i in range (0,101):
    if i % 3 == 0 and i % 5 == 0: 
        print("fizzbuzz", i) #add empty string and add on to it
    elif i % 3 == 0:
        print("fizz", i)
    elif i % 5 == 0:
        print("buzz", i)
    else:
        print(i)
        
        
