#Import the required libraries and download the NLTK data
import nltk
import json
import numpy as np
import tensorflow
import keras
from keras.layers import Dense
from keras.models import Sequential
from flask import Flask, request, jsonify
nltk.download('punkt')
#Preprocess the data by tokenizing and stemming the text
from nltk.stem.lancaster import LancasterStemmer
stemmer = LancasterStemmer()
#Load the training data and preprocess it
def preprocess(sentence):
    sentence_words = nltk.word_tokenize(sentence)
    sentence_words = [stemmer.stem(word.lower()) for word in sentence_words]
    return sentence_words
#Load the training data and preprocess it
with open('intents.json') as file:
    data = json.load(file)

training_data = []
output_data = []

for intent in data['intents']:
    for example in intent['examples']:
        training_data.append(preprocess(example))
        output_data.append(intent['intent'])
#Create a bag of words model
words = []
for sentence in training_data:
    words.extend(sentence)

words = sorted(list(set(words)))

output_classes = sorted(list(set(output_data)))

training = []
output = []

for i, sentence in enumerate(training_data):
    bag = [1 if word in sentence else 0 for word in words]
    training.append(bag)
    output_bag = np.zeros(len(output_classes))
    output_bag[output_classes.index(output_data[i])] = 1
    output.append(output_bag)

training = np.array(training)
output = np.array(output)

#Define the neural network model
model = Sequential()
model.add(Dense(8, input_shape=(len(training[0]),), activation='relu'))
model.add(Dense(8, activation='relu'))
model.add(Dense(len(output_classes), activation='softmax'))
#Train the model
model.compile(loss='categorical_crossentropy', optimizer='adam', metrics=['accuracy'])
model.fit(training, output, epochs=1000, batch_size=8)
#Create a function to generate responses
def generate_response(sentence):
    sentence_words = preprocess(sentence)
    bag = [1 if word in sentence_words else 0 for word in words]
    prediction = model.predict(np.array([bag]))[0]
    max_index = np.argmax(prediction)
    if prediction[max_index] > 0.7:
        return output_classes[max_index]
    else:
        return 'unknown'
#Create a Flask app to host the chatbot
app = Flask(__name__)

@app.route('/chatbot', methods=['POST'])
def chatbot():
    data = request.get_json()
    message = data['message']
    response = generate_response(message)
    return jsonify({'message': response})

if __name__ == '__main__':
    app.run()
#Create a JSON file to store the intents
{
  "intents": [
    {
      "intent": "greeting",
      "examples": [
        "hi",
        "hello",
        "hey",
        "good morning",
        "how are you?"
      ]
    },
    {
      "intent": "farewell",
      "examples": [
        "bye",
        "goodbye",
        "see you later",
        "take care"
      ]
    },
    {
      "intent": "thanks",
      "examples": [
        "thank you",
        "thanks a lot",
        "appreciate it"
      ]
    },
    {
      "intent": "help",
      "examples": [
        "help me",
        "I need assistance",
        "can you assist?",
        "what can you do?"
      ]
    },
    {
      "intent": "order",
      "examples": [
        "I'd like to order a pizza",
        "Can I get a large pepperoni pizza?",
        "What's on the menu?"
      ]
    }
  ]
}
