#implemented the algortihm used in the book chap 7.3
import random
numstep = 0
def RquickSort(arr, first, last ):
    if first < last:
        # Use rPartition instead of partition
        q = rPartition(arr, first, last )
        RquickSort(arr, first, q - 1, )
        RquickSort(arr, q + 1, last )

def rPartition(arr, first, last, ):
    # Choose a random pivot and swap it with the last element
    #https://www.geeksforgeeks.org/python-program-for-quicksort/#
    rvalue = random.randint(first, last)
    arr[rvalue], arr[last] = arr[last], arr[rvalue]

    return partition(arr, first, last, )
#partitioned array
#used https://www.geeksforgeeks.org/python-program-for-quicksort/#
def partition(arr, first, last, ):
    pivot = arr[last] #the pivot assigned as the last element of arr
    i = first - 1 #highest index of low side
    for j in range(first, last-1): #process each element of arr except pivot
        if arr[j] <= pivot:
            i = i + 1 #incrementing through arr
            arr[i], arr[j] = arr[j], arr[i] #swap
    arr[i + 1], arr[last] = arr[last], arr[i + 1]
    # made numstepa global variable
    #https://stackoverflow.com/questions/45113190/quick-sort-counting
    global numstep
    numstep += 1  # Update the numstep directly 
    return i + 1

# test 1
arr = [3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5]
# number of steps
RquickSort(arr, 0, len(arr) - 1)
print(arr)
print(numstep)





