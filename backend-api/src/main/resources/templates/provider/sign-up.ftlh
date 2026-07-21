<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up - PetPal</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50 font-sans text-black min-h-screen flex items-center justify-center p-6">

    <!--card-->
    <div class="bg-white border border-gray-200 rounded-2xl p-8 shadow-sm max-w-md w-full">
        
        <!--header-->
        <div class="text-center mb-8">
            <h1 class="text-2xl font-extrabold tracking-tight mb-2">PetPal</h1>
            <p class="text-gray-500">Create your account</p>
        </div>

        <!--form-->
        <form method="post" action="/provider/create/" class="space-y-5">
            
            <!--name-->
            <div>
                <label class="block text-sm font-semibold mb-2">Full Name</label>
                <input type="text" name="name" placeholder="e.g. Jane Doe" required class="w-full px-4 py-2.5 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-black">
            </div>

            <!--name-->
            <div>
                <label class="block text-sm font-semibold mb-2">Profile Image Url</label>
                <input type="text" name="imageUrl" placeholder="e.g. Jane Doe" required class="w-full px-4 py-2.5 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-black">
            </div>

            <!--email-->
            <div>
                <label class="block text-sm font-semibold mb-2">Email Address</label>
                <input type="text" name="email" placeholder="you@example.com" required class="w-full px-4 py-2.5 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-black">
                <#if emailError??>
                    <div id="badEmail" class="mb-4 px-4 py-3 rounded-lg bg-red-50 border border-red-200 text-red-700 text-sm font-medium">
                        ${emailError}
                    </div>
                </#if>
            </div>

            <!--phone-->
            <div>
                <label
                    class="block text-xs text-gray-500 uppercase tracking-widest font-semibold mb-2 mt-8">
                    Phone Number
                </label>
                <input type="text" name="phone" placeholder="(123) 456-7890"
                    required
                    class="w-full px-6 py-4 text-sm border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-black focus:border-transparent transition-all">
            </div>

            <!--location-->
            <div>
                <label class="block text-sm font-semibold mb-2">Location</label>
                <input type="text" name="address" placeholder="City, ST" required class="w-full px-4 py-2.5 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-black">
            </div>

            <div>
                <label
                    class="block text-xs text-gray-500 uppercase tracking-widest font-semibold mb-2 mt-8">
                    Description
                </label>
                <textarea rows="8" placeholder="Write about yourself..." required name="description"
                    class="w-full px-6 py-4 text-sm border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-black focus:border-transparent transition-all"></textarea>
            </div>

            <!--password-->
            <div>
                <label class="block text-sm font-semibold mb-2">Password</label>
                <input type="password" name="password" placeholder="••••••••" required class="w-full px-4 py-2.5 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-black">
                <p class="text-xs text-gray-400 mt-2">Must be at least 8 characters.</p>
            </div>

            <!--submit-->
            <button type="submit" class="w-full bg-black text-white py-3 rounded-lg font-semibold hover:bg-gray-800 transition-colors mt-2">
                Create Account
            </button>

        </form>

        <!--divider-->
        <div class="mt-8 pt-6 border-t border-gray-100 text-center">
            <p class="text-sm text-gray-500">
                Already have an account? 
                <!--link back to log in-->
                <a href="/" class="font-bold text-black hover:underline">Log in here</a>
            </p>
        </div>

    </div>

</body>
</html>