<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Sign Up - PetPal</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50 font-sans text-black min-h-screen flex items-center justify-center p-6">
    <main class="bg-white border border-gray-200 rounded-2xl p-8 shadow-sm max-w-lg w-full">
        <div class="text-center mb-8">
            <h1 class="text-2xl font-extrabold tracking-tight mb-2">PetPal</h1>
            <p class="text-gray-500">Create your pet owner account</p>
        </div>

        <#if signupError??>
            <p class="mb-5 px-4 py-3 rounded-lg bg-red-50 border border-red-200 text-red-700 text-sm font-medium" role="alert">
                ${signupError}
            </p>
        </#if>

        <form method="post" action="/customer/sign-up" class="space-y-5">
            <div>
                <label for="name" class="block text-sm font-semibold mb-2">Full Name</label>
                <input id="name" name="name" type="text" value="${customerSignup.name!}" autocomplete="name" maxlength="100" required
                    class="w-full px-4 py-2.5 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-black">
            </div>

            
            <div>
                <label for="email" class="block text-sm font-semibold mb-2">Email Address</label>
                <input id="email" name="email" type="email" value="${customerSignup.email!}" autocomplete="email" maxlength="150" required
                    class="w-full px-4 py-2.5 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-black">
            </div>

            <div>
                <label for="phone" class="block text-sm font-semibold mb-2">Phone Number</label>
                <input id="phone" name="phone" type="tel" value="${customerSignup.phone!}" autocomplete="tel" maxlength="30" required
                    placeholder="(336) 555-0123"
                    pattern="\([0-9]{3}\) [0-9]{3}-[0-9]{4}"
                    class="w-full px-4 py-2.5 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-black">
            </div>

            <div>
                <label for="imageUrl" class="block text-sm font-semibold mb-2">Profile Image URL <span class="text-gray-400 font-normal">(optional)</span></label>
                <input id="imageUrl" name="imageUrl" type="text" value="${customerSignup.imageUrl!}"
                    placeholder="/content/female.jpg"
                    class="w-full px-4 py-2.5 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-black">
            </div>

            <div class="grid grid-cols-1 sm:grid-cols-2 gap-5">
                <div>
                    <label for="password" class="block text-sm font-semibold mb-2">Password</label>
                    <input id="password" name="password" type="password" autocomplete="new-password" minlength="9" required
                        class="w-full px-4 py-2.5 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-black">
                </div>
                <div>
                    <label for="confirmPassword" class="block text-sm font-semibold mb-2">Confirm Password</label>
                    <input id="confirmPassword" name="confirmPassword" type="password" autocomplete="new-password" minlength="9" required
                        class="w-full px-4 py-2.5 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-black">
                </div>
            </div>
            <p class="text-xs text-gray-500">Use more than 8 characters.</p>

            <button type="submit" class="w-full bg-black text-white py-3 rounded-lg font-semibold hover:bg-gray-800 transition-colors">
                Create Customer Account
            </button>
        </form>

        <div class="mt-8 pt-6 border-t border-gray-100 text-center">
            <p class="text-sm text-gray-500">
                Already have an account?
                <a href="/" class="font-bold text-black hover:underline">Log in here</a>
            </p>
        </div>
    </main>
</body>
</html>
