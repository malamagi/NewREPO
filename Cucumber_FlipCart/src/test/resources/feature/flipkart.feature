Feature: Flipkart Application Testing



Scenario: Login to Flipkart
Given User is on Flipkart homepage
When User clicks on login text
And User logs in with credentials from excel
And user clicks on request OTP
Then User is redirected to the Flipkart home page
Then user account name is displayed

Scenario: Extract details from Electronics category
When User navigates to Electronics category
Then User selects a product and writes its details to excel

Scenario: Extract details from Fashion category
When User navigates to Fashion category
Then User selects products and writes its details to excel

