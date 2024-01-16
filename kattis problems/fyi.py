def fyi(n):
    n = input()
    ns = int(str(n)[:3])
    dy = 555
    print(f"Input: {n}, Prefix: {ns}")
    if ns == dy:
        return 1
    else:
        return 0

# Example usage:print(int(input()[:3] == '555'))
print(int(input()[:3] == '555'))
print(int(input()[:3] == '555'))
print(int(input()[:3] == '555'))
print(int(input()[:3] == '555'))

try:
    user_input = input("Enter a seven-digit telephone number: ")
    result = fyi(user_input)
    print(result)

except ValueError:
    print("Invalid input. Please enter a valid seven-digit telephone number.")
