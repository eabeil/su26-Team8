<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PetPal - Edit Profile</title>
    <!--tailwind setup-->
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-50 min-h-screen font-sans text-black">

    <!-- SITE HEADER -->
    <header>
        <div class="bg-white border-b border-gray-200">
            <div class="max-w-4xl mx-auto px-6 py-4 flex items-center">
                <h1 class="text-2xl font-extrabold tracking-tight">PetPal</h1>
                <nav class="ml-auto flex items-center gap-6">
                    <a href="/provider/${providerId}/dashboard/${providerId}"
                        class="text-sm font-semibold text-black hover:text-black transition-colors">Dashboard</a>
                    <a href="/provider/${providerId}/customer-list"
                        class="text-sm font-semibold text-black hover:text-black transition-colors">Customers</a>
                    <a href="/"
                        class="text-sm font-semibold text-black hover:text-black transition-colors">Logout</a>
                </nav>
            </div>
    </header>

    <main class="max-w-4xl mx-auto px-6 py-10 space-y-6">

        <!-- EDIT PROFILE -->
        <section class="bg-white border border-gray-200 rounded-xl shadow-sm p-8">
            <h3 class="text-xs text-center text-gray-500 uppercase tracking-widest font-semibold mb-6">
                Edit Profile
            </h3>
            <form class="space-y-6">
                <div class="flex flex-col sm:flex-row gap-6 items-start">
                    <div class="flex-1 w-full">
                        <form class="space-y-6">
                            <div class="flex flex-col sm:flex-row gap-6 items-start">
                                <div class="flex-1 w-full">
                                    <label
                                        class="block text-xs text-gray-500 uppercase tracking-widest font-semibold mb-2 mt-8">
                                        Profile Image Link
                                    </label>
                                    <input type="text" value="${provider.imageUrl}"
                                        placeholder="Paste an image URL"
                                        class="w-full px-6 py-4 text-sm border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-black focus:border-transparent transition-all">
                                </div>
                            </div>
                            <div>
                                <label
                                    class="block text-xs text-gray-500 uppercase tracking-widest font-semibold mb-2 mt-8">
                                    Name
                                </label>
                                <input type="text" placeholder="John Doe" value="${provider.name}"
                                    class="w-full px-6 py-4 text-sm border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-black focus:border-transparent transition-all">
                            </div>                           <div>
                            <label
                                    class="block text-xs text-gray-500 uppercase tracking-widest font-semibold mb-2 mt-8">
                                    Phone Number
                                </label>
                                <input type="text" placeholder="(123) 456-7890" value="${provider.phone}"
                                    class="w-full px-6 py-4 text-sm border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-black focus:border-transparent transition-all">
                            </div>
                            <div>
                                <label
                                    class="block text-xs text-gray-500 uppercase tracking-widest font-semibold mb-2 mt-8">
                                    Location
                                </label>
                                <input type="text" placeholder="City, ST" value="${provider.address}"
                                    class="w-full px-6 py-4 text-sm border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-black focus:border-transparent transition-all">
                            </div>
                            <div>
                                <label
                                    class="block text-xs text-gray-500 uppercase tracking-widest font-semibold mb-2 mt-8">
                                    Description
                                </label>
                                <textarea rows="8"
                                    class="w-full px-6 py-4 text-sm border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-black focus:border-transparent transition-all">
${provider.description}
                                </textarea>
                            </div>

                            <div class="flex gap-4">
                                <a href="/provider/${providerId}/dashboard/"
                                    class="flex justify-center flex-1 px-6 py-4 mt-8 text-white bg-black rounded-lg hover:bg-gray-500 transition-colors font-medium shadow-sm">
                                    Cancel
                                </a> 
                                <a href="/provider/${providerId}/dashboard/"
                                    class="flex justify-center flex-1 px-6 py-4 mt-8 text-white bg-black rounded-lg hover:bg-gray-500 transition-colors font-medium shadow-sm">
                                    Save
                                </a>
                            </div>
                        </form>
                    </div>
                </div>
            </form>
        </section>

        <!-- DELETE ACCOUNT BUTTON  -->
        <div class="flex justify-center">
            <a href="/provider/${providerId}/dashboard/"
                class="flex justify-center px-6 py-4 text-red bg-white border-2 border-red rounded-lg hover:bg-red-500 transition-colors font-medium">
                Delete Account
            </a>
        </div>

    </main>
</body>

</html>