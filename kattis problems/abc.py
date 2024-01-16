# Read input
x, y, z = map(int, input().split())

# Read desired order
order = input()

# Create a mapping
mapping = {'A': min(x, y, z), 'B': sorted([x, y, z])[1], 'C': max(x, y, z)}

# Sort the numbers based on the mapping
result = [mapping[letter] for letter in order]

# Print the result
print(*result)
