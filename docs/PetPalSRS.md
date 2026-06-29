
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
- **US-20 — Provider Login**  
  _Story:_ As a provider, I want to login to my account so that I can edit my profile.
  _Acceptance:_ The provider can login using Google account.
  ```gherkin
  Scenario: The provider successfully logs in to her account.
    Given The provider has a google account.
    When The provider logs into PetPal app through login page.
    Then  The provider is logged in and is able to edit her profile.

- **US-21 — Provider Logout**  
  _Story:_ As a provider, I want to logout of my account so that noone else can access it on my computer.
  _Acceptance:_ After the provider clicks logout button, account information is no longer accessible.
  ```gherkin
  Scenario: The provider logs out of her account.
    Given The provider is already logged in.
    When  The provider clicks the logout button (visible on every page once logged in).
    Then  The provider is logged out of her account.

- **US-22 — Provider Profile Editing**  
  _Story:_ As a provider, I want to edit my profile to advertise myself to potential customers.
  _Acceptance:_ The provider can modify profile images, description, contact info, and other information.
  ```gherkin
  Scenario: The provider successfuly edits every feature of her profile.
    Given The provider is logged in to her account.
    When  The provider goes to the profile edit page, modifies her profile, and saves changes.
    Then  The provider's profile is changed based on her edits.
  ```

- **US-23 — Provider Services List**  
  _Story:_ As a provider, I want to edit a list of services so that customers can see what I offer.
  _Acceptance:_ The provider can create and edit a list of services, which includes times avaliable and price information.
  ```gherkin
  Scenario: The provider changes services listed on her profile.
    Given The provider is logged in to her account.
    When  The provider goes to profile edit page and modifies services available.
    Then  The provider's service list is updated based on her edits.

- **US-24 — Provider Update Feed**  
  _Story:_ As a provider, I want to post an update so customers can see what I am doing.
  _Acceptance:_ The provider can post updates which appear in a feed on her profile. The updates can include text, embedded images, and embedded videos.
  ```gherkin
  Scenario: The provider posts an update to her profile.
    Given The provider is logged in to her account.
    When  The provider writes a post and clicks post button on her profile.
    Then  The provider's post becomes viewable on her profile.
  ```

- **US-25 — Provider Delete Posts**  
  _Story:_ As a provider, I want to delete a post from my profile because I made a mistake when writing it.
  _Acceptance:_ The provider can delete posts from her update feed.
  ```gherkin
  Scenario: The provider deletes a post from her profile.
    Given The provider is logged in to her account.
    When  The provider clicks a button to delete a post from her profile, and clicks another button to confirm post deletion.
    Then  The provider's post is deleted from her profile.
  ```

- **US-26 — Provider Modify Posts**  
  _Story:_ As a provider, I want to modify a post from my profile because I made a mistake when writing it.
  _Acceptance:_ The provider can modify posts from her update feed.
  ```gherkin
  Scenario: The provider modifies a post from her profile.
    Given The provider is logged in to her account.
    When  The provider clicks a button to edit a post from her profile, makes changes, and then clicks another button to confirm the changes.
    Then  The provider's post is modified.
  ```

- **US-27 — Provider Review Response**  
  _Story:_ As a provider, I want to reply to reviews so I can respond to customers.
  _Acceptance:_
  ```gherkin
  Scenario: Provider can provide a response to customer reviews.
    Given The provider is logged in to her account.
    When  The provider writes a response to a customer review and clicks a button to post the response.
    Then  The provider's response is posted to her profile under the review.

- **US-28 — Provider Profile Deletion**  
  _Story:_ As a provider, I want delete my profile so that customers stop contacting me for my services.
  _Acceptance:_ The provider can delete account from the edit profile page.
  ```gherkin
  Scenario: The provider deletes her account.
    Given The provider is logged in to her account.
    When  The provider can delete her account by clicking a button on the edit profile page and then clicking confirm.
    Then  The provider should be logged out and her account information and profile should be deleted from the database.
---

## 3. Non‑Functional Requirements (make them measurable) 
- **Performance:** Customers should expect <400ms when filtering through providers, and <300 ms when searching for providers,   <!-- Emmanuel task -->
- **Security/Privacy:** Customers can use Gmail to log in to ensure secuirty <!-- Emmanuel task -->
- **Availability/Reliability:** The website needs to be avaliable online and needs to remain online as much as possible. It should work well in browsers with different screen sizes.
- **Usability:** The site should have an understandable user interface.

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