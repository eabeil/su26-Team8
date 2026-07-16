<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PetPal - Dashboard</title>
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
                    <a href="/provider/dashboard"
                        class="text-sm font-semibold text-black hover:text-black underline transition-colors">Dashboard</a>
                    <a href="/provider/customer-list"
                        class="text-sm font-semibold text-black hover:text-black transition-colors">Customers</a>
                    <a href="/"
                        class="text-sm font-semibold text-black hover:text-black transition-colors">Logout</a>
                </nav>
            </div>
    </header>

    <main class="max-w-4xl mx-auto px-6 py-10 space-y-6">

        <!-- PROFILE HEADER -->
        <section class="bg-white border border-gray-200 rounded-xl shadow-sm p-8">
            <div class="flex flex-col sm:flex-row gap-6">
                <img src="/content/cat.jpg" alt="Grumpy"
                    class="w-24 h-24 rounded-full object-cover border border-gray-200 flex-shrink-0">
                <div class="flex-1">
                    <h2 class="text-xl font-extrabold tracking-tight mb-2">Grumpy</h2>
                    <p class="text-sm text-gray-600 mb-6">Cat • Greensboro, NC</p>
                    <p class="text-sm text-gray-500 leading-relaxed">Hi! I am cat. I am the owner of Mike Torres.
                    </p>
                </div>
            </div>
        </section>

        <!-- TAGS -->
        <section class="bg-white border border-gray-200 rounded-xl shadow-sm p-8">
            <h3 class="text-xs text-center text-gray-500 uppercase tracking-widest font-semibold mb-2">
                Tags
            </h3>

            <div class="flex flex-wrap justify-center gap-6">
                <span class="border border-gray-200 px-4 py-2 rounded-full text-sm font-semibold">
                    High Energy
                </span>
                <span class="border border-gray-200 px-4 py-2 rounded-full text-sm font-semibold">
                    Anxious
                </span>
                <span class="border border-gray-200 px-4 py-2 rounded-full text-sm font-semibold">
                    Requires Medication
                </span>
            </div>
        </section>

        <!-- INFO -->
        <section class="bg-white border border-gray-200 rounded-xl shadow-sm p-8">
            <h3 class="text-xs text-center text-gray-500 uppercase tracking-widest font-semibold mb-2">
                About Me
            </h3>
            <div class="overflow-x-auto">
                <table class="w-full text-sm text-center border-collapse">
                    <tbody>
                        <tr class="border-b border-gray-200">
                            <td class="px-6 py-4 text-gray-500">Breed:</td>
                            <td class="px-6 py-4 text-gray-500">Tabby</td>
                        </tr>
                    </tbody>
                    <tbody>
                        <tr class="border-b border-gray-200">
                            <td class="px-6 py-4 text-gray-500">Age:</td>
                            <td class="px-6 py-4 text-gray-500">4 Years</td>
                        </tr>
                    </tbody>
                    <tbody>
                        <tr class="border-b border-gray-200">
                            <td class="px-6 py-4 text-gray-500">Weight:</td>
                            <td class="px-6 py-4 text-gray-500">16 lbs</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </section>

        <!-- SPECIAL CARE INSTRUCTIONS -->
        <section class="bg-white border border-gray-200 rounded-xl shadow-sm p-8">
            <div class="flex flex-col sm:flex-row gap-6">
                <div class="flex-1">
                    <h2 class="text-xl font-extrabold tracking-tight mb-2">Special Care Instruction</h2>
                    <p class="text-sm text-gray-500 leading-relaxed">I do not like water.
                    </p>
                </div>
            </div>
        </section>

        <!-- OWNER -->
        <section class="bg-white border border-gray-200 rounded-xl shadow-sm p-8">
            <h3 class="text-xs text-center text-gray-500 uppercase tracking-widest font-semibold mb-2">
                Owner
            </h3>
            <div class="space-y-6">

                    <!-- Update 1 -->
                    <div
                        class="bg-white border border-gray-200 rounded-2xl p-6 shadow-sm flex flex-col sm:flex-row gap-6 hover:shadow-md transition-shadow">
                        <!-- Photo -->
                        <img src="/content/male.jpg" alt="Mike Torres"
                            class="w-24 h-24 sm:w-32 sm:h-32 rounded-xl object-cover border border-gray-200 flex-shrink-0">

                        <!-- Content -->
                        <div class="flex-grow flex flex-col">
                            <div class="flex justify-between items-start">
                                <div>
                                    <h3 class="text-xl font-bold">Mike Torres</h3>
                                    <p class="text-sm text-gray-600 mb-6">Greensboro, NC</p>
                                </div>
                            </div>

                            <!--bio-->
                            <p class="text-sm text-gray-600 line-clamp-2 mb-4">
                               Hi! I am a greensboro native who loves cats! My favorite cat is my own. 
                            </p>
                        </div>
                    <!--info-->
                    <div class="mt-auto flex justify-end">
                        <a href="/provider/${providerId}/customer-profile"
                            class="text-sm font-semibold border-2 border-black px-6 py-2 rounded-lg hover:bg-black hover:text-white transition-colors">Profile</a>
                    </div>
                    </div>
                </div>

            </div>
        </section>
    </main>
</body>

</html>