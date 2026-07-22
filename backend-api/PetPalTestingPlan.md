**Project Name: PetPal**   
**Version: 1.0** 
**Date: July 23, 2026**  
**Purpose: To connect pet owners with pet service providers** 

## Actors
- Provider P: Provider of pet care services
- Customer C: Pet owner in the market for services
- Service S: Pet care activities such as dog walking

## Use Cases
#### U1.  <!--eabiel-->
1.
2. 

#### U2.  <!--eabiel-->
1.
2. 

#### U3. <!--eabiel-->
1.
2.

#### U4. <!--eabiel-->
1.
2.

#### U5. Provider: Create publically viewable profile which includes contact info
1. User navigates to provider signup page.
2. User fills in information to create a provider account, including an email which is not already taken.
3. User creates the account and is redirected to the provider dashboard page.
4. User edits the provider details in the edit provider details page.
5. User deletes the provider account and is logged out.

#### U6. Provider: Create a list of pet services with included location, prices, and times available.
1. User logs in as a provider.
2. User goes to edit services page.
3. User adds a new service with a location, price, and time.

#### U7. Provider: Post updates about pet services which can include photos and videos.
1. User logs in as a provider.
2. User posts an update to dashboard.

#### U8. Provider: Reply to customer reviews.
1. User logs in as a provider.
2. User navigates to review viewing page.
3. User responds to a customer review.

## CROSS-CUTTING TEST SCENARIOS (Non-Functional Requirements)

### Performance Requirements

**Scenario P1: ** <!--eabiel -->
- **Setup:** 
- **Steps:**
  1.
  2.
- **Expected Outcome:** 95% of requests ≤ 1.5 seconds

**Scenario P2: Provider logs in**
- **Setup: Server is online** 
- **Steps:**
  1. User navigates to login page
  2. User logs in as provider
- **Expected Outcome: 95% of requests ≤ 1.5 seconds** 

### Security & Privacy Requirements

**Scenario S1: Tries to access customer dashboard without logging in**  <!--eabiel -->
- **Setup:  Customer exists with id 1** 
- **Steps:**
  1. Navigate to homepage while logged out
  2. Go to customer dashboard directly through GET request /customer/1/dashboard
- **Expected Outcome: The user is redirected back to the homepage** 

**Scenario S2: **  <!--eabiel -->
- **Setup:  ** 
- **Steps:**
  1. 
  2. 
- **Expected Outcome: ** 

**Scenario S3: **  <!--eabiel -->
- **Setup:  ** 
- **Steps:**
  1. 
  2. 
- **Expected Outcome: ** 

**Scenario S4: Tries to access Provider dashboard without logging in**
- **Setup:  Provider exists with id 1** 
- **Steps:**
  1. Navigate to homepage while logged out
  2. Go to provider dashboard directly through GET request /provider/1/dashboard
- **Expected Outcome: The user is redirected back to the homepage** 

**Scenario S5: Tries to navigate to Customer dashboard while logged in as Provider**
- **Setup:  Provider exists with id 1 and customer exists with id 1** 
- **Steps:**
  1. Login as provider with id 1
  2. Go to customer dashboard directly through GET request /customer/1/dashboard
- **Expected Outcome: The user is redirected back to the homepage and logged out** 

**Scenario S6: Tries to delete Provider while logged out**
- **Setup:  Provider exists with id 1** 
- **Steps:**
  1. Navigate to homepage while logged out
  2. Attempt to delete provider with GET endpoint /provider/1/delete
- **Expected Outcome: The user is redirected back to the homepage and the Provider is not deleted from the database** 

### Usability Requirements

**Scenario U1:**  <!--eabiel -->
- **Setup:** 
- **Steps:**
  1. x
  2. y
- **Expected Outcome:** 

**Scenario U2:**  <!--eabiel -->
- **Setup:** 
- **Steps:**
  1. x
  2. y
- **Expected Outcome:** 

**Scenario U3:**  <!--eabiel -->
- **Setup:** 
- **Steps:**
  1. x
  2. y
- **Expected Outcome:** 

**Scenario U4:**  <!--eabiel -->
- **Setup:** 
- **Steps:**
  1. x
  2. y
- **Expected Outcome:** 

**Scenario U5: Provider: Create publically viewable profile which includes contact info.**
- **Setup: Server is online** 
- **Steps:**
  1. User navigates to Provider signup page
  2. User enters info for Provider with a unique email
  3. User creates Provider account
- **Expected Outcome: A new Provider is added to the database, and the user is redirected to the Provider dashboard page.** 

**Scenario U6: Provider: Create a list of pet services with included location, prices, and times available.**
- **Setup: Server is online** 
- **Steps:**
  1. User logs in as provider
  2. User navigates to edit services page
  3. User enters info about a new services and creates it
- **Expected Outcome: The service is added to the database and the user's page is updated accordingly.** 

**Scenario U7: Provider: Post updates about pet services which can include photos and videos.**
- **Setup: Server is online** 
- **Steps:**
  1. User logs in as Provider.
  2. User navigates to dashboard page.
  2. User fills in update form on dashboard page.
- **Expected Outcome: The update is added to the database and the Provider's page is updated for all users.** 

**Scenario U8: Provider: Reply to customer reviews.**
- **Setup: The server is online and a provider has unresponded  reviews** 
- **Steps:**
  1. User logs in as Provider.
  2. User navigates to their review page.
  3. User fills in response form to respond to a review.
- **Expected Outcome: The review is updated in the database and the review is updated for all users.** 