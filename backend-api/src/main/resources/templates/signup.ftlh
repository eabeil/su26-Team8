<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up - PetPal</title>
    <link rel="stylesheet" href="/css/app.css">
</head>
<body class="auth-page">
    <main class="auth-page">
        <header class="auth-heading">
            <h1 class="brand">PetPal</h1>
            <p>Create your customer account.</p>
        </header>
        
        <form action="/signup" method="post" class="stack">
            <div class="field">
                <label for="name">Full name</label>
                <input id="name" name="name" type="text" value="${(form.name)!}"
                       autocomplete="name" required>
            </div>

            <div class="field">
                <label for="email">Email address</label>
                <input id="email" name="email" type="email" value="${(form.email)!}"
                       autocomplete="email" required>
            </div>

            <div class="field">
                <label for="phone">Phone number <span class="supporting-text">(optional)</span></label>
                <input id="phone" name="phone" type="tel" value="${(form.phone)!}"
                       autocomplete="tel">
            </div>

            <div class="field">
                <label for="password">Password</label>
                <input id="password" name="password" type="password"
                       autocomplete="new-password" minlength="8" required>
                <p class="field-hint">Use at least 8 characters.</p>
            </div>

            <#if error??>
                <p class="form-message visible error" role="alert">${error}</p>
            </#if>

            <button class="button" type="submit">Create account</button>
        </form>

        <footer class="auth-footer">
            Already have an account? <a href="/">Log in</a>
        </footer>
    </main>
</body>
</html>
