**Project Name: PetPal**   
**Version: 1.0** 
**Date: July 23, 2026**  
**Purpose: To connect pet owners with pet service providers** 

## Actors
- Provider P: Provider of pet care services
- Customer C: Pet owner in the market for services
- Service S: Pet care activities such as dog walking

## Use Cases
#### 1. Customer: US-1 — Customer Account and Profile Management

1. Customer C1 opens the customer sign-up page and creates an account with valid information.
2. C1 logs in and is redirected to their customer dashboard.
3. C1 opens My Profile and updates their name, email, phone number, or profile image.
4. C1 logs out and is no longer able to access the customer dashboard directly.
5. Using a disposable account, C1 confirms account deletion and the account can no longer be used to log in.

#### 2. Customer: US-2 — Pet Profile Management

1. Customer C1 logs in and selects Add a New Pet.
2. C1 creates pet PT1 with a name, species or breed, age, image, traits, and special-care instructions.
3. PT1 is saved and displayed on C1's dashboard.
4. C1 edits PT1 and confirms that the updated information remains after reloading the dashboard.
5. C1 confirms deletion of PT1 and the pet is removed from the dashboard and database.

#### 3. Customer: US-3 — Browse and Filter Providers

1. Customer C1 logs in and navigates to the provider directory.
2. C1 enters search criteria such as a keyword, location, service type, or maximum rate.
3. C1 views the providers matching the selected criteria.
4. C1 sorts the results by review count, recommendation rate, or starting price.
5. C1 clears the filters and views the complete provider directory again.


#### 4. Customer: US-4 — View providers profile and leave review

1. Customer C1 logs in and opens provider P1's profile.
2. C1 chooses Recommend or Don't Recommend, writes review R1, and submits it.
3. R1 is saved and displayed in P1's review section.
4. If P1 has responded, the single provider response appears below R1 without a reply chain.
5. C1 deletes R1 and the review is removed without affecting reviews written by other customers.


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

**Scenario P1: Provider directory response time < 2 seconds**

- **Setup:** Server under typical classroom-demo load with at least 5 providers and 10 services
- **Steps:**
  1. Measure the response time for loading the provider directory.
  2. Apply a service and maximum-rate filter.
- **Expected Outcome:** At least 95% of directory and filter requests complete in 2 seconds or less.


**Scenario P2: Provider logs in**
- **Setup: Server is online** 
- **Steps:**
  1. User navigates to login page
  2. User logs in as provider
- **Expected Outcome: 95% of requests ≤ 1.5 seconds** 

### Security & Privacy Requirements

**Scenario S1: Customer pages require a valid customer session**
- **Setup:** Customer C1 is logged out.
- **Steps:**
  1. C1 enters a customer dashboard, pet edit, profile, or directory URL directly.
  2. Observe the response.
- **Expected Outcome:**
  - C1 is redirected to the login page.
  - Customer account and pet information are not displayed.
  - Logging out invalidates the existing customer session.

**Scenario S2: Tries to access Provider dashboard without logging in**
- **Setup:  Provider exists with id 1** 
- **Steps:**
  1. Navigate to homepage while logged out
  2. Go to provider dashboard directly through GET request /provider/1/dashboard
- **Expected Outcome: The user is redirected back to the homepage** 
 - User is redirected to login page
 
### Usability Requirements

**Scenario U1: A customer leaves and deletes a review within 2 minutes**

- **Setup:** Customer C1 is logged in and provider P1 is available in the directory.
- **Steps:**
  1. C1 opens P1's profile.
  2. C1 chooses a recommendation, writes review R1, and submits it.
  3. C1 confirms that R1 appears in the Reviews section.
  4. C1 deletes R1.
- **Expected Outcome:**
  - C1 completes the workflow without assistance in 2 minutes or less.
  - Success messages clearly confirm creation and deletion.
  - The page returns to the Reviews section after each action.

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