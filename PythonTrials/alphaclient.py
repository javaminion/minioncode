import requests
import json

url = "http://127.0.0.1:5000/alphavantagedata"
headers = {
    "Content-Type": "application/json"
}
data = {
    "uniqueId": "TESTREF",
    "symbol": "COOP",
    "function": "BALANCE_SHEET",
    "apikey": "UC4U0W5ERDO9048L",
    "cutoff": "2020",
    "filter": ["annualReports", "quarterlyReports"]
}

response = requests.post(url, json=data, headers=headers)

# Print the response from the API
print("Status Code:", response.status_code)
print("Response:", response)

# Check if the response is valid JSON
#if response.status_code == 200:
#        # Parse the JSON response
#        response_json = response.json()
#        print("Response JSON:", json.dumps(response_json, indent=4))

        # Write the JSON response to a file
#        output_file = "response_data.json"
#        with open(output_file, "w") as file:
#            json.dump(response_json, file, indent=4)
#        print(f"Response written to {output_file}")
#else:
#        print("Failed to fetch data. Status Code:", response.status_code)
#        print("Response Text:", response.text)
