
def fizzbuzz_maker(tuples_list):
    def fizzbuzz():
        results = []
        for i in range(1, 101):
            out = ""  # Initialize an empty string for the current output

            for word, number in tuples_list:  #goes trhough every word and number of a tuple
                if i % number == 0:
                    out += word  # Append the word if the number is divisible
            if i % number != 0:
                out = str(i)  # If no words were added, use the number itself
            results.append(out)  # Add the current output to the list
        
        return results  # Return the list of results

    return fizzbuzz #return function
#test case
tuples_list1 = [("Fizz", 10), ("Buzz", 5), ("four", 4)]
fizzbuzz_gen = fizzbuzz_maker(tuples_list1)()

for result in fizzbuzz_gen:
    print(result)

    