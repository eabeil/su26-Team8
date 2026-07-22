**Project Name: PetPal**   
**Version: 1.0** 
**Date: July 23, 2026**  
**Purpose: To connect pet owners with pet service providers** 

## Actors
- Provider P: Provider of pet care services
- Customer C: Pet owner in the market for services
- Service S: Pet care activities such as dog walking

## Use Cases
#### U1.  <!--eabiel -->
1.
2. 

#### U2.  <!--eabiel -->
1.
2. 

#### U3. <!--eabiel -->
1.
2.

#### U4. <!--eabiel -->
1.
2.

#### U5. Provider: Create/modify/remove publically viewable profile which includes contact info
1. 
2.

#### U6. Provider: Create a list of pet services with included location, prices, and times available.
1.
2.

#### U7. Provider: Post updates about pet services which can include photos and videos.
1.
2.

#### U8. Provider: Reply to customer reviews.
1.
2.

## CROSS-CUTTING TEST SCENARIOS (Non-Functional Requirements)

### Performance Requirements

**Scenario P1: ** <!--eabiel -->
- **Setup:** 
- **Steps:**
  1.
  2.
- **Expected Outcome:** 95% of requests ≤ 1.5 seconds

**Scenario P2:**
- **Setup:** 
- **Steps:**
  1. x
  2. y
- **Expected Outcome:** 

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

**Scenario U5: Provider: Create/modify/remove publically viewable profile which includes contact info.**
- **Setup:** 
- **Steps:**
  1. x
  2. y
- **Expected Outcome:** 

**Scenario U6: Provider: Create a list of pet services with included location, prices, and times available.**
- **Setup:** 
- **Steps:**
  1. x
  2. y
- **Expected Outcome:** 

**Scenario U7: Provider: Post updates about pet services which can include photos and videos.**
- **Setup:** 
- **Steps:**
  1. x
  2. y
- **Expected Outcome:** 

**Scenario U8: Provider: Reply to customer reviews.**
- **Setup:** 
- **Steps:**
  1. x
  2. y
- **Expected Outcome:** 