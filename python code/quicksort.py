def quickSort(arr, first, last):
    if first < last:
        q = partition(arr, first, last)
        quickSort(arr, first, q - 1)
        quickSort(arr, q + 1, last)

def partition(arr, first, last):
    pivot = arr[last]
    i = first - 1
    for j in range(first, last):
        if arr[j] <= pivot:
            i = i + 1
            arr[i], arr[j] = arr[j], arr[i]
    arr[i + 1], arr[last] = arr[last], arr[i + 1]
    return i + 1

arr = [3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5]
quickSort(arr, 0, len(arr) - 1)
print(arr)


            
            
        
    