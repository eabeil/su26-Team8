<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${pageTitle} - PetPal</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50 font-sans text-black min-h-screen flex flex-col">
    <nav class="bg-white border-b border-gray-200 px-8 md:px-12 py-4 flex justify-between items-center sticky top-0 z-10">
        <a href="/customer/${customerId?c}/dashboard" class="text-xl font-extrabold tracking-tight">PetPal</a>
        <div class="space-x-6 text-sm font-medium">
            <a href="/customer/${customerId?c}/dashboard" class="text-gray-500 hover:text-black transition-colors">Dashboard</a>
            <a href="/customer/${customerId?c}/providers" class="text-gray-500 hover:text-black transition-colors">Directory</a>
            <a href="/customer/${customerId?c}/profile" class="text-black border-b-2 border-black pb-1">My Profile</a>
            <form action="/customer/logout" method="post" class="inline ml-4">
                <button
                    type="submit"
                    class="text-red-600 hover:text-red-800 transition-colors">
                    Log Out
                </button>
            </form>
        </div>   
    </nav>


    <main class="max-w-4xl mx-auto px-8 md:px-12 py-12 flex-grow w-full">
        <header class="mb-8">
            <a href="/customer/${customerId?c}/dashboard" class="text-sm text-gray-500 hover:text-black font-semibold mb-4 inline-block">
                &larr; Back to Dashboard
            </a>
            <h1 class="text-3xl font-extrabold mb-2">${pageTitle}</h1>
            <p class="text-gray-500">Keep your PetPal contact information up to date.</p>
        </header>
        <#if successMessage??>
            <p class="bg-green-50 text-green-800 border border-green-200 rounded-xl px-5 py-3 mb-6 text-sm font-semibold" role="status">
                ${successMessage}
            </p>
        </#if>
        <#if errorMessage??>
            <p class="bg-red-50 text-red-800 border border-red-200 rounded-xl px-5 py-3 mb-6 text-sm font-semibold" role="alert">
                ${errorMessage}
            </p>
        </#if>

        <form method="post" action="${formAction}" class="space-y-8">
            <section class="bg-white border border-gray-200 rounded-2xl p-8 shadow-sm">
                <div class="flex flex-col md:flex-row md:items-center gap-6 pb-8 mb-8 border-b border-gray-100">
                    <#if profile.imageUrl?? && profile.imageUrl?has_content>
                        <img
                            src="${profile.imageUrl}"
                            alt="${profile.name!'Customer'}"
                            class="w-24 h-24 rounded-full object-cover border-2 border-gray-200 flex-shrink-0">
                    <#else>
                        <div class="w-24 h-24 rounded-full border-2 border-dashed border-gray-300 bg-gray-50 flex items-center justify-center text-3xl flex-shrink-0" aria-label="No profile photo">
                            👤
                        </div>
                    </#if>
                    <div class="flex-grow w-full">
                        <label for="imageUrl" class="block text-sm font-bold mb-1">Profile Image URL</label>
                        <p class="text-xs text-gray-500 mb-3">Enter a direct image URL for your profile photo.</p>
                        <input
                            id="imageUrl"
                            name="imageUrl"
                            type="text"
                            value="${profile.imageUrl!}"
                            placeholder="https://example.com/profile.jpg"
                            class="w-full px-4 py-2.5 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-black">
                    </div>
                </div>
                <h2 class="text-xl font-bold mb-6">Personal Details</h2>
                <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
                    <div>
                        <label for="name" class="block text-sm font-bold mb-2">
                            Full Name <span class="text-red-500">*</span>
                        </label>
                        <input
                            id="name"
                            name="name"
                            type="text"
                            value="${profile.name!}"
                            maxlength="100"
                            autocomplete="name"
                            required
                            class="w-full px-4 py-2.5 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-black">
                    </div>
                    <div>
                        <label for="phone" class="block text-sm font-bold mb-2">
                            Phone Number <span class="text-red-500">*</span>
                        </label>
                        <input
                            id="phone"
                            name="phone"
                            type="tel"
                            value="${profile.phone!}"
                            maxlength="30"
                            autocomplete="tel"
                            required
                            class="w-full px-4 py-2.5 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-black">
                    </div>
                    <div class="md:col-span-2">
                        <label for="location" class="block text-sm font-bold mb-2">
                            City and State <span class="text-red-500">*</span>
                        </label>
                        <input
                            id="location"
                            name="location"
                            type="text"
                            value="${profile.location!}"
                            maxlength="120"
                            placeholder="Greensboro, NC"
                            required
                            class="w-full px-4 py-2.5 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-black">
                    </div>
                    <div class="md:col-span-2">
                        <label for="email" class="block text-sm font-bold mb-2">
                            Email Address <span class="text-red-500">*</span>
                        </label>
                        <input
                            id="email"
                            name="email"
                            type="email"
                            value="${profile.email!}"
                            maxlength="150"
                            autocomplete="email"
                            required
                            class="w-full px-4 py-2.5 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-black">
                        <p class="text-xs text-gray-500 mt-2">This address is used as your customer contact email.</p>
                    </div>
                </div>
            </section>
            <div class="flex justify-end items-center gap-4">
                <a href="/customer/${customerId?c}/dashboard" class="px-6 py-2.5 rounded-lg font-semibold text-gray-600 hover:bg-gray-100 transition-colors">
                    Cancel
                </a>
                <button type="submit" class="bg-black text-white px-8 py-2.5 rounded-lg font-semibold hover:bg-gray-800 transition-colors shadow-sm">
                    Save Changes
                </button>
            </div>
        </form>


        <section class="mt-12 bg-white border border-red-200 rounded-2xl p-8 shadow-sm">
            <h2 class="text-xl font-bold text-red-700 mb-2">Delete Customer Account</h2>
            <p class="text-sm text-gray-600 mb-5">
                This permanently removes your profile, pets, and reviews. Provider accounts are not affected.
            </p>
            <form method="post" action="/customer/${customerId?c}/delete">
                <label class="flex items-start gap-3 text-sm mb-5">
                    <input type="checkbox" required class="mt-1 accent-red-700">
                    <span>I understand that this customer account cannot be restored.</span>
                </label>
                <button type="submit" class="bg-red-700 text-white px-6 py-2.5 rounded-lg font-semibold hover:bg-red-800 transition-colors">
                    Delete My Account
                </button>
            </form>
        </section>
    </main>
</body>
</html>