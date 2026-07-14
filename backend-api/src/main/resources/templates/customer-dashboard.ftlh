<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Dashboard | PetPal</title>
    <link rel="stylesheet" href="/css/app.css">
</head>
<body>    
    <nav class="site-nav">
        <div class="brand">PetPal</div>
        <div class="nav-actions">
            <span class="supporting-text">${customer.name}</span>
            <form action="/logout" method="post">
                <button class="link-button" type="submit">Log out</button>
            </form>
        </div>
    </nav>

    <main class="dashboard">

        <p class="eyebrow">Customer dashboard</p>
        <h1>Welcome, ${customer.name}.</h1>
        <p class="dashboard-intro">
            Your account is saved and your login session is active.
        </p>

        <div class="dashboard-grid">
            <section class="panel">
                <h2>Your account</h2>
                <p>This information was rendered from the backend model rather than hardcoded into the page.</p>
                <dl class="account-details">
                    <div><dt>Name</dt><dd>${customer.name}</dd></div>
                    <div><dt>Email</dt><dd>${customer.email}</dd></div>
                    <div>
                        <dt>Phone</dt>
                        <dd><#if customer.phone?has_content>${customer.phone}<#else>Not provided</#if></dd>
                    </div>
                </dl>
            </section>
            <section class="panel">
                <h2>Next: your pets</h2>
                <p>The next customer slice will render persisted pets here and connect the existing Add Pet prototype.</p>
            </section>
        </div>    
    </main>
</body>
</html>