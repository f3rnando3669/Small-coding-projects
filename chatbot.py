import re
import sys
import random
from nltk.tokenize import regexp_tokenize
from nltk.util import ngrams

print("Hello, I'm a chatbot. I'm here to chat with you. What would you like to talk about today?")
greetings = ("hey", "hello", "hi", "yo", "what's up")
greeting_response = ["hey", "what's up?", "hey, how are you doing?"]

# Function to handle greetings first
def greeting(sentence):
    for word in sentence.split():
        if word.lower() in greetings:
            return random.choice(greeting_response)

# Function to read responses from the chat txt file
def read_responses(filename):
    with open(filename, 'r') as file:
        return [line.strip() for line in file]

# Function to generate n-grams 
def generate_ngrams(tokens, n=2):
    return list(ngrams(tokens, n))

# Function to handle basic convos
def response1(input_text):
    responses1 = [
    # most of these involve with reusing some user inpout or using inputs that dont work well
    # with ngram approach
        (r'my name is (.*)', 'Hello %s, how are you today?'),
        (r'what do you think about (.*)', ' %s? Can you explain more about it?'),
        (r"i'm (.*)(good|happy|fine)", "I'm glad you're doing well!"),
        (r"i'm (.*) (bad|horrible)", 'im sorry you are not doing so well'),
        (r'I think that (.*)','why do you think that %s')
        
    ]
    # Apply the search and replace method
    for pattern, replacement in responses1:
        match = re.search(pattern, input_text, re.IGNORECASE)
        if match:
            if '%s' in replacement:
                print(replacement % match.group(1))
            else:
                print(replacement)
            return
    responses2(input_text, read_responses('chatbot.txt'))

# Attempt at a function to handle chatbot responses using n-grams
def responses2(input_text, responses):
    # Tokenize user input
    user_tokens = regexp_tokenize(input_text.lower(), pattern=r'\b\w+\b')
    user_ngrams = generate_ngrams(user_tokens)
    best_match = 0
    best_match_response = None
    
    # Iterate over each response pattern
    for response in responses:
        response_tokens = regexp_tokenize(response.lower(), pattern=r'\b\w+\b')
        response_ngrams = generate_ngrams(response_tokens)
        # Calculate n-grams between user input and response pattern
        score = 0
        for ngram in user_ngrams:
            if ngram in response_ngrams:
                score += 1
        if score > best_match:
            best_match = score
            best_match_response = response

    # Print the best match response
    if best_match_response is not None:
        print(best_match_response)
    else:
        print("Sorry, I didn't understand that.")


# Main conversation loop
while True:
    user_input = input()
    if user_input.lower() == 'bye':
        print("Bye, thanks for chatting with me :).")
        sys.exit()
    else:
        if greeting(user_input) is not None:
            print("Chatbot:", greeting(user_input))
        else:
            print("Chatbot:", end=" ")
            response1(user_input)

#souurces used:
#https://www.analyticsvidhya.com/blog/2021/07/build-a-simple-chatbot-using-python-and-nltk/
#https://www.analyticsvidhya.com/blog/2021/09/what-are-n-grams-and-how-to-implement-them-in-python/
#https://www.geeksforgeeks.org/n-gram-language-modelling-with-nltk/
