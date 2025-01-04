from flask import Flask, request
import requests
import json

app = Flask(__name__)

@app.route('/alphavantagedata', methods=['POST'])
def alphavantagedata():

    # Print the entire request for debugging
    print("Request:", request.json)

    # Parse JSON data from the request
    data = request.get_json()

    # Get all params
    uniqueid = data.get('uniqueId')
    symbol = data.get('symbol')
    function = data.get('function')
    apikey = data.get('apikey')
    cutoff_year = int(data.get("cutoff", 2020))  # Default to 2020 if not provided
    filters = data.get('filter', [])

    extracted_data = {}

    # The URL for the company facts
    base_url = "https://www.alphavantage.co/query?function=FUNCINPUT&symbol=SYMBOLINPUT&apikey=APIKEYINPUT"
    url = base_url.replace("FUNCINPUT",function)
    url = url.replace("SYMBOLINPUT",symbol)
    url = url.replace("APIKEYINPUT",apikey)

    print("Revised URL:" + url)

    # Adding headers to simulate a browser request
    headers = {
        'User-Agent': 'Developer Demo somasundaramg84.extra@gmail.com'
    }

    # Send GET request with headers
    response = requests.get(url, headers=headers)

    json_response = response.json()

    # Filter out rows with fiscalDateEnding > cutoff year
    for filter_key in filters:
        if filter_key in json_response:
            

            filtered_data = []
            for row in json_response[filter_key]:
                try:
                    year = int(row["fiscalDateEnding"].split("-")[0])
                    
                    if year >= cutoff_year:
                        row['documentRef']=uniqueid
                        filtered_data.append(row)
                        
                except Exception as e:
                    print(f"Error processing row {row}: {e}")

            print(f"Data after filtering: {filtered_data}")
            extracted_data[filter_key] = filtered_data

    extracted_data['id']=uniqueid

    # REST API endpoint
    api_url = "https://windy-planet-193902.uc.r.appspot.com/api/saveReports"

    # POST headers (example with JSON content type)
    headers = {
        "Content-Type": "application/json",
    }

    # Send POST request
    try:
        response = requests.post(api_url, headers=headers, json=extracted_data)
        
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

if __name__ == '__main__':
    app.run(debug=True)
