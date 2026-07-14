<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PetPal | Login</title>
    <link rel="stylesheet" href="/css/app.css">
</head>
<body class="auth-page">

    <!--login container-->
    <main class="auth-card">
        <header class="auth-heading">
            <h1 class="brand">PetPal</h1>
            <p>Find trusted pet care in your neighborhood.</p>
        </header>

        <form action="/login" method="post" class="stack">
                <!--email input-->
            <div class="field">
                <label for="email">Email</label>
                <input id="email" name="email" type="email" value="${(form.email)!}"
                       autocomplete="email" required>
            </div>

                <!--password input-->
            <div class="field">
                <label for="password">Password</label>
                <input id="password" name="password" type="password" 
                       autocomplete="current-password" required>
            </div>

             <#if error??>
                <p class="form-message visible error" role="alert">${error}</p>
            </#if>

            <button class="button" type="submit">Log in as a pet owner</button>
            <button class="button" type="submit">Log in as a pet provider</button>
        </form>

        <!--sign up card-->
        <footer class="auth-footer">
            Don't have an account? <a href="/signup">Create one</a>
        </footer>
    </main>    
</body>
</html>    
