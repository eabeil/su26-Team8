
# Requirements

**Project Name:** PetPal
**Team:** Emmanuel (customer) and Daniel (provider)
**Course:** CSC 340\
**Version:** 1.0\
**Date:** 2026-07-23

---

## 1. Overview
<!-- Emmanuel task -->
**Vision.** A directory built for pet owners who struggle to find trustworthy, well reviewed pet care. By providing transparent reviews, service listings, and direct contact information for local pet care professionals, it allows owners discover and reach out to the right provider for their pet's unique needs.

**Glossary** Terms used in the project <!-- Not sure what to do here. Maybe we should ask teacher. -->
- **Term 1:** description.
- **Term 2:** description

**Primary Users / Roles.**
<!-- Emmanuel task -->
- **Customer (Pet Owners)** — Creates detailed pet profiles and uses the directory to find, evaluate, and contact local pet care professionals based on reviews and services. 
<!-- Daniel task -->
- **Provider (Pet Service Provider )** — Advertise pet services to pet owners in Greensboro, North Carolina.

<!-- Daniel task (fill out based on what we agreed upon already) -->
**Scope (this semester).**
- Users shall create and view user profiles.
- Customers shall search for pet care professionals.
- Providers shall post updates to a feed viewable by customers.
- Customers shall post reviews on provider profiles.

<!-- Emmanuel task -->
**Out of scope (deferred).**
- <deferred 1> Social network for pet owners
- <deferred 2> Pet adoption service
- <deferred 3> Appointment booking and scheduler system
- <deferred 4> GPS/tracking pet system.


<!-- NOTE: This document is **requirements‑level** and solution‑neutral; design decisions (UI layouts, API endpoints, schemas) are documented separately. -->

---

## 2. Functional Requirements (User Stories)
<!-- NOTE: Write each story as: **As a `<role>`, I want `<capability>`, so that `<benefit>`.** Each story includes at least one **Given/When/Then** scenario. -->

### 2.1 Customer Stories <!-- Emmanuel task -->
- **US‑1 — Creating an account**  
  _Story:_ As a customer, I want to create a personal account, so that I can set up my pet profiles, and leave reviews 
  _Acceptance:_
  ```gherkin
  Scenario: Successfully registering a new account
    Given unregistered user is on the platform's sign-up page
    When  They enter a valid email address, create a password, and click "Create Account"
    Then  the system registers their account, logs them in, and redirects them to the customer dashboard to add their first pet profile
  ```

- **US‑2 — Creating Pet profile**  
  _Story:_ As a customer, I want to create a profile for my pet with all of their information and existing pet profiles, so providers could have an understanding of their needs. 
  _Acceptance:_
  ```gherkin
  Scenario: Successfully creating a new pet profile
    Given the customer is logged into their account and navigates to the "Add Pet" page
    When  they fill out the pet information form with valid details (name, breed, care needs) and click "Save"
    Then  the system saves the pet profile and displays the new profile on the customer's dashboard
  ```

- **US‑3 — <Search and filer through provider>**  
  _Story:_ As a customer, I want to be able to navigate and search for pet care professionals based on previous reviews, prices, and services, so that I can find one that match my needs. 
  _Acceptance:_
  ```gherkin
  Scenario: Filtering providers by specific service and price
    Given the customer is on the provider search directory
    When  they select a specific service (e.g., "Dog Walking"), set a maximum price filter, and click "Search"
    Then  the system displays a list of providers who offer that service within the price range, sorted by review ratings
  ```

- **US‑4 — Access provider profile and Write reviews**  
  _Story:_ As a customer, I want to write a reviews, so that I can leave feedback for the provider.
  _Acceptance:_
  ```gherkin
  Scenario: Submitting a review on a provider's page
    Given the customer is logged in and viewing a specific provider's profile
    When  they click "Write a Review", select a star rating, enter their written feedback, and click "Submit"
    Then  the system saves the review and updates the provider's public profile with the new rating
  ```


  ```


### 2.2 Provider Stories <!-- Daniel task -->
- **US-20 — Provider Account Creation**  
  _Story:_ As a provider, I want create a profile so that I can use PetPal.
  _Acceptance:_ The provider can create an account.
  ```gherkin
  Scenario: The provider successfully creates an account.
    Given Noone is logged in.
    When The provider creates an account.
    Then  A new provider account is added to the system and the provider can sign in.

  - **US-21 — Provider Pet Services**  
  _Story:_ As a provider, I want to create a list of services so I can advertise what I offer.
  _Acceptance:_ The provider can post a list of services to her profile.
  ```gherkin
  Scenario: The provider successfullyl lists services on her profile.
    Given The provider is logged in.
    When The provider creates services on the edt services page.
    Then  The services are added to the provider's profile and are publically viewable.

- **US-22 — Provider Updates**  
  _Story:_ As a provider, I want to create updates so that customers can see what I am doing.
  _Acceptance:_ The provider can create post updates to her profile.
  ```gherkin
  Scenario: The provider successfully creates an account.
    Given The provider is logged in.
    When The provider posts an update to her dashboard.
    Then  The update is viewable by customers who view the profile.

- **US-23 — Provider Review Responses**  
  _Story:_ As a provider, I want to respond to customer reviews so that I can address their concerns.
  _Acceptance:_ The provider can respond to reviews on her profile.
  ```gherkin
  Scenario: The provider responds to a customer review.
    Given The provider is logged in.
    When The provider writes a response to a review on her profile.
    Then  Her response is viewable by all customers.

## 3. Non‑Functional Requirements (make them measurable) 
- **Performance:** 
  * Searching provider directory as a customer should take less than 2 seconds.
  * Logging in as a provider should take less than 1.5 seconds.
- **Security/Privacy**
  * Customer pages require a valid customer session
  * Cannot access provider account dashboard without logging in.
- **Availability/Reliability:** The website needs to be avaliable online and needs to remain online as much as possible. It should work well in browsers with different screen sizes.
- **Usability:** The site should have an understandable user interface.

## 4. Milestones (course‑aligned) <!-- we dont change these -->
- **M1 Requirements** — this file + stories opened as issues. 
- **M2 High‑fidelity prototype** — core customer/provider flows fully interactive. 
- **M3 Design** — architecture, schema, API outline. 
- **M4 Backend API** — key endpoints + tests. 
- **M5 Increment** — ≥2 use cases end‑to‑end. 
- **M6 Final** — complete system & documentation. 

---

## 5. Change Management <!-- we dont change these -->
- Stories are living artifacts; changes are tracked via repository issues and linked pull requests.  
- Major changes should update this SRS.