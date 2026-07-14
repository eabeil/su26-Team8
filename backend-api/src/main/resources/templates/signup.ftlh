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
            <p class="text-gray-500">Create your customer account</p>
        </div>

        <!--form-->
        <form onsubmit="event.preventDefault(); window.location.href='customer/dashboard.html';" class="space-y-5">
            
            <!--name-->
            <div>
                <label class="block text-sm font-semibold mb-2">Full Name</label>
                <input type="text" placeholder="e.g. Jane Doe" required class="w-full px-4 py-2.5 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-black">
            </div>

            <!--email-->
            <div>
                <label class="block text-sm font-semibold mb-2">Email Address</label>
                <input type="email" placeholder="you@example.com" required class="w-full px-4 py-2.5 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-black">
            </div>

            <!--password-->
            <div>
                <label class="block text-sm font-semibold mb-2">Password</label>
                <input type="password" placeholder="••••••••" required class="w-full px-4 py-2.5 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-black">
                <p class="text-xs text-gray-400 mt-2">Must be at least 8 characters.</p>
            </div>

            <div class="pt-2">
                <div class="grid grid-cols-2 gap-4">
                    <label class="cursor-pointer">
                        <input type="radio" name="account_type" value="owner" class="peer sr-only" checked>
                        <div class="text-center px-4 py-3 border border-gray-300 rounded-lg peer-checked:border-black peer-checked:bg-black peer-checked:text-white hover:bg-gray-50 peer-checked:hover:bg-black transition-colors text-sm font-semibold">
                            Find Pet Care
                        </div>
                    </label>
                    <label class="cursor-pointer">
                        <input type="radio" name="account_type" value="professional" class="peer sr-only">
                        <div class="text-center px-4 py-3 border border-gray-300 rounded-lg peer-checked:border-black peer-checked:bg-black peer-checked:text-white hover:bg-gray-50 peer-checked:hover:bg-black transition-colors text-sm font-semibold">
                            Offer Pet Care
                        </div>
                    </label>
                </div>
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
                <a href="index.html" class="font-bold text-black hover:underline">Log in here</a>
            </p>
        </div>

    </div>

</body>
</html>