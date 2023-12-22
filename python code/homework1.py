import itertools
def query_complexity(x): 
    query = 0
    #implementing allegra's algorthim
    for i in x.length():
        #number of queries increase every time we check a variable
        query +=1
        if x[i] == False:
            return false , query
    return query
     
def total_qc(n):
    # Initiating query_complex to 0
    query_complex = 0
    # Generate all possible outcomes
    pos_outcomes = itertools.product([True, False], repeat=n)
    # Create an empty list to hold pos_outcomes
    listoflist = []
    # Iterate through outcomes and convert to lists
    for i in pos_outcomes:
        listoflist.append(list(i))
    # Iterate through the list of lists and calculate query complexity for each
    for i in listoflist:
        query_count = query_complexity(i)
        query_complex += query_count
    return query_complex
 #test run       
n = 3
total_query_complexity = total_qc(n)
print(f"total query complexity n={n}: {total_query_complexity}")


    
def avrg_qc(n):
    #find avrage by dividing amount of queries by amount of outcomes?
    totalq = total_qc(n)
    numofin = 2**n  #fixed the numofin by doing 2 squared n instead of n squared 2
    avrgq = totalq/numofin
    return avrgq
#test run 
n = 3
avrage_query_complexity = avrg_qc(n)
print(f"avrage on query complexity n={n}: {avrage_query_complexity}")

    
        



