## OAuth Basics

- Before we dive into the process, let's clarify what these terms mean:

    - Client ID: A unique identifier for your application.
    - Client Secret: A secret key used to verify your application's identity.   

- These credentials are essential for interacting with Google's OAuth 2.0 system and authorizing users to access their Google data.

## Steps to Obtain Credentials
- Create a Google Cloud Project:
  - Go to the Google Cloud Console (https://console.cloud.google.com/).
  - Create a new project or select an existing one.
  - In the left-hand navigation, go to APIs & Services -> OAuth consent screen.   
  - Create a new consent screen.
    - Fill in the required information about your application, such as:
     - App name
     - User support email
     - Developer contact information
     - Scopes (permissions your app needs)
- Create OAuth Client:
    - In the left-hand navigation, go to APIs & Services -> Credentials.
      - Click Create credentials -> OAuth client.
      - Select Web application as the application type.  
      - Enter a name for your client.
      - In the Authorized redirect URIs field, add the redirect URI(s) your application will use. This is typically something like http://localhost:8080/oauth2/code/google for development.
      - Click Create.
    - Obtain Client ID and Client Secret:
      - After creating the OAuth client, you'll see the Client ID and Client Secret displayed. Keep these safe!