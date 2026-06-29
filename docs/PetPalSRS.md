
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
- **Provider (e.g., Teacher/Doctor/Pet Sitter/etc. )** — 1 line goal statement.

<!-- Daniel task (fill out based on what we agreed upon already) -->
**Scope (this semester).**
- <capability 1> 
- <capability 2>
- <capability 3>

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
  _Story:_ As a customer, I want to create a profile for my pet with all of their information, so providers could have an understanding of their needs. 
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

- **US‑4 — Write a review**  
  _Story:_ As a customer, I want to write a review after an appt., so that I can help others in case if they want to book them in the future.
  _Acceptance:_
  ```gherkin
  Scenario: Submitting a review on a provider's page
    Given the customer is logged in and viewing a specific provider's profile
    When  they click "Write a Review", select a star rating, enter their written feedback, and click "Submit"
    Then  the system saves the review and updates the provider's public profile with the new rating
  ```

- **US‑5 — Acess provider details**  
  _Story:_ As a customer, I want access a provider's contact details, so that I can reach out to them directly to schedule an appointment off-platform. 
  _Acceptance:_
  ```gherkin
  Scenario: Accessing contact details to book service
    Given the customer is viewing a specific provider's profile
    When  they click the "Contact Provider" button
    Then  the system displays the provider's business phone number, email, and external website link
  ```


### 2.2 Provider Stories <!-- Daniel task -->
- **US-20 — <short title>**  
  _Story:_ As a provider, I want … so that …  
  _Acceptance:_
  ```gherkin
  Scenario: <happy path>
    Given <preconditions>
    When  <action>
    Then  <observable outcome>
  ```

- **US-21 — <short title>**  
  _Story:_ As a provider, I want … so that …  
  _Acceptance:_
  ```gherkin
  Scenario: <happy path>
    Given <preconditions>
    When  <action>
    Then  <observable outcome>
  ```
---

## 3. Non‑Functional Requirements (make them measurable) 
- **Performance:** Customers should expect <400ms when filtering through providers, and <300 ms when searching for providers,   <!-- Emmanuel task -->
- **Availability/Reliability:** description <!-- Daniel task -->
- **Security/Privacy:** Customers can use Gmail to log in to ensure secuirty <!-- Emmanuel task -->
- **Usability:** description <!-- Daniel task -->

---

## 4. Assumptions, Constraints, and Policies
<!-- Daniel/Emmanuel add as you see fit after discussing  -->
- list any rules, policies, assumptions, etc.

---

## 5. Milestones (course‑aligned) <!-- we dont change these -->
- **M1 Requirements** — this file + stories opened as issues. 
- **M2 High‑fidelity prototype** — core customer/provider flows fully interactive. 
- **M3 Design** — architecture, schema, API outline. 
- **M4 Backend API** — key endpoints + tests. 
- **M5 Increment** — ≥2 use cases end‑to‑end. 
- **M6 Final** — complete system & documentation. 

---

## 6. Change Management <!-- we dont change these -->
- Stories are living artifacts; changes are tracked via repository issues and linked pull requests.  
- Major changes should update this SRS.