from flask import Flask, request
import requests
import json

app = Flask(__name__)

@app.route('/secedgarcompanyfacts', methods=['POST'])
def secedgarcompanyfacts(request):

    # Print the entire request for debugging
    print("Request:", request.json)

    # Parse JSON data from the request
    data = request.get_json()

    # Get CIK value
    cik = data.get('cik')
    uniqueid = data.get('uniqueId')

    # Get list of topics
    topics = data.get('topics', [])

    # For debugging purposes, let's print the parsed values
    print(f"CIK: {cik}, Topics: {topics}")

    extracted_data = []

    # The URL for the company facts
    base_url = "https://data.sec.gov/api/xbrl/companyfacts/CIKINPUT.json"
    url = base_url.replace("CIKINPUT",cik)

    print("Revised URL:" + url)

    # Adding headers to simulate a browser request
    headers = {
        'User-Agent': 'Developer Demo somasundaramg84.extra@gmail.com'
    }

    # Send GET request with headers
    response = requests.get(url, headers=headers)

    print(response.text)

    # Check if the request was successful
    if response.status_code == 200:
        print("Got response from API")
        data = response.json()
        # Function to extract data with "fy": 2023
        
        for topic in topics:
            if topic in data['facts']['us-gaap']:
                try:
                    for value in data['facts']['us-gaap'][topic]['units']['USD']:
                        value['topic']=topic
                        value['documentRef']=uniqueid
                        extracted_data.append(value)
                except KeyError as e:
                    print(f"Key error for topic '{topic}': {e}")
            else:
                print(f"Topic '{topic}' not found in the data.")
            
    new_json_string = json.dumps(extracted_data, indent=4)

    def validate_json(json_string):
        try:
            json_object = json.loads(json_string)
            return True, json_object
        except ValueError as e:
            return False, str(e)

    # Validate JSON
    is_valid, result = validate_json(new_json_string)

    if is_valid:
        print("The JSON is valid.")
    else:
        print("The JSON is invalid:", result)


    # REST API endpoint
    api_url = "https://windy-planet-193902.uc.r.appspot.com/api/saveFacts"

    # POST headers (example with JSON content type)
    headers = {
        "Content-Type": "application/json",
    }

    # Send POST request
    try:
        response = requests.post(api_url, headers=headers, json=new_json_string)
        
        # Check the response status
        if response.status_code == 200 or response.status_code == 201:
            print("Data successfully posted to the API.")
            return "OK"
            print("Response:", response.json())  # Print the API response if needed
        else:
            print(f"Failed to post data. HTTP Status: {response.status_code}")
            print("Response:", response.text)
    except Exception as e:
        print(f"An error occurred: {e}")
