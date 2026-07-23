**Project Name: PetPal**   
**Version: Final** 
**Date:July, 23rd, 2026**  
**Purpose: Test plan for PetPal** 

## Actors
- Provider P:
- Customer C: Pet owners lookiing for petcare services.
- Service S: 

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


#### 6. Provider: 
1.
2.

#### 7. Provider: 
1.
2.

## CROSS-CUTTING TEST SCENARIOS (Non-Functional Requirements)

### Performance Requirements

**Scenario P1: Provider directory response time < 2 seconds**

- **Setup:** Server under typical classroom-demo load with at least 5 providers and 10 services
- **Steps:**
  1. Measure the response time for loading the provider directory.
  2. Apply a service and maximum-rate filter.
  3. Repeat the test 10 times.
- **Expected Outcome:** At least 95% of directory and filter requests complete in 2 seconds or less.

**Scenario P2:**
- **Setup:** 
- **Steps:**
  1. x
  2. y
- **Expected Outcome:** 

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

**Scenario S2:**
- **Setup:** 
- **Steps:**
  1. x
  2. y
- **Expected Outcome:**

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

  
**Scenario U2:**
- **Setup:** 
- **Steps:**
  1. x
  2. y
- **Expected Outcome:** 